/**
 * 
 */
package com.haarmk.service;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haarmk.dto.EmailDetails;
import com.haarmk.dto.RazorpaySuccessReq;
import com.haarmk.dto.domain.ContactInfo;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.dto.domain.DomainRegisterContactsReq;
import com.haarmk.dto.domain.DomainRegisterReq;
import com.haarmk.dto.domain.DomainRegisterRes;
import com.haarmk.dto.domain.DomainReq;
import com.haarmk.exception.AuthenticationException;
import com.haarmk.exception.HaarmkException;
import com.haarmk.model.Address;
import com.haarmk.model.OrderItem;
import com.haarmk.model.Orders;
import com.haarmk.model.Product;
import com.haarmk.model.Services;
import com.haarmk.model.Status;
import com.haarmk.model.User;
import com.haarmk.repository.ServiceRepo;
import com.haarmk.service.interfaces.EmailService;
import com.haarmk.service.interfaces.NameService;
import com.haarmk.service.interfaces.OrderService;
import com.haarmk.service.interfaces.PaymentService;
import com.haarmk.service.interfaces.ServiceService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.RazorpaySignature;
import com.haarmk.util.Utils;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

/**
 * @author indicate0
 *
 */

@Service
public class ServiceServiceImpl implements ServiceService{
	
	@Autowired NameService nameService; 
	@Autowired OrderService orderService;
	@Autowired UserService userService;
	@Autowired ServiceRepo serviceRepo;
	@Autowired ObjectMapper objectMapper;
	@Autowired PaymentService paymentService;
	@Autowired Environment environment;
	@Autowired EmailService emailService;
	@Autowired WhmServiceImpl whmService;
	
	
	
	@Override
 	public List<Services> addService(RazorpaySuccessReq razorpaySuccessReq) {
		
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		Payment payment = paymentService.getRazorpayPaymentById(razorpaySuccessReq.getRazorpay_payment_id());
		
		String paymentStatus = payment.get("staus");
//		if(! paymentStatus.equals("paid")) {
//			throw new HaarmkException("You have not paid yet!");
//		}
		
		String razorpayOrderId = paymentService.varifyRazorpayPayment(razorpaySuccessReq);
		
		Orders orders = orderService.getOrderByRazorpayOrderId(razorpayOrderId);
		User currentUser = orders.getUser();
		
		if(!currentUser.getUsername().equals(currentUsername)) {
			throw new AuthenticationException("You didn't place this order.");
		}
		
		orders.setStatus(Status.PAID);
		orders.setRazorpayPaymentId(razorpaySuccessReq.getRazorpay_payment_id());
		orders.setRazorpaySignature(razorpaySuccessReq.getRazorpay_signature());
		userService.updateUser(orders.getUser());
		
		
//		sending mail
		sendInvoceMail(orders, currentUser);
		
		
		
		List<Services> services = new ArrayList<>();
		
		String refundStatus = payment.get("refund_status").toString();
		
		
		for(OrderItem oi : orders.getItems()) {
			
			if(! oi.getIsActivated() && refundStatus.equals("null")) {
				
				Product product = oi.getProduct();
				
				Services service = new Services();
				
				service.setOrders(orders);
				service.setProduct(product);
				service.setUser(currentUser);
				currentUser.getServises().add(service);
				currentUser.getServises().add(service);
				if(product.getCategory().getName().equals("domain")){
					
					DomainRegisterRes domainRegisterRes = registerDomainHelper(oi, orders.getBillingAddress());
					service.setDetails(domainRegisterRes);
					
					
				} else if(product.getCategory().getName().equals("hosting")){
					
					JsonNode productDetails = objectMapper.valueToTree(oi.getProduct().getDetails());
					
					JsonNode oiDetails = objectMapper.valueToTree(oi.getDetails());
					
					String plan = productDetails.get("plan").asText();
					String domainName = oiDetails.get("domainName").asText().toString();
					
					JsonNode res = whmService.createCpanelAccount(getUniqueCpanelUsername(), domainName,plan);
					
					if(res.get("metadata").get("result").asInt() == 1) {
						
						service.setDetails(res);
						
						String rawMsg = res.get("metadata").get("output").get("raw").asText();
						
						sendHostingCreationMail(currentUser, rawMsg, plan);
						
					}else {
						throw new HaarmkException(res.get("metadata").get("reason").asText());
					}
				}
				
				oi.setIsActivated(true);
				service.setStatus(Status.ACTIVE);
//				userService.updateUser(currentUser);
				Services savedservice = serviceRepo.save(service);
				services.add(savedservice);
			}
			else {
				
			}
		}
		return services;
		
	}
	
	
	String getUniqueCpanelUsername() {
		
		String newUsername = Utils.generateRandonString(8);
		
		while(true) {
			if(!whmService.checkCpanelUsernameIfExists(newUsername)) {
				return newUsername;
			}
			
			newUsername= Utils.generateRandonString(8);
			
			
		}
	}
//	
	
	void sendHostingCreationMail(User user, String msg, String plan) {
		String newMsg = "<h3>Dear "+user.getFirstName()+", Thank you for choosing HAARMK INFOTCH</h3>"
	+"<p>Bellow is your hosting plan details.</p><h3>Plan code: "+plan+"</h3>"
				+msg;
		System.out.println("newMsg"+newMsg);
		String[] recipients = {user.getEmail()};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Cpanel Account Details")
				.isHtml(Boolean.TRUE)
				.msgBody(newMsg)
				.build();
				
		emailService.sendMail(emailDetails);
	}
	
	
	void sendInvoceMail(Orders orders, User user) {
		
		String msgTop= "<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n"
				+ "    <style>\n"
				+ "        body{\n"
				+ "            display: grid;\n"
				+ "            justify-content: center;\n"
				+ "            border: 1px solid gray;\n"
				+ "        }\n"
				+ "        h1{\n"
				+ "            text-align: center;\n"
				+ "        }\n"
				+ "        table,\n"
				+ "        td, th {\n"
				+ "            text-align: center;\n"
				+ "            border: 1px solid #333;\n"
				+ "            padding: 5px;\n"
				+ "        }\n"
				+ "\n"
				+ "        thead,\n"
				+ "        tfoot {\n"
				+ "            background-color: #333;\n"
				+ "            color: #fff;\n"
				+ "        }\n"
				+ "        #signatory{\n"
				+ "            font-size: calc(.5vw + 1.5vh);\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "   <!-- <h1></h1> -->\n"
				+ "   <h3>Dear "+user.getFirstName()+", Thank you for choosing HAARMK INFOTCH</h3>\n"
				+ "   <p>Bellow is your billing details.</p>\n"
				+ "   <p>If you need help, please, contact us.</p> \n"
				+ "   <br>\n"
				+ "    <h3>Order id: "+orders.getRazorpayOrderId()+"</h3>\n"
				+ "    <h3>Date: "+orders.getCreatedAt()+"</h3>\n"
				+ "    <table>\n"
				+ "        <thead>\n"
				+ "            <tr>\n"
				+ "                <th>Product Code</th>\n"
				+ "                <th>Details</th>\n"
				+ "                <th>Qty (Months)</th>\n"
				+ "                <th>Price <span>&#8377;</span></th>\n"
				+ "            </tr>\n"
				+ "\n"
				+ "        </thead>\n"
				+ "        \n"
				+ "        <tbody>\n";
				
		
				String msgFooter =	 "            <tr>\n"
				+ "                <td></td>\n"
				+ "                <td>Total</td>\n"
				+ "                <td>_</td>\n"
				+ "                <td>"+orders.getSubTotal()+"</td>\n"
				+ "            </tr>\n"
				+ "        </tbody>\n"
				+ "    </table>\n"
				+ "    <hr>\n"
				+ "    <H3>SubTotal: <span>&#8377;</span>"+orders.getSubTotal()+" </H3>\n"
				+ "    <H3>Tax: <span>&#8377;</span> "+orders.getTax()+"</H3>\n"
				+ "    <H3>Total: <span>&#8377;</span> "+orders.getTotal()+"</H3>\n"
				+ "    <p id=\"signatory\">Haarmk Infotch Private Limited</p>\n"
				+ "</body>\n"
				+ "</html>";

				
			
		
		String msgBody = "";
		for(OrderItem oi : orders.getItems()) {
			msgBody += "<tr>"
						+ "<td>"+oi.getProduct().getUniqueName()+"</td>"
						+ "<td>"+oi.getProduct().getDisplayName()+"</td>";
						
			if(oi.getProduct().getCategory().getName().equals("domain")) {
				msgBody += "<td>"+oi.getQty()*12+"</td>";
			}else{
				msgBody += "<td>"+oi.getQty()+"</td>";
			}
						
			msgBody +=   "<td>"+oi.getPrice()+"</td>"
					+ "</tr>";
		}
		
		
		String msg = msgTop + msgBody + msgFooter;
		String[] recipients = {user.getEmail()};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Order Summary")
				.isHtml(Boolean.TRUE)
				.msgBody(msg)
				.build();
				
		emailService.sendMail(emailDetails);
	}
	
	public List<Services> getAllServices() {
		return serviceRepo.findAll();
	}
	
	
	public DomainRegisterRes registerDomainHelper(OrderItem orderItem, Address billingAddress) {
		DomainRegisterReq domainRegisterReq = new DomainRegisterReq();
		DomainReq domainReq = new DomainReq();
		domainRegisterReq.setDomain(domainReq);
		DomainRegisterContactsReq domainRegisterContactsReq = new DomainRegisterContactsReq();
		domainReq.setDomainRegisterContactsReq(domainRegisterContactsReq);
		
//		setting domain name and years
		domainRegisterReq.setYears(orderItem.getQty());
		domainRegisterReq.getDomain().setDomainName(orderItem.getProduct().getUniqueName());
		
//		setting addresses
		ContactInfo newBillingAddress = new  ContactInfo();
		
		newBillingAddress.setAddress1(billingAddress.getAddressLine1());
		newBillingAddress.setCity(billingAddress.getCity());
		newBillingAddress.setState(billingAddress.getState());
		newBillingAddress.setCountry(billingAddress.getCountry());
		newBillingAddress.setFirstName(billingAddress.getFirstName());
		newBillingAddress.setLastName(billingAddress.getLastName());
		newBillingAddress.setPhone(billingAddress.getPhone());
		newBillingAddress.setEmail(billingAddress.getEmail());
		
		domainRegisterContactsReq.setBilling(newBillingAddress);
		domainRegisterContactsReq.setRegistrant(newBillingAddress);
		
		return nameService.registerDomain(domainRegisterReq);
		 
	}


	@Override
	public Services getServiceById(Long id) {
		System.out.println("I am in get service by id: "+id);
//		return serviceRepo.findById(id).orElseThrow(()-> new HaarmkException("Service not found for id: "+id));
		return serviceRepo.getServiceById(id).orElseThrow(()-> new HaarmkException("Service not found for id: "+id));
		
	}


	@Override
	public Services updateService(Services service) {
		Services foundServices =  getServiceById(service.getId());
		return serviceRepo.save(service);
		
	}
	
	
	@Override
	public Set<Services> getServiceByCategoryName(String categoryName) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Set<Services> services = serviceRepo.getServiceByCategoryName(categoryName, username);
		if(services.isEmpty()) throw new HaarmkException("No record found for category name: "+categoryName);
		return services;
	}
	
// ============================================================= DNS ===================================================================================
	
	@Override
	public DnsCreateRecordResDto addDnsRecord(Long serviceId, DnsCreateRecordReqDto dnsCreateRecordReqDto) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User currentUser = userService.getUserByUsername(username);
		
		Services foundServices =  getServiceById(serviceId);
		System.out.println(username+foundServices.getUser().getUsername());
		if(! foundServices.getUser().getUsername().equals(username)) {
			throw new AuthenticationException("unauthorized");
		}
	
		Product product = foundServices.getProduct();
		
		if(product.getCategory().getName().equals("domain")) {
//			DnsCreateRecordResDto dnsCreateRecordResDto =  nameService.createDnsRecord(dnsCreateRecordReqDto,"strindsfdsg.com");
			
			DnsCreateRecordResDto dnsCreateRecordResDto =  nameService.createDnsRecord(dnsCreateRecordReqDto,product.getUniqueName());
			
			return dnsCreateRecordResDto;
			
		} else {
			
			throw new HaarmkException("This service is not a domain. Service id: "+ serviceId);
			
		}
		
		
	}




	@Override
	public JsonNode getDnsRecord(Long serviceId) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User currentUser = userService.getUserByUsername(username);
		
		Services foundServices =  getServiceById(serviceId);
		
		if(! foundServices.getUser().getUsername().equals(username)) {
			throw new AuthenticationException("unauthorized");
		}
	
		Product product = foundServices.getProduct();
		
		if(product.getCategory().getName().equals("domain")) {

			JsonNode res =  nameService.getDnsRecord(product.getUniqueName());
			
			return res;
			
		} else {
			
			throw new HaarmkException("This service is not a domain. Service id: "+ serviceId);
			
		}
	}


	@Override
	public DnsCreateRecordResDto updateDnsRecord(DnsCreateRecordReqDto dnsCreateRecordReqDto, Long serviceId,
			Integer recordId) {
		System.out.println(dnsCreateRecordReqDto);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User currentUser = userService.getUserByUsername(username);
		
		Services foundServices =  getServiceById(serviceId);
		
		if(! foundServices.getUser().getUsername().equals(username)) {
			throw new AuthenticationException("unauthorized");
		}
	
		Product product = foundServices.getProduct();
		
		if(product.getCategory().getName().equals("domain")) {
//			DnsCreateRecordResDto dnsCreateRecordResDto =  nameService.createDnsRecord(dnsCreateRecordReqDto,"strindsfdsg.com");
			
			DnsCreateRecordResDto dnsCreateRecordResDto =  nameService.updateDnsRecord(dnsCreateRecordReqDto,product.getUniqueName(), recordId);
			
			return dnsCreateRecordResDto;
			
		} else {
			
			throw new HaarmkException("This service is not a domain. Service id: "+ serviceId);
		}

	}
	
	@Override
	public void deleteDnsRecord(Long serviceId, Integer recordId) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User currentUser = userService.getUserByUsername(username);
		
		Services foundServices =  getServiceById(serviceId);
		
		if(! foundServices.getUser().getUsername().equals(username)) {
			throw new AuthenticationException("unauthorized");
		}
	
		Product product = foundServices.getProduct();
		
		if(product.getCategory().getName().equals("domain")) {
//			DnsCreateRecordResDto dnsCreateRecordResDto =  nameService.createDnsRecord(dnsCreateRecordReqDto,"strindsfdsg.com");
			
			nameService.deleteDnsRecord(product.getUniqueName(), recordId);
			
			
			
		} else {
			
			throw new HaarmkException("This service is not a domain. Service id: "+ serviceId);
		}
	}







	
	
// ============================================================= DNS END ===================================================================================

	
	
	
	
	
	
	
	
	
	
}

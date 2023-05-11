/**
 * 
 */
package com.haarmk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityReq;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityRes;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.dto.domain.DomainRegisterReq;
import com.haarmk.dto.domain.DomainRegisterRes;
import com.haarmk.dto.domain.DomainSearchReq;
import com.haarmk.exception.DomainException;
import com.haarmk.exception.HaarmkException;
import com.haarmk.service.interfaces.CartItemService;
import com.haarmk.service.interfaces.CurrencyService;
import com.haarmk.service.interfaces.NameService;

/**
 * @author indicate0
 *
 */

@Service
public class NameServiceImpl implements NameService{
	@Autowired private RestTemplate restTemplate;
	@Autowired private Environment environment;
	@Autowired private Gson gson;
	@Autowired private CartItemService cartItemService;
	@Autowired private CurrencyService currencyService;
	
	@Autowired private ObjectMapper objectMapper;
//	String baseUrl = "https://api.name.com";
	String baseUrl = "https://api.dev.name.com";
	String[] tldFilter = {"com",  "org",  "net",  "edu",  "gov",  "co",  "io","in",  "co.in",  ".org.in",  "gov.in",  "ac.in",  "biz",  "info",  "me",  "tv",  "pro",  "mobi",  "name",  "travel"};
	DomainSearchReq domainSearchReq = new DomainSearchReq(1000, null, tldFilter);
	private HaarmkException haarmkException = new HaarmkException("Somthing went wrong in Name, please try agian!"); 
	
	private HttpHeaders getDomainHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
        return headers;
	}
	
	@Override
	public CheckDomainNameAvailabilityRes searchDamain(String searchTerm) {
		String urlSearch = baseUrl+"/v4/domains:search";
		CheckDomainNameAvailabilityReq checkDomainNameAvailabilityReq = new CheckDomainNameAvailabilityReq();
		String[] rootDomain = searchTerm.split("\\.");
		String domain = rootDomain[0];
		String defaultTld = ".com";
		if(rootDomain.length == 2) {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm);
			defaultTld = rootDomain[1];
		}else {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm+defaultTld);
		}
		
//		Check availability
		DomainCheckData availabilityRes = checkAvailability(checkDomainNameAvailabilityReq.getDomainNames().get(0));
		
		availabilityRes.setPurchasePrice(currencyService.usdToInr(availabilityRes.getPurchasePrice()) + currencyService.getDomainMargin());
		availabilityRes.setRenewalPrice(currencyService.usdToInr(availabilityRes.getRenewalPrice()) + currencyService.getDomainMargin());
		
        availabilityRes.setIsPresentInCart(cartItemService.isProductPresentInCart(availabilityRes.getDomainName()));
        
//      search domain
        domainSearchReq.setKeyword(domain);
        
        
        
        HttpEntity<String> requestEntitySearch = new HttpEntity<>(gson.toJson(domainSearchReq), getDomainHeader());    
        
        ResponseEntity<String> responseEntitySearch = this.restTemplate.exchange(urlSearch, HttpMethod.POST, requestEntitySearch, String.class);
        
        CheckDomainNameAvailabilityRes searchRes = gson.fromJson(responseEntitySearch.getBody(), CheckDomainNameAvailabilityRes.class);
        
        
        List<DomainCheckData> suggestions = new ArrayList<>();
        
        for(DomainCheckData dcd: searchRes.getSuggestions()) {
        	if(dcd.isPurchasable() && ! dcd.getDomainName().equals(availabilityRes.getDomainName())) {
        		dcd.setIsPresentInCart(cartItemService.isProductPresentInCart(dcd.getDomainName()));
        		dcd.setPurchasePrice(currencyService.usdToInr(dcd.getPurchasePrice()) + currencyService.getDomainMargin());
        		dcd.setRenewalPrice(currencyService.usdToInr(dcd.getRenewalPrice() + currencyService.getDomainMargin()));
				suggestions.add(dcd);
        	}
        }
        
//        for(int i = 0; i < searchRes.getSuggestions().size(); i++) {
//        	
//        	if (searchRes.getSuggestions().get(i).isPurchasable()) {
//        		
//			}
//        }
        searchRes.setResult(availabilityRes);
        searchRes.setSuggestions(suggestions);
        if(searchRes.getSuggestions().isEmpty() && !availabilityRes.isPurchasable()) {
        	throw new DomainException("No domain found for: "+searchTerm);
        }
        
        return searchRes;
	}
	
	@Override
	public DomainCheckData checkAvailability(String domainName) {
		String urlCheckAvailability = baseUrl+"/v4/domains:checkAvailability";
		CheckDomainNameAvailabilityReq checkDomainNameAvailabilityReq = new CheckDomainNameAvailabilityReq();
		checkDomainNameAvailabilityReq.getDomainNames().add(domainName);
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(checkDomainNameAvailabilityReq), getDomainHeader());        
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(urlCheckAvailability, HttpMethod.POST, requestEntity, String.class);
        CheckDomainNameAvailabilityRes availabilityRes = gson.fromJson(responseEntity.getBody(), CheckDomainNameAvailabilityRes.class);
        return availabilityRes.getSuggestions().get(0);
    }

	@Override
	public DomainRegisterRes registerDomain(DomainRegisterReq domainRegisterReq) {
		DomainCheckData domainCheckData = checkAvailability(domainRegisterReq.getDomain().getDomainName());
		
		JsonNode res =  this.getPricingForDomain(domainRegisterReq.getDomain().getDomainName(), domainRegisterReq.getYears());
		if(domainCheckData.isPurchasable()) {
//			domainRegisterReq.setPurchasePrice((double)(Math.round(domainCheckData.getPurchasePrice() * (double)(domainRegisterReq.getYears())*100)/100));
//			domainRegisterReq.setPurchasePrice(domainCheckData.getPurchasePrice());
			
			domainRegisterReq.setPurchasePrice(res.get("purchasePrice").asDouble());
			
		}else {
			throw new HaarmkException(domainRegisterReq.getDomain().getDomainName()+" is not available. Please contact haarmak infotect for refond");
		}
		domainRegisterReq.getDomain().setAutorenewEnabled(false);
		domainRegisterReq.getDomain().setLocked(false);

		String url = baseUrl+"/v4/domains";
		HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(domainRegisterReq), getDomainHeader());
		
		
			
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		DomainRegisterRes registerRes = gson.fromJson(responseEntity.getBody(), DomainRegisterRes.class);
		return registerRes;
		
	}
	
	@Override
	public JsonNode helloDomain() {		
		String url = baseUrl+"/v4/hello";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	
	
	@Override
	public JsonNode getDomains() {		
		String url = baseUrl+"/v4/domains";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	@Override
	public JsonNode getPricingForDomain(String domainName, Integer years) {		
		
		String url = baseUrl+"/v4/domains/"+domainName+":getPricing?years="+years;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
        JsonNode res;
        
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	
	
//	========================================		DNS		=====================================================
	
	
	
	
	@Override
	public JsonNode getDnsRecordById(String domainName
			,Integer recordId
			){		
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	
	
	@Override
	public JsonNode getDnsRecord(String domainName
			
			) {		
		String url = baseUrl+"/v4/domains/"+domainName+"/records";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	
	
	
	
	
	@Override
	public DnsCreateRecordResDto createDnsRecord(DnsCreateRecordReqDto dnsCreateRecordReqDto,
			String domainName
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records";
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(dnsCreateRecordReqDto), getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        DnsCreateRecordResDto registerRes = gson.fromJson(responseEntity.getBody(), DnsCreateRecordResDto.class);
        return registerRes;
	}
	
	
	@Override
	public DnsCreateRecordResDto updateDnsRecord(
			DnsCreateRecordReqDto dnsCreateRecordReqDto,
			String domainName,
			Integer recordId
			
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(dnsCreateRecordReqDto), getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        DnsCreateRecordResDto registerRes = gson.fromJson(responseEntity.getBody(), DnsCreateRecordResDto.class);
        return registerRes;
	}
	
	@Override
	public void deleteDnsRecord(
			String domainName,
			Integer recordId
			
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<Void> responseEntity = this.restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,Void.class);
        
	}
	
//	==========================================		orders		=====================================================================
	
	
	@Override
	public JsonNode getOrderById(
			Integer orderId
			) {		
		String url = baseUrl+"/v4/orders/"+orderId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}
        return res;
	}
	
	@Override
	public JsonNode getOrder() {		
		String url = baseUrl+"/v4/orders";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			throw haarmkException;
		}

        return res;
	}
	
	
	
}

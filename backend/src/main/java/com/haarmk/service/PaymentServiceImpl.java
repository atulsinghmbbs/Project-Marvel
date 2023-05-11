package com.haarmk.service;

import java.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.haarmk.dto.RazorpaySuccessReq;
import com.haarmk.exception.HaarmkException;
import com.haarmk.service.interfaces.PaymentService;
import com.haarmk.util.RazorpaySignature;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired RazorpayClient razorpayClient;
	@Autowired Environment environment;
	
	@Override
	public String varifyRazorpayPayment(RazorpaySuccessReq razorpaySuccessReq) {
		
		String razorpay_payment_id = razorpaySuccessReq.getRazorpay_payment_id();
		
		try {
			Payment foundRazorpayPayment = razorpayClient.payments.fetch(razorpay_payment_id);
			String order_id = foundRazorpayPayment.get("order_id");
			String data = order_id+"|"+razorpay_payment_id;
			System.out.println(data);
			String signature = RazorpaySignature.calculateRFC2104HMAC(data, environment.getProperty("razorpay_secret"));
			System.out.println(signature);
			if(signature.equals(razorpaySuccessReq.getRazorpay_signature())) {
				return order_id;
			}else {
				throw new HaarmkException("signiture is not valid!");
			}
			
		} catch (RazorpayException | SignatureException e) {
			throw new HaarmkException(e.getMessage());
		}

	}

	@Override
	public Payment getRazorpayPaymentById(String paymentId) {
		
		 try { 
			 
			 return razorpayClient.payments.fetch(paymentId);
		
		 }
		 catch (RazorpayException e1) { 
			 
			 throw new HaarmkException("payment id is not correct!");
			 
		 }
	}
}

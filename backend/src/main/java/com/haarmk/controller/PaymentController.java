package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.RazorpaySuccessReq;
import com.haarmk.service.interfaces.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired private PaymentService paymentService;
	
	
	
	@PostMapping(value = "/varify")
	public ResponseEntity<Void> varifyRazorpayPayment(RazorpaySuccessReq razorpaySuccessReq) {
		paymentService.varifyRazorpayPayment(razorpaySuccessReq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

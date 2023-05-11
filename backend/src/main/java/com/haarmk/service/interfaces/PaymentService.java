package com.haarmk.service.interfaces;

import com.haarmk.dto.RazorpaySuccessReq;
import com.razorpay.Payment;

public interface PaymentService {
	String varifyRazorpayPayment(RazorpaySuccessReq razorpaySuccessReq);
	Payment getRazorpayPaymentById(String paymentId);
}

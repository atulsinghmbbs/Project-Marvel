/**
 * 
 */
package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.EmailDetails;
import com.haarmk.service.interfaces.EmailService;

/**
 * @author Ankit Patel
 *
 */
@RestController
@RequestMapping(value = "/emails")
public class EmailSenderController {
	@Autowired EmailService emailService;
	
//	@PostMapping(value = "/simple")
//	public String sedAMail(@RequestBody EmailDetails emailDetails) {
//		return emailService.sendSimpleMail(emailDetails);
//	}
	
	
	@PostMapping(value = "/withAttachment")
	public void sedAMailWithAttachment(@RequestBody EmailDetails emailDetails) {
		emailService.sendMail(emailDetails);
	}
}

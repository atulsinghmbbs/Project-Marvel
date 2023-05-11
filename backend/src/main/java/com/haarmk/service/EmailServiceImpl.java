/**
 * 
 */
package com.haarmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;
import com.haarmk.dto.EmailDetails;
import com.haarmk.exception.EmailException;
import com.haarmk.service.interfaces.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * @author Ankit Patel
 *
 */

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired private JavaMailSender emailSender;
	@Autowired Environment environment;
	@Value("${spring.mail.username}") private String sender;
//	@Override
//	public String sendSimpleMail(EmailDetails details) {
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setFrom(sender);
//		simpleMailMessage.setTo(details.getRecipients());
//		simpleMailMessage.setText(details.getMsgBody());
//		simpleMailMessage.setSubject(details.getSubject());
//		emailSender.send(simpleMailMessage);
//		return "Email sent successfully !";
//	}

	@Override
	public void sendMail(EmailDetails details) {
		MimeMessage message = emailSender.createMimeMessage();
	   
	    MimeMessageHelper mimeMessageHelper;
	   
	    try {
	    	 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(message, true,"utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipients());
            mimeMessageHelper.setText(details.getMsgBody(),details.getIsHtml());
            
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            if(details.getAttachment() != null) {
            	 FileSystemResource file
                 = new FileSystemResource(
                     new File(details.getAttachment()));
  
	             mimeMessageHelper.addAttachment(
	                 file.getFilename(), file);
            }
           
 
            // Sending the mail
            emailSender.send(message);
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            throw new EmailException(e.getMessage());
        }
    
	}

}

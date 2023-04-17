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

import com.haarmk.dto.EmailDetails;
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
	@Override
	public String sendSimpleMail(EmailDetails details) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setTo(details.getRecipient());
		simpleMailMessage.setText(details.getMsgBody());
		simpleMailMessage.setSubject(details.getSubject());
		emailSender.send(simpleMailMessage);
		return "Email sent successfully !";
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		MimeMessage message = emailSender.createMimeMessage();
	   
	    MimeMessageHelper mimeMessageHelper;
	   
	    try {
	    	 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText("<H1>Hello, I am HTML content!</H1>\n"+details.getMsgBody(),true);
            
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            
            FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            // Sending the mail
            emailSender.send(message);
            return "Mail sent Successfully";
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    
	}

}

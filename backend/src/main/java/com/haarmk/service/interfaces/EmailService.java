/**
 * 
 */
package com.haarmk.service.interfaces;

import com.haarmk.dto.email.EmailDetails;

/**
 * @author Ankit Patel
 *
 */
public interface EmailService {
	
	 String sendSimpleMail(EmailDetails details);
	 
	 String sendMailWithAttachment(EmailDetails details);
}

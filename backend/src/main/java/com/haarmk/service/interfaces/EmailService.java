/**
 * 
 */
package com.haarmk.service.interfaces;

import com.haarmk.dto.EmailDetails;

/**
 * @author Ankit Patel
 *
 */
public interface EmailService {
	
//	 String sendSimpleMail(EmailDetails details);
	 
	 void sendMail(EmailDetails details);
}

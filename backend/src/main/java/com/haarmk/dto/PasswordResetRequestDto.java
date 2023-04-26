/**
 * 
 */
package com.haarmk.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * @author Ankit Patel
 *
 */

@Data
public class PasswordResetRequestDto {
	@Email
	private String email;
}

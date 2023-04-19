/**
 * 
 */
package com.haarmk.dto;

import lombok.Data;

/**
 * @author indicate0
 *
 */
@Data
public class PasswordResetReq {
	String token;
	String newPassword;
}

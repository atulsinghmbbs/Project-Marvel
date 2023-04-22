/**
 * 
 */
package com.haarmk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author HMK05
 *
 */

@Data
public class SignupDto {
	@NotBlank @NotEmpty @NotNull
	private String firstName;
	@NotBlank @NotEmpty @NotNull
	private String lastName;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = " password pattern that contains at least one capital letter, one small letter, one number, and one special character, and is between 8 and 20 characters long")
    private String password;
    @Email
    private String email;
}

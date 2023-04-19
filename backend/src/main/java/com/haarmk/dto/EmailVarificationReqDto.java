package com.haarmk.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailVarificationReqDto {
	@Email
	String email;
}

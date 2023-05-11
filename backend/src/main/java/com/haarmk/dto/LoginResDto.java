package com.haarmk.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class LoginResDto {
	private String token;
	private String type;
}

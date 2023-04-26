package com.haarmk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFeebackResDto {
	private String username;
	private String firstName;
	private String lastName;
    private String image;
}

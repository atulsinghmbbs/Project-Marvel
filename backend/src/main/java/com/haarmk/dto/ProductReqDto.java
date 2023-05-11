package com.haarmk.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ProductReqDto {
	
	private String uniqueName;
	private String displayName;
	private Double price;
	private Boolean isActive;
	private Integer categoryId;
    private Map<String,String> properties;

}

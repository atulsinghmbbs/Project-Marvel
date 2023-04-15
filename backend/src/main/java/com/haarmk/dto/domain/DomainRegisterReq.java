/**
 * 
 */
package com.haarmk.dto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Ankit Patel
 *
 */
@Data
public class DomainRegisterReq {
	@NotBlank @NotEmpty @NotNull
	private DomainReq domain;
	@JsonIgnore
	private Double purchasePrice;
//	@JsonIgnore
//	private String purchaseType;
	private Integer years;
//	@JsonIgnore
//	private List<String> tldRequirements;
//	@JsonIgnore
//	private String promoCode;

}


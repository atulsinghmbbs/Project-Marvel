package com.haarmk.dto.domain;

import lombok.Data;

@Data
public class DomainRegisterContactsReq {
//	@JsonIgnore
	private ContactInfo registrant;
//	@JsonIgnore
//    private ContactInfo admin;
//	@JsonIgnore
//    private ContactInfo tech;
    private ContactInfo billing;
}

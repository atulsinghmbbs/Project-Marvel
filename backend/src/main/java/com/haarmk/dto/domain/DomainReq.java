package com.haarmk.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DomainReq {
	private String domainName;
//	@JsonIgnores
//    private List<String> nameservers;
    private DomainRegisterContactsReq contacts;
    @JsonIgnore
    private boolean locked;
    @JsonIgnore
    private boolean autorenewEnabled;
}

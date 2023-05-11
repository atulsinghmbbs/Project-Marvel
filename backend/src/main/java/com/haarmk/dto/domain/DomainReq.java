package com.haarmk.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DomainReq {
	String domainName;
    DomainRegisterContactsReq domainRegisterContactsReq;
    boolean locked;
    boolean autorenewEnabled;
}

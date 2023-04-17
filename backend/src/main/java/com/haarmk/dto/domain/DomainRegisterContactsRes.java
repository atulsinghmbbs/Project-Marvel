package com.haarmk.dto.domain;

import lombok.Data;

@Data
public class DomainRegisterContactsRes {
	private ContactInfo registrant;
    private ContactInfo billing;
}

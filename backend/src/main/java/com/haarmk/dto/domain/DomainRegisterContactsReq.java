package com.haarmk.dto.domain;

import lombok.Data;

@Data
public class DomainRegisterContactsReq {
	ContactInfo registrant;
    ContactInfo admin;
    ContactInfo tech;
    ContactInfo billing;
}

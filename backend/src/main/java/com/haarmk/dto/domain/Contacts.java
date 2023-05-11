package com.haarmk.dto.domain;

import lombok.Data;

@Data
public class Contacts {
    private ContactInfo registrant;
    private ContactInfo admin;
    private ContactInfo tech;
    private ContactInfo billing;
}

package com.haarmk.dto.domain;

import java.util.List;

import lombok.Data;

@Data
public class Domain {
    private String domainName;
    private List<String> nameservers;
    private Contacts contacts;
    private boolean locked;
    private boolean autorenewEnabled;
    private String expireDate;
    private String createDate;
    private double renewalPrice;
}

@Data
class Contacts {
    private ContactInfo registrant;
    private ContactInfo admin;
    private ContactInfo tech;
    private ContactInfo billing;
}

@Data
class ContactInfo {
    private String firstName;
    private String lastName;
    private String address1;
    private String city;
    private String state;
    private String country;
    private String phone;
}

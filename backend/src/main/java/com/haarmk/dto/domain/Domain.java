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



package com.haarmk.dto.domain;


import lombok.Data;

@Data
public class DomainCheckData {
    private String domainName;
    private String sld;
    private String tld;
    private boolean purchasable;
    private double purchasePrice;
    private String purchaseType;
    private double renewalPrice;
}

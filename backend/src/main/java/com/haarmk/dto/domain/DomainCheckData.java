package com.haarmk.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainCheckData {
    @JsonProperty("domainName")
    private String domainName;
    @JsonProperty("sld")
    private String sld;
    @JsonProperty("tld")
    private String tld;
    @JsonProperty("purchasable")
    private boolean purchasable;
    @JsonProperty("purchasePrice")
    private double purchasePrice;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("renewalPrice")
    private double renewalPrice;
}

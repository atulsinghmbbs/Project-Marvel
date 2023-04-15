/**
 * 
 */
package com.haarmk.dto.domain;

import java.util.List;

import lombok.Data;

/**
 * @author HMK05
 *
 */

@Data
public class DomainRes {
    private String domainName;
    private List<String> nameservers;
    private DomainRegisterContactsRes contacts;
    private boolean locked;
    private boolean autorenewEnabled;
    private String expireDate;
    private String createDate;
    private double renewalPrice;
}

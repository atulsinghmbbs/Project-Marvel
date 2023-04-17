/**
 * 
 */
package com.haarmk.dto.domain;

import lombok.Data;

/**
 * @author Ankit Patel
 *
 */
@Data
public class DomainRegisterReq {
	private String domainName;
	private ContactsReq contacts;
}

@Data
class ContactsReq {
    private ContactInfoReq billing;
}

@Data
class ContactInfoReq {
    private String firstName;
    private String lastName;
    private String address1;
    private String city;
    private String state;
    private String country;
    private String phone;
}

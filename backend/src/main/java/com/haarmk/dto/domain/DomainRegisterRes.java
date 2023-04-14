package com.haarmk.dto.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainRegisterRes {
    private Domain domain;
    private int order;
    private double totalPaid;

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class DomainRes {
    private String domainName;
    private List<String> nameservers;
    private Contacts contacts;
    private boolean locked;
    private boolean autorenewEnabled;
    private LocalDateTime expireDate;
    private LocalDateTime createDate;
    private double renewalPrice;

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ContactsRes {
//    private ContactInfo registrant;
    private ContactInfo admin;
    private ContactInfo tech;
    private ContactInfo billing;
}









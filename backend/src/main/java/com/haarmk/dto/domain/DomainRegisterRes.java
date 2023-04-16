package com.haarmk.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainRegisterRes {
    private DomainRes domain;
    private int order;
    private double totalPaid;

}













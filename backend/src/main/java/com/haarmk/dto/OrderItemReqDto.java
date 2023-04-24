package com.haarmk.dto;


import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemReqDto {


    private Integer qty;
    private Integer productId;
    private Map<String, String> additionalInfo;


}
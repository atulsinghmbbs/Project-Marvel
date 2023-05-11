package com.haarmk.dto;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemReqDto {

    private Integer qty;
    private Integer productId;
    


}
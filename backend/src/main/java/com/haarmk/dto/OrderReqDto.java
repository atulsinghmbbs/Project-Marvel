package com.haarmk.dto;


import java.util.ArrayList;
import java.util.List;

//package com.viniciusaugusto.orderapi.dto;
//
//import com.viniciusaugusto.orderapi.dto.responses.ClientResponseDTO;
//import com.viniciusaugusto.orderapi.entities.Order;
//import com.viniciusaugusto.orderapi.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderReqDto {

	
    private List<OrderItemReqDto> items = new ArrayList<>();


}

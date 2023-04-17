package com.haarmk.dto;


import com.haarmk.model.OrderItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Integer quantity;
    private Double price;
    private String product;
    private Long order;
    public double getSubTotal;

    public OrderItemDto(OrderItem orderItem) {
        id = orderItem.getId();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
        product = orderItem.getProduct().getDomainName();
        order = orderItem.getOrder().getId();
        getSubTotal = orderItem.getSubTotal();
    }
}
package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.OrderDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Orders;


public interface OrderService {

    public List<OrderDto> getAllOrders() throws OrderException;

    OrderDto getOrderById(Long id)throws OrderException;

    OrderDto addOrder(Orders order)throws OrderException;

    public Orders deleteOrder(Long id)throws OrderException;
}
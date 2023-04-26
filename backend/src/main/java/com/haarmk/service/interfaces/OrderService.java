package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.OrderReqDto;
import com.haarmk.dto.OrderResDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Orders;


public interface OrderService {

    List<OrderReqDto> getAllOrders() throws OrderException;

    OrderReqDto getOrderById(Long id) throws OrderException;

    Orders deleteOrder(Long id) throws OrderException;

	Orders addOrder(OrderReqDto orderReqDto) throws OrderException;
}
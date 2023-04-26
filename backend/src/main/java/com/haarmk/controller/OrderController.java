package com.haarmk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.OrderReqDto;
import com.haarmk.model.Orders;
import com.haarmk.service.interfaces.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {

	@Autowired
    private OrderService orderService;

 

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderReqDto>> findAll() {
    	List<OrderReqDto> orders = orderService.getAllOrders();
    	return new ResponseEntity<List<OrderReqDto>>(orders, HttpStatus.OK);

    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderReqDto> findById(@PathVariable("id") Long id) {
          OrderReqDto orderDto = orderService.getOrderById(id);
          return new ResponseEntity<OrderReqDto>(orderDto, HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Orders> addOrder(@RequestBody @Valid OrderReqDto orderReqDto) {
        Orders order = orderService.addOrder(orderReqDto);
        return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteOrder/{id}")
    public ResponseEntity<Orders> delete(@PathVariable("id") Long id) {
        Orders order = orderService.deleteOrder(id);
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }
}

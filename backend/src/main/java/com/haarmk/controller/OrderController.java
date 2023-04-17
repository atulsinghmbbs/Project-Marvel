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

import com.haarmk.dto.OrderDto;
import com.haarmk.model.Order;
import com.haarmk.service.interfaces.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {

	@Autowired
    private OrderService orderService;

 

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDto>> findAll() {
    	List<OrderDto> orders = orderService.getAllOrders();
    	return new ResponseEntity<List<OrderDto>>(orders, HttpStatus.OK);

    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id) {
          OrderDto orderDto = orderService.getOrderById(id);
          return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDto> addOrder(@RequestBody @Valid Order order) {
        OrderDto order1 = orderService.addOrder(order);
        return new ResponseEntity<OrderDto>(order1, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteOrder/{id}")
    public ResponseEntity<Order> delete(@PathVariable("id") Long id) {
        Order order = orderService.deleteOrder(id);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}

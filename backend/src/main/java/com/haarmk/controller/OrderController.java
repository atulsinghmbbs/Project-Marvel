package com.haarmk.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.OrderReqDto;
import com.haarmk.model.Address;
import com.haarmk.model.Orders;
import com.haarmk.service.interfaces.OrderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
@SecurityRequirement(name = "bearer-key")
public class OrderController {

	@Autowired
    private OrderService orderService;

 

    @GetMapping("/getAllOrders")
    public ResponseEntity<Set<Orders>> findAll() {
    	Set<Orders> orders = orderService.getAllOrders();
    	return new ResponseEntity<Set<Orders>>(orders, HttpStatus.OK);

    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Orders> findById(@PathVariable("id") Long id) {
          Orders orderDto = orderService.getOrderById(id);
          return new ResponseEntity<Orders>(orderDto, HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Orders> addOrder(@RequestBody Address billingAddress) {
    	
        Orders order = orderService.addOrder(billingAddress);
        return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteOrder/{id}")
    public ResponseEntity<Orders> delete(@PathVariable("id") Long id) {
        Orders order = orderService.deleteOrder(id);
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }
    
    @GetMapping("/pay")
    public ResponseEntity<Set<Orders>> pay() {
    	Set<Orders> orders = orderService.getAllOrders();
    	return new ResponseEntity<Set<Orders>>(orders, HttpStatus.OK);

    }
}

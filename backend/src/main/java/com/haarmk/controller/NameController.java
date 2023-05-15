package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.service.interfaces.NameService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/name")
public class NameController {
	@Autowired private NameService nameService;
	
	@GetMapping(value= "/orders/{orderId}")
	public ResponseEntity<JsonNode> getOrderById(
			@PathVariable Integer orderId
			) {		
		
        JsonNode res = nameService.getOrderById(orderId);
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	@GetMapping(value= "/orders")
	public ResponseEntity<JsonNode> getOrder() throws JsonMappingException, JsonProcessingException {		
		
        JsonNode res = nameService.getOrder();
        return new ResponseEntity<JsonNode>(res , HttpStatus.OK);
	}
}

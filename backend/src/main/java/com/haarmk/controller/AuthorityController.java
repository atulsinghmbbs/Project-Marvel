package com.haarmk.controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.model.Authority;
import com.haarmk.service.interfaces.AuthorityService;

@RestController
@RequestMapping(value = "/authorities")
public class AuthorityController {
	
    @Autowired private AuthorityService authorityService;
	
	
    @PostMapping(value = "/add-authority")
    public ResponseEntity<Authority> addAuthority( Authority authority) {
    	
		return new ResponseEntity<Authority>(authorityService.addAuthority(authority),HttpStatus.CREATED);
	}
    

}

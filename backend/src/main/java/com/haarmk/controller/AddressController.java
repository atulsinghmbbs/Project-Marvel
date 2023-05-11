package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.model.Address;
import com.haarmk.service.interfaces.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
    
	@Autowired
	private AddressService addressservice;
	
	
	@PostMapping("/")
	public ResponseEntity<Address> RegisterAddress(@Valid @RequestBody Address address){
		
		Address Regaddress = addressservice.RegisterAddress(address);
		
		return new ResponseEntity<Address>(Regaddress , HttpStatus.CREATED);
		
		
	}
	
        @GetMapping ("/{id}")
	    public ResponseEntity<Address> GetAddressById(@PathVariable("id") Integer addressId){
		
		Address Getaddress = addressservice.GetAddressbyId(addressId);
		
		return new ResponseEntity<Address>(Getaddress , HttpStatus.CREATED);
		
		
	}
        
        @DeleteMapping("/{id}")
	    public ResponseEntity<Address> DeleteAddressById(@PathVariable("id") Integer addressId){
		
		Address deleteaddress = addressservice.deleteAddress(addressId);
		
		return new ResponseEntity<Address>(deleteaddress , HttpStatus.CREATED);
		
		
	}   
	
        
        @PutMapping("/")
	    public ResponseEntity<Address> updateaddress(@RequestBody Address address){
		
		Address updateaddress = addressservice.updateAddress(address);
		
		return new ResponseEntity<Address>(updateaddress , HttpStatus.CREATED);
		
		
	}   
	
	
	
}

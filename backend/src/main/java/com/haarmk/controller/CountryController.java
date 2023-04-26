package com.haarmk.controller;

import java.util.List;

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

import com.haarmk.model.Country;
import com.haarmk.service.interfaces.CountryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryservice;
	
	
	@PostMapping("/")
	public ResponseEntity<Country> RegisterCountry(@Valid @RequestBody Country country){
		
		Country regcountry = countryservice.RegisterCountry(country);
		
		return new ResponseEntity<Country>(regcountry , HttpStatus.CREATED);
		
		
	}
	
        @GetMapping ("/{id}")
	    public ResponseEntity<Country> GetAddressById(@PathVariable("id") Integer cid){
		
        Country getcountry = countryservice.GetCountrybyId(cid);
		
		return new ResponseEntity<Country>(getcountry , HttpStatus.CREATED);
		
		
	}
        
        @GetMapping("/")
    	public ResponseEntity<List<Country>>GetAllCountry() {
    		
    		List<Country> getlistofcountry = countryservice.GetListofCountry();
    		
    		return new ResponseEntity<List<Country>>(getlistofcountry , HttpStatus.OK);
    	}
    	
        
        

        @DeleteMapping("/{id}")
	    public ResponseEntity<Country> DeleteAddressById(@PathVariable("id") Integer cid){
		
        Country deletecountry = countryservice.deletecountry(cid);
		
		return new ResponseEntity<Country>(deletecountry , HttpStatus.CREATED);
		
		
	}   
	
        
        @PutMapping("/")
	    public ResponseEntity<Country> updateaddress(@RequestBody Country country){
		
        Country updatecountry = countryservice.updatecountry(country);
		
		return new ResponseEntity<Country>(updatecountry , HttpStatus.CREATED);
		
		
	}   
		
	
}

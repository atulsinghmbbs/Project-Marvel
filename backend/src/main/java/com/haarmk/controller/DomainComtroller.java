package com.haarmk.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haarmk.dto.domain.CheckAvailabilityResponse;
@RestController(value = "/domain")
public class DomainComtroller {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	 @JsonProperty("domainNames")
     private String[] domainNames;
	@GetMapping(value= "/check-availability")
	public ResponseEntity<CheckAvailabilityResponse> chekDamainAvalabillity(@RequestParam String domainName) {
		
		String url = "https://api.dev.name.com/v4/domains:checkAvailability";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
       
        String requestJson = "{\"domainNames\":[\""+domainName+"\"]}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);        
        ResponseEntity<CheckAvailabilityResponse> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, CheckAvailabilityResponse.class);

        return responseEntity;
	}
	
}

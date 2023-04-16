/**
 * 
 */
package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author indicate0
 *
 */

@RestController
@RequestMapping(value = "cpanel")
public class CPanelController {
	
	@Autowired	private RestTemplate restTemplate;
	@Autowired	private Environment environment;
	@Autowired private Gson gson;
	@Autowired private ObjectMapper objectMapper;
	String baseUrl = "https://haarmk.com:2083";
	
	private HttpHeaders getWhmHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth(environment.getProperty("WHM_AUTH_HEADER"), environment.getProperty("WHM_TOKEN"));
        headers.set(environment.getProperty("CPanel_AUTH_HEADER"), environment.getProperty("CPanel_TOKEN"));
        return headers;
	}
	
	
	@GetMapping(value= "/domains_data")
	public ResponseEntity<JsonNode> getDomainData() {		
		String url = baseUrl+"/execute/DomainInfo/domains_data";
		System.out.println(url);
        HttpEntity<String> requestEntity = new HttpEntity<>(getWhmHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println("out of rest template!");
        JsonNode res = null;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	@GetMapping(value= "/domains_data")
	public ResponseEntity<JsonNode> createSubdomain() {		
		String url = baseUrl+"/execute/DomainInfo/domains_data";
		System.out.println(url);
        HttpEntity<String> requestEntity = new HttpEntity<>(getWhmHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println("out of rest template!");
        JsonNode res = null;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
//	void
}

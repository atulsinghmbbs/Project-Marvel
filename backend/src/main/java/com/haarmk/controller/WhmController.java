package com.haarmk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haarmk.service.WhmServiceImpl;

@RestController
@RequestMapping(value = "/whm")
public class WhmController {
	@Autowired	private RestTemplate restTemplate;
	@Autowired	private Environment environment;
	@Autowired private Gson gson;
	@Autowired private ObjectMapper objectMapper;
	String baseUrl = "https://haarmk.com:2087";
	@Autowired private WhmServiceImpl whmService;
	
	private HttpHeaders getWhmHeader() {
		HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth(environment.getProperty("WHM_AUTH_HEADER"), environment.getProperty("WHM_TOKEN"));
        headers.set(environment.getProperty("WHM_AUTH_HEADER"), environment.getProperty("WHM_TOKEN"));
        return headers;
	}
	

	
	
	@GetMapping(value= "/info")
	public ResponseEntity<JsonNode> getInfo() {		
		
		
		JsonNode res = whmService.getinfo();
		
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	@GetMapping(value= "/cpanel-accounts")
	public ResponseEntity<JsonNode> getCpanelAccounts() {		
		
		JsonNode res = whmService.getCpanelAccounts();
		
		return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	@GetMapping(value= "/create-cpanel-account")
	public ResponseEntity<JsonNode> createCpanelAccount(@RequestParam String username, @RequestParam String domain,@RequestParam String plan) {		
		
		JsonNode res = whmService.createCpanelAccount(username, domain, plan);
		
		return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	@GetMapping(value= "/chexk-cpanel-username")
	public Boolean checkCpanelUsernameIfExists(@RequestParam String username) {		
		
		return  whmService.checkCpanelUsernameIfExists(username);
		
//		return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
//	@PostMapping(value= "/create-subdomain")
//	public ResponseEntity<JsonNode> createSubdomain(@RequestParam String subdomainName) {		
//		String url = baseUrl+"/json-api/create_subdomain?api.version=2&domain="+subdomainName+".haarmk.com&document_root=/home1/haarmkco";
//		System.out.println(getWhmHeader());
//		System.out.println(url);
//        HttpEntity<String> requestEntity = new HttpEntity<>(getWhmHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        System.out.println("out of rest template!");
//        JsonNode res = null;
//		try {
//			res = objectMapper.readTree(responseEntity.getBody());
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
}

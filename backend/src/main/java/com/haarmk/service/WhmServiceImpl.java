package com.haarmk.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haarmk.exception.HaarmkException;

@Service
public class WhmServiceImpl {
	private final RestTemplate restTemplate;
	private Environment environment;
	@Autowired private Gson gson;
	@Autowired private ObjectMapper objectMapper;
	String baseUrl = "https://haarmk.com:2087";
	
	
	
	@Autowired
	public WhmServiceImpl(RestTemplateBuilder restTemplateBuilder, Environment environment) {
		this.environment = environment;
		UriTemplateHandler uriTemplateHandler = new RootUriTemplateHandler(baseUrl);
		this.restTemplate = restTemplateBuilder
		.uriTemplateHandler(uriTemplateHandler)
		.setConnectTimeout(Duration.ofMillis(10000))
		.setReadTimeout(Duration.ofMinutes(2))
//		.basicAuthentication(environment.getProperty("WHM_USERNAME"), environment.getProperty("WHM_PASSWORD"))
		.defaultHeader(environment.getProperty("WHM_AUTH_HEADER"), environment.getProperty("WHM_TOKEN"))
		.build();
		
	}
	
	
	public JsonNode getinfo() {		
		String url = "/json-api/listaccts?api.version=1";
//        HttpEntity<String> requestEntity = new HttpEntity<>();
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        JsonNode res = null;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return res;
	}
	
	public JsonNode getCpanelAccounts() {		
		String url = "/json-api/listaccts?api.version=1";
//        HttpEntity<String> requestEntity = new HttpEntity<>();
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		JsonNode res = null;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Boolean checkCpanelUsernameIfExists(String username) {		
		String url = "/json-api/verify_new_username?api.version=1&user="+username;
//        HttpEntity<String> requestEntity = new HttpEntity<>();
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		JsonNode res = null;
		
		
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			throw new HaarmkException(e.getMessage());
		} catch (JsonProcessingException e) {
			throw new HaarmkException(e.getMessage());
		}
		System.out.println(res);
		if(res.get("metadata").get("result").asInt() == 0) return true;
		return false;
		
	}
	
	public JsonNode createCpanelAccount(String username, String domain, String plan) {		
		String url = "/json-api/createacct?api.version=1&username="+username+"&domain="+domain+"&plan="+plan;
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		JsonNode res = null;
		try {
			res = objectMapper.readTree(responseEntity.getBody());
		} catch (JsonMappingException e) {
			throw new HaarmkException(e.getMessage());
		} catch (JsonProcessingException e) {
			throw new HaarmkException(e.getMessage());
		}
		return res;
	}
	

}

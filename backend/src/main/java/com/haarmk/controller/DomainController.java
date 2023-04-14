package com.haarmk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityReq;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityRes;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.dto.domain.DomainRegisterReq;
import com.haarmk.dto.domain.DomainSearchReq;
import com.haarmk.exception.DomainException;

@RestController
@RequestMapping("/domains")
public class DomainController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	@Autowired private Gson gson;
//	String baseUrl = "https://api.name.com";
	String baseUrl = "https://api.dev.name.com";
	
	DomainSearchReq domainSearchReq = new DomainSearchReq(1000, null, null);
	
	@GetMapping(value= "/search")
	public ResponseEntity<CheckDomainNameAvailabilityRes> chekDamainNameAvalabillity(@RequestParam String searchTerm) {
		String urlCheckAvailability = baseUrl+"/v4/domains:checkAvailability";
		String urlSearch = baseUrl+"/v4/domains:search";
		
		CheckDomainNameAvailabilityReq checkDomainNameAvailabilityReq = new CheckDomainNameAvailabilityReq();
		String[] rootDomain = searchTerm.split("\\.");
		String domain = rootDomain[0];
		String defaultTld = ".com";
		if(rootDomain.length == 2) {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm);
			defaultTld = rootDomain[1];
		}else {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm+defaultTld);
		}
		
//		Check availability
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(checkDomainNameAvailabilityReq), getDomainHeader());        
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(urlCheckAvailability, HttpMethod.POST, requestEntity, String.class);
        CheckDomainNameAvailabilityRes availabilityRes = gson.fromJson(responseEntity.getBody(), CheckDomainNameAvailabilityRes.class);
        
//      search domain
        domainSearchReq.setKeyword(domain);
        HttpEntity<String> requestEntitySearch = new HttpEntity<>(gson.toJson(domainSearchReq), getDomainHeader());        
        ResponseEntity<String> responseEntitySearch = this.restTemplate.exchange(urlSearch, HttpMethod.POST, requestEntitySearch, String.class);
        CheckDomainNameAvailabilityRes searchRes = gson.fromJson(responseEntitySearch.getBody(), CheckDomainNameAvailabilityRes.class);
        
        
        List<DomainCheckData> suggestions = new ArrayList<>();
        
        for(int i = 0; i < searchRes.getSuggestions().size(); i++) {
        	if (searchRes.getSuggestions().get(i).isPurchasable()) {
				suggestions.add(searchRes.getSuggestions().get(i));
			}
        }
        searchRes.setResult(availabilityRes.getSuggestions().get(0));
        searchRes.setSuggestions(suggestions);
        if(availabilityRes.getSuggestions().isEmpty() && !availabilityRes.getResult().isPurchasable()) {
        	throw new DomainException("No domain found for: "+searchTerm);
        }
        
        return new ResponseEntity<>(searchRes, HttpStatus.OK);
	}
	
	
	private HttpHeaders getDomainHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
        return headers;

	}
	
	
	@PostMapping(value= "/register-domain")
	public ResponseEntity<CheckDomainNameAvailabilityRes> registerDomain(@RequestParam DomainRegisterReq domainRegisterReq) {
		String urlCheckAvailability = baseUrl+"/v4/domains";
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(domainRegisterReq), getDomainHeader());        
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(urlCheckAvailability, HttpMethod.POST, requestEntity, String.class);
        CheckDomainNameAvailabilityRes availabilityRes = gson.fromJson(responseEntity.getBody(), CheckDomainNameAvailabilityRes.class);
 
        
//        return new ResponseEntity<>(searchRes, HttpStatus.OK);
        return null;
	}
	
	
	
	
}
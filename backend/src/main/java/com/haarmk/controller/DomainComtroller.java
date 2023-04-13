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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityReq;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityRes;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.exception.DomainException;

@RestController(value = "/domain")
public class DomainComtroller {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	@Autowired private Gson gson;
//	String baseUrl = "https://api.name.com";
	String baseUrl = "https://api.dev.name.com";
	@PostMapping(value= "/check-availability")
	public ResponseEntity<CheckDomainNameAvailabilityRes> chekDamainNameAvalabillity(@RequestParam String searchTerm) {
		String url = baseUrl+"/v4/domains:checkAvailability";
		       
        HttpEntity<String> requestEntity = new HttpEntity<>(domainNameSuggestion(searchTerm), getDomainHeader());        
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        CheckDomainNameAvailabilityRes availabilityRes = gson.fromJson(responseEntity.getBody(), CheckDomainNameAvailabilityRes.class);
        
        
        List<DomainCheckData> suggestions = new ArrayList<>();
        
        for(int i = 0; i < availabilityRes.getSuggestions().size(); i++) {
        	if(availabilityRes.getSuggestions().get(i).getDomainName().equals(searchTerm)) {
        		availabilityRes.setResult(availabilityRes.getSuggestions().get(i));
        	}else if (availabilityRes.getSuggestions().get(i).isPurchasable()) {
				suggestions.add(availabilityRes.getSuggestions().get(i));
			}
        }
        availabilityRes.setSuggestions(suggestions);
        if(availabilityRes.getSuggestions().isEmpty() && !availabilityRes.getResult().isPurchasable()) {
        	throw new DomainException("No domain found for: "+searchTerm);
        }
        
        return new ResponseEntity<>(availabilityRes, HttpStatus.OK);
	}
	
	
	private String domainNameSuggestion(String searchTerm) {
		CheckDomainNameAvailabilityReq checkDomainNameAvailabilityReq = new CheckDomainNameAvailabilityReq();
		String[] suggestedTLDs = {".in",".org",".io",".net",".ai",".store",".info",".online"};
//		String[] suggestedDomainNamePrefix = {"",".in",".org",".io",".net",".ai",".store"};
//		String[] suggestedDomainNameSuffix = {"info",".tech",".og",".io",".net",".ai",".store"};
		
		String[] rootDomain = searchTerm.split("\\.");
		String domain = rootDomain[0];
		String defaultTld = ".com";
		if(rootDomain.length == 2) {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm);
			defaultTld = rootDomain[1];
		}else {
			checkDomainNameAvailabilityReq.getDomainNames().add(searchTerm+defaultTld);
		}
		System.out.println(defaultTld);
		for(int i = 0; i < suggestedTLDs.length; i++) {
			if(! (suggestedTLDs[i]).equals("."+defaultTld)) {
				checkDomainNameAvailabilityReq.getDomainNames().add(domain + suggestedTLDs[i]);
			}
			
		}
		return gson.toJson(checkDomainNameAvailabilityReq);
	}
	
	private HttpHeaders getDomainHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
        return headers;

	}
	
	
	
	
	
	
	
}

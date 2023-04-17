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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityReq;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityRes;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.dto.domain.DomainRegisterReq;
import com.haarmk.dto.domain.DomainRegisterRes;
import com.haarmk.dto.domain.DomainSearchReq;
import com.haarmk.exception.DomainException;

@RestController
@RequestMapping( value = "/domains")
public class DomainController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment environment;
	@Autowired private Gson gson;
	@Autowired private ObjectMapper objectMapper;
//	String baseUrl = "https://api.name.com";
	String baseUrl = "https://api.dev.name.com";
	
	DomainSearchReq domainSearchReq = new DomainSearchReq(1000, null, null);
	
	private HttpHeaders getDomainHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
        return headers;
	}
	
	@GetMapping(value= "/search")
	public ResponseEntity<CheckDomainNameAvailabilityRes> searchDamain(@RequestParam String searchTerm) {
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
	
	

	@PostMapping(value= "/register-domain")
	public ResponseEntity<DomainRegisterRes> registerDomain(@RequestBody DomainRegisterReq domainRegisterReq) {		domainRegisterReq.getDomain().setAutorenewEnabled(false);
		domainRegisterReq.getDomain().setLocked(false);
		String url = baseUrl+"/v4/domains";
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(domainRegisterReq), getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        DomainRegisterRes registerRes = gson.fromJson(responseEntity.getBody(), DomainRegisterRes.class);
        return new ResponseEntity<DomainRegisterRes>(registerRes, HttpStatus.CREATED);
	}
	
	@GetMapping(value= "/hello")
	public ResponseEntity<JsonNode> helloDomain() throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/hello";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res = objectMapper.readTree(responseEntity.getBody());
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	@GetMapping(value= "/")
	public ResponseEntity<JsonNode> getDomains() throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/domains";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
        JsonNode res = objectMapper.readTree(responseEntity.getBody());
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
//	========================================		DNS		=====================================================
	
	
	
	
	@GetMapping(value= "/dns/{recordId}")
	public ResponseEntity<JsonNode> getDnsRecordById(@RequestParam String domainName
			,@PathVariable Integer recordId
			) throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
        JsonNode res = objectMapper.readTree(responseEntity.getBody());
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	@GetMapping(value= "/dns")
	public ResponseEntity<JsonNode> getDnsRecord(@RequestParam String domainName
			
			) throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/domains/"+domainName+"/records";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res = objectMapper.readTree(responseEntity.getBody());
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value= "/dns")
	public ResponseEntity<DnsCreateRecordResDto> createDnsRecord(@RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto,
			@RequestParam String domainName
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records";
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(dnsCreateRecordReqDto), getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        DnsCreateRecordResDto registerRes = gson.fromJson(responseEntity.getBody(), DnsCreateRecordResDto.class);
        return new ResponseEntity<DnsCreateRecordResDto>(registerRes, HttpStatus.CREATED);
	}
	
	

	@PutMapping(value= "/dns")
	public ResponseEntity<DnsCreateRecordResDto> updateDnsRecord(
			@RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto,
			@RequestParam String domainName,
			@RequestParam Integer recordId
			
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(dnsCreateRecordReqDto), getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        DnsCreateRecordResDto registerRes = gson.fromJson(responseEntity.getBody(), DnsCreateRecordResDto.class);
        return new ResponseEntity<DnsCreateRecordResDto>(registerRes, HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/dns")
	public ResponseEntity<DnsCreateRecordResDto> deleteDnsRecord(
			@RequestParam String domainName,
			@RequestParam Integer recordId
			
			) {
		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<Void> responseEntity = this.restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,Void.class);
        return new ResponseEntity<DnsCreateRecordResDto>(HttpStatus.NO_CONTENT);
	}
	
//	==========================================		orders		=====================================================================
	
	
	
	@GetMapping(value= "/orders/{orderId}")
	public ResponseEntity<JsonNode> getOrderById(
			@PathVariable Integer orderId
			) throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/orders/"+orderId;
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res = objectMapper.readTree(responseEntity.getBody());
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	@GetMapping(value= "/orders")
	public ResponseEntity<JsonNode> getOrder() throws JsonMappingException, JsonProcessingException {		
		String url = baseUrl+"/v4/orders";
        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonNode res = objectMapper.readTree(responseEntity.getBody());

        return new ResponseEntity<JsonNode>(res , HttpStatus.OK);
	}
	
	
	
}


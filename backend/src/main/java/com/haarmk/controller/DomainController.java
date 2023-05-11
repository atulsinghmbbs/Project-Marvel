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
import com.haarmk.dto.domain.DomainTestReq;
import com.haarmk.exception.DomainException;
import com.haarmk.exception.HaarmkException;
import com.haarmk.service.interfaces.NameService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping( value = "/domains")
public class DomainController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired NameService nameService;
	@Autowired
	private Environment environment;
	@Autowired private Gson gson;
	@Autowired private ObjectMapper objectMapper;
//	String baseUrl = "https://api.name.com";
	String baseUrl = "https://api.dev.name.com";
	
//	DomainSearchReq domainSearchReq = new DomainSearchReq(1000, null, null);
//	
//	private HttpHeaders getDomainHeader() {
//		HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth(environment.getProperty("NAME_USERNAME"), environment.getProperty("NAME_TOKEN"));
//        return headers;
//	}
	
	@GetMapping(value= "/search")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CheckDomainNameAvailabilityRes> searchDamain(@RequestParam String searchTerm) {
		
		CheckDomainNameAvailabilityRes checkDomainNameAvailabilityRes = nameService.searchDamain(searchTerm);
		return new ResponseEntity<CheckDomainNameAvailabilityRes>(checkDomainNameAvailabilityRes, HttpStatus.OK);
	}
	
	


//	public DomainCheckData checkAvailability(String domainName) {
//		String urlCheckAvailability = baseUrl+"/v4/domains:checkAvailability";
//		CheckDomainNameAvailabilityReq checkDomainNameAvailabilityReq = new CheckDomainNameAvailabilityReq();
//		checkDomainNameAvailabilityReq.getDomainNames().add(domainName);
//        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(checkDomainNameAvailabilityReq), getDomainHeader());        
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(urlCheckAvailability, HttpMethod.POST, requestEntity, String.class);
//        CheckDomainNameAvailabilityRes availabilityRes = gson.fromJson(responseEntity.getBody(), CheckDomainNameAvailabilityRes.class);
//        return availabilityRes.getSuggestions().get(0);
//    }
	
//	@PostMapping(value= "/register-domain")
//	public ResponseEntity<DomainRegisterRes> registerDomain(@RequestBody DomainRegisterReq domainRegisterReq) {
//		
//		DomainRegisterRes domainRegisterRes = nameService.registerDomain(domainRegisterReq);
//		
//		return new ResponseEntity<DomainRegisterRes>(domainRegisterRes, HttpStatus.CREATED);
//	}
//	
//	
//	@PostMapping(value= "/register-domain-test")
//	public ResponseEntity<String> registerDomainTest(@RequestBody DomainRegisterReq domainRegisterReq) throws JsonProcessingException {
//		System.out.println(domainRegisterReq);
//		String url = "https://api.dev.name.com/v4/domains";
//		System.out.println("hello "+gson.toJson(domainRegisterReq));
//		HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(domainRegisterReq), getDomainHeader());        
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//		
//		return new ResponseEntity<String>(responseEntity.getBody() ,HttpStatus.CREATED);
//	}
//	
//	@GetMapping(value= "/hello")
//	public ResponseEntity<JsonNode> helloDomain() throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/hello";
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
//	
//	
//	@GetMapping(value= "/")
//	public ResponseEntity<JsonNode> getDomains() throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/domains";
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
	
	
//	========================================		DNS		=====================================================
	
	
	
//	
//	@GetMapping(value= "/dns/{recordId}")
//	public ResponseEntity<JsonNode> getDnsRecordById(@RequestParam String domainName
//			,@PathVariable Integer recordId
//			) throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);;
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
//	
//	
//	@GetMapping(value= "/dns")
//	public ResponseEntity<JsonNode> getDnsRecord(@RequestParam String domainName
//			
//			) throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/domains/"+domainName+"/records";
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
//	
//	
//	
//	
//	
//	@PostMapping(value= "/dns")
//	public ResponseEntity<DnsCreateRecordResDto> createDnsRecord(@RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto,
//			@RequestParam String domainName
//			) {
//		
//        
//        DnsCreateRecordResDto registerRes = nameService.createDnsRecord(dnsCreateRecordReqDto, domainName);
//        return new ResponseEntity<DnsCreateRecordResDto>(registerRes, HttpStatus.CREATED);
//	}
//	
//	
//
//	@PutMapping(value= "/dns")
//	public ResponseEntity<DnsCreateRecordResDto> updateDnsRecord(
//			@RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto,
//			@RequestParam String domainName,
//			@RequestParam Integer recordId
//			
//			) {
//		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
//        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(dnsCreateRecordReqDto), getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
//        DnsCreateRecordResDto registerRes = gson.fromJson(responseEntity.getBody(), DnsCreateRecordResDto.class);
//        return new ResponseEntity<DnsCreateRecordResDto>(registerRes, HttpStatus.OK);
//	}
//	
//	@DeleteMapping(value= "/dns")
//	public ResponseEntity<DnsCreateRecordResDto> deleteDnsRecord(
//			@RequestParam String domainName,
//			@RequestParam Integer recordId
//			
//			) {
//		String url = baseUrl+"/v4/domains/"+domainName+"/records/"+recordId;
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<Void> responseEntity = this.restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,Void.class);
//        return new ResponseEntity<DnsCreateRecordResDto>(HttpStatus.NO_CONTENT);
//	}
//	
//	==========================================		orders		=====================================================================
	
	
	
//	@GetMapping(value= "/orders/{orderId}")
//	public ResponseEntity<JsonNode> getOrderById(
//			@PathVariable Integer orderId
//			) throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/orders/"+orderId;
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
//	}
//	
//	
//	@GetMapping(value= "/orders")
//	public ResponseEntity<JsonNode> getOrder() throws JsonMappingException, JsonProcessingException {		
//		String url = baseUrl+"/v4/orders";
//        HttpEntity<String> requestEntity = new HttpEntity<>(getDomainHeader());
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        JsonNode res = objectMapper.readTree(responseEntity.getBody());
//
//        return new ResponseEntity<JsonNode>(res , HttpStatus.OK);
//	}
	
	
	
}


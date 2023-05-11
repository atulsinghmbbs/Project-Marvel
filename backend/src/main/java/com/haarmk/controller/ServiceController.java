/**
 * 
 */
package com.haarmk.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.dto.RazorpaySuccessReq;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.model.Services;
import com.haarmk.service.interfaces.ServiceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author indicate0
 *
 */

@RestController
@RequestMapping(value = "/services")
@SecurityRequirement(name = "bearer-key")
public class ServiceController {
	
	@Autowired ServiceService serviceService;
	
	@PostMapping(value = "/activate")
	public ResponseEntity<List<Services>> addService(@RequestBody RazorpaySuccessReq razorpaySuccessReq) {
		
		List<Services> services = serviceService.addService(razorpaySuccessReq);
		return new ResponseEntity<List<Services>>(services,HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public List<Services> getAllServices() {
		return serviceService.getAllServices();
	}
	
	@GetMapping(value = "/{id}")
	public Services getAllServicesById(@PathVariable Long id) {
		return serviceService.getServiceById(id);
	}
	
	@GetMapping(value = "/find-by-categoryName")
	public ResponseEntity<Set<Services>> getAllServicesByCategory(@RequestParam String categoryName) {
		Set<Services> services =  serviceService.getServiceByCategoryName(categoryName);
		return new ResponseEntity<Set<Services>>(services,HttpStatus.OK);
	}
	

	
	
	
//=============================================================== DND ==================================================================================
	
	@PostMapping(value = "/domains/dns")
	public ResponseEntity<DnsCreateRecordResDto> addDnsRecord(@RequestParam Long serviceId, @RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto) {
		return new ResponseEntity<DnsCreateRecordResDto>( serviceService.addDnsRecord(serviceId, dnsCreateRecordReqDto),HttpStatus.CREATED);
	}


	
	
	@GetMapping(value= "/domains/dns")
	public ResponseEntity<JsonNode> getDnsRecord(@RequestParam Long serviceId) {		
		
        JsonNode res = serviceService.getDnsRecord(serviceId);
        return new ResponseEntity<JsonNode>(res, HttpStatus.OK);
	}
	
	
	

	@PutMapping(value= "/domains/dns")
	public ResponseEntity<DnsCreateRecordResDto> updateDnsRecord(@RequestBody DnsCreateRecordReqDto dnsCreateRecordReqDto, 
			@RequestParam Long serviceId,
			@RequestParam Integer recordId
			
			) {
		
        DnsCreateRecordResDto registerRes = serviceService.updateDnsRecord(dnsCreateRecordReqDto, serviceId, recordId);
        return new ResponseEntity<DnsCreateRecordResDto>(registerRes, HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/domains/dns")
	public ResponseEntity<Void> deleteDnsRecord(@RequestParam Long serviceId, @RequestParam Integer recordId) {
		
        serviceService.deleteDnsRecord(serviceId, recordId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
//=============================================================== DND END ==================================================================================
	
	
	
	
	
	
	
}

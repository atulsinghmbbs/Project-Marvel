/**
 * 
 */
package com.haarmk.service.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.dto.RazorpaySuccessReq;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.model.Orders;
import com.haarmk.model.Product;
import com.haarmk.model.Services;

/**
 * @author indicate0
 *
 */
public interface ServiceService {

	List<Services> addService(RazorpaySuccessReq razorpaySuccessReq);
	List<Services> getAllServices();
	Services getServiceById(Long id);
	Services updateService(Services service);
	Set<Services> getServiceByCategoryName(String categoryName);
	
//	================================================DNS==========================================================
	DnsCreateRecordResDto addDnsRecord(Long serviceId, DnsCreateRecordReqDto dnsCreateRecordReqDto);
//	JsonNode getDnsRecordById(Long serviceId ,Integer recordId );
	JsonNode getDnsRecord(Long serviceId);
	DnsCreateRecordResDto updateDnsRecord( DnsCreateRecordReqDto dnsCreateRecordReqDto, Long serviceId, Integer recordId);
	void deleteDnsRecord(Long serviceId, Integer recordId);
//	================================================DNS==========================================================
	
	
	

}

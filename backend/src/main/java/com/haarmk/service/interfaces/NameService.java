/**
 * 
 */
package com.haarmk.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.dto.domain.CheckDomainNameAvailabilityRes;
import com.haarmk.dto.domain.DnsCreateRecordReqDto;
import com.haarmk.dto.domain.DnsCreateRecordResDto;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.dto.domain.DomainRegisterReq;
import com.haarmk.dto.domain.DomainRegisterRes;

/**
 * @author indicate0
 *
 */
public interface NameService {
	
	/**
	 * documentation is available at https://www.name.com/api-docs
	 * */
	CheckDomainNameAvailabilityRes searchDamain(String searchTerm);
	DomainRegisterRes registerDomain(DomainRegisterReq domainRegisterReq);
	JsonNode helloDomain();
	JsonNode getDomains();
	JsonNode getDnsRecordById(String domainName ,Integer recordId);
	JsonNode getDnsRecord(String domainName);
	DnsCreateRecordResDto createDnsRecord(DnsCreateRecordReqDto dnsCreateRecordReqDto, String domainName); 
	DnsCreateRecordResDto updateDnsRecord(DnsCreateRecordReqDto dnsCreateRecordReqDto, String domainName,Integer recordId);
	void deleteDnsRecord(String domainName,Integer recordId);
	JsonNode getOrderById(Integer orderId);
	JsonNode getOrder();
	JsonNode getPricingForDomain(String domainName, Integer years);
	DomainCheckData checkAvailability(String domainName);
	
}

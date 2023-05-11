package com.haarmk.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.haarmk.exception.HaarmkException;
import com.haarmk.service.interfaces.CurrencyService;

@Service
public class CurrencyServiceImpl  implements CurrencyService{
	
	@Autowired private RestTemplate restTemplate;
	
	@Autowired private ObjectMapper objectMapper;
	@Value("${fcsapi_token}")
	private String token;
	private double domainMargin = 20D;
	
	private Double inrPrice = null;
	private OffsetDateTime inrPriceSetAt = null;
	private String baseUrl = "https://fcsapi.com/api-v3/forex/latest";
	
	public Double getInrPrice() {
		
		OffsetDateTime currenTime = OffsetDateTime.now();
		if(inrPrice == null || inrPriceSetAt == null || inrPriceSetAt.plusMinutes(100).isBefore(currenTime)) {
			
			String url = baseUrl+"?access_key="+token+"&id=25";

	        String responseEntity = this.restTemplate.getForObject(url,  String.class);
	        try {
				JsonNode res = objectMapper.readTree(responseEntity);
				System.out.println(res.toString());
				JsonNode node = res.get("response").get(0);
				inrPrice = node.get("h").asDouble();
				inrPriceSetAt = OffsetDateTime.now();
			} catch (JsonProcessingException e) {
				throw new HaarmkException("Somthing went wrong while getting the inr(Rupee) price");
			}
			
		}
		return inrPrice;
//			return 81D;
	}

	@Override
	public Double roundToTwoDecimalPace(Double amount) {
		
		return (double)(Math.round(amount * 100) /100);
	}

	@Override
	public Double usdToInr(Double amount) {
		
		Double inr = amount * getInrPrice();
		Double roundedInr = roundToTwoDecimalPace(inr);
		return roundedInr;
	}
	@Override
	public Double getDomainMargin() {
		
		return domainMargin;
	}
}

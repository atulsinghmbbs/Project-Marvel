package com.haarmk.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@lombok.Data
class Data{
	List<String> domainNames = new ArrayList<>();
}
public class RestTemplateExample {
    public static void main(String[] args) {
    	Gson gson = new GsonBuilder().create();
    	Data data = new Data();
    	data.getDomainNames().add("haarmkinfo.com");
        String url = "https://api.dev.name.com/v4/domains:checkAvailability";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("ankitpatel.akp4-test", "cc3d051c5a94b961d7f388143510b8027fd63cfd");
//        String requestJson = "{\"domainNames\":[\"haarmkinfo.com\"]}";
        String requestJson = gson.toJson(data);
        System.out.println(requestJson);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        String reponse = gson.fromJson(responseEntity.getBody() , String.class);
        System.out.println(reponse);
    }
}

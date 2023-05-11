package com.haarmk.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haarmk.dto.LoginDto;
import com.haarmk.model.MySqlJsonTest;
import com.haarmk.repository.MySqlJsontestRepo;
import com.nimbusds.jose.crypto.impl.RSA1_5;


@RestController
@RequestMapping(value = "/json")
public class MySqlJsonController {
//	@Autowired MySqlJsontestRepo mySqlJsontestRepo;
//	@Autowired ObjectMapper objectMapper;
//	
//	@PostMapping(value = "/")
//	private MySqlJsonTest addJson(@RequestBody MySqlJsonTest mySqlJsonTest) {
//		System.out.println(mySqlJsonTest);
//		return mySqlJsontestRepo.save(mySqlJsonTest);
//		
//	}
//	
//	@GetMapping(value = "/")
//	private List<MySqlJsonTest> getAllJson() {
//		List<MySqlJsonTest> result = mySqlJsontestRepo.findAll();
//		System.out.println(result);
//		return result;
//		
//	}
//	@GetMapping(value = "/{id}")
//	private MySqlJsonTest getAllJson(@PathVariable Integer id) {
//		MySqlJsonTest result = mySqlJsontestRepo.findById(id).get();
////		System.out.println(result);
////		JsonNode jsonNode = objectMapper.valueToTree(result.getJson());
////		System.out.println("from jsonNode: "+jsonNode.get("details").get("address"));
////		Map map = (Map) result.getJson();
////		System.out.println( ((Map) (map.get("details"))).get("address"));
////		LinkedHashMap<String, Object> maps = (LinkedHashMap) result.getJson();
//		
//		return result;
//		
//	}
}

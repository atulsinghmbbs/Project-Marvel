package com.haarmk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/static")
public class StaticController {

	@GetMapping("/images")
	public String imageReader() {
		System.out.println("token in static controller");
		return "images/img.jpg";
	}
	
	@GetMapping("/reset-password")
	public String resetPassword(@RequestParam String token) {
		System.out.println("token in static controller" +token);
		return "public/index.html";
	}

}

package com.haarmk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping(value = "/statics")
@Controller
//@RestController
public class StaticController {

	@RequestMapping("/hii")
	public String resetPassword() {
//		@RequestParam String token
//		System.out.println("token in static file: "+ token);
		return "img.jpg";
	}

}

package com.haarmk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@RestController
@RequestMapping("/haarmk")
public class HomeController {
	
	
    @GetMapping(value = "/")
    public String root(){
        return  "welcome";
    }
    @SecurityRequirement(name = "bearer-key")
    @GetMapping(value = "/home")
    public String home(){
        return  "welcome";
    }
    @SecurityRequirement(name = "bearer-key")
    @GetMapping(value = "/secure")
    public String secure(){
        return  "welcome";
    }
    
   
}

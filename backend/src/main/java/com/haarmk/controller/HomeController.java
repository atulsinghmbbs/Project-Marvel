package com.haarmk.controller;

import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/home")
public class HomeController {
//	@Autowired private CookieUtils cookieUtils;
	
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
    public String secure(Principal principal){
    	System.out.println(principal.getName());
        return  "welcome";
    }
    
    @GetMapping("/set-cookie")
    public ResponseEntity<String> setCookie() {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("abc", "hello-world")
        .maxAge(20000000)
//        .httpOnly(true)
        .sameSite("None")
        .secure(false)
        .path("/")
        .build();
        
        System.out.println(cookie.toString());
        headers.add("Set-Cookie", cookie.toString());
//        headers.add("Set-Cookie", "cookieName=cookieValue; Path=/; HttpOnly");
//        headers.add("Set-Cookie", "username=ankit patel; Path=/; HttpOnly; Secure");

        return ResponseEntity.ok()
                .headers(headers)
                .body("Cookie set successfully");
    }
    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie(@CookieValue(required = false) String username) {
//    	HttpHeaders headers = new HttpHeaders();
//        ResponseCookie cookie = ResponseCookie.from("abc", "hello-world")
//        .maxAge(20000000)
//        .httpOnly(true)
//        .secure(false)
//        .path("/")
//        .build();
    	
    	
//        headers.add("Set-Cookie", cookie.toString());
//        headers.add("Set-Cookie", "cookieName=cookieValue; Path=/; HttpOnly");
//    	headers.add("Set-Cookie", "username=ankit patel; Path=/; HttpOnly; Secure");
    	
    	return ResponseEntity.ok()
    			
    			.body("cookie: "+username);
    }
    
   
}

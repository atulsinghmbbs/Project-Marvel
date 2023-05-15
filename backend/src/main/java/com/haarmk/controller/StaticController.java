package com.haarmk.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/static")
public class StaticController {
    @Autowired private HttpServletRequest request;
    
//    
//	@GetMapping("/images")
//	public String imageReader() {
//		System.out.println("token in static controller");
//		return "images/img.jpg";
//	}
	
//	@GetMapping("/reset-password")
//	public String resetPassword(@RequestParam String token) {
//		System.out.println("token in static controller" +token);
//		return "public/index.html";
//	}
//	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , value = "/upload-pic")
//	private String uploadProfilePicture(@RequestPart MultipartFile document) throws IOException {
//		//System.out.println(name);
//		String filePath = request.getServletContext().getRealPath("/"); 
////		System.out.println(filePath);
//		document.transferTo(new File(filePath));
////		System.out.println(document.getOriginalFilename());
////		System.out.println(document.getSize());
////		File file = new File(document.getOriginalFilename());
////		Path path = Paths.get("images/"+document.getOriginalFilename());
////		document.transferTo(file);
//		
////		document.transferTo(path);
////		document.getBytes();
////		try {
////			System.out.println(document.getInputStream().toString());
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return "done";	
//	}
}

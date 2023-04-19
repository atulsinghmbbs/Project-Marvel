/**
 * 
 */
package com.haarmk.controller;


import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author HMK05
 *
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , value = "/uploadpic")
	private String uploadProfilePicture(@RequestPart MultipartFile document) {
		//System.out.println(name);
		System.out.println(document.getName());
		System.out.println(document.getSize());
		try {
			System.out.println(document.getInputStream().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "done";	
	}
	

}

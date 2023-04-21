/**
 * 
 */
package com.haarmk.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haarmk.dto.OperationStatusDto;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.UserService;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @author HMK05
 *
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired UserService userService;
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , value = "/upload-pic")
	private ResponseEntity<OperationStatusDto> uploadProfilePicture(@RequestPart MultipartFile document, Principal principal) throws IOException {
		User currentUser =  userService.getUserByUsername(principal.getName());
		 try {
			 String[] documentType = document.getContentType().split("/");
			 System.out.println(principal.getName());
			 System.out.println();
		        // Get the filename of the uploaded file
		        String filename = "IMG"+principal.getName()+"."+documentType[1];
		        System.out.println(filename);
		        // Get the output stream for writing the file
		        OutputStream outputStream = new FileOutputStream(new File("src/main/resources/static/images/" + filename));
		        
		        // Write the file to the output stream
		        outputStream.write(document.getBytes());
		        
		        // Close the output stream
		        outputStream.close();
		        
		        // Return a success message
		        currentUser.setImage(filename);
		        userService.updateUser(currentUser);
		        return new ResponseEntity<OperationStatusDto>( new OperationStatusDto("upload image","File uploaded successfully"),HttpStatus.CREATED);
		    } catch (IOException e) {
		        // Return an error message if there was an error writing the file
		        throw new RuntimeException("Error uploading file");
		    }	
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<User> getUser(Principal principal) {
		return new ResponseEntity<User>(userService.getUserByUsername(principal.getName()), HttpStatus.OK);
	}
	
	
}

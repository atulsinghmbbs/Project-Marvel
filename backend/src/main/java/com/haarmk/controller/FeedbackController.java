package com.haarmk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.FeedbackResDto;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Feedback;
import com.haarmk.repository.FeedbackRepo;
import com.haarmk.service.interfaces.FeedbackService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
	
	@Autowired
	private FeedbackService fedservice;
	@Autowired FeedbackRepo feedbackRepo;

	
	
	@PostMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Feedback> registerfeedback(@Valid  @RequestBody Feedback feedback) throws FeedbackException{
		Feedback addfed = fedservice.Registerfeedback(feedback);
		
		return new ResponseEntity<Feedback>(addfed , HttpStatus.CREATED);
	}
	


	
	@GetMapping("/{id}")
	public ResponseEntity<FeedbackResDto> GetFeedbackById(@PathVariable("id") Integer fid) throws FeedbackException{
		
		FeedbackResDto getfed = fedservice.GetFeedbackbyId(fid);
		
		return new ResponseEntity<FeedbackResDto>(getfed,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<FeedbackResDto>>GetAllCustomer() throws FeedbackException{
		
		List<FeedbackResDto> getcus = fedservice.GetListofFeedback();
		return new ResponseEntity<List<FeedbackResDto>>(getcus , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Feedback>DeletefeedbackById(@PathVariable("id") Integer eid) throws FeedbackException{
		
		 Feedback deleteemp = fedservice.deletefeedback(eid);
		
		return new ResponseEntity<Feedback>(deleteemp , HttpStatus.OK);
	}
	
	
	
	@PutMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Feedback> updatefeedback(@RequestBody Feedback feedback) throws FeedbackException{
		
		Feedback updateemp = fedservice.updatefeedback(feedback);
		
		return new ResponseEntity<Feedback>(updateemp , HttpStatus.ACCEPTED);
	}
	
	

}

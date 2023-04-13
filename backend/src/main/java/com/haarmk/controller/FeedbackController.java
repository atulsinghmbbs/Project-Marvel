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

import com.haarmk.dto.FeedbackDto;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Feedback;
import com.haarmk.service.interfaces.FeedbackService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService fedservice;
	
	
	@PostMapping("/register")
	public ResponseEntity<Feedback> Registerfeedback(@Valid  @RequestBody Feedback feedback) throws FeedbackException{
		
		Feedback addfed = fedservice.Registerfeedback(feedback);
		
		return new ResponseEntity<Feedback>(addfed , HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/getfeedback/{id}")
	public ResponseEntity<Feedback> GetFeedbackById(@PathVariable("id") Integer fid) throws FeedbackException{
		
		Feedback getfed = fedservice.GetFeedbackbyId(fid);
		
		return new ResponseEntity<Feedback>(getfed , HttpStatus.OK);
	}
	
	@GetMapping("/feedback")
	public ResponseEntity<List<FeedbackDto>>GetAllCustomer() throws FeedbackException{
		
		List<FeedbackDto> getcus = fedservice.GetListofFeedback();
		
		return new ResponseEntity<List<FeedbackDto>>(getcus , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/Delfeedback/{id}")
	public ResponseEntity<Feedback>DeletefeedbackById(@PathVariable("id") Integer eid) throws FeedbackException{
		
		 Feedback deleteemp = fedservice.deletefeedback(eid);
		
		return new ResponseEntity<Feedback>(deleteemp , HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updatefeedback")
	public ResponseEntity<Feedback> updatefeedback(@RequestBody Feedback feedback) throws FeedbackException{
		
		Feedback updateemp = fedservice.updatefeedback(feedback);
		
		return new ResponseEntity<Feedback>(updateemp , HttpStatus.ACCEPTED);
	}
	
	

}

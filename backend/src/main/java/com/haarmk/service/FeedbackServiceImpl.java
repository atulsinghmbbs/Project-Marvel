package com.haarmk.service;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.haarmk.dto.FeedbackResDto;
import com.haarmk.exception.AuthenticationException;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Feedback;
import com.haarmk.model.User;
import com.haarmk.repository.FeedbackRepo;
import com.haarmk.service.interfaces.FeedbackService;
import com.haarmk.service.interfaces.UserService;

@Service
public class FeedbackServiceImpl implements  FeedbackService {
	
	 @Autowired
	 private FeedbackRepo feedbackRepo;
	 @Autowired UserService userService;
	 

	@Override
	public Feedback Registerfeedback(Feedback feedback) throws FeedbackException {
		
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(username);
        feedback.setCreatedAt(OffsetDateTime.now());
        feedback.setUser(currentUser);
        currentUser.getFeedbacks().add(feedback);
		Feedback Regfed = feedbackRepo.save(feedback);
		
		if(Regfed == null) {
			
			throw new FeedbackException("Enter valid information");
		}else {
		
		return Regfed;
		}
	}

	@Override
	public FeedbackResDto GetFeedbackbyId(Integer feedbackId) throws FeedbackException {
		
		 Optional<FeedbackResDto> getfed = feedbackRepo.getFeedbackById(feedbackId);
			
			if(getfed.isPresent()) {
				
				FeedbackResDto feedback = getfed.get();
				
				return feedback;
			}else {
				throw new FeedbackException("Enter feedback id is not valid: "+feedbackId);
			}
			
	}

	@Override
	public List<FeedbackResDto> GetListofFeedback() throws FeedbackException {
       List<FeedbackResDto> list = feedbackRepo.getAllfeedback();
		
		if(list.isEmpty()) {
			
			throw new FeedbackException("Not found");
		}
		else {
			return list;
		}
		
	}

	@Override
	public Feedback deletefeedback(Integer feedbackId) throws FeedbackException {
		
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(username);
		Optional<Feedback> foundFeedBack = feedbackRepo.findById(feedbackId);
		System.out.println("hello");
		if(foundFeedBack.isPresent()) {
			
			
		   if (!currentUser.getUsername().equals(foundFeedBack.get().getUser().getUsername())){
			   throw new AuthenticationException("unauthorized");
		   }
		   currentUser.getFeedbacks().remove(foundFeedBack.get());
		   feedbackRepo.deleteById(feedbackId);
		   return foundFeedBack.get();
		   
		 }else {
			 
			 throw new FeedbackException("Enter Valid id");
		 }
	}

	@Override
	public Feedback updatefeedback(Feedback feedback) throws FeedbackException {
		   String username = SecurityContextHolder.getContext().getAuthentication().getName();
	       User currentUser = userService.getUserByUsername(username);
	       Optional<Feedback> foundFeedBack = feedbackRepo.findById(feedback.getId());
			
			if(foundFeedBack.isPresent()) {
				if (!currentUser.getUsername().equals(foundFeedBack.get().getUser().getUsername())){
					   throw new AuthenticationException("unauthorized");
				   }
				feedback.setUser(currentUser);
				return feedbackRepo.save(feedback);
			}else {
				
				throw new FeedbackException("Not Found........");
			}
	}

}

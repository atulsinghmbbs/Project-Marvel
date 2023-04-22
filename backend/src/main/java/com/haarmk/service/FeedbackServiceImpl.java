package com.haarmk.service;


import java.time.LocalDateTime;
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
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUser(currentUser);
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
				throw new FeedbackException("Enter customer Id is not valid"+feedbackId);
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
		
		if(foundFeedBack.isPresent()) {
			
			
		   if (!currentUser.getUsername().equals(foundFeedBack.get().getUser().getUsername())){
			   throw new AuthenticationException("unauthorized");
		   }
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

			return feedbackRepo.save(feedback);
			}else {
				
				throw new FeedbackException("Not Found........");
			}
	}

}

package com.haarmk.feedbackservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.feedbackexception.FeedbackException;
import com.haarmk.feedbackmodel.Feedback;
import com.haarmk.feedbackmodel.feedbackdto;
import com.haarmk.feedbackrepository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements  FeedbackService {
	
	 @Autowired
	 private FeedbackRepository feedbackRepo;
	 

	@Override
	public Feedback Registerfeedback(Feedback feedback) throws FeedbackException {
        
		Feedback Regfed = feedbackRepo.save(feedback);
		
		if(Regfed == null) {
			
			throw new FeedbackException("Enter valid information");
		}else {
		
		return Regfed;
		}
	}

	@Override
	public Feedback GetFeedbackbyId(Integer feedbackId) throws FeedbackException {
		 Optional<Feedback> getfed = feedbackRepo.findById(feedbackId);
			
			if(getfed.isPresent()) {
				
				Feedback feedback = getfed.get();
				
				return feedback;
			}else {
				throw new FeedbackException("Enter customer Id is not valid"+feedbackId);
			}
			
	}

	@Override
	public List<feedbackdto> GetListofFeedback() throws FeedbackException {
       List<feedbackdto> list = feedbackRepo.GetAllfeedback();
		
		if(list.isEmpty()) {
			
			throw new FeedbackException("Not found");
		}
		else {
			return list;
		}
		
	}

	@Override
	public Feedback deletefeedback(Integer feedbackId) throws FeedbackException {
		
		
		Optional<Feedback> del = feedbackRepo.findById(feedbackId);
		
		if(del.isPresent()) {
			
			feedbackRepo.deleteById(feedbackId);
		   
		   return del.get();
		   
		 }else {
			 
			 throw new FeedbackException("Enter Valid id");
		 }
	}

	@Override
	public Feedback updatefeedback(Feedback feedback) throws FeedbackException {
		
	       Optional<Feedback> emp = feedbackRepo.findById(feedback.getFeedBackId());
			
			if(emp.isPresent()) {
				

			return feedbackRepo.save(feedback);
			}else {
				
				throw new FeedbackException("Not Found........");
			}
	}

}

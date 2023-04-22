package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.FeedbackDto;
import com.haarmk.dto.FeedbackResDto;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Feedback;

public interface FeedbackService {

	
	public Feedback Registerfeedback(Feedback feedback) throws FeedbackException;
	
	public FeedbackResDto GetFeedbackbyId(Integer feedbackId) throws FeedbackException;
	
	public List<FeedbackResDto> GetListofFeedback() throws FeedbackException;
	
	public Feedback deletefeedback(Integer feedbackId) throws FeedbackException;
	
	public Feedback updatefeedback(Feedback feedback) throws FeedbackException;
	
	
}

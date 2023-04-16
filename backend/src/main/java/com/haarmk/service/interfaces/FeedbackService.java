package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.FeedbackDto;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Feedback;

public interface FeedbackService {

	
	public Feedback Registerfeedback(Feedback feedback) throws FeedbackException;
	
	public Feedback GetFeedbackbyId(Integer feedbackId) throws FeedbackException;
	
	public List<FeedbackDto> GetListofFeedback() throws FeedbackException;
	
	public Feedback deletefeedback(Integer feedbackId) throws FeedbackException;
	
	public Feedback updatefeedback(Feedback feedback) throws FeedbackException;
	
	
}

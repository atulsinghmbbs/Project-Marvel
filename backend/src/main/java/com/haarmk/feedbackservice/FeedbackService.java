package com.haarmk.feedbackservice;

import java.util.List;

import com.haarmk.feedbackexception.FeedbackException;
import com.haarmk.feedbackmodel.Feedback;
import com.haarmk.feedbackmodel.feedbackdto;

public interface FeedbackService {

	
	public Feedback Registerfeedback(Feedback feedback) throws FeedbackException;
	
	public Feedback GetFeedbackbyId(Integer feedbackId) throws FeedbackException;
	
	public List<feedbackdto> GetListofFeedback() throws FeedbackException;
	
	public Feedback deletefeedback(Integer feedbackId) throws FeedbackException;
	
	public Feedback updatefeedback(Feedback feedback) throws FeedbackException;
	
	
}

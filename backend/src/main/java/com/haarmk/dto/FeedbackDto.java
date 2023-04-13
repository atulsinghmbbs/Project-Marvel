package com.haarmk.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

	private Integer feedBackId;
	
	private double serviceRating;
	
	private double overallRating;
	
	private LocalDate feedbackdate;
	
	private String firstname;
	
	private String Lastname;

	public FeedbackDto(Integer feedBackId, String firstname, String lastname) {
		super();
		this.feedBackId = feedBackId;
		this.firstname = firstname;
		Lastname = lastname;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}

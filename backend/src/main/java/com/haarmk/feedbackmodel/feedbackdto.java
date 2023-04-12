package com.haarmk.feedbackmodel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class feedbackdto {

	private Integer feedBackId;
	
	private double serviceRating;
	
	private double overallRating;
	
	private LocalDate feedbackdate;
	
	private String firstname;
	
	private String Lastname;

	public feedbackdto(Integer feedBackId, String firstname, String lastname) {
		super();
		this.feedBackId = feedBackId;
		this.firstname = firstname;
		Lastname = lastname;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}

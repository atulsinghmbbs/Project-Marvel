package com.haarmk.feedbackmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haarmk.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer feedBackId;
	
	@NotNull(message = "can't set as null")
	private Integer serviceRating;
	
	@NotNull(message = "can't set as null")
	private Integer overallRating;
	
	private String comments;

	private LocalDate feedBackDate;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE}) 
	private User user;
	 
}

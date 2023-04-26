package com.haarmk.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

	@Id	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "can't set as null")
	@Min(0)
	@Max(5)
	private Integer rating;
	
	private String comment;

	@CreationTimestamp
	private OffsetDateTime createdAt;
	
	@JsonIgnore 
	@ManyToOne
	private User user;
	 
}

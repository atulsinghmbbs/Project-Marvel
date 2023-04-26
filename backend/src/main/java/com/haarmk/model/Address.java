/**
 * 
 */
package com.haarmk.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * @author indicate0
 *
 */
@Data
@Entity
public class Address {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	@CreationTimestamp
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
	private OffsetDateTime createdAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Country country;
	@JsonIgnore
	@OneToOne
	private User user;
	
	
	
}

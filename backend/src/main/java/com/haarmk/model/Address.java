/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
	@Id	@GeneratedValue
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	@ManyToOne
	private Country country;
	@OneToOne
	private User user;
	
}

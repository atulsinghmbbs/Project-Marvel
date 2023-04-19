/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author indicate0
 *
 */
@Data
public class Address {
	@Id	@GeneratedValue
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;
	private Country country;
	private Boolean isPrimary;
}

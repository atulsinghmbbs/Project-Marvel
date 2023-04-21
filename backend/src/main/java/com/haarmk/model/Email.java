/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author indicate0
 *
 */

@Data
public class Email {
	@Id	@GeneratedValue
	private Integer id;
	@Column(unique = true, nullable = false)
	private String email;
	private Boolean isPrimary;
	private Boolean isVarified;
	@ManyToOne
	private User user;
}

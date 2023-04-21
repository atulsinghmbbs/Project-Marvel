/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit Patel
 *
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
	@Id @GeneratedValue
	private Integer id;
	@Column(unique = true, nullable = false)
	private String token;
}

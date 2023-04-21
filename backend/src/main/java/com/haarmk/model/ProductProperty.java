/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit Patel
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String propName;
	private String value;
	@ManyToOne
	private Product product;
	
}

/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author HMK05
 *
 */

@Entity
@Data
public class ServiseStaus {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String status;
}

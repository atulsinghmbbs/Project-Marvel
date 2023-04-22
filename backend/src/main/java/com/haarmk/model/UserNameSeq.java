/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Ankit Patel
 *
 */

@Data
@Entity
public class UserNameSeq {
	@Id
	private Long Id;
}

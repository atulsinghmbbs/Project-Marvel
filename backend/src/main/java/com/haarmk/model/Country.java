/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author indicate0
 *
 */

@Data
@Entity
public class Country {
	@Id
	private Integer code;
	private String name;
}

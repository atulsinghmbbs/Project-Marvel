/**
 * 
 */
package com.haarmk.model;

import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author indicate0
 *
 */

@Data
public class Country {
	@Id
	private Integer code;
	private String name;
}

/**
 * 
 */
package com.haarmk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HMK05
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationStatusDto {
	private String operation;
	private String status;
}

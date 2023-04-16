/**
 * 
 */
package com.haarmk.dto.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Ankit Patel
 *
 */

@Data
public class CheckDomainNameAvailabilityReq {
	List<String> domainNames = new ArrayList<>();
}

/**
 * 
 */
package com.haarmk.dto.domain;

import lombok.Data;

/**
 * @author Ankit Patel
 *
 */

@Data
public class DnsCreateRecordReqDto {
	private String host;
	private DnsRecordType type;
	private String answer;
	private Integer ttl;
	private Integer priority;	
}

/**
 * 
 */
package com.haarmk.dto;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit Patel
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResDto{
	private Integer id;
	private String comment;
	private Integer rating;
	private OffsetDateTime createdAt;
	private UserFeebackResDto user;
}

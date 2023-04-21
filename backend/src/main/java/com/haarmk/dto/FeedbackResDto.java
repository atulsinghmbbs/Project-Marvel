/**
 * 
 */
package com.haarmk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

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
public class FeedbackResDto {
	private Integer id;
	private String comment;
	private Integer rating;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime createdAt;
	private UserFeebackResDto user;
}

/**
 * 
 */
package com.haarmk.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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
public class ServiceStaus {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String status;
	@CreationTimestamp
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
	private OffsetDateTime createdAt;
}

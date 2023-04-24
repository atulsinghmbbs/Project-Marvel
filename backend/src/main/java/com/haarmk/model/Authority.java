package com.haarmk.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String authority;
	@CreationTimestamp
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
	private OffsetDateTime createdAt;
	
}

/**
 * 
 */
package com.haarmk.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haarmk.config.JpaConverterJson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * @author Ankit Patel
 *
 */

@Entity
@Data
public class Services {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Product product;
    
//    @JsonIgnore
    @ManyToOne
    private Orders orders;
    
//    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Status status;
    
	@Convert(converter = JpaConverterJson.class)
	@Column(columnDefinition = "json")
	private Object details;
	
    @JsonIgnore
    @ManyToOne
    private User user;
    
	@CreationTimestamp
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
	private OffsetDateTime createdAt;
}

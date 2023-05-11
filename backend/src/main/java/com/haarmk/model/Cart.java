package com.haarmk.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {	    
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private Double subTotal = 0.0;
		@CreationTimestamp
		@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false, updatable = false, insertable = false)
		private OffsetDateTime createdAt;
	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	    private Set<CartItem> items = new HashSet<>();
	    @OneToOne
	    @JsonIgnore
	    private User user;
	   
	    
}

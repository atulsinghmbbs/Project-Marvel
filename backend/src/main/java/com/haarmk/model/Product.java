package com.haarmk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name="Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer domainId;
	
	private String domainName;
	private String domainSLD;
	
	private String domainTLD;
	
	private int timeDuration;
	
	private double purchasePrice;
	

	
}

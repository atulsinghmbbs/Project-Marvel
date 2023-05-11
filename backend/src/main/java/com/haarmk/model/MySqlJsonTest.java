package com.haarmk.model;

import com.haarmk.config.JpaConverterJson;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MySqlJsonTest {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Convert(converter = JpaConverterJson.class)
	@Column(columnDefinition = "json")
	private Object json;
	

	
}

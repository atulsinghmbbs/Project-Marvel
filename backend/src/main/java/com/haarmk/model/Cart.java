package com.haarmk.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	    private List<Product> products = new ArrayList<>();
	    @OneToOne
	    private User user;
	   
	    
}

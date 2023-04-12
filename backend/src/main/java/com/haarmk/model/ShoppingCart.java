package com.haarmk.model;

import java.util.HashMap;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ShoppingCart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCart {
	   public static String ORDERED = "ordered";
	    public static String PENDING = "pending";

	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    public String status; //pending, ordered

//	    @OneToOne
//	    private User user;
//	    
//	    @OneToMany
//	    private List<Product> products;

	    private int  totalPrice = 0;
}

package com.haarmk.model;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCart {
	   public static String ORDERED = "ordered";
	    public static String PENDING = "pending";

	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer CartId;

	    public String status; //pending, ordere
	    
	    private int  totalPrice;
	    
	    @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(
	    	    name = "Product_Cart",
	    	    joinColumns = @JoinColumn(name="CartId", referencedColumnName ="CartId"),
	    	    inverseJoinColumns = @JoinColumn(name="domainId", referencedColumnName = "domainID")
	    )
	    private List<Product> products;
	    
}

package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haarmk.model.Product;
import com.haarmk.model.ShoppingCart;
@Repository
public interface CartRepo extends JpaRepository<ShoppingCart, Integer> {

	@Query("select sc.CartId from ShoppingCart sc Inner Join User u ON sc.CartId=u.carts where u.id=:userId")
	public Integer getCartIdByUserId(@Param("userId") int userId);
	
	
}

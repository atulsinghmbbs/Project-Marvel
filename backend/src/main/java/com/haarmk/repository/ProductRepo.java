package com.haarmk.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	Optional<Product> findByUniqueName(String uniqueName);
	@Query("select p from Product p where p.uniqueName=:uniqueName and p.category.name=:categoryName")
	Optional<Product> getPeoductByUniqueNameAndCategory(String uniqueName, String categoryName);
	@Query("select p from Product p where p.category.name=:categoryName")
	Set<Product> getPeoductByCategoryName(String categoryName);
}

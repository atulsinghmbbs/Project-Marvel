package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Product;
import com.haarmk.model.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer>{
	Optional<ProductCategory> findByName(String name);
}

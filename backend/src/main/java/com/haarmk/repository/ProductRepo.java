package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String name);
}

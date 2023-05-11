package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Product;
import com.haarmk.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	Optional<Category> findByName(String name);
}

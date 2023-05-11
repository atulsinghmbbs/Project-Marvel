package com.haarmk.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.model.Services;

public interface ServiceRepo extends JpaRepository<Services, Long>{
	@Query("from Services s where s.id=:id")
	Optional<Services> getServiceById(Long id);
	@Query("select s from Services s where s.product.category.name=:categoryName")
	Set<Services> getServiceByCategoryName(String categoryName);
}

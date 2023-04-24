package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.model.User;

public interface UserRepo extends JpaRepository<User,Long> {


    public Optional<User> findByEmail(String username);

	public Optional<User> findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT id FROM user ORDER BY ID DESC LIMIT 1")
	Optional<Long> getAutoIncrementValue();
    
    
}

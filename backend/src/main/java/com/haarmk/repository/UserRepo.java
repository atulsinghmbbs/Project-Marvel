package com.haarmk.repository;

import com.haarmk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    public Optional<User> findByEmail(String username);

	public Optional<User> findByUsername(String username);
    
    
}

package com.haarmk.repository;

import com.haarmk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {


    public Optional<User> findByUsername(String username);
}

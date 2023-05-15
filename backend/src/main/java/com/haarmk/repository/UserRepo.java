package com.haarmk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.dto.UserResDto;
import com.haarmk.model.Authority;
import com.haarmk.model.User;

public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String username);

	public Optional<User> findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT id FROM user ORDER BY ID DESC LIMIT 1")
	Optional<Long> getAutoIncrementValue();
	
	@Query( "SELECT new com.haarmk.dto.UserResDto(u.id, u.username, u.firstName, u.lastName, u.email, u.image, u.enabled) FROM User u")
	public List<UserResDto> getUsers(Pageable paging);
	
	@Query( "SELECT u.authorities FROM User u where u.username=:username")
	public List<Authority> getAuthoritiesOfUserByUserId(String username);
}
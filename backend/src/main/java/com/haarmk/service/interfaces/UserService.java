package com.haarmk.service.interfaces;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.haarmk.dto.UserResDto;
import com.haarmk.model.Authority;
import com.haarmk.model.User;

public interface UserService {
	
	User addUser(User user);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	User updateUser(User user);
	Long getAutoIncrementValue();
	User loadUserById(Long id);
	User registerUser(User user);
	List<UserResDto> getUsers(Integer pageNo, Integer pageSize, String sortBy);
	List<Authority> getAuthoritiesOfUserByUserId(String username);
	Void assignAuthority(String authority, String username);


}

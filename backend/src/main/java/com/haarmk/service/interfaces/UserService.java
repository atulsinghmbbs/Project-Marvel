package com.haarmk.service.interfaces;


import java.util.Optional;

import com.haarmk.model.User;

public interface UserService {
	
	User addUser(User user);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	User updateUser(User user);
	Long getAutoIncrementValue();

}

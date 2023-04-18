package com.haarmk.service.interfaces;


import com.haarmk.model.User;

public interface UserService {
	
	User addUser(User user);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
}

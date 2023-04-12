package com.haarmk.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.haarmk.model.User;

public interface UserService {
	
	User addUser(User user , MultipartFile file);
	
	User getUserByUsername(String username);
}

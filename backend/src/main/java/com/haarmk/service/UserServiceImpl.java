package com.haarmk.service;


import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.service.interfaces.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
   
    @Override
    public User getUserByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found with username"+username));
    }

	

	@Override
	public User addUser(User user) {
		Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
		
		if(!existingUser.isPresent()) {
			
			return userRepo.save(user);
			
		}else throw new IllegalArgumentException("User allready exist...");
	}
	
	@Override
	public User updateUser(User user) {
		Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
		
		if(existingUser.isPresent()) {
			
			return userRepo.save(user);
			
		}else throw new IllegalArgumentException("User does not exist...");
	}



	@Override
	public User getUserByEmail(String email) {
		 return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found with username"+email));
	}




}

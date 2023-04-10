package com.haarmk.service;

import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User getUserByUsername(String username){
        return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with username"+username));
    }
	@Override
	public User addUser(User user) {
		if(user != null) {
			return userRepo.save(user);
		}else throw new IllegalArgumentException("not a valid argument");
		
	}
}

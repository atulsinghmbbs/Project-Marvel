package com.haarmk.service;

import com.haarmk.feedbackmodel.Feedback;
import com.haarmk.model.ImageUtils;
import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.service.interfaces.UserService;


import java.io.IOException;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
   
    @Override
    public User getUserByUsername(String username){
        return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with username"+username));
    }

	
    
    @Override
	public User addUser(User user , MultipartFile file) {
    	if(user == null) {
    		throw new IllegalArgumentException("not a valid argument");

    	}
    	User saveduser = userRepo.save(user);
    	try {
			saveduser.setImagedata(ImageUtils.compressImage(file.getBytes()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	@Override
	public User addUser(User user) {
		Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
		
		if(!existingUser.isPresent()) {
			
			return userRepo.save(user);
			
		}else throw new IllegalArgumentException("User allready exist...");

		
			
		return saveduser;
	}
}

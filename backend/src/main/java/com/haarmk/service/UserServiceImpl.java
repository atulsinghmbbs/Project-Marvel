package com.haarmk.service;


import com.haarmk.exception.ResourceNotFoundException;
import com.haarmk.model.Authority;
import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.security.UserPrincipal;
import com.haarmk.service.interfaces.AuthorityService;
import com.haarmk.service.interfaces.UserService;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired private AuthorityService authorityService;
    @Autowired private PasswordEncoder passwordEncoder;
   
    @Override
    public User getUserByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found with username: "+username));
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
		 return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found with email: "+email));
	}


	@Override
	public Long getAutoIncrementValue() {
		
		Optional<Long> lastId =  userRepo.getAutoIncrementValue();
		if(lastId.isPresent()) {
			return lastId.get();
		}else {
			return 0L;
		}
	}

//
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("fjksdhfkjsdlkfsd");
		User user = getUserByEmail(username);
//		System.out.println("hello"+user);
		return user;
	}
	
	@Transactional
	@Override
    public User loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return user;
	}




	@Override
	public User registerUser(User user) {
		Authority authority = authorityService.getAuthorityByAuthorityName("ROLE_user");
		System.out.println(user.getEmail()+"dsjfkldsjfkdsjfsdjfksdjflkjsdfkjsd");
		Long next_id = getAutoIncrementValue();
		user.setUsername("HIPL"+(next_id+1));
		user.getAuthorities().add(authority);
		user.getCart().setUser(user);
		if(user.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		System.out.println(user.getAuthorities()+"authorities                  fksdl");
		User addedUser = addUser(user);
		System.out.println(user.getEmail()+"Ankit dsjfkldsjfkdsjfsdjfksdjflkjsdfkjsd");
		return addedUser;
	}


}

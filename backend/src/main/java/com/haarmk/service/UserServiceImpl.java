package com.haarmk.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haarmk.dto.UserResDto;
import com.haarmk.exception.HaarmkException;
import com.haarmk.exception.ResourceNotFoundException;
import com.haarmk.model.Authority;
import com.haarmk.model.User;
import com.haarmk.repository.UserRepo;
import com.haarmk.service.interfaces.AuthorityService;
import com.haarmk.service.interfaces.UserService;


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
    public List<UserResDto> getUsers(Integer pageNo, Integer pageSize, String sortBy){
    	Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    	
    	List<UserResDto> users = userRepo.getUsers(paging);
    	if(users.isEmpty()) throw new HaarmkException("No record found!");
    	
    	for(UserResDto urd: users) {
			urd.setAuthorities(getAuthoritiesOfUserByUserId(urd.getUsername()));
		}
    	return users;
    }
    
    @Override
    public List<Authority> getAuthoritiesOfUserByUserId(String username){
    	
    	List<Authority> authorities = userRepo.getAuthoritiesOfUserByUserId(username);
    	return authorities;
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
		User user = getUserByEmail(username);
		return user;
	}
	
//	@Transactional
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
		Long next_id = getAutoIncrementValue();
		user.setUsername("HIPL"+(next_id+1));
		user.getAuthorities().add(authority);
		user.getCart().setUser(user);
		if(user.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		User addedUser = addUser(user);
		return addedUser;
	}

	@Override
	public Void assignAuthority(String authority, String username) {
		Authority authority2 = authorityService.getAuthorityByAuthorityName(authority);
		User user = this.getUserByUsername(username);
		user.getAuthorities().add(authority2);
		this.updateUser(user);
		return null;
		
	}
    

}

package com.haarmk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.haarmk.dto.FeedbackDto;
import com.haarmk.exception.AddressException;
import com.haarmk.model.Address;
import com.haarmk.model.User;
import com.haarmk.repository.AddressRepo;
import com.haarmk.service.interfaces.AddressService;
import com.haarmk.service.interfaces.UserService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepo addressrepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Address RegisterAddress(Address address) throws AddressException {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		
//		User currentUser = userService.getUserByUsername(username);
//        if(address == null) {
//        	
//        }
        
//        currentUser.setAddresses(address);
//        address.setUser(currentUser);
        
        Address regaddress = addressrepo.save(address);
		
		if(regaddress == null) {
			
			throw new AddressException("Enter valid information");
		}else {
		
		    return regaddress;
		}
		
	}

	@Override
	public Address GetAddressbyId(Integer addressId) throws AddressException {
		 Optional<Address> getAddress =  addressrepo.findById(addressId);
			
			if(getAddress.isPresent()) {
				
				Address address = getAddress.get();
				
				return address;
			}else {
				throw new AddressException("Enter customer Id is not valid"+ addressId);
			}
	}

	

	@Override
	public Address deleteAddress(Integer addressid) throws AddressException {
        Optional<Address> deleteaddress =  addressrepo.findById(addressid);
		
		if(deleteaddress.isPresent()) {
			
		addressrepo.deleteById(addressid);
		   
		   return deleteaddress.get();
		   
		 }else {
			 
			 throw new AddressException("Enter Valid id");
		 }
		
	}

	@Override
	public Address updateAddress(Address address) throws AddressException {
		
		Optional<Address> updateaddress = addressrepo.findById(address.getId());
		
		if(updateaddress.isPresent()) {
		return addressrepo.save(address);
		
		}else {
			
			throw new AddressException("Not Found........");
		}
		
	}
	
	

}

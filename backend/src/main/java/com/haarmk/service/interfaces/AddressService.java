package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.FeedbackDto;
import com.haarmk.exception.AddressException;
import com.haarmk.model.Address;

public interface AddressService {
	
	
	
    public Address RegisterAddress(Address address)throws AddressException;
	
	public Address GetAddressbyId(Integer addressId) throws AddressException;
	
	public Address deleteAddress(Integer addressid) throws AddressException;
	
	public Address updateAddress(Address address) throws AddressException;
	
	
}

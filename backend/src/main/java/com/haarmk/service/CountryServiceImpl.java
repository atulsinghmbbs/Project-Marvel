package com.haarmk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.dto.FeedbackResDto;
import com.haarmk.exception.AddressException;
import com.haarmk.exception.CountryException;
import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Address;
import com.haarmk.model.Country;
import com.haarmk.repository.CountryRepo;
import com.haarmk.service.interfaces.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepo countryrepo;

	@Override
	public Country RegisterCountry(Country country) {
		
	   Country regcountry = countryrepo.save(country);
	   
	   if(regcountry == null) {
		   
		   throw new CountryException("Enter valid Country name");
	   }
	   else {
		   
		   return regcountry;
	   }
		
	
	}

	@Override
	public Country GetCountrybyId(Integer cid) {
		
		 Optional<Country> getcountry = countryrepo.findById(cid);
			
			if(getcountry.isPresent()) {
				
				Country country = getcountry.get();
				
				return country;
			}else {
				throw new CountryException("Enter customer Id is not valid"+ cid);
			}
	
}

	@Override
	public List<Country> GetListofCountry() {
      List<Country> list =   countryrepo.findAll();
		
		if(list.isEmpty()) {
			
			throw new CountryException("Not found");
		}
		else {
			return list;
		}
		
	}

	@Override
	public Country deletecountry(Integer cid) {
		
		  Optional<Country> deletecountry = countryrepo.findById(cid);
			
			if(deletecountry.isPresent()) {
				
				countryrepo.deleteById(cid);
			   
			   return deletecountry.get();
			   
			 }else {
				 
				 throw new AddressException("Enter Valid id");
			 }
	}

	@Override
	public Country updatecountry(Country country) {
		Optional<Country> updatecountry = countryrepo.findById(country.getCode());
		
		if(updatecountry.isPresent()) {
		return countryrepo.save(country);
		
		}else {
			
			throw new CountryException("Not Found........");
		}
	}

}

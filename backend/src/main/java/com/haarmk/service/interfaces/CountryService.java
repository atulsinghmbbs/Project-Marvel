package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.model.Country;

public interface CountryService {
	
    public Country RegisterCountry(Country country);
	
	public Country GetCountrybyId(Integer cid) ;
	
	public List<Country> GetListofCountry() ;
	
	public Country deletecountry(Integer cid) ;
	
	public Country updatecountry(Country country);
	

}

package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Country;

public interface CountryRepo extends JpaRepository<Country, Integer>  {

}

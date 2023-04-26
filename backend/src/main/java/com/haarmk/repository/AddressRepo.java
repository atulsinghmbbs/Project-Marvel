package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>  {

}

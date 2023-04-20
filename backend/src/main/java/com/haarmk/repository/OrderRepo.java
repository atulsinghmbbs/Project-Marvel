package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
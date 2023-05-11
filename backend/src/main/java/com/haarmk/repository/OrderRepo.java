package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {
	Optional<Orders> findByRazorpayOrderId(String razorpayOrderId);
}
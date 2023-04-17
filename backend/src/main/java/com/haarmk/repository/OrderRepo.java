package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
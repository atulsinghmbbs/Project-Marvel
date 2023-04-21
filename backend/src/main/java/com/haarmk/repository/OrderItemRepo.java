package com.haarmk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}

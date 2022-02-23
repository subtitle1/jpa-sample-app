package com.example.storeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}

package com.example.storeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}

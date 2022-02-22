package com.example.storeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}

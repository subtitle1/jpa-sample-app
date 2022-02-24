package com.example.storeweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
	Optional<CartItem> findByMemberIdAndBookId(long memberId, long bookId);
	List<CartItem> findAllByMemberId(long memberId);
	void deleteByIdAndMemberId(long cartItemId, long memberId);
}

package com.example.storeweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.CartItem;
import com.example.storeweb.entity.Member;
import com.example.storeweb.repository.BookRepository;
import com.example.storeweb.repository.CartItemRepository;
import com.example.storeweb.repository.MemberRepository;
import com.example.storeweb.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemService {

	private final BookRepository bookRepository;
	private final CartItemRepository cartItemRepository;
	private final MemberRepository memberRepository;
	
	public void insertCartItem(long bookId, int quantity) {
		long memberId = SecurityUtils.getMemberId();		
		Optional<CartItem> optional = cartItemRepository.findByMemberIdAndBookId(memberId, bookId);
		
		if (optional.isEmpty()) {
			Member member = memberRepository.getById(memberId);
			Book book = bookRepository.getById(bookId);
			
			CartItem cartItem = new CartItem();
			cartItem.setMember(member);
			cartItem.setBook(book);
			cartItem.setQuantity(quantity);
			
			cartItemRepository.save(cartItem);
			
		} else {
			CartItem cartItem = optional.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			
			cartItemRepository.save(cartItem);
		}
		
	}

	public List<CartItem> getCartItems() {
		long memberId = SecurityUtils.getMemberId();
		return cartItemRepository.findAllByMemberId(memberId);
	}
	
	public List<CartItem> getCartItems(List<Long> cartItemIds) {
		return cartItemRepository.findAllById(cartItemIds);
	}

	public void updateCartItem(long cartItemId, int quantity) {
		long memberId = SecurityUtils.getMemberId();
		CartItem cartItem = cartItemRepository.getById(cartItemId);
		if (cartItem.getMember().getId() == memberId) {
			cartItem.setQuantity(quantity);
			cartItemRepository.save(cartItem);
		}		
	}

	public void deleteCartItem(List<Long> cartItemIds) {
		long memberId = SecurityUtils.getMemberId();
		
		for (long cartItemId : cartItemIds) {
			cartItemRepository.deleteByIdAndMemberId(cartItemId, memberId);
		}
	}
	
}

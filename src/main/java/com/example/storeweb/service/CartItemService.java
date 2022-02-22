package com.example.storeweb.service;

import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.CartItem;
import com.example.storeweb.entity.Member;
import com.example.storeweb.repository.BookRepository;
import com.example.storeweb.repository.CartItemRepository;
import com.example.storeweb.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemService {

	private final BookRepository bookRepository;
	private final CartItemRepository cartItemRepository;
	private final MemberRepository memberRepository;
	
	public void insertCartItem(long memberId, long bookId, int quantity) {
		
		Member member = memberRepository.getById(memberId);
		Book book = bookRepository.getById(bookId);
		
		CartItem cartItem = new CartItem();
		cartItem.setMember(member);
		cartItem.setBook(book);
		cartItem.setQuantity(quantity);
		
		cartItemRepository.save(cartItem);
	}
	
}

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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemService {

	private final BookRepository bookRepository;
	private final CartItemRepository cartItemRepository;
	private final MemberRepository memberRepository;
	
	public void insertCartItem(long memberId, long bookId, int quantity) {
		
		Optional<CartItem> optional = cartItemRepository.findByMemberIdAndBookId(memberId, bookId);
		if (optional.isEmpty()) {
			Member member = memberRepository.getById(memberId);
			Book book = bookRepository.getById(bookId);
			
			CartItem cartItem = new CartItem(); // CartItem 객체는 비영속 상태이다.
			cartItem.setMember(member);
			cartItem.setBook(book);
			cartItem.setQuantity(quantity);
			
			cartItemRepository.save(cartItem); // 영속성 컨텍스트에 엔티티 객체를 새로 등록시켜서 영속 상태로 만든다.
											   // 트랜잭션 종료 시점에 insert 쿼리가 실행되고, 데이터베이스에 추가된다.
			
		} else {
			CartItem cartItem = optional.get(); // cartItem 객체는 영속 상태다.
			cartItem.setQuantity(cartItem.getQuantity() + quantity); // 영속상태에 있는 엔티티 객체의 필드값 변경
			
			cartItemRepository.save(cartItem); // cartItem은 이미 영속성 컨텍스트에 등록되어 있는 객체이기 때문에 트랜잭션 종료시점에 update 쿼리가 실행되고, 
											   // 변경 내용이 데이터베이스에 추가된다.
		}
		
	}

	public List<CartItem> getCartItems(long memberId) {
		return cartItemRepository.findAllByMemberId(memberId);
	}

	public void updateCartItem(long memberId, long cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.getById(cartItemId);
		
		if (cartItem.getMember().getId() == memberId) {
			cartItem.setQuantity(quantity);
			cartItemRepository.save(cartItem);
		}		
	}

	public void deleteCartItem(long memberId, List<Long> cartItemIds) {
		for (long cartItemId : cartItemIds) {
			CartItem cartItem = cartItemRepository.getById(cartItemId);
			
			if (cartItem.getMember().getId() == memberId) {
				cartItemRepository.delete(cartItem);
			}	
		}
		
	}
	
}

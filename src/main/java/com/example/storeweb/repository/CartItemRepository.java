package com.example.storeweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
	/**
	 * 지정된 회원아이디와 책아이디로 장바구니에 저장된 장바구니 아이템을 조회하는 기능
	 * select * from cart_items where member_id = ? and book_id = ?
	 * @param memberId
	 * @param bookId
	 * @return 책 정보, 해당하는 아이템이 존재하지 않을 경우 Optional 객체는 비어있다.
	 */
	// Optional => null point exception 방지 차원에서 도입된 기술
	// 무조건 Optional 객체가 반환이 되며, 객체는 비어 있을 수는 있지만 null일 수는 없다.
	Optional<CartItem> findByMemberIdAndBookId(Long memberId, Long bookId);
	
	/**
	 * 지정된 회원아이디의 회원이 장바구니에 담아둔 모든 아이템을 반환한다.
	 * select * from cart_items where member_id = ?
	 * @param memberId
	 * @return 장바구니 아이템 목록 정보
	 */
	List<CartItem> findAllByMemberId(Long memberId);
}

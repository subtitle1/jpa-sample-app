package com.example.storeweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @Column(name = "cart_item_quantity")
    private int quantity;

    // 비즈니스 로직의 요구사항에 맞는 getter 메소드를 추가로 정의해도 상관없다.
    public int getItemPrice() {
    	return book.getPrice()*quantity;
    }
    public int getItemSellPrice() {
    	return book.getDiscountPrice()*quantity;
    }
    public int getItemDiscountPrice() {
    	return getItemPrice() - getItemSellPrice();
    }
}

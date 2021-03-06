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
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "order_item_price")
    private int price;

    @Column(name = "order_item_quantity")
    private int quantity;
    
    public OrderItem(Book book, int price, int quantity) {
    	this.book = book;
    	this.price = price;
    	this.quantity = quantity;
    }
    
    public int getTotalBookPrice() {
    	return book.getPrice()*quantity;
    }
    public int getTotalOrderPrice() {
    	return book.getDiscountPrice()*quantity;
    }
    public int getTotalDiscountPrice() {
    	return getTotalBookPrice() - getTotalOrderPrice();
    }

}

package com.example.storeweb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.storeweb.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_title")
    private String title;

    @Column(name = "order_total_book_price")
    private int totalBookPrice;

    @Column(name = "order_total_order_price")
    private int totalOrderPrice;

    @Column(name = "order_total_discount_price")
    private int totalDiscountPrice;

    @Column(name = "order_use_point")
    private int usePoint;

    @Column(name = "order_total_payment_price")
    private int totalPaymentPrice;

    @Column(name = "order_deposit_point")
    private int depositPoint;

    @Column(name = "order_total_quantity")
    private int totalQuantity;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public String getStatus() {
        if (OrderStatus.ORDER == orderStatus) {
            return "주문완료";
        }
        return "취소";
    }

}

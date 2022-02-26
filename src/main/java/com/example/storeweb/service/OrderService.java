package com.example.storeweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storeweb.constant.OrderStatus;
import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.Member;
import com.example.storeweb.entity.Order;
import com.example.storeweb.entity.OrderItem;
import com.example.storeweb.repository.BookRepository;
import com.example.storeweb.repository.MemberRepository;
import com.example.storeweb.repository.OrderItemRepository;
import com.example.storeweb.repository.OrderRepository;
import com.example.storeweb.web.form.OrderForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	
	public long insertOrder(OrderForm orderForm, long memberId) {		
		Member member = memberRepository.getById(memberId);
		
		Order order = this.createOrder(orderForm, member);
		orderRepository.save(order);
		
		List<OrderItem> orderItems = this.getOrderItems(order, orderForm);
		orderItemRepository.saveAll(orderItems);	
		
		return order.getId();
	}

	public Order getOrderDetail(long orderId) {
		return orderRepository.getById(orderId);
	}

    public List<Order> getOrders(long memberId) {
		return orderRepository.findAllByMemberId(memberId);
    }

	private Order createOrder(OrderForm orderForm, Member member) {
		Book book = bookRepository.getById(orderForm.getIds()[0]);

		Order order = new Order();
		order.setMember(member);
		order.setTitle(orderForm.getTitle(book));
		order.setOrderStatus(OrderStatus.ORDER);
		order.setTotalBookPrice(orderForm.getTotalBookPrice());
		order.setTotalDiscountPrice(orderForm.getTotalDiscountPrice());
		order.setTotalOrderPrice(orderForm.getTotalOrderPrice());
		order.setUsePoint(orderForm.getUsePoint());
		order.setTotalPaymentPrice(orderForm.getTotalPaymentPrice() - orderForm.getUsePoint());
		order.setTotalQuantity(orderForm.getTotalQuantity());
		order.setDepositPoint(orderForm.getDepositPoint());

		return order;
	}

	private List<OrderItem> getOrderItems(Order order, OrderForm orderForm) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (int index = 0; index < orderForm.getIds().length; index++) {
			long bookId = orderForm.getIds()[index];
			int quantity = orderForm.getQuantities()[index];

			Book book = bookRepository.getById(bookId);
			book.setStock(book.getStock() - quantity);

			OrderItem orderItem = new OrderItem();
			orderItem.setBook(book);
			orderItem.setOrder(order);
			orderItem.setPrice(book.getDiscountPrice());
			orderItem.setQuantity(quantity);

			orderItems.add(orderItem);
		}
		return orderItems;
	}

}

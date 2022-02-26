package com.example.storeweb.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.Order;
import com.example.storeweb.entity.OrderItem;
import com.example.storeweb.exception.MemberDismatchException;
import com.example.storeweb.service.BookService;
import com.example.storeweb.service.OrderService;
import com.example.storeweb.util.SecurityUtils;
import com.example.storeweb.web.form.OrderForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

	private final BookService bookService;
	private final OrderService orderService;
	
	@GetMapping("/form")
	public String orderform(@RequestParam("id") int bookId, @RequestParam("quantity") int quantity, Model model) {
		log.info("바로 구매하기");
		log.info("주문할 book_id [" + bookId + "]");
		log.info("주문할 quantity [" + quantity + "]");
		
		Book book = bookService.getBookDetail(bookId);
		
		int totalBookPrice = book.getPrice()*quantity;
		int totalPaymentPrice = book.getDiscountPrice()*quantity;
		
		model.addAttribute("totalBookPrice", totalBookPrice);
		model.addAttribute("totalDiscountPrice", totalBookPrice - totalPaymentPrice);
		model.addAttribute("totalOrderPrice", totalPaymentPrice);
		model.addAttribute("totalPaymentPrice", totalPaymentPrice);
		
		OrderItem orderItem = new OrderItem(book, book.getDiscountPrice(), quantity);
		model.addAttribute("orderItems", List.of(orderItem));		
		
		return "/order/form";
	}
	
	@PostMapping("/insert")
	public String insert(OrderForm orderForm) {
		log.info("주문 정보 저장하기");
		log.info("주문정보 : " + orderForm);
		
		long orderId = orderService.insertOrder(orderForm, SecurityUtils.getMemberId());
		
		return "redirect:/order/completed?id=" + orderId;
	}
	
	@GetMapping("/completed")
	public String completed(@RequestParam("id") long orderId, Model model) {
		log.info("주문 완료 정보 조회하기");
		log.info("주문아이디: " + orderId);
		
		Order order = orderService.getOrderDetail(orderId);
		if (order.getMember().getId() != SecurityUtils.getMemberId()) {
			throw new MemberDismatchException("주문자와 일치하지 않습니다.");
		}
		model.addAttribute("order", order);
		
		return "order/completed";
	}
	
}

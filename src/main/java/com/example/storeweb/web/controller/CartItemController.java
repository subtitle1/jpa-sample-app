package com.example.storeweb.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.entity.CartItem;
import com.example.storeweb.service.CartItemService;
import com.example.storeweb.util.SecurityUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartItemController {

	private final CartItemService cartItemService;
	
	@GetMapping("/add")
	public String add(@RequestParam("id") long bookId,
			@RequestParam("quantity") int quantity) {
		log.info("장바구니 아이템 추가하기");
		log.info("book_id [" + bookId + "]");
		log.info("quantity [" + quantity + "]");
		
		cartItemService.insertCartItem(SecurityUtils.getMemberId(), bookId, quantity);
		
		return "redirect:/cart/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("나의 장바구니 아이템 조회");
		List<CartItem> cartItems = cartItemService.getCartItems(SecurityUtils.getMemberId());
		
		int totalPrice = cartItems.stream().mapToInt(cartItem -> cartItem.getItemPrice()).sum();
		int totalPaymentPrice = cartItems.stream().mapToInt(cartItem -> cartItem.getItemSellPrice()).sum();
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalDiscountPrice", totalPrice - totalPaymentPrice);
		model.addAttribute("totalPaymentPrice", totalPaymentPrice);
		
		return "cart/list";
	}
	
	@GetMapping("/update")
	public String list(@RequestParam("id") long cartItemId,
			@RequestParam("quantity") int quantity) {
		log.info("장바구니 아이템 변경하기");
		log.info("cart_item_id [" + cartItemId + "]");
		log.info("quantity [" + quantity + "]");
		
		cartItemService.updateCartItem(SecurityUtils.getMemberId(), cartItemId, quantity);
		
		return "redirect:/cart/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") List<Long> cartItemIds) {
		log.info("장바구니 아이템 삭제하기");
		log.info("삭제할 cart_item_id [" + cartItemIds + "]");
		
		cartItemService.deleteCartItem(SecurityUtils.getMemberId(), cartItemIds);
		
		return "redirect:/cart/list";
	}
}

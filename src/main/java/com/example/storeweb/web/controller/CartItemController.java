package com.example.storeweb.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.entity.CartItem;
import com.example.storeweb.service.CartItemService;
import com.example.storeweb.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartItemController {

	private final CartItemService cartItemService;
	
	@GetMapping("/add")
	public String add(@RequestParam("id") long bookId,
			@RequestParam("quantity") int quantity) {
		
		cartItemService.insertCartItem(SecurityUtils.getMemberId(), bookId, quantity);
		
		return "redirect:/cart/list";
	}
	
	@GetMapping("/list")
	public String list() {
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "cart/list";
	}
}

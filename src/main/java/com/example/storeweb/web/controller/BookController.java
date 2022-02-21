package com.example.storeweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.storeweb.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/list")
	public String books(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		
		return "book/list";
	}
}

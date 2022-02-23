package com.example.storeweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/list")
	public String books(Model model) {
		log.info("책 목록 조회");
		
		model.addAttribute("books", bookService.getAllBooks());
		
		return "book/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "id") long bookId, Model model) {
		log.info("책 상세정보 조회");
		log.info("book_id [" + bookId + "]");
		model.addAttribute("book", bookService.getBookDetail(bookId));
		
		return "book/detail";
	}
}

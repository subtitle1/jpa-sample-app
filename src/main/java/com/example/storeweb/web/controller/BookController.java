package com.example.storeweb.web.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.dto.Pagination;
import com.example.storeweb.entity.Book;
import com.example.storeweb.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	// 요청 URI : /book/list
	// 요청 URI : /book/list?page=3&sort=DESC:price
	@GetMapping("/list")
	public String books(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo, 
			@RequestParam(name = "sort", required = false, defaultValue = "DESC:id") String sort,  Model model) {
		log.info("책 목록 조회");
		log.info("페이지번호: " + pageNo);
		log.info("정렬기준: " + sort);			// DESC:price
		
		String[] sortItems = sort.split(":");
		
		// 페이징처리에 필요한 Pageable 구현객체 획득하기(Pageable객체는 페이징처리를 위한 SQL 작성에 필요한 정보를 포함하는 객체다.)
		Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Direction.valueOf(sortItems[0]), sortItems[1]));
		// Spring Data JPA의 페이징처리 SQL 조회결과를 포함하고 있는 Page<T>객체 획득하기
		Page<Book> page = bookService.getBooks(pageable);
		
		Pagination pagination = new Pagination(page.getNumber() + 1, 5, page.getTotalPages());
		
		List<Book> books = page.getContent();
		model.addAttribute("books", books);
		model.addAttribute("pagination", pagination);
		
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

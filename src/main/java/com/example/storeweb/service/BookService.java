package com.example.storeweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Book;
import com.example.storeweb.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBookDetail(long bookId) {
		return bookRepository.getById(bookId);
	}
}

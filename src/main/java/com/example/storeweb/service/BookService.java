package com.example.storeweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Book;
import com.example.storeweb.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public Page<Book> getBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	public Book getBookDetail(long bookId) {
		return bookRepository.getById(bookId);
	}
}

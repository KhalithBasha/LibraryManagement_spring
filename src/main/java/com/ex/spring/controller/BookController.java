package com.ex.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex.spring.Config.ResponseStructure;
import com.ex.spring.dto.Book;
import com.ex.spring.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book b) {
		return service.saveBook(b);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Book>> findBook(@RequestParam int id) {
		return service.findBook(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Book>> deleteBook(@RequestParam int id) {
		return service.deleteBook(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book b,@RequestParam int id) {
		return service.updateBook(b, id);
	}
	
	@GetMapping("/lessthan")
	public ResponseEntity<ResponseStructure<List<Book>>>lessThan(@RequestParam int copies) {
		return service.lessThan(copies);
	}
	
}

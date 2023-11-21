package com.ex.spring.controller;

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
import com.ex.spring.dto.Author;
import com.ex.spring.service.AuthorService;
@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	AuthorService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<Author>> saveAuthor(@RequestBody Author a) {
		return service.saveAuthor(a);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Author>> findAuthor(@RequestParam int id) {
		return service.findAuthor(id);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Author>> deleteAuthor(@RequestParam int id) {
		return service.deleteAuthor(id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Author>> updateAuthor(@RequestBody Author a,@RequestParam int id) {
		return service.updateAuthor(a, id);
	}
	@PutMapping("/assign")
	public ResponseEntity<ResponseStructure<Author>> assignBook(@RequestParam int aid,@RequestParam int bid) {
		return service.assignBook(aid, bid);
	}
	
	
	
}

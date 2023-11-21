package com.ex.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex.spring.dto.Book;
import com.ex.spring.repo.BookRepo;

@Repository
public class BookDao {
	@Autowired
	BookRepo repo;
	
	public Book saveBook(Book b) {
		return repo.save(b);
	}
	
	public Book findBook(int id) {
		Optional<Book> opBook = repo.findById(id);
		if (opBook.isPresent()) {
			return opBook.get();
		}
		return null;
	}
	
	public Book deleteBook(int id) {
		Book exBook = findBook(id);
		if (exBook!=null) {
			repo.delete(exBook);
			return exBook;
		}
		return null;
	}
	
	public Book updateBook(Book b, int id) {
		Book exBook = findBook(id);
		if (exBook!=null) {
			if (b.getName()==null) {
				b.setName(exBook.getName());
			}
			if (b.getPrice()<=0) {
				b.setPrice(exBook.getPrice());
			}
			if (b.getCopies()<=0) {
				b.setCopies(exBook.getCopies());
			}
			b.setId(id);
			return repo.save(b);
		}
		return null;
	}
	
	public List<Book> getAllProducts() {
		return repo.findAll();
	}
	
	public List<Book> lessThan(int copies) {
		return repo.lessThan(copies);
	}
}

package com.ex.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ex.spring.Config.ResponseStructure;
import com.ex.spring.Exception.BookNotFound;
import com.ex.spring.dao.BookDao;
import com.ex.spring.dto.Book;

@Service
public class BookService {
	@Autowired
	BookDao dao;
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book b) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		structure.setData(dao.saveBook(b));
		structure.setMsg("Book Has Been Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Book>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Book>> findBook(int id) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		if (dao.findBook(id)!=null) {
			structure.setData(dao.findBook(id));
			structure.setMsg("Book is Present");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Book>>(structure,HttpStatus.FOUND);
		}
		throw new BookNotFound("No Book Present With the given id");
	}
	
	public ResponseEntity<ResponseStructure<Book>> deleteBook(int id) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		if (dao.findBook(id)!= null) {
			structure.setData(dao.deleteBook(id));
			structure.setMsg("Book is Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Book>>(structure,HttpStatus.OK);
		}
		throw new BookNotFound("No Book Present With the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> findAll() {
		ResponseStructure<List<Book>> structure = new ResponseStructure<>();
		List<Book> books = dao.getAllProducts();
		if (!books.isEmpty()) {
			structure.setData(books);
			structure.setMsg("All Books are Found");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Book>>>(structure,HttpStatus.FOUND);
		}
		throw new BookNotFound("No Books Are Found");
	}
	
	public ResponseEntity<ResponseStructure<Book>> updateBook(Book b,int id) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		
		if (dao.findBook(id)!= null) {
			structure.setData(dao.updateBook(b, id));
			structure.setMsg("Book is Updated Succesfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Book>>(structure,HttpStatus.OK);
		}
		throw new BookNotFound("Book is Not Updated");
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> lessThan(int copies) {
		ResponseStructure<List<Book>> structure = new ResponseStructure<>();
		structure.setData(dao.lessThan(copies));
		structure.setMsg("Book has been Selected");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Book>>>(structure,HttpStatus.OK);
	}
	
}

package com.ex.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ex.spring.Config.ResponseStructure;
import com.ex.spring.Exception.AuthorNotFound;
import com.ex.spring.Exception.BookNotFound;
import com.ex.spring.dao.AuthorDao;
import com.ex.spring.dao.BookDao;
import com.ex.spring.dto.Author;
import com.ex.spring.dto.Book;

@Service
public class AuthorService {
	@Autowired
	AuthorDao dao;
	@Autowired
	BookDao bdao;
	
	public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author a) {
		ResponseStructure<Author> structure = new ResponseStructure<>();
		structure.setData(dao.saveAuthor(a));
		structure.setMsg("Author Has Been Saved");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Author>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Author>> findAuthor(int id) {
		ResponseStructure<Author> structure = new ResponseStructure<>();
		if (dao.findAuthor(id)!=null) {
			structure.setData(dao.findAuthor(id));
			structure.setMsg("Author Has Been Found");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Author>>(structure,HttpStatus.FOUND);
		}
		throw new AuthorNotFound("No Author Has Present in Given id");
	}
	
	public ResponseEntity<ResponseStructure<Author>> deleteAuthor(int id) {
		ResponseStructure<Author> structure = new ResponseStructure<>();
		if (dao.findAuthor(id)!=null) {
			structure.setData(dao.deleteAuthor(id));
			structure.setMsg("Author Has Been Deleted");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Author>>(structure,HttpStatus.OK);
		}
		throw new AuthorNotFound("No Author Has Present in Given id");
	}
	
	public ResponseEntity<ResponseStructure<Author>> updateAuthor(Author a,int id) {
		ResponseStructure<Author> structure = new ResponseStructure<>();
		if (dao.findAuthor(id)!=null) {
			structure.setData(dao.updateAuthor(a, id));
			structure.setMsg("Author Has Been Updated");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Author>>(structure,HttpStatus.OK);
		}
		throw new AuthorNotFound("No Author Has Present in Given id");
	}
	
	public ResponseEntity<ResponseStructure<Author>> assignBook(int aid,int bid) {
		Author a = dao.findAuthor(aid);
		Book b = bdao.findBook(bid);
		if (a!=null) {
			if (b!=null) {
				a.setBook(b);
				ResponseStructure<Author> structure = new ResponseStructure<>();
				structure.setData(dao.updateAuthor(a, bid));
				structure.setMsg("Book Has Been Assigned To Author");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Author>>(structure,HttpStatus.OK);
			}
			throw new BookNotFound("No Book Present With the given id");
		}
		throw new AuthorNotFound("No Author Has Present in Given id");
	}
	
}

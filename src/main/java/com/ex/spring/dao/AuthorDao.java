package com.ex.spring.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex.spring.dto.Author;
import com.ex.spring.repo.AuthorRepo;

@Repository
public class AuthorDao {
	
	@Autowired
	AuthorRepo repo;
	
	public Author saveAuthor(Author a) {
		return repo.save(a);
	}
	
	public Author findAuthor(int id) {
		Optional<Author> opAuthor = repo.findById(id);
		if (opAuthor.isPresent()) {
			return opAuthor.get();
		}
		return null;
	}
	
	public Author deleteAuthor(int id) {
		Author exAuthor = findAuthor(id);
		if (exAuthor!=null) {
			repo.delete(exAuthor);
			return exAuthor;
		}
		return null;
	}
	
	public Author updateAuthor(Author a, int id) {
		Author exAuthor = findAuthor(id);
		if (exAuthor!=null) {
			if (a.getName()==null) {
				a.setName(exAuthor.getName());;
			}
			if (a.getContact()<=0) {
				a.setContact(exAuthor.getContact());
			}
			if (a.getBook()!= null) {
				a.setBook(exAuthor.getBook());;
			}
			a.setId(id);
			return repo.save(a);
		}
		return null;
	}


}

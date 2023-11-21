package com.ex.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ex.spring.dto.Book;

public interface BookRepo extends JpaRepository<Book,Integer> {
	@Query("select b from Book b where b.copies < ?1")
	public List<Book> lessThan(int copies);
}

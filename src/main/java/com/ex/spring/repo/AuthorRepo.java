
package com.ex.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.spring.dto.Author;

public interface AuthorRepo  extends JpaRepository<Author,Integer> {

}

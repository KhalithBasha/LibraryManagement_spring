package com.ex.spring.Exception;

import lombok.Getter;

@Getter
public class BookNotFound extends RuntimeException{
	
	String msg;

	public BookNotFound(String msg) {
		this.msg = msg;
	}
	
	
	
}

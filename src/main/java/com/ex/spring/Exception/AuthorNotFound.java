package com.ex.spring.Exception;

import lombok.Getter;

@Getter
public class AuthorNotFound extends RuntimeException {
	
	String msg;

	public AuthorNotFound(String msg) {
		this.msg = msg;
	}
	
	
}

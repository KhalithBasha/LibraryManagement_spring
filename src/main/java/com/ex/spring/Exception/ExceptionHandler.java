package com.ex.spring.Exception;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ex.spring.Config.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> list = ex.getAllErrors();
		HashMap<String, String> map = new HashMap<>();
		
		for(ObjectError error: list) {
			String msg = error.getDefaultMessage();
			String FieldName = ((FieldError) error).getField();
			map.put(FieldName, msg);
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> productNotFound(BookNotFound ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Product Not Found");
		structure.setMsg(ex.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> productNotFound(AuthorNotFound ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Product Not Found");
		structure.setMsg(ex.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}

	
	
}

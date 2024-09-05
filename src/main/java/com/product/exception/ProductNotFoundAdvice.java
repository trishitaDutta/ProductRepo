package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductNotFoundAdvice {
	
	 @ResponseBody
	 @ExceptionHandler(ProductNotFoundException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 String productrNotFoundHandler(ProductNotFoundException ex) {
	    return ex.getMessage();
	 }

}

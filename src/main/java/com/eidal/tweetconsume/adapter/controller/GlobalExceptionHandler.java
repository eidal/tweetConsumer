package com.eidal.tweetconsume.adapter.controller;

import com.eidal.tweetconsume.core.domain.exception.TweetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({TweetNotFoundException.class})
	public final ResponseEntity<String> handleNotFoundException(Exception exception, WebRequest request) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class,
		HttpMessageNotReadableException.class})
	public final ResponseEntity<String> handleMethodArgumentException(Exception exception, WebRequest request) {
		return new ResponseEntity<>("Bad Arguments", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleGeneralException(Exception exception, WebRequest request) {
		return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

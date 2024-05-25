package com.paris2024.ticketing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exception.SportNotFoundException;

@RestControllerAdvice
public class SportErrorAdvice {

	@ResponseBody
	@ExceptionHandler(SportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String sportNotFoundHandler(SportNotFoundException ex) {
		return ex.getMessage();
	}

}

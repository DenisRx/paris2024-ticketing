package com.paris2024.ticketing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exception.CompetitionNotFoundException;

@RestControllerAdvice
public class CompetitionErrorAdvice {

	@ResponseBody
	@ExceptionHandler(CompetitionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String competitionNotFoundHandler(CompetitionNotFoundException ex) {
		return ex.getMessage();
	}

}

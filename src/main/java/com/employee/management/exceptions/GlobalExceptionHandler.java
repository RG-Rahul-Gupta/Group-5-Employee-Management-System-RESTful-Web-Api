package com.employee.management.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { NoSuchElementException.class })
	protected ResponseEntity<Object> handleConflict(WebRequest request, NoSuchElementException nex) {
		String bodyOfResponse = "No Record with Coresponding Id or Name Exist in the Database";
		return handleExceptionInternal(nex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { DataNotFoundException.class })
	protected ResponseEntity<Object> handleConflict(WebRequest request, DataNotFoundException exp) {
		return handleExceptionInternal(exp, exp.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = { OperationNotAllowedException.class })
	protected ResponseEntity<Object> handleConflict(WebRequest request, OperationNotAllowedException oex) {
		return handleExceptionInternal(oex, oex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}


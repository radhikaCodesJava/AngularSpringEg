package com.demo.assignment.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler (MethodArgumentNotValidException.class )
	public ResponseEntity<?> handleCustomValidationExceptions(MethodArgumentNotValidException m){
		System.out.println("In handler for MethodArgumentNotValidException ");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Error", m.getBindingResult().getFieldError().getDefaultMessage() );
	    Map<String, String> errors = new HashMap<>();
	    m.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
		return new ResponseEntity<>( errors, HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler ( IllegalArgumentException.class )
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception,WebRequest request)
	{
	System.out.println("Inside Illegal arg exception handler");
	ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false) );
	
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(TypeMismatchException.class)
    public ResponseStatusException handleTypeMismatchException(TypeMismatchException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value '${e.value}'", e);
    }
	
	@ExceptionHandler(DuplicateResourceFound.class )
	public ResponseEntity<?> handleDuplicateResourceException(DuplicateResourceFound d, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), d.getMessage(), request.getDescription(false) );
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler ( ResourceNotFoundException.class )
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException r, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), r.getMessage(), request.getDescription(false) );
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler ( HttpMessageNotReadableException.class )
	public ResponseEntity<?> handleHttpMessageNotReadableException ( HttpMessageNotReadableException h, WebRequest request){
		ErrorDetails errorDetails = null;
		if ( h.getMessage().contains("Failed to parse Date") ) {
			if ( h.getMessage().contains( "creation_time"))
			    errorDetails = new ErrorDetails(new Date(), "Please give a valid creation_time in 'yyyy-MM-dd'T'HH:mm:ss.SSSXXX' format", request.getDescription(false) );
			else if  ( h.getMessage().contains( "last_mod_time"))
			    errorDetails = new ErrorDetails(new Date(), "Please give a valid last_mod_time in 'yyyy-MM-dd'T'HH:mm:ss.SSSXXX' format", request.getDescription(false) );
		}
		else {
			errorDetails = new ErrorDetails(new Date(), h.getMessage(), request.getDescription(false) );
		}
		return new ResponseEntity<>( errorDetails, HttpStatus.BAD_REQUEST );
	}
	
	
	@ExceptionHandler ( Exception.class )
	public ResponseEntity<?> handleGlobalExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false) );
		System.out.println( "In Exception Handler : " + ex.getLocalizedMessage() );
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}

package com.devsuperior.movieflix.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionsHandler {
	
	@ExceptionHandler( ResourceNotFoundException.class )
	public ResponseEntity<StandardError> entityNotFound( ResourceNotFoundException e, HttpServletRequest request) 
	{
		HttpStatus  status = HttpStatus.NOT_FOUND;
		StandardError err = new  StandardError() ;
		err.setTimestamp(Instant.now());
		err.setStatus( status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status( status).body( err );
				
	}
	@ExceptionHandler( DatabaseException.class )
	public ResponseEntity<StandardError> database( DatabaseException e, HttpServletRequest request) 
	{
		HttpStatus  status = HttpStatus.BAD_REQUEST;
		StandardError err = new  StandardError() ;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status( status).body( err );
				
	}
	@ExceptionHandler(com.devsuperior.movieflix.services.exceptions.ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request) {
		OAuthCustomError err = new OAuthCustomError("Forbidden", e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e, HttpServletRequest request) {
		OAuthCustomError err = new OAuthCustomError("Unauthorized", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}	
	
}
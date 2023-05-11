package com.haarmk.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;



@ControllerAdvice
public class GlobalExceptionHandler{


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> myIllegalArgumentException(IllegalArgumentException me) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(me.getMessage());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(e.getMessage());
        return ResponseEntity.status(403).body(err);
    }

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ErrorDetails> handleSignatureException(SignatureException e, HttpServletRequest request){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(request.getContextPath());
		return ResponseEntity.status(403).body(err);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorDetails> handleJwtException(JwtException e, HttpServletRequest request){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(request.getContextPath());
		return ResponseEntity.status(403).body(err);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myInvalidDataExpHandler(MethodArgumentNotValidException me) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
		
	}



	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myAnyExpHandler(Exception ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
			
	
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
	
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}

	

	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<ErrorDetails> myAnyExpHandler(FeedbackException ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ErrorDetails> doainExceptionHandler(DomainException exception,WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exception.getMessage());
		err.setDetails(req.getDescription(false));		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExpHandler(UserException ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> CartExpHandler(CartException ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> ProductExpHandler(ProductException ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorDetails> OrderExpHandler(OrderException ie,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<ErrorDetails> emailExceptionHandler(EmailException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ErrorDetails> tokenExceptionHandler(TokenException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorDetails> athenticationExceptionHandler(AuthenticationException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.FORBIDDEN);
		
	}
	@ExceptionHandler(HaarmkException.class)
	public ResponseEntity<ErrorDetails> haarmkExceptionHandler(HaarmkException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDetails> badCredentialsExceptionHandler(BadCredentialsException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(MalformedJwtException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(JsonParseException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmailNotVerifiedException.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(EmailNotVerifiedException e,WebRequest req){
		
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.FORBIDDEN);
		
	}

}

package com.trading.traders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler  {

	@ExceptionHandler(value= {CustomException.class})
	public ResponseEntity<ExceptionData> customeExceptionHandling(CustomException e)
	{
		//ExceptionData exceptionData=new ExceptionData(e.getMessage(),LocalDateTime.now(),e);
		ExceptionData exceptionData=new ExceptionData(e.getMessage());
		
		return new ResponseEntity<>(exceptionData,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= {TradeAlreadyExist.class})
	public ResponseEntity<ExceptionData> customeExceptionHandling(TradeAlreadyExist e)
	{
		ExceptionData exceptionData=new ExceptionData(e.getMessage());
		
		return new ResponseEntity<>(exceptionData,HttpStatus.BAD_REQUEST);
	}
}
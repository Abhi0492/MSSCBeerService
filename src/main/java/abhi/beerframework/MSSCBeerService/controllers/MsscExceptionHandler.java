package abhi.beerframework.MSSCBeerService.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MsscExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationErrorHandling(ConstraintViolationException cve) {
		List<String> errorsList = new ArrayList<>(cve.getConstraintViolations().size());
		
		cve.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));
		
		return new ResponseEntity<List>(errorsList, HttpStatus.BAD_REQUEST);
	}

}

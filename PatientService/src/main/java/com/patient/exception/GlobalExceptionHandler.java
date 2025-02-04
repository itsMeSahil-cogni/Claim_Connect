package com.patient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.patient.dto.ApiResponse;
import com.patient.dto.PatientDTO;
import com.patient.entity.ClaimRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Handle all uncaught exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception ex) {
		log.error("Exception: ", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle Patient Not Found Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ApiResponse<PatientDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("ResourceNotFoundException: ", ex);
		return new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), null);

	}

	// Handle Claim Not Found Exception
	@ExceptionHandler(ClaimNotFoundException.class)
	public ApiResponse<ClaimRequest> handleClaimNotFoundException(ClaimNotFoundException ex) {
		log.error("ClaimNotFoundException: ", ex);
		return new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), null);

	}

	// Handle Validation Errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		log.error("ValidationException: ", ex);
		StringBuilder errors = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(
				error -> errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; "));
		return new ResponseEntity<>("Validation error(s): " + errors.toString(), HttpStatus.BAD_REQUEST);
	}

}

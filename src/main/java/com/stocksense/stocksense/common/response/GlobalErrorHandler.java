package com.stocksense.stocksense.common.response;

import com.stocksense.stocksense.common.exceptions.AlreadyPresentException;
import com.stocksense.stocksense.common.exceptions.NotFoundException;
import com.stocksense.stocksense.common.exceptions.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> err = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> err.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ErrorResponse<Map<String, String>>("Validation error", err));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedError(UnAuthorizedException ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundError(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentError(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AlreadyPresentException.class)
    public ResponseEntity<?> handleAlreadyPresentExceptionError(AlreadyPresentException ex) {
        return new ResponseEntity<>(new ErrorResponse<>(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleServerError(Exception ex) {
        System.out.println("errorrrr:" + ex);
        return ResponseEntity.internalServerError().body(new ErrorResponse<>("Internal server error"));
    }
}

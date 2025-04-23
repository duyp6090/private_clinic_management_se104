package com.example.demo.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.response.RestResponse;

import jakarta.validation.ConstraintViolation;

@RestControllerAdvice
public class GlobalException {

    private static final String MIN_ATRIBUTE = "min";
    private static final String MAX_ATRIBUTE = "max";
    private static final String SIZE_ATRIBUTE = "size";
    private static final String PATTERN_ATRIBUTE = "pattern";
    private static final String NOT_NULL_ATRIBUTE = "notNull";
    private static final String NOT_EMPTY_ATRIBUTE = "notEmpty";
    private static final String NOT_BLANK_ATRIBUTE = "notBlank";

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<RestResponse<Object>> handleRuntimeException(RuntimeException e) {
        // init RestResponse
        RestResponse<Object> restResponse = new RestResponse();

        // Set parameters
        restResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        restResponse.setError("RuntimeException");
        restResponse.setMessage(e.getMessage());
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<RestResponse<Object>> handleAppException(AppException e) {
        // Get errorCode
        ErrorCode errorCode = e.getErrorCode();

        // init RestResponse
        RestResponse<Object> restResponse = new RestResponse();

        // Set parameters
        restResponse.setStatusCode(errorCode.getCode());
        restResponse.setError("AppException");
        restResponse.setMessage(errorCode.getMessage());
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        String errorMessage = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = null;
        Map<String, Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(errorMessage);

            // Customize error message
            var constrainViolation = e.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constrainViolation.getConstraintDescriptor().getAttributes();

        } catch (IllegalArgumentException ex) {
            errorCode = ErrorCode.INVALID_INPUT;
        }

        // Init RestResponse
        RestResponse<Object> restResponse = new RestResponse();

        // Set parameters
        restResponse.setStatusCode(errorCode.getCode());
        restResponse.setError("MethodArgumentNotValidException");
        restResponse.setMessage(errorCode.getMessage());
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = attributes.get(MIN_ATRIBUTE) != null ? attributes.get(MIN_ATRIBUTE).toString() : null;
        if (minValue != null)
            message = message.replaceAll("\\{min}", minValue);
        return message;
    }
}

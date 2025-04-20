package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.response.RestResponse;

@RestControllerAdvice
public class GlobalException {
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
        return ResponseEntity.status(errorCode.getCode()).body(restResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        String errorMessage = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.valueOf(errorMessage);
        if (errorCode == null) {
            errorCode = ErrorCode.INVALID_INPUT;
        }

        // init RestResponse
        RestResponse<Object> restResponse = new RestResponse();

        // Set parameters
        restResponse.setStatusCode(errorCode.getCode());
        restResponse.setError("MethodArgumentNotValidException");
        restResponse.setMessage(errorCode.getMessage());
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }
}

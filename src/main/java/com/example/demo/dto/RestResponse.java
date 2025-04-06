package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private int statusCode;
    private String error;
    private String message;
    private T data;

    // Default constructor
    public RestResponse() {}

    // Constructor for success response
    public RestResponse(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    // Constructor for error response
    public RestResponse(int statusCode, String error, String message) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Convenience method to create success response
    public static <T> RestResponse<T> success(int statusCode, T data) {
        return new RestResponse<>(statusCode, data);
    }

    // Convenience method to create error response
    public static RestResponse<Object> error(int statusCode, String error, String message) {
        return new RestResponse<>(statusCode, error, message);
    }
}
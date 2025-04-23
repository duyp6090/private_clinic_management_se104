package com.example.demo.exception;

public enum ErrorCode {

    INVALID_INPUT(400, "Invalid input"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    PASSWORD_INVALID(400, "Password must be at least 8 characters"),
    INFORMATION_EXISTS(400, "Information already exists"),
    INVALID_SORT_FIELD(400, "Invalid sort field");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

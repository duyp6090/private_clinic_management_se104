package com.example.demo.exception;

public enum ErrorCode {

    INVALID_INPUT(400, "Invalid input"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    PASSWORD_INVALID(400, "Password must be at least 8 characters"),
    INFORMATION_EXISTS(400, "Information already exists"),
    INVALID_SORT_FIELD(400, "Invalid sort field");
    EXPIRED_TOKEN(401,"Expired token"),

    //AUTHENTICATION
    AUTH_INVALID_CREDENTIALS(401, "Invalid username or password"),
    AUTH_EXPIRED_TOKEN(401, "Expired access token"),
    AUTH_INVALID_REFRESH_TOKEN(401, "Invalid or expired refresh token"),
    AUTH_LOGOUT_FAILED(500, "Logout failed due to server error"),

    SIGNUP_USERNAME_TAKEN(400, "Username is already taken"),
    SIGNUP_EMAIL_TAKEN(400, "Email is already in use"),
    SIGNUP_FAILED(500, "Unexpected error during registration"),

    //PERMISSION
    PERMISSION_NOT_FOUND(404, "Permission not found"),
    PERMISSION_CREATE_FAILED(500, "Failed to create permission"),
    PERMISSION_UPDATE_FAILED(500, "Failed to update permission"),
    PERMISSION_DELETE_FAILED(500, "Failed to delete permission"),
    PERMISSION_INVALID_ROLE_ID(400, "Invalid role ID"),
    PERMISSION_ALREADY_EXISTS(400, "Permission already exists");
}

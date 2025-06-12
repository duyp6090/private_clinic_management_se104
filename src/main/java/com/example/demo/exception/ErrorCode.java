package com.example.demo.exception;

public enum ErrorCode {
    INVALID_INPUT(400, "Invalid input"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    PASSWORD_INVALID(400, "Password must be at least 8 characters"),
    INFORMATION_EXISTS(400, "Information already exists"),
    INVALID_SORT_FIELD(400, "Invalid sort field"),
    EXPIRED_TOKEN(401, "Expired token"),
    PATIENT_MAX(400, "Maximum number of patients reached"),
    NOT_ENOUGH_QUANTITY(400, "Not enough quantity"),

    // AUTHENTICATION
    AUTH_INVALID_CREDENTIALS(401, "Invalid username or password"),
    AUTH_EXPIRED_TOKEN(401, "Expired access token"),
    AUTH_INVALID_REFRESH_TOKEN(401, "Invalid or expired refresh token"),
    AUTH_LOGOUT_FAILED(500, "Logout failed due to server error"),

    SIGNUP_USERNAME_TAKEN(400, "Username is already taken"),
    SIGNUP_EMAIL_TAKEN(400, "Email is already in use"),
    SIGNUP_FAILED(500, "Unexpected error during registration"),

    // PERMISSION
    PERMISSION_NOT_FOUND(404, "Permission not found"),
    PERMISSION_CREATE_FAILED(500, "Failed to create permission"),
    PERMISSION_UPDATE_FAILED(500, "Failed to update permission"),
    PERMISSION_DELETE_FAILED(500, "Failed to delete permission"),
    PERMISSION_INVALID_ROLE_ID(400, "Invalid role ID"),
    PERMISSION_ALREADY_EXISTS(400, "Permission already exists"),

    // PATIENTS
    PHONE_NUMBER_EXISTS(409, "Phone number already exists"),
    PHONE_NUMBER_NOT_BLANK(400, "Phone number cannot be blank"),
    PHONE_NUMBER_NOT_NULL(400, "Phone number cannot be null"),
    RESIDENTAL_IDENTITY_EXISTS(409, "Residental identity already exists"),
    RESIDENTAL_IDENTITY_NOT_BLANK(400, "Residental identity cannot be blank"),
    RESIDENTAL_IDENTITY_NOT_NULL(400, "Residental identity cannot be null"),
    FULL_NAME_NOT_BLANK(400, "fullName cannot be blank"),
    FULL_NAME_NOT_NULL(400, "fullName cannot be null"),
    GENDER_NOT_NULL(400, "gender cannot be null"),
    ADDRESS_NOT_BLANK(400, "address cannot be blank"),
    ADDRESS_NOT_NULL(400, "address cannot be null"),
    YEAR_OF_BIRTH_NOT_NULL(400, "yearOfBirth cannot be null"),;

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

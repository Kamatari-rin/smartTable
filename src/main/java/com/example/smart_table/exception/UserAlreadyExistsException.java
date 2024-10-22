package com.example.smart_table.exception;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String userEmail) {
        super("User with this email {0} already exists", userEmail);
    }
}

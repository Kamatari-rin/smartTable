package com.example.smart_table.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(long userId) {
        super(String.format("User with ID %d not found", userId));
    }
}

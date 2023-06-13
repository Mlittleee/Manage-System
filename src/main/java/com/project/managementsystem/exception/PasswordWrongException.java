package com.project.managementsystem.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(String message){
        super(message);
    }
}

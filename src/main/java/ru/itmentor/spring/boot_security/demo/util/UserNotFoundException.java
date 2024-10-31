package ru.itmentor.spring.boot_security.demo.util;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String error, String message) {
        super(message);
    }
}

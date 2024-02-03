package org.example.blog_application.exception;

public class WrongItemException extends RuntimeException {
    public WrongItemException(String message) {
        super(message);
    }
}

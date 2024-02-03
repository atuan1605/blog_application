package org.example.blog_application.exception;

public class ItemExistException extends RuntimeException {
    public ItemExistException(String message) {
        super(message);
    }
}

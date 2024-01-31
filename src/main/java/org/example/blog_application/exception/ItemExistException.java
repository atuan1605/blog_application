package org.example.blog_application.exception;

import org.apache.coyote.BadRequestException;

public class ItemExistException extends BadRequestException {
    public ItemExistException(String message) {
        super(message);
    }
}

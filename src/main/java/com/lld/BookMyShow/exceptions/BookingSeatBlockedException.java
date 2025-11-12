package com.lld.BookMyShow.exceptions;

public class BookingSeatBlockedException extends RuntimeException {
    public BookingSeatBlockedException(String message) {
        super(message);
    }
}

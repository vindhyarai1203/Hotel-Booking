package com.hotel.booking.exception;

public class InvalidDateFormat extends RuntimeException{
    public InvalidDateFormat() {
        super("Enter valid date");
    }

    public InvalidDateFormat(String message) {
        super(message);
    }
}

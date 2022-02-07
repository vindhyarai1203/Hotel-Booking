package com.hotel.booking.exception;

public class NotValidFormat extends RuntimeException{
    public NotValidFormat() {
        super("Please enter data in valid format");
    }
    public NotValidFormat(String s) {
        super(s);
    }
}

package com.hotel.booking.exception;

public class BookingAlreadyCancelledException extends RuntimeException{

    public BookingAlreadyCancelledException() {
        super("Booking Already Cancelled");
    }
}

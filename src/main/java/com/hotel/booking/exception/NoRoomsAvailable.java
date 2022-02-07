package com.hotel.booking.exception;

public class NoRoomsAvailable extends RuntimeException {
    public NoRoomsAvailable() {
        super("No Rooms Available in this hotel ");
    }
}

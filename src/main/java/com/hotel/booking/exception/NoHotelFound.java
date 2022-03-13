package com.hotel.booking.exception;

public class NoHotelFound extends RuntimeException {
 public NoHotelFound() {
     super("No Hotel Found in this city");
    }
    public NoHotelFound(String s) {
        super(s);
    }
}

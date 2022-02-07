package com.hotel.booking.service;

import com.hotel.booking.model.Booking;

import java.text.ParseException;

public interface BookingService {
    Booking saveBooking(Booking booking) throws ParseException;

    String cancelBooking(long id);

}

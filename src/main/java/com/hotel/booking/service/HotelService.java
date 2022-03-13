package com.hotel.booking.service;

import com.hotel.booking.model.Hotel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);



    List<Hotel> getHotelListById(int cityCode);

    //void getData(Date date);
    List<Hotel> getHotelList(String city, Date checkIn, Date checkOut) throws ParseException;

    Hotel updateHotel(Hotel hotel);
}

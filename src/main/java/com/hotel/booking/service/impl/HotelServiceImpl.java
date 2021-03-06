package com.hotel.booking.service.impl;

import com.hotel.booking.Repository.BookingRepository;
import com.hotel.booking.Repository.HotelRepository;

import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    public HotelRepository hotelRepository;

    @Autowired
    public BookingRepository bookingRepository;



    private static ReentrantLock lock = new ReentrantLock();
    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    public Hotel saveHotel(Hotel hotel) {

        int r = hotel.getRooms();


        hotel.setRooms_available(r);
        String s = hotel.getCity();
        if (s.equals("Delhi"))
            hotel.setCityCode(1);
        if (s.equals("Mumbai"))
            hotel.setCityCode(2);
        if (s.equals("Noida"))
            hotel.setCityCode(3);
        if (s.equals("Gurugram"))
            hotel.setCityCode(4);
        if (s.equals("Jaipur"))
            hotel.setCityCode(5);
        if (hotel.getCityCode() == 0)
            hotel.setCityCode(6);


        Hotel h=null;
        lock.lock();
        try {
            h = hotelRepository.save(hotel);
        }
        finally {
            lock.unlock();
        }


        return h;


    }


    @Override
    public List<Hotel> getHotelListById(int cityCode) {
        return hotelRepository.findByCityCode(cityCode);
    }

    @Override
    public List<Hotel> getHotelList(String city, Date checkIn, Date checkOut)  {


        List<Hotel> hotel = hotelRepository.findAllByCity(city);//city
        List<Hotel> hotel_available=new ArrayList<>();
        for (int i = 0; i < hotel.size(); i++) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String format = formatter.format(checkIn);

            List<Booking> bookedRooms = bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckin(hotel.get(i).getHotelId(), 0, format);


            int r = hotel.get(i).getRooms() - bookedRooms.size();

            hotel.get(i).setRooms_available(r);
        }
        for(int i=0;i<hotel.size();i++)
        {
            if(hotel.get(i).getRooms_available()>0)
            {
                hotel_available.add(hotel.get(i));
            }
        }
        if (hotel_available.size() == 0) {
            logger.info("There are no hotels available in " + city);
        } else {
            logger.info("List of all hotels available in " + city + " is send to the client");
        }


        return hotel_available;
    }


}

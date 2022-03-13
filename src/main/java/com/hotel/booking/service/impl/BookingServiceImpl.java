package com.hotel.booking.service.impl;


import com.hotel.booking.Repository.BookingRepository;
import com.hotel.booking.Repository.HotelRepository;
import com.hotel.booking.exception.NoHotelFound;
import com.hotel.booking.exception.NoRoomsAvailable;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class BookingServiceImpl implements BookingService {


    private static final ReentrantLock lock = new ReentrantLock();
    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    public HotelRepository hotelRepository;
    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    public Booking saveBooking(Booking booking) throws ParseException {
        Booking b = null;
        booking.setBookingCancelFlag(0);
        try {
            Hotel hotel = hotelRepository.findByHotelId(booking.getHotelId()).orElseThrow(
                    () -> new NoHotelFound());


            Optional<Hotel> h = hotelRepository.findByHotelId(booking.getHotelId());

            hotel = h.get();
            List<Booking> bookedRooms = bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckin(hotel.getHotelId(), 0, booking.getCheckin());

            int r = hotel.getRooms() - bookedRooms.size();

            if (r < 1) {
                throw new NoRoomsAvailable();
            }


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(booking.getCheckin()));
            c.add(Calendar.DATE, 1);
            booking.setCheckout(sdf.format(c.getTime()));
        } catch (ParseException e) {
            System.out.println("Enter Dates Correctly\n\n");
            return b;
        } catch (NoRoomsAvailable e) {

            logger.info(e.getMessage() + booking.getHotelId());
            return b;

        } catch (NoHotelFound e) {
            logger.info(e.getMessage() + booking.getHotelId());


            return b;
        }
        Booking booking_dummy = null;
        lock.lock();
        try {
            booking_dummy = bookingRepository.save(booking);
        } finally {
            lock.unlock();
        }

        return booking_dummy;
    }

    @Override
    public String cancelBooking(long id) {


        List<Booking> bookingList = bookingRepository.findAllByBookingIdAndBookingCancelFlag(id, 0);


        String s;
        lock.lock();
        if (bookingList.size() == 0) {
            s = "Booking Id doesn't exist Id:- ";
        } else {
            bookingList.get(0).setBookingCancelFlag(1);
            bookingRepository.save(bookingList.get(0));
            s = "Booking Cancelled Id:- ";
        }
        lock.unlock();

        return s;
    }
}

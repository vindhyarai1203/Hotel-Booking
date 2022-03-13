package com.hotel.booking;


import com.hotel.booking.Repository.BookingRepository;
import com.hotel.booking.Repository.HotelRepository;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingService;
import com.hotel.booking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes={BookingServiceTesting.class})
public class BookingServiceTesting {

    @InjectMocks
    BookingService bookingService=new BookingServiceImpl();

    @Mock
    BookingRepository bookingRepository;

    @Mock
    HotelRepository hotelRepository;

    @Test
    @Order(1)
    public void saveBookingTest_1() throws ParseException {
        List<Booking> mybooking=new ArrayList<>();
        Booking booking1=new Booking(1,2,"2022-01-01","2022-01-02",0);
        mybooking.add(booking1);
        mybooking.add(booking1);
        mybooking.add(booking1);
        Booking booking2=new Booking();
        booking2.setBookingId(1);
        booking2.setCheckin("2022-01-01");
        booking2.setHotelId(2);
        Hotel hotel=new Hotel(2,"mars","delhi",4,5);
        when(hotelRepository.findByHotelId(2)).thenReturn(Optional.of(hotel));
        when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckin(2,0,"2022-01-01")).thenReturn(mybooking);
        when(bookingRepository.save(booking2)).thenReturn(booking2);
       booking2= bookingService.saveBooking(booking2);
       assertEquals(booking1.getClass(),booking2.getClass());



    }
    @Test
    @Order(2)
    public void saveBookingTest_2() throws ParseException {
        List<Booking> mybooking=new ArrayList<>();
        Booking booking1=new Booking(1,2,"2022-01-01","2022-01-02",0);
        mybooking.add(booking1);
        mybooking.add(booking1);
        mybooking.add(booking1);
        mybooking.add(booking1);
        Booking booking2=new Booking();
        booking2.setBookingId(1);
        booking2.setCheckin("2022-01-01");
        booking2.setHotelId(2);
        Hotel hotel=new Hotel(2,"mars","delhi",4,5);
        when(hotelRepository.findByHotelId(2)).thenReturn(Optional.of(hotel));
        when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckin(2,0,"2022-01-01")).thenReturn(mybooking);
        when(bookingRepository.save(booking2)).thenReturn(booking2);
        booking2= bookingService.saveBooking(booking2);
        assertEquals(null,booking2);



    }
    @Test
    @Order(3)
    public void cancelBookingTest_1()
    {
       List<Booking> mybooking=new ArrayList<>();
       mybooking.add(new Booking(1,2,"2022-01-01","2022-01-02",0));
       Booking dummyBooking= new Booking(1,2,"2022-01-01","2022-01-02",1);
       when(bookingRepository.findAllByBookingIdAndBookingCancelFlag(1,0)).thenReturn(mybooking);
       when(bookingRepository.save(mybooking.get(0))).thenReturn(dummyBooking);
       String s;
       s=bookingService.cancelBooking(1);
       assertEquals("Booking Cancelled Id:- 1",s+1);
       assertEquals(1,mybooking.get(0).getBookingCancelFlag());


    }
    @Test
    @Order(4)
    public void cancelBookingTest_2()
    {
        List<Booking> mybooking=new ArrayList<>();
        when(bookingRepository.findAllByBookingIdAndBookingCancelFlag(1,0)).thenReturn(mybooking);
        String s;
        s=bookingService.cancelBooking(1);
        assertEquals("Booking Id doesn't exist Id:- 1",s+1);

    }

}

package com.hotel.booking;

import com.hotel.booking.Repository.BookingRepository;
import com.hotel.booking.controller.BookingControl;
import com.hotel.booking.controller.HotelController;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingService;
import com.hotel.booking.service.HotelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(classes={BookingControllerTesting.class})
public class BookingControllerTesting {

    @Mock
    BookingService bookingService;
    @InjectMocks
    BookingControl bookingControl=new BookingControl();
    @Test
    public void bookingControllerTestingPostMethod() throws ParseException {
        Date date=new Date();
        Booking mybooking=new Booking(1,1,"2022-06-01","2022-06-02",0);
        when(bookingService.saveBooking(mybooking)).thenReturn(mybooking);
        ResponseEntity<Booking> res= bookingControl.saveBooking(mybooking);
        assertEquals(HttpStatus.CREATED,res.getStatusCode());
        assertEquals(mybooking,res.getBody());

    }
    @Test
    public void bookingControllerDeleteMethod()
    {
        String string=new String("Booking Cancelled ");
        when(bookingService.cancelBooking(123)).thenReturn(string);
        ResponseEntity<String> res = bookingControl.cancelBooking(123);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals("Booking Cancelled 123",res.getBody());
    }

}

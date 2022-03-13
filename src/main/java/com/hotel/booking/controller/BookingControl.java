package com.hotel.booking.controller;

import com.hotel.booking.exception.InvalidDateFormat;
import com.hotel.booking.exception.NoHotelFound;
import com.hotel.booking.model.Booking;
import com.hotel.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/booking/")
public class BookingControl {


    @Autowired
    public BookingService bookingService;

    public BookingControl() {

    }

    @PostMapping("/a")
    public ResponseEntity<Booking> saveBooking1(@RequestParam(name = "hotelId") int hotelId,
                                                @RequestParam(name = "checkIn") Date checkIn) throws ParseException {
        System.out.println("Running........");
        Booking booking = new Booking();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(checkIn);
        booking.setHotelId(hotelId);
        booking.setCheckin(strDate);


        log.info("New booking request received on " + booking.getCheckin() + " for hotel Id " + booking.getHotelId());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckin());
            Date date1 = new Date();
            if (date1.after(date)) {
                throw new InvalidDateFormat("Please Enter date after " + date);
            }

        } catch (Exception e) {
            throw new InvalidDateFormat();
        }

        ResponseEntity<Booking> res = new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
        if (res.getBody() == null)
            throw new NoHotelFound();
        return res;
    }

    @PostMapping
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) throws ParseException {

        boolean b = true;
        String s = "";
        s = booking.getCheckout();
        if (booking.getCheckout() == null) {
            b = false;
        }
        booking.setCheckout(null);

        log.info("New booking request received on " + booking.getCheckin() + " for hotel Id " + booking.getHotelId());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getCheckin());
            Date date1 = new Date();
            if (date1.after(date)) {
                throw new InvalidDateFormat("Please Enter date after " + date);
            }

        } catch (Exception e) {
            throw new InvalidDateFormat();
        }

        ResponseEntity<Booking> res = new ResponseEntity<Booking>(bookingService.saveBooking(booking), HttpStatus.CREATED);
        if (b) {
            res.getBody().setCheckout(s);
        }

        if (res.getBody() == null)
            throw new NoHotelFound();
        return res;
    }

    @DeleteMapping("{booking_id}")
    public ResponseEntity<String> cancelBooking(@PathVariable("booking_id") long booking_id) {
        log.info("Booking cancellation request received  with booking Id:- " + booking_id);

        String s = bookingService.cancelBooking(booking_id);
        s = s + booking_id;
        log.info(s);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBooking(@PathVariable("id") long id) {
        String s = bookingService.cancelBooking(id);

        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    @ExceptionHandler({NoHotelFound.class})
    public String exceptionHandler() {
        return "No Hotel Found";
    }

    @ExceptionHandler({InvalidDateFormat.class})
    public String exceptionDate() {
        return "Enter valid date";
    }


}

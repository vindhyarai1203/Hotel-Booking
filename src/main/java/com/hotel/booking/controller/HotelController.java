package com.hotel.booking.controller;


import com.hotel.booking.exception.NoHotelFound;
import com.hotel.booking.exception.NotValidFormat;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private static final ReentrantLock lock = new ReentrantLock();
    Logger logger = LoggerFactory.getLogger(HotelController.class);
    @Autowired
    private HotelService hotelService;
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelList(@RequestParam(name = "city", required = false) String city,
                                                    @RequestParam(name = "check_in", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkIn,
                                                    @RequestParam(name = "check_out", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOut) throws ParseException {
        ResponseEntity<List<Hotel>> res = null;
        logger.info("Get hotels list request received---> City:- " + city + " on " + checkIn);
        if (city == null)
            throw new NoHotelFound("Please Enter city name");
        if (checkIn == null || checkOut == null)
            throw new NoHotelFound("Please Enter valid date");
        if (checkOut.before(checkIn) || checkIn.equals(checkOut))
            throw new NoHotelFound("Please Enter valid date");
        Date date1 = new Date();
        if (date1.after(checkIn)) {
            throw new NoHotelFound("Please Enter date after " + date1);
        }
        res = new ResponseEntity<List<Hotel>>(hotelService.getHotelList(city, checkIn, checkOut), HttpStatus.OK);
        List<Hotel> myHotel = hotelService.getHotelList(city, checkIn, checkOut); 
        if (myHotel.size() == 0) {
            throw new NoHotelFound();
        }
        return res;
    }
    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@Valid @RequestBody Hotel hotel) {
        logger.debug("Controller Working");
        ResponseEntity<Hotel> h = null;
        h = new ResponseEntity<Hotel>(hotelService.saveHotel(hotel), HttpStatus.CREATED);
        logger.info("New hotel is added in database with hotel Id " + hotel.getHotelId());
        return h;
    }
    @PostMapping("/call")
    public ResponseEntity<String> apiDemo(
                                          @RequestParam(name="user") String user,
                                          @RequestParam(name="pass") String pass
    )
    {
        String s="Done";
        logger.debug(user+"--and--"+pass);
        System.out.println("Done"+user+pass);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PutMapping("/updateHotel")
    public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotel)
    {
        ResponseEntity<Hotel> responseEntity=null;
        try{
            Hotel hotel1=hotelService.updateHotel(hotel);
            responseEntity=new ResponseEntity<Hotel>(hotel1,HttpStatus.CREATED);
            if(hotel1==null)
                throw new NoHotelFound("Something went wrong");
        }
        catch (Exception exception)
        {
            logger.info(exception.getMessage());
        }
        return responseEntity;
    }
    @DeleteMapping("{value}")
    public ResponseEntity<String> deleteHotel(@PathVariable("value") long value)
    {
        ResponseEntity<String> response;
        response=new ResponseEntity<String>("we received value:- "+value,HttpStatus.OK);

        return response;
    }
    @ExceptionHandler({NoHotelFound.class})
    public String exceptionHandler_1(Exception e) {
        return e.getMessage();
    }
    @ExceptionHandler({NotValidFormat.class})
    public ResponseEntity<String> exceptionHandler_2(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}

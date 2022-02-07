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

    @GetMapping("/demo")
    public String fun(@RequestParam(name = "name", required = false, defaultValue = "Vindhya") String name,
                      @RequestParam(name = "lname", required = false, defaultValue = "Rai") String lname) {
        //return "Welcome "+name+" "+lname;
        throw new NoHotelFound();
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotelList(@RequestParam(name = "city", required = false, defaultValue = "Unknown") String city,
                                                    @RequestParam(name = "check_in", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkIn,
                                                    @RequestParam(name = "check_out", required = false, defaultValue = "2022-01-02") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOut) throws ParseException {
        ResponseEntity<List<Hotel>> res = null;
       // System.out.println(checkIn);
        logger.info("Get hotels list request received---> City:- " + city + " on " + checkIn);
        if (city.equals("Unknown"))
            throw new NoHotelFound("Please Enter city name");
        if (checkIn == null)
            throw new NoHotelFound("Please Enter check in date");

        res = new ResponseEntity<List<Hotel>>(hotelService.getHotelList(city, checkIn, checkOut), HttpStatus.OK);


        List<Hotel> myHotel = hotelService.getHotelList(city, checkIn, checkOut);

            /* if(myHotel.size()==0) {
                 throw new NoHotelFound();
             }

             */




        return res;
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@Valid @RequestBody Hotel hotel) {


        ResponseEntity<Hotel> h = null;

        h = new ResponseEntity<Hotel>(hotelService.saveHotel(hotel), HttpStatus.CREATED);
        logger.info("New hotel is added in database with hotel Id " + hotel.getHotelId());

        return h;


    }

    @GetMapping("{cityCode}")
    public List<Hotel> getHotelListById(@PathVariable("cityCode") int cityCode) {
        return hotelService.getHotelListById(cityCode);
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

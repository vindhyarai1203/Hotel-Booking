package com.hotel.booking.controller;

import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;


public class RESTClient {
    private static final String GET_ALL_HOTELS_API1="http://localhost:8080/api/hotel?city=Noida&check_in=2022-03-25&check_out=2022-03-27";
    private static final String GET_ALL_HOTELS_API="http://localhost:8080/api/hotel?city={city}&check_in={check_in}&check_out={check_out}";
    ///private static final String POST_BOOK_HOTEL_API=
    private static final String POST_SAVE_BOOKING_API="http://localhost:8080/api/booking/";
    private static final String CANCEL_BOOKING_API="http://localhost:8080/api/booking/";
    static RestTemplate restTemplate=new RestTemplate();
    static Logger logger = LoggerFactory.getLogger(RESTClient.class);
    public static void main(String[] args) {
        PIN_CODE_DETAILS();

    }
    private static void PIN_CODE_DETAILS()
    {
        String url="https://api.postalpincode.in/pincode/";
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        Map<String,String> mp=new HashMap<>();
        HttpEntity<String> response=restTemplate.getForEntity(url+i,String.class,mp);

        System.out.println(response.getBody());
    }
    private static void CANCEL_BOOKING()
    {

        int id=16;
        /*
        try{
            restTemplate.delete(CANCEL_BOOKING_API+id);

        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }

         */
        //Another Method



    }
    private static void GetAllHotelsApi()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        ResponseEntity<String> response=null;
        try{
            response = restTemplate.exchange(GET_ALL_HOTELS_API1, HttpMethod.GET, entity, String.class);
            if(response.getStatusCode()==HttpStatus.FOUND)
                System.out.println(response.getBody());
            else
                System.out.println("Bad Response received");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private static void GetAllHotelsApiUsingParam()
    {
        Map<String,String> param=new HashMap<>();
        param.put("city","Kanpur");
        param.put("check_in","2022-03-25");
        param.put("check_out","2022-03-27");

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>("parameters",httpHeaders);


      // ResponseEntity<String> response = restTemplate.exchange(GET_ALL_HOTELS_API, HttpMethod.GET, entity, String.class);
       //HttpEntity<List<Hotel>> res=new ResponseEntity<List<Hotel>>(GET_ALL_HOTELS_API,param);
         Hotel[] hotels=restTemplate.getForObject(GET_ALL_HOTELS_API,Hotel[].class,param);

//                TODO:
        /***
         * @value
         * RestClient
         * HtppClient
         */

        System.out.println(hotels.length);
    }
    private static void BookHotelApi()
    {
        Booking booking=new Booking();
        booking.setHotelId(1);
        booking.setCheckin("2022-03-18");
        try{
            ResponseEntity<Booking> response=restTemplate.postForEntity(POST_SAVE_BOOKING_API,booking,Booking.class);
            System.out.println(response.getStatusCode());
            if(response.getStatusCode()==HttpStatus.CREATED)
            {
                logger.info("Booking Confirmed");
                logger.info(response.getBody().getCheckout());
            }
            else
                throw new RuntimeException("Booking failed");
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }

    }
}

package com.hotel.booking;


import com.hotel.booking.controller.HotelController;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes={HotelControllerTesting.class})
public class HotelControllerTesting {

    @Mock
    HotelService hotelService;

    @Mock
    ResponseEntity<List<Hotel>> res;

    @InjectMocks
    HotelController hotelController=new HotelController();

    @Test
    public void hotelControllerTestingGetMethod() throws ParseException {
        List<Hotel> myhotel= new ArrayList<>();
            Date date=new Date(1000000000000l);



        myhotel.add(new Hotel(1,"abc","abc",6,8));
        myhotel.add(new Hotel(2,"abc","abc",6,8));
        res=new ResponseEntity<List<Hotel>>(myhotel,HttpStatus.OK);
        when(hotelService.getHotelList("abc",new Date(),new Date())).thenReturn(myhotel);
        //when(res.getBody()).thenReturn(myhotel);
        ResponseEntity<List<Hotel>> res_1 = hotelController.getHotelList("abc",date,date);
        assertEquals(HttpStatus.OK,res_1.getStatusCode());
        assertEquals(myhotel.size(),2);


    }
}

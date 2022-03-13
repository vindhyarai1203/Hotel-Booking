package com.hotel.booking;
import com.hotel.booking.Repository.BookingRepository;
import com.hotel.booking.service.*;
import com.hotel.booking.Repository.HotelRepository;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingApplicationTests {

	@Mock
	HotelRepository hotelRepository;
	@Mock
	BookingRepository bookingRepository;

	@InjectMocks
	HotelService hotelService=new HotelServiceImpl();


	Date a= new Date();
	String demo=new String();

	@Test
	@Order(1)
	public void HotelServiceTesting() throws ParseException {
		// This block is used to check the number of hotel registered
		List<Hotel> myhotel=new ArrayList<Hotel>();
		List<Booking> mybooking=new ArrayList<Booking>();
		mybooking.add(new Booking(900l,1, demo, demo, 0));


		myhotel.add(new Hotel(2,"abc","Delhi",9,9));
		myhotel.add(new Hotel(3,"abc","Delhi",9,9));
		myhotel.add(new Hotel(4,"abc","Delhi",9,9));
		myhotel.add(new Hotel(6,"abc","Delhi",9,9));
		myhotel.add(new Hotel(7,"abc","Delhi",9,9));
		myhotel.add(new Hotel(8,"abc","Noida",9,9));
		myhotel.add(new Hotel(9,"abc","Jaipur",9,9));
		String s=new String();

		try {
			//when(hotelService.getHotelList("Delhi",a,a)).thenReturn(myhotel);
			when(hotelRepository.findAllByCity("Delhi")).thenReturn(myhotel);

		}
		catch (Exception e)
		{
			System.out.println("------\n"+e.getMessage()+"\n-----------");
		}
		try {
			when(bookingRepository.findAllByHotelIdAndBookingCancelFlagAndCheckin(1,0,s)).thenReturn(mybooking);

		}
		catch (Exception e)
		{
			System.out.println("****\n"+e.getMessage()+"\n****");
		}
		String city="Delhi";

			assertEquals(7, hotelService.getHotelList(city, a, a).size());



	}
	@Test
	@Order(2)
	public void hotelServiceTesting_2()
	{
		
	}






}

package com.hotel.booking.Repository;

import com.hotel.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;


@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {



    public List<Hotel> findByCityCode(int cityCode);
    //Hotel findByDateInside(Date checkIn);

   // @Query (value = "select * from hotels where hotels.city =:c",nativeQuery = true)

    public List<Hotel> findAllByCity(String city);

    Optional<Hotel> findByHotelId(int hotelId);

    //Boolean isHotelExistByHotelId(int hotelId);


    // @Query(value="select count(*) from booking where booking.check_in =:d and booking.hotel_id =:h",
         //   nativeQuery = true)
    //public List<Booking> findAllByHotelId(int hotelId);
    //int occupiedRoomsHotel(@Param("d") Date checkIn,@Param("h") int hotelId);
}

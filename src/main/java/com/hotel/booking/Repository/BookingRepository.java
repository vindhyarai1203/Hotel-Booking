package com.hotel.booking.Repository;

import com.hotel.booking.model.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long> {
    public List<Booking> findAllByHotelIdAndBookingCancelFlagAndCheckin(int hotelId, int b,String checkin);

    void deleteAllByBookingId(long id);

   // List<Booking> findAllByBookingIdAndCancelFlag(long id,int b);

    List<Booking> findAllByBookingIdAndBookingCancelFlag(long id, int i);

   public List<Booking> findAllByCheckin(Date checkIn);







}

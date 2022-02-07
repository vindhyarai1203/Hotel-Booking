package com.hotel.booking.model;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //deifne primary key
    private long bookingId;
    @Column(name = "hotel_id", nullable = false)
    @Positive
    @Digits(message = "Hotel Id must be an integer", integer = 5, fraction = 0)
    private int hotelId;
    //@Column(name="check_in",nullable = false)
    @Column(name = "check_in")
    private String checkin;
    @Column(name = "check_out")
    private String checkout;
    /*   @Column(name="checkin_date",nullable = false)
                        private Date date;

                      */
    @Column(name = "cancel_flag")
    private int bookingCancelFlag;

    public Booking() {

    }
/*  public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   */

    public Booking(long bookingId, int hotelId, String checkin, String checkout, int bookingCancelFlag) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.checkin = (checkin);
        this.checkout = checkout;
        this.bookingCancelFlag = bookingCancelFlag;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public int getHotelId() {
        return hotelId;
    }

 /*   @Column(name="customer_id",nullable = false)
    private int customer_id;

  */

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getBookingCancelFlag() {
        return bookingCancelFlag;
    }

    public void setBookingCancelFlag(int bookingCancelFlag) {
        this.bookingCancelFlag = bookingCancelFlag;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
    /*@ManyToOne()
    private User user;

     */
/*   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Hotel hotel;

 */










    /*
    //for debugging

    Booking()
    {

        System.out.print("Constructor Called Booking");

    }
    */


}

package com.hotel.booking.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;// camel case
    @Column(name = "hotel_name", nullable = false, unique = true)
    @Size(min = 2, max = 20, message = "Please Enter Valid hotel name:")
    private String hotelName;
    @Column(name = "rooms", columnDefinition = "integer default 5")
    @Positive(message = "Rooms cannot be negative or zero")
    @Digits(message = "Hotel Id must be an integer", integer = 3, fraction = 0)
    private int rooms;
    @Column(name = "rooms_available", columnDefinition = "integer default 5")
    private int rooms_available = rooms;    //
    @Column(name = "city", nullable = false)
    @Size(min = 3, max = 20, message = "Please Enter Valid city name:")
    private String city;
    @Column(name = "city_code", nullable = false)
    private int cityCode;

    public Hotel() {

    }

    public Hotel(int i, String hname, String c, int r, int code) {
        this.hotelId = i;
        this.city = c;
        this.rooms = r;
        this.rooms_available = r;
        this.cityCode = 0;
        this.hotelName = hname;
    }

    public int getRooms_available() {
        return rooms_available;
    }

    public void setRooms_available(int rooms_available) {
        this.rooms_available = rooms_available;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }


}

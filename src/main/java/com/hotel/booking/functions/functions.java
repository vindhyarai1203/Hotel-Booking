package com.hotel.booking.functions;

import java.util.Calendar;
import java.util.Date;

public class functions {
    public static Date decrementByOne(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime ( date ); // convert your date to Calendar object
        int daysToDecrement = -1;
        cal.add(Calendar.DATE, daysToDecrement);
        date = cal.getTime();
        return  date;
    }
}

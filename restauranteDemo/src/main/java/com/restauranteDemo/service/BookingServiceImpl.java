package com.restauranteDemo.service;

import com.restauranteDemo.domain.Booking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge J Arcila Santiago
 */
@Service
public class BookingServiceImpl implements BookingService {

    private static List<Booking> bookings;

    @Override
    public List<Booking> findBookingsByDate(Date date) {
        if (null == bookings) {
            return new ArrayList<>();
        }
        Date from = createDate(date, true);
        Date to = createDate(date, false);
        System.out.println("From: " + from.toString() + " to " + to.toString());
        bookings.stream().forEach(b -> {
            System.out.println(b.getBookingDate().toString() + " before to: " + b.getBookingDate().before(to) + ", after from: " + b.getBookingDate().after(from));
        });
        return bookings.stream().filter(b -> b.getBookingDate().after(from) && b.getBookingDate().before(to)).collect(Collectors.toList());
    }

    @Override
    public void scheduleBooking(Booking booking) {
        if (null == bookings) {
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
    }

    private Date createDate(Date date, boolean startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, (startDate) ? 0 : 23);
        cal.set(Calendar.MINUTE, (startDate) ? 0 : 59);
        cal.set(Calendar.SECOND, (startDate) ? 0 : 59);
        return cal.getTime();
    }

    @Override
    public List<Booking> findAll() {
        if (null == bookings) {
            bookings = new ArrayList<>();
        }
        return bookings;
    }

}

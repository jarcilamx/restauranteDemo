package com.restauranteDemo.service;

import com.restauranteDemo.domain.Booking;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge J Arcila Santiago
 */
public interface BookingService {

    public List<Booking> findBookingsByDate(Date date);
    
    public void scheduleBooking(Booking booking);

    public List<Booking> findAll();
}

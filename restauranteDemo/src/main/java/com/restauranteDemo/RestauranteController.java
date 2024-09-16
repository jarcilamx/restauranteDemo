package com.restauranteDemo;

import com.restauranteDemo.commons.Commons;
import com.restauranteDemo.domain.Booking;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.commons.lang.time.DateUtils;

import com.restauranteDemo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jorge J Arcila Santiago
 */
@Controller
@Slf4j
public class RestauranteController {

    @Value("${message.emptyBooking}")
    private String emptyBooking;

    @Autowired
    BookingService bookingService;
    
    private Date bookingDate;

    @GetMapping("/")
    public String init(Model model) {

        if (bookingService.findAll().isEmpty()) {
            Date date = new Date();

            log.info("Creating bookings");
            bookingService.scheduleBooking(new Booking(date, "John", "Doe", 2));

            Calendar calendario = GregorianCalendar.getInstance();
            calendario.setTime(DateUtils.addDays(date, 1));
            bookingService.scheduleBooking(new Booking(new Date(), "Jane", "Smith", 10));
        }

        log.info("Retrieving bookings, and setting into model");
        model.addAttribute("bookings", bookingService.findAll());
        this.bookingDate = null;

        return "indice";
    }

    @GetMapping("/gotoScheduleNewBooking")
    public String gotoScheduleNewBooking(Model model) {
        model.addAttribute("booking", new Booking(new Date(), "", "", 1));
        return "newBooking";
    }

    @PostMapping("scheduleBooking")
    public String scheduleBooking(Booking booking) {
        bookingService.scheduleBooking(booking);
        return "redirect:/";
    }

    @GetMapping("/gotoSearchBookings")
    public String gotoSearchBookings(Model model) {
        log.info("Retrieving bookings, and setting into model");
        model.addAttribute("emptyBooking", emptyBooking);
        model.addAttribute("bookings", bookingService.findBookingsByDate((null != this.bookingDate) ? this.bookingDate : new Date()));
        return "bookings";
    }

    @PostMapping("filterBookings")
    public String filterBookings(String bookingDateStr) {
        this.bookingDate = Commons.convertStringToDate(bookingDateStr);
        return "redirect:/gotoSearchBookings";
    }
}

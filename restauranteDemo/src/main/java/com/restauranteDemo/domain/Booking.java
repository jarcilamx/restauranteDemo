package com.restauranteDemo.domain;

import com.restauranteDemo.commons.Commons;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Jorge J Arcila Santiago
 */
@Data
public class Booking {

    private String bookingDateStr;
    private String firstName;
    private String lastName;
    private Integer tableSize;
    private Date bookingDate;

    public Booking(Date bookingDate, String firstName, String lastName, Integer tableSize) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tableSize = tableSize;
        if (null != bookingDate) {
            this.bookingDate = bookingDate;
            this.bookingDateStr = this.bookingDate.toString();
        }
    }
    
    public void setBookingDateStr(String bookingDateStr) {
        this.bookingDateStr = bookingDateStr;
        if (null != bookingDateStr && !bookingDateStr.isEmpty()) {
            this.bookingDate = Commons.convertStringToDate(bookingDateStr);
        }
    }
}

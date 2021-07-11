package com.tp.pojos;

public class TodosPojo02ActualBookingPojo {

    private int bookingid;
    private TodosPojo02BookingPojo booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public TodosPojo02BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(TodosPojo02BookingPojo booking) {
        this.booking = booking;
    }

    public TodosPojo02ActualBookingPojo() {
    }

    public TodosPojo02ActualBookingPojo(int bookingid, TodosPojo02BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "TodosPojo02ActualBookingPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}

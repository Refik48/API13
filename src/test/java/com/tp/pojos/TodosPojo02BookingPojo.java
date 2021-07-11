package com.tp.pojos;

public class TodosPojo02BookingPojo {

    /*
    "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
     */

    // bookingdates değişkenine datatype olarak BookingDatesPojo atadık. Böylece
    // BookingDatesPojo içerisindeki yapıyı BookingPojo ya gömmüş olduk.


    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private TodosPojo02BookingDatesPojo bookingdates;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public TodosPojo02BookingDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(TodosPojo02BookingDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    public TodosPojo02BookingPojo() {
    }

    public TodosPojo02BookingPojo(String firstname, String lastname,
                                  int totalprice, boolean depositpaid,
                                  TodosPojo02BookingDatesPojo bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }

    @Override
    public String toString() {
        return "TodosPojo02BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}

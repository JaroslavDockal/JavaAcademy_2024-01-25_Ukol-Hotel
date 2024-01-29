package com.engeto.ja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking {
    private int bookingNo;
    private Room room;
    //private int noOfBeds = room.getNoOfBeds();
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private TypeOfStay typeOfStay;
    private List<Guest> otherGuests;
    // Hosty dat do pole o velikosti noOfBeds, ale pak jak to krmit?

    private void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    private void setGuest(Guest guest) {
        this.guest = guest;
    }

    private void setRoom(Room room) {
        this.room = room;
    }

    private void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    private void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    private void setTypeOfStay(TypeOfStay typeOfStay) {
        this.typeOfStay = typeOfStay;
    }

    public void setBooking(int bookingNo, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        this.bookingNo = bookingNo;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.typeOfStay = typeOfStay;
    }

    //Zkrácená verze - od dnes + dalších 6 dní, soukromý pobyt
    public void setBooking(int bookingNo, Guest guest, Room room) {
        LocalDate currentDate = LocalDate.now();
        this.bookingNo = bookingNo;
        this.guest = guest;
        this.room = room;
        this.checkInDate = currentDate;
        this.checkOutDate = currentDate.plusDays(6);
        this.typeOfStay = TypeOfStay.PRIVATE;
    }

    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setBookingNo(bookingNo);
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setTypeOfStay(typeOfStay);

        return booking;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    @Override public String toString() {
        BigDecimal pricePerNight = room.getPricePerNight();
        long stayDuration = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return "Rezervace č. " + bookingNo + ", " + checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " - " +
                checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ", " + guest.toString() +", " + room.toString() +
                " Celkem k úhradě: " + String.format("%.0f",pricePerNight.multiply(BigDecimal.valueOf(stayDuration))) + " CZK.";
    }

}

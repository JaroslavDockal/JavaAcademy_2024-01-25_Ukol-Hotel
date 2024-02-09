package com.engeto.ja.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingNo;
    private Room room;
    private List<Guest> guests = new ArrayList<>();
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private TypeOfStay typeOfStay;
    private int bookingLength;
    private BigDecimal totalPrice;

    private void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    private void setGuest(List<Guest> guests) {
        this.guests = guests;
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
        this.guests.add(guest);
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.typeOfStay = typeOfStay;
        this.bookingLength = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal pricePerNight = room.getPricePerNight();
        this.totalPrice = pricePerNight.multiply(BigDecimal.valueOf(bookingLength));
    }

    public void setBooking(Guest guest) {
        this.guests.add(guest);
    }

    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setBookingNo(bookingNo);
        booking.setGuest(guests);
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setTypeOfStay(typeOfStay);

        return booking;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public TypeOfStay getTypeOfStay() {
        return typeOfStay;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public int getBookingLength(){
        return bookingLength;
    }

    public BigDecimal getPrice(){
        return totalPrice;
    }

    @Override public String toString() {
        return "Rezervace č. " + bookingNo + ", " + checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " - " +
                checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ", " + guests.getFirst().toString() +", " + room.toString() +
                " Celkem k úhradě: " + String.format("%.0f",totalPrice) + " CZK.";
    }

}

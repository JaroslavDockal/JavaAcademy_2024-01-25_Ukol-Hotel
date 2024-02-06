package com.engeto.ja.hotel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking {
    private int bookingNo;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private TypeOfStay typeOfStay;
    private boolean extraGuest = Boolean.FALSE;

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

    private void setExtraGuest(boolean extraGuest) {
        this.extraGuest = extraGuest;
    }

    public void setBooking(int bookingNo, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        this.bookingNo = bookingNo;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.typeOfStay = typeOfStay;
    }

    public void setBooking(int bookingNo, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay, boolean extraGuest) {
        this.bookingNo = bookingNo;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.typeOfStay = typeOfStay;
        this.extraGuest = Boolean.TRUE;
    }

    public Booking getBooking() {
        Booking booking = new Booking();
        booking.setBookingNo(bookingNo);
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setTypeOfStay(typeOfStay);
        booking.setExtraGuest(extraGuest);

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

    @Override public String toString() {
        BigDecimal pricePerNight = room.getPricePerNight();
        long stayDuration = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        String totalPriceStr;

        if(extraGuest){
            totalPriceStr = " Celkem k úhradě: " + String.format("%.0f",pricePerNight.multiply(BigDecimal.valueOf(stayDuration))) + " CZK.";
        }else{
            totalPriceStr = "";
        }

        return "Rezervace č. " + bookingNo + ", " + checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " - " +
                checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ", " + guest.toString() +", " + room.toString() +
                totalPriceStr;
    }

}

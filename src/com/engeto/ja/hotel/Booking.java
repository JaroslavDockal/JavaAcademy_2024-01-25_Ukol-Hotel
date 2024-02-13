package com.engeto.ja.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    // Private fields to store booking details
    private final Room room;
    private final List<Guest> guests = new ArrayList<>();
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final TypeOfStay typeOfStay;

    // Constructor for the Booking class
    public Booking(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        this.guests.add(guest);
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.typeOfStay = typeOfStay;
    }

    public Booking(Room room, Guest guest) {
        this.guests.add(guest);
        this.room = room;
        this.checkInDate = LocalDate.now();
        this.checkOutDate = LocalDate.now().plusDays(6);
        this.typeOfStay = TypeOfStay.PRIVATE;
    }

    // Method to add guest to the booking
    public void addGuestToBooking(Guest guest) {
        this.guests.add(guest);
    }


    // Getter methods for private fields
    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public TypeOfStay getTypeOfStay() {
        return typeOfStay;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public int getBookingLength(){
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public BigDecimal getPrice(){
        return room.getPricePerNight().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(checkInDate, checkOutDate)));
    }

    public Booking getBooking(){
        return this;
    }

    // Override toString method to generate a string representation of the booking
    @Override public String toString() {
        return "Rezervace od " + checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " do " + checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ", " +
                guests.getFirst().toString() +", " + room.toString() +
                " Celkem k úhradě: " + String.format("%.0f",room.getPricePerNight().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(checkInDate, checkOutDate)))) + " CZK.";
    }

}

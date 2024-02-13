package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager {
    // List to store all bookings
    List<Booking> bookings = new ArrayList<>();

    // Method to add a booking to the list
    public void addBooking(Booking booking){

        // Print information about the booking being created
        System.out.println("\nVytvářím rezervaci: Pokoj č. " + booking.getRoom().getRoomNo() + " pro hosta " + booking.getGuests().getFirst().getFullName() + " (" +
                booking.getCheckInDate().format(DateTimeFormatter.ofPattern("d.M.y")) + " až " + booking.getCheckOutDate().format(DateTimeFormatter.ofPattern("d.M.y")) + ")");

        // Check for overlapping booking dates
        boolean bookingOverlap = false;
        for (Booking existingBooking : bookings) {
            bookingOverlap = (dateRangesDoNotOverlap(existingBooking.getRoom() ,existingBooking.getCheckInDate(), existingBooking.getCheckOutDate(), booking.getRoom(), booking.getCheckInDate(), booking.getCheckOutDate()));
        }

        // Check the validity of the selected room
        if (booking.getRoom() != null) {
            // Check if there is an existing booking for the selected room and dates
            Booking existingBooking = findBooking(bookings, booking.getRoom(), booking.getCheckInDate(), booking.getCheckOutDate());

            // If there is an overlap with existing bookings
            if (bookingOverlap) {
                if(existingBooking!=null){
                    // Add another guest if there is a free bed available
                    if (booking.getRoom().getNoOfBeds() > Objects.requireNonNull(existingBooking).getGuests().size()) {
                        existingBooking.addGuestToBooking(booking.getGuests().getFirst());
                        System.out.println("Do rezervace úspěšně přidán další host.");
                    } else {
                        System.out.println("Vytvoření rezervace nebylo úspěšné - na požadovaném pokoji není volná postel.");
                    }
                } else {
                    System.out.println("Vytvoření rezervace nebylo úspěšné - požadovaný pokoj není v daných datech k dispozici.");
                }
            } else {
                // If there is no overlap, create a new booking
                if(checkDateOrder(booking.getCheckInDate(), booking.getCheckOutDate())){
                    bookings.add(booking);
                    System.out.println("Rezervace úspěšně vytvořena.");
                } else {
                    System.out.println("Vytvoření rezervace nebylo úspěšné - špatně zadané datum od/do.");
                }
            }
        } else {
            System.out.println("Vytvoření rezervace nebylo úspěšné - " + booking.getGuests().getFirst().getName() + " spí venku!");
        }
    }

    // Method to check if a booking already exists
    private Booking findBooking(List<Booking> bookings, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (Booking booking : bookings) {
            if (room == booking.getRoom() &&
                    checkInDate.equals(booking.getCheckInDate()) &&
                    checkOutDate.equals(booking.getCheckOutDate())) {
                System.out.println("Pro požadované období už existuje rezervace č. " + bookings.indexOf(booking));
                return booking.getBooking();
            }
        }
        return null;
    }

    // Method to check if check-in and check-out dates are in correct order
    private boolean checkDateOrder(LocalDate checkInDate, LocalDate checkOutDate) {
        return checkInDate.isBefore(checkOutDate);
    }

    // Method to check if date ranges of two bookings do not overlap
    private boolean dateRangesDoNotOverlap(Room room1, LocalDate checkIn1, LocalDate checkOut1, Room room2, LocalDate checkIn2, LocalDate checkOut2) {
        if (checkIn2.isBefore(checkIn1)){
            return checkIn1.isBefore(checkOut2) && room1 == room2;
        }else {
            return checkIn2.isBefore(checkOut1) && room1 == room2;
        }
    }

    // Method to get all bookings
    public List<Booking> getBookings(){
        return bookings;
    }

    // Method to clear all bookings
    public void clearBookings(){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Mažu seznam rezervací...");
        bookings.clear();
        System.out.println("Seznam rezervací vymazán.");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void printAllGuests(){
        List<Guest> allGuests = new ArrayList<>();

        for (Booking booking : bookings) {
            allGuests.addAll(booking.getGuests());
        }

        allGuests.sort(Comparator
                .comparing(Guest::getSurname)
                .thenComparing(Guest::getName)
                .thenComparing(Guest::getDateOfBirth));

        List<Guest> uniqueGuests = new ArrayList<>();
        for (int i = 0; i < allGuests.size(); i++) {
            if (i == 0 || !allGuests.get(i).equals(allGuests.get(i - 1))) {
                uniqueGuests.add(allGuests.get(i));
            }
        }

        for (Guest guest : uniqueGuests) {
            System.out.println(guest.getSurname() + " " + guest.getName() + ", " + guest.getDateOfBirth());
        }
    }

    // Method to get the number of working bookings
    public int getNumberOfWorkingBookings(){
        int noOfWorkingBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.BUSINESS){
                noOfWorkingBookings++;
            }
        }
        return noOfWorkingBookings;
    }

    // Method to get the number of private bookings
    public int getNumberOfPrivateBookings(){
        int noOfPrivateBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.PRIVATE){
                noOfPrivateBookings++;
            }
        }
        return noOfPrivateBookings;
    }

    // Method to calculate the average number of guests per booking
    public double getAverageGuests(){
        int noOfGuests = 0;
        for (Booking booking : bookings) {
            noOfGuests += booking.getGuests().size();
        }

        System.out.println("Celkový počet rezervací: " + bookings.size());
        System.out.println("Celkový počet hostů : " + noOfGuests);

        return (double) noOfGuests/bookings.size();
    }
}


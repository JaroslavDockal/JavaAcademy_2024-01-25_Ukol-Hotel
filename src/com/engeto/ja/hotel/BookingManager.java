package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.engeto.ja.hotel.Rooms.*;

public class BookingManager {
    // Static lists to hold rooms, bookings, and guests
    static List<Room> rooms = Rooms.createRooms();
    static List<Booking> bookings = new ArrayList<>();
    static List<Guest> guests = new ArrayList<>();

    // Method to add a new booking with specific parameters
    public static void addBooking(int roomNo, String name, String surname, LocalDate dateOfBirth,
                                  LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay){
        createBooking(roomNo, name, surname, dateOfBirth, checkInDate, checkOutDate, typeOfStay);
    }

    // Overloaded method to add a new booking with default parameters
    public static void addBooking(int roomNo, String name, String surname, LocalDate dateOfBirth){
        createBooking(roomNo, name, surname, dateOfBirth, LocalDate.now(), LocalDate.now().plusDays(6), TypeOfStay.PRIVATE);
    }

    // Method to check if a guest already exists
    private static boolean guestExists(List<Guest> guests, Guest newGuest) {
        for (Guest guest : guests) {
            if (newGuest.getName().equals(guest.getName()) &&
                    newGuest.getSurname().equals(guest.getSurname()) &&
                    newGuest.getDateOfBirth().equals(guest.getDateOfBirth())) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a booking already exists
    private static int bookingExists(List<Booking> bookings, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (Booking booking : bookings) {
            if (room.getRoomNo() == booking.getRoom().getRoomNo() &&
                    checkInDate.equals(booking.getCheckInDate()) &&
                    checkOutDate.equals(booking.getCheckOutDate())) {
                System.out.println("Pro požadované období už existuje rezervace (č. " + booking.getBookingNo() + ").");
                return booking.getBookingNo();
            }
        }
        return 0;
    }

    // Method to check if check-in and check-out dates are in correct order
    private static boolean checkDateOrder(LocalDate checkInDate, LocalDate checkOutDate) {
        return checkInDate.isBefore(checkOutDate);
    }

    // Method to check if date ranges of two bookings do not overlap
    private static boolean dateRangesDoNotOverlap(int roomNo1, LocalDate checkIn1, LocalDate checkOut1, int roomNo2, LocalDate checkIn2, LocalDate checkOut2) {
        if (checkIn2.isBefore(checkIn1)){
            return checkIn1.isBefore(checkOut2) && roomNo1 == roomNo2;
        }else {
            return checkIn2.isBefore(checkOut1) && roomNo1 == roomNo2;
        }
    }

    // Method to create a new booking
    private static void createBooking(int roomNo, String name, String surname, LocalDate dateOfBirth,
                                      LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        // Create a new booking and guest
        Booking booking = new Booking();
        Guest guest = new Guest();
        guest.setGuest(name, surname, dateOfBirth);

        // Check if the guest already exists in the guest list
        if (!guestExists(guests, guest)) {
            guests.add(guest);
        }

        // Find the room by its number
        Room selectedRoom = findRoomByNumber(rooms, roomNo);

        // Print information about the booking being created
        System.out.println("\nVytvářím rezervaci: Pokoj č. " + roomNo + " pro hosta " + guest.getFullName() + " (" +
                checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " až " + checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ")");
        System.out.println(roomValidationStatus(selectedRoom, roomNo));

        // Determine the highest booking number
        int highestBookingNo = 0;
        for (Booking existingBooking : bookings) {
            highestBookingNo = Math.max(existingBooking.getBookingNo(), highestBookingNo);
        }

        // Check for overlapping booking dates
        boolean bookingOverlap = false;
        for (Booking existingBooking : bookings) {
            bookingOverlap = (dateRangesDoNotOverlap(existingBooking.getRoom().getRoomNo() ,existingBooking.getCheckInDate(), existingBooking.getCheckOutDate(), roomNo, checkInDate, checkOutDate));

            //Debug messages:
            //System.out.println(existingBooking.getRoom().getRoomNo()+" "+existingBooking.getCheckInDate()+" "+existingBooking.getCheckOutDate()+", "+roomNo+" "+checkInDate+" "+checkOutDate);
            //System.out.println(bookingOverlap ? "Rezervace " + roomNo + " se překrývá s rezervací " + existingBooking.getBookingNo() + "." : "Rezervace " + roomNo + " se nepřekrývá se s rezervací " + existingBooking.getBookingNo() + ".");
        }

        // Check the validity of the selected room
        if (roomIsValid(selectedRoom)) {
            // Check if there is an existing booking for the selected room and dates
            int existingBookingNo = bookingExists(bookings, selectedRoom, checkInDate, checkOutDate );

            // If there is an overlap with existing bookings
            if (bookingOverlap) {
                if(existingBookingNo!=0){
                    // Add a guest to an existing booking
                    booking.setBooking(existingBookingNo, guest, selectedRoom, checkInDate, checkOutDate, typeOfStay);

                    // Add another guest if there is a free bed available
                    if (selectedRoom.getNoOfBeds() > Objects.requireNonNull(getBooking(existingBookingNo)).getGuests().size()) {
                        Guest newGuest = new Guest();
                        newGuest.setGuest(name, surname, dateOfBirth);
                        Objects.requireNonNull(getBooking(existingBookingNo)).setBooking(newGuest);
                        System.out.println("Do rezervace úspěšně přidán další host.");
                    } else {
                        System.out.println("Vytvoření rezervace nebylo úspěšné - na požadovaném pokoji není volná postel.");
                    }
                } else {
                    System.out.println("Vytvoření rezervace nebylo úspěšné - požadovaný pokoj není v daných datech k dispozici.");
                }
            } else {
                // If there is no overlap, create a new booking
                if(checkDateOrder(checkInDate, checkOutDate)){
                    booking.setBooking(highestBookingNo + 1, guest, selectedRoom, checkInDate, checkOutDate, typeOfStay);
                    bookings.add(booking);
                    System.out.println("Rezervace úspěšně vytvořena.");
                } else {
                    System.out.println("Vytvoření rezervace nebylo úspěšné - špatně zadané datum od/do.");
                }
            }
        } else {
            System.out.println("Vytvoření rezervace nebylo úspěšné - " + guest.getName() + " spí venku!");
        }
    }

    // Method to get a booking by its number
    public static Booking getBooking(int bookingNo){
        for (Booking booking : bookings) {
            if(booking.getBookingNo() == bookingNo){
                return booking;
            }
        }
        return null;
    }

    // Method to get all bookings
    public static List<Booking> getBookings(){
        return bookings;
    }

    // Method to clear all bookings
    public static void clearBookings(){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Mažu seznam rezervací...");
        bookings.clear();
        System.out.println("Seznam rezervací vymazán.");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Method to get all guests
    public static void getGuests(){
        guests.sort(Comparator.comparing(Guest::getSurname));

        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }

    // Method to get the number of working bookings
    public static int getNumberOfWorkingBookings(){
        int noOfWorkingBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.BUSINESS){
                noOfWorkingBookings++;
            }
        }
        return noOfWorkingBookings;
    }

    // Method to get the number of private bookings
    public static int getNumberOfPrivateBookings(){
        int noOfPrivateBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.PRIVATE){
                noOfPrivateBookings++;
            }
        }
        return noOfPrivateBookings;
    }

    // Method to calculate the average number of guests per booking
    public static double getAverageGuests(){
        int noOfGuests = 0;
        for (Booking booking : bookings) {
            noOfGuests += booking.getGuests().size();
        }

        System.out.println("Celkový počet rezervací: " + bookings.size());
        System.out.println("Celkový počet hostů : " + noOfGuests);

        return (double) noOfGuests/bookings.size();
    }
}


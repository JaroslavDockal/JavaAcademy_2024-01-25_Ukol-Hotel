package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.engeto.ja.hotel.Rooms.*;

public class BookingManager {
    static List<Room> rooms = Rooms.createRooms();
    static List<Booking> bookings = new ArrayList<>();
    static List<Guest> guests = new ArrayList<>();

    public static void addBooking(int roomNo, String name, String surname, LocalDate dateOfBirth,
                                  LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay){
        createBooking(roomNo, name, surname, dateOfBirth, checkInDate, checkOutDate, typeOfStay);
    }

    public static void addBooking(int roomNo, String name, String surname, LocalDate dateOfBirth){
        createBooking(roomNo, name, surname, dateOfBirth, LocalDate.now(), LocalDate.now().plusDays(6), TypeOfStay.PRIVATE);

    }

    //region Pomocné funkce
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

    private static boolean dateRangesDoNotOverlap(int roomNo1, LocalDate checkIn1, LocalDate checkOut1, int roomNo2, LocalDate checkIn2, LocalDate checkOut2) {
        return (checkIn1.isBefore(checkOut2) || checkOut1.isBefore(checkIn2)) && roomNo1 == roomNo2;
    }

    private static int noOfBookingsRecorded(List<Booking> bookings, int bookingNoToCount) {
        int count = 0;

        for (Booking booking : bookings) {
            if (booking.getBookingNo() == bookingNoToCount) {
                count++;
            }
        }
        return count;
    }
    //endregion

    //region Vytvoření rezervace
    private static void createBooking(int roomNo, String name, String surname, LocalDate dateOfBirth,
                                      LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        Booking booking = new Booking();
        Guest guest = new Guest();
        guest.setGuest(name, surname, dateOfBirth);
        if (!guestExists(guests, guest)) {
            guests.add(guest);
        }
        Room selectedRoom = findRoomByNumber(rooms, roomNo);
        System.out.println("\nVytvářím rezervaci: Pokoj č. " + roomNo + " pro hosta " + guest.getFullName() + " (" +
                checkInDate.format(DateTimeFormatter.ofPattern("d.M.y")) + " až " + checkOutDate.format(DateTimeFormatter.ofPattern("d.M.y")) + ")");
        System.out.println(roomIsValidMsg(selectedRoom, roomNo));
        int highestBookingNo = 0;
        for (Booking existingBooking : bookings) {
            highestBookingNo = Math.max(existingBooking.getBookingNo(), highestBookingNo);
        }

        boolean bookingOverlap = false;
        for (Booking existingBooking : bookings) {
            bookingOverlap = (dateRangesDoNotOverlap(existingBooking.getRoom().getRoomNo() ,existingBooking.getCheckInDate(), existingBooking.getCheckOutDate(), roomNo, checkInDate, checkOutDate));
        }

        if (roomIsValid(selectedRoom)) {
            int existingBookingNo = bookingExists(bookings, selectedRoom, checkInDate, checkOutDate );

            if (bookingOverlap) {
                if(existingBookingNo!=0){
                    booking.setBooking(existingBookingNo, guest, selectedRoom, checkInDate, checkOutDate, typeOfStay, Boolean.TRUE);
                    int noOfExistingBookings = noOfBookingsRecorded(bookings, existingBookingNo);
                    if (selectedRoom.getNoOfBeds() > noOfExistingBookings) {
                        bookings.add(booking);
                        System.out.println("Do rezervace úspěšně přidán další host.");
                    } else {
                        System.out.println("Vytvoření rezervace nebylo úspěšné - na požadovaném pokoji není volná postel.");
                    }
                } else {
                    System.out.println("Vytvoření rezervace nebylo úspěšné - požadovaný pokoj není v daných datech k dispozici.");
                }
            } else {
                booking.setBooking(highestBookingNo + 1, guest, selectedRoom, checkInDate, checkOutDate, typeOfStay);
                bookings.add(booking);
                System.out.println("Rezervace úspěšně vytvořena.");
            }
        } else {
            System.out.println("Vytvoření rezervace nebylo úspěšné - " + guest.getName() + " spí venku!");
        }
    }
    //endregion

    //region
    public static void getBookings(){
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public static void getGuests(){
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }
    //endregion

    public static int getNumberOfWorkingBookings(){
        int noOfWorkingBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.BUSINESS){
                noOfWorkingBookings++;
            }
        }
        return noOfWorkingBookings;
    }

    public static int getNumberOfPrivateBookings(){
        int noOfPrivateBookings = 0;
        for (Booking booking : bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.PRIVATE){
                noOfPrivateBookings++;
            }
        }
        return noOfPrivateBookings;
    }

    // Asi není ideál, ale účel to splní
    public static double getAverageGuests(){
        int maxBookingNumber = 0;

        for (Booking booking : bookings) {
            maxBookingNumber = Math.max(maxBookingNumber,booking.getBookingNo());
        }

        System.out.println("Celkový počet rezervací: " + maxBookingNumber);
        System.out.println("Celkový počet záznamů : " + bookings.size());

        return (double) bookings.size()/maxBookingNumber;
    }

    public static void clearBookings(){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Mažu seznam rezervací...");
        bookings.clear();
        System.out.println("Seznam rezervací vymazán.");
    }
    //endregion

}


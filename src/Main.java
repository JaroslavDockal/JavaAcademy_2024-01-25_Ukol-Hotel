import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.engeto.ja.hotel.*;

public class Main {

    // Creating booking manager
    static BookingManager bookingManager = new BookingManager();

    // Creating rooms
    static Room room01 = new Room(1, 2, true, true, 1000);
    static Room room02 = new Room(2, 2, true, true, 1000);
    static Room room03 = new Room(3, 3, false, true, 2400);
    static Room room04 = new Room(4, 1, false, false, 900);
    static Room room05 = new Room(5, 2, false, true, 1200);
    static Room room06 = new Room(6, 2, true, false, 900);
    static Room room07 = new Room(7, 3, true, true, 2800);
    static Room room08 = new Room(8, 1, false, false, 700);
    static Room room09 = new Room(9, 2, false, true, 1100);
    static Room room10 = new Room(10, 3, true, true, 2800);

    public static void main(String[] args) {

        // Adding various bookings
        bookingManager.addBooking(new Booking(room01,new Guest("Adéla","Malíková",LocalDate.of(1993, 3, 13)),
                LocalDate.of(2024, 7, 19), LocalDate.of(2024, 7, 26), TypeOfStay.BUSINESS));

        bookingManager.addBooking(new Booking(room02,new Guest("Jan","Dvořáček",LocalDate.of(1995, 5, 5))));
        bookingManager.addBooking(new Booking(room02,new Guest("Adéla","Malíková",LocalDate.of(1993, 3, 13))));

        bookingManager.addBooking(new Booking(room06,new Guest("Jan","Novák",LocalDate.of(1988, 7, 22)),
                LocalDate.of(2024, Month.FEBRUARY,29),LocalDate.of(2024,Month.MARCH,3),TypeOfStay.BUSINESS));

        bookingManager.addBooking(new Booking(room06,new Guest("Martin","Novák",LocalDate.of(1976, 4, 6)),
                LocalDate.of(2024, 2,27),LocalDate.of(2024,3,1),TypeOfStay.PRIVATE));

        bookingManager.addBooking(new Booking(room05,new Guest("Martin","Novák",LocalDate.of(1976, 4, 6))));
        bookingManager.addBooking(new Booking(room05,new Guest("Josef","Vyskočil",LocalDate.of(1944, 7, 22))));
        bookingManager.addBooking(new Booking(room05,new Guest("Lojza","Vopršálek",LocalDate.of(1965, 3, 14))));

        // Printing statistics of all bookings
        printAllBookingsStatistics();

        // Clearing bookings
        bookingManager.clearBookings();

        // Refilling bookings
        fillBookings();

        // Printing statistics of all bookings after refilling
        printAllBookingsStatistics();

    }

    // Method to fill bookings with predefined data
    public static void fillBookings(){
        bookingManager.addBooking(new Booking(room03,new Guest("Karel","Dvořák",LocalDate.of(1990, 5, 15)),
                LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 7), TypeOfStay.BUSINESS));

        bookingManager.addBooking(new Booking(room02,new Guest("Karel","Dvořák",LocalDate.of(1979, 1, 3)),
                LocalDate.of(2024, 7, 18), LocalDate.of(2024, 7, 21), TypeOfStay.PRIVATE));

        // Adding 10 bookings with 2 nights each
        for (int i = 0; i < 20; i=i+2) {
            int noOfNights = 1;
            LocalDate firstBookingDate = LocalDate.of(2024, 8,1);
            LocalDate checkInDate = firstBookingDate.plusDays(i);
            LocalDate checkOutDate = checkInDate.plusDays(noOfNights);

            bookingManager.addBooking(new Booking(room02,new Guest("Karolína","Tmavá",LocalDate.of(1987, 4, 9)),
                    checkInDate, checkOutDate, TypeOfStay.PRIVATE));
        }

        bookingManager.addBooking(new Booking(room03,new Guest("Karolína","Tmavá",LocalDate.of(1987, 4, 9)),
                LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31), TypeOfStay.BUSINESS));
    }

    // Method to print details of all bookings
    public static void printAllBookings(){
        List<Booking> bookings = bookingManager.getBookings();
        for (Booking booking : bookings) {
            System.out.println(getBookingFormatted(booking));
        }
    }

    // Method to get formatted booking details
    public static String getBookingFormatted(Booking booking){
        return booking.getCheckInDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " až " +
                booking.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ": " +
                booking.getGuests().getFirst().toString() + " (" + booking.getGuests().size() + ", " +
                (booking.getRoom().hasSeaView() ? "ano" : "ne") + ") za " + booking.getPrice() + " CZK.";
    }

    // Method to print details of private bookings
    public static void printPrivateBookings(int noOfBookings){
        List<Booking> Bookings = bookingManager.getBookings();
        int counter = 0;
        for (Booking booking : Bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.PRIVATE){
                System.out.println(getBookingFormatted(booking));
                counter++;
            }
            if(counter == noOfBookings){
                break;
            }
        }
    }

    // Method to print details of business bookings
    public static void printBusinessBookings(int noOfBookings){
        List<Booking> Bookings = bookingManager.getBookings();
        int counter = 0;
        for (Booking booking : Bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.BUSINESS){
                System.out.println(getBookingFormatted(booking));
                counter++;
            }
            if(counter == noOfBookings){
                break;
            }
        }
    }

    // Method to print guest statistics
    public static void printGuestStatistics(){
        List<Booking> Bookings = bookingManager.getBookings();
        int oneGuest = 0;
        int twoGuests = 0;
        int moreGuests = 0;
        for (Booking booking : Bookings) {
            if(booking.getGuests().size() == 1){
                oneGuest++;
            } else if(booking.getGuests().size() == 2){
                twoGuests++;
            } else {
                moreGuests++;
            }
        }
        System.out.println("Počet rezervací s jedním hostem: " + oneGuest);
        System.out.println("Počet rezervací se dvěma hosty: " + twoGuests);
        System.out.println("Počet rezervací s více hosty: " + moreGuests);
    }

    // Method to calculate average stay length
    public static double getAverageStayLenght(){
        List<Booking> Bookings = bookingManager.getBookings();
        int totalLenght = 0;
        for (Booking booking : Bookings) {
            totalLenght += booking.getBookingLength();
        }
        return (double) totalLenght/Bookings.size();
    }

    // Method to print statistics of all bookings
    public static void printAllBookingsStatistics(){
        String stringLine = "--------------------------------------------------------------------------------------";

        System.out.println(stringLine);
        System.out.println("Výpis všech vytvořených rezervací: ");
        printAllBookings();

        System.out.println(stringLine);
        System.out.println("Seznam všech hostů: ");
        bookingManager.printAllGuests();

        System.out.println(stringLine);
        System.out.println("Statistiky rezervací");
        System.out.println("Průměrný počet hostů na rezervaci: " + String.format("%.2f",bookingManager.getAverageGuests()));
        System.out.println("Počet rezervací pracovnícho pobytu: " + bookingManager.getNumberOfWorkingBookings());
        System.out.println("Počet rezervací soukromého pobytu: " + bookingManager.getNumberOfPrivateBookings());
        printGuestStatistics();
        System.out.println("Průměrná délka pobytu: " + String.format("%.2f",getAverageStayLenght()));

        int noOfBookingToPrint = 8;
        System.out.println(stringLine);
        System.out.println("Prvních " + noOfBookingToPrint + " soukromých rezervací: ");
        printPrivateBookings(noOfBookingToPrint);

        System.out.println(stringLine);
        System.out.println("Prvních " + noOfBookingToPrint + " pracovních rezervací: ");
        printBusinessBookings(noOfBookingToPrint);

    }
}

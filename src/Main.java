import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.engeto.ja.hotel.Booking;
import com.engeto.ja.hotel.TypeOfStay;
import static com.engeto.ja.hotel.BookingManager.*;

public class Main {

    public static void main(String[] args) {

        // Adding various bookings
        addBooking(1,"Adéla","Malíková",LocalDate.of(1993, 3, 13),
                LocalDate.of(2024, 7, 19), LocalDate.of(2024, 7, 26), TypeOfStay.BUSINESS);

        addBooking(2,"Jan","Dvořáček",LocalDate.of(1995, 5, 5));
        addBooking(2,"Adéla","Malíková",LocalDate.of(1993, 3, 13));

        addBooking(6,"Jan","Novák",LocalDate.of(1988, 7, 22),
                LocalDate.of(2024, Month.FEBRUARY,29),LocalDate.of(2024,Month.MARCH,3),TypeOfStay.BUSINESS);

        addBooking(12,"Josef","Vyskočil",LocalDate.of(1944, 7, 22));

        addBooking(6,"Martin","Novák",LocalDate.of(1976, 4, 6),
                LocalDate.of(2024, 2,27),LocalDate.of(2024,3,1),TypeOfStay.PRIVATE);

        addBooking(5,"Martin","Novák",LocalDate.of(1976, 4, 6));
        addBooking(5,"Josef","Vyskočil",LocalDate.of(1944, 7, 22));
        addBooking(5,"Lojza","Vopršálek",LocalDate.of(1965, 3, 14));

        // Printing statistics of all bookings
        printAllBookingsStatistics();

        // Clearing bookings
        clearBookings();

        // Refilling bookings
        fillBookings();

        // Printing statistics of all bookings after refilling
        printAllBookingsStatistics();

    }

    // Method to fill bookings with predefined data
    public static void fillBookings(){
        addBooking(3,"Karel","Dvořák",LocalDate.of(1990, 5, 15),
                LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 7), TypeOfStay.BUSINESS);

        addBooking(2,"Karel","Dvořák",LocalDate.of(1979, 1, 3),
                LocalDate.of(2024, 7, 18), LocalDate.of(2024, 7, 21), TypeOfStay.PRIVATE);

        for (int i = 0; i < 20; i=i+2) {
            int noOfNights = 1;
            LocalDate firstBookingDate = LocalDate.of(2024, 8,1);
            LocalDate checkInDate = firstBookingDate.plusDays(i);
            LocalDate checkOutDate = checkInDate.plusDays(noOfNights);

            addBooking(2,"Karolína","Tmavá",LocalDate.of(1987, 4, 9),
                    checkInDate, checkOutDate, TypeOfStay.PRIVATE);
        }

        addBooking(3,"Karolína","Tmavá",LocalDate.of(1987, 4, 9),
                LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31), TypeOfStay.BUSINESS);
    }

    // Method to print details of all bookings
    public static void printAllBookings(){
        List<Booking> bookings = getBookings();
        for (Booking booking : bookings) {
            System.out.println(getBookingFormated(booking));
        }
    }

    // Method to get formatted booking details
    public static String getBookingFormated(Booking booking){
        return booking.getCheckInDate().format(DateTimeFormatter.ofPattern("d.M.y")) + " až " +
                    booking.getCheckOutDate().format(DateTimeFormatter.ofPattern("d.M.y")) + ": " +
                    booking.getGuests().getFirst().toString() + " (" + booking.getGuests().size() + ", " +
                    (booking.getRoom().hasSeaView() ? "ano" : "ne") + ") za " + booking.getPrice() + " CZK.";
    }

    // Method to print details of private bookings
    public static void printPrivateBookings(int noOfBookings){
        List<Booking> Bookings = getBookings();
        int counter = 0;
        for (Booking booking : Bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.PRIVATE){
                System.out.println(getBookingFormated(booking));
                counter++;
            }
            if(counter == noOfBookings){
                break;
            }
        }
    }

    // Method to print details of business bookings
    public static void printBusinessBookings(int noOfBookings){
        List<Booking> Bookings = getBookings();
        int counter = 0;
        for (Booking booking : Bookings) {
            if(booking.getTypeOfStay() == TypeOfStay.BUSINESS){
                System.out.println(getBookingFormated(booking));
                counter++;
            }
            if(counter == noOfBookings){
                break;
            }
        }
    }

    // Method to print guest statistics
    public static void printGuestStatistics(){
        List<Booking> Bookings = getBookings();
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
        List<Booking> Bookings = getBookings();
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
        getGuests();

        System.out.println(stringLine);
        System.out.println("Statistiky rezervací");
        System.out.println("Průměrný počet hostů na rezervaci: " + String.format("%.2f",getAverageGuests()));
        System.out.println("Počet rezervací pracovnícho pobytu: " + getNumberOfWorkingBookings());
        System.out.println("Počet rezervací soukromého pobytu: " + getNumberOfPrivateBookings());
        printGuestStatistics();
        System.out.println("Průměrná délka pobytu: " + String.format("%.2f",getAverageStayLenght()));

        System.out.println(stringLine);
        System.out.println("Prvních 8 soukromých rezervací: ");
        printPrivateBookings(8);

        System.out.println(stringLine);
        System.out.println("Prvních 8 pracovních rezervací: ");
        printBusinessBookings(8);

    }
}

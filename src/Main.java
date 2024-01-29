/*
Potřebujeme evidovat hosty (guest), které ubytováváme v hotelu.
O každém hostu musíme uložit jméno, příjmení a datum narození.

Hosty vždy ubytováváme na pokojích (room). Pokoj je identifikován svým číslem.
O každém pokoji chceme evidovat, kolik lůžek tam je (neřeš přistýlky apod.),
zda má pokoj balkón a zda má výhled na moře a jaká je cena pokoje za jednu noc
(předpokládej, že je cena pevně daná bez ohledu na sezónu a počet ubytovaných).

V rámci jedné rezervace (booking) vždy přiřazujeme pokoj jednomu nebo více hostům na
časové období mezi dvěma daty (například pokoj číslo 3 na období od 15. 7. do 24. 7. 2021).
Pobyt je buď pracovní, nebo rekreační (type of vacation).

Chceme také mít k dispozici třídu, která bude uchovávat všechny rezervace (například list of bookings),
které jsme v systému vytvořili a bude mít metodu, která vypíše informace o všech rezervacích.

Vytvoř hosty Adélu Malíkovou (narozena 13. 3. 1993) a Jana Dvořáčka (narozen 5.5.1995).
Vypiš na obrazovku jejich souhrnný popis ve formátu: Adéla Malíková (1993-03-13)
Vytvoř pokoje a vypiš na obrazovku jejich popis:
    pokoj číslo 1 a pokoj číslo 2 jsou jednolůžkové za cenu 1000 Kč/noc, s balkonem a výhledem na moře.
    pokoj číslo 3 je trojlůžkový za cenu 2400 Kč/noc, bez balkónu, s výhledem na moře.
Připrav rezervace:
    pro Adélu na pokoj č. 1 od 19. do 26. 7. 2021.
    pro oba (společná rezervace) na pokoj č. 3 od 1. do 14. 9. 2021.
Vypiš seznam všech rezervací.
*/

import com.engeto.ja.Booking;
import com.engeto.ja.Room;
import com.engeto.ja.Guest;
import com.engeto.ja.TypeOfStay;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Room> rooms = createRooms();
        List<Booking> bookings = new ArrayList<>();
        List<Guest> guests = new ArrayList<>();

        createBooking(rooms,bookings,guests,1,"Adéla","Malíková",LocalDate.of(1993, 3, 13),
                LocalDate.of(2024, 7, 19), LocalDate.of(2024, 7, 26), TypeOfStay.BUSINESS);

        createBooking(rooms,bookings,guests,7,"Jan","Dvořáček",LocalDate.of(1995, 5, 5));
        createBooking(rooms,bookings,guests,7,"Adéla","Malíková",LocalDate.of(1993, 3, 13));

        createBooking(rooms,bookings,guests,6,"Jan","Novák",LocalDate.of(1988, 7, 22),
                LocalDate.of(2024, Month.FEBRUARY,29),LocalDate.of(2024,Month.MARCH,3),TypeOfStay.BUSINESS);

        createBooking(rooms,bookings,guests,12,"Josef","Vyskočil",LocalDate.of(1944, 7, 22));

        divide();
        System.out.println("Výpis všech vytvořených rezervací: ");

        for (Booking booking : bookings) {
            System.out.println(booking);
        }

        divide();
        System.out.println("Seznam všech hostů v systému: ");

        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }

    //region Rooms definition
    private static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room();
        room1.setRoom(1,2,true,true,1000);
        rooms.add(room1);

        Room room2 = new Room();
        room2.setRoom(2,2,true,true,1000);
        rooms.add(room2);

        Room room3 = new Room();
        room3.setRoom(3,3,false,true,2400);
        rooms.add(room3);

        Room room4 = new Room();
        room4.setRoom(4, 1, false, false, 900);
        rooms.add(room4);

        Room room5 = new Room();
        room5.setRoom(5, 2, false, true, 1200);
        rooms.add(room5);

        Room room6 = new Room();
        room6.setRoom(6, 2, true, false, 900);
        rooms.add(room6);

        Room room7 = new Room();
        room7.setRoom(7, 3, true, true, 2800);
        rooms.add(room7);

        Room room8 = new Room();
        room8.setRoom(8, 1, false, false, 700);
        rooms.add(room8);

        Room room9 = new Room();
        room9.setRoom(9, 2, false, true, 1100);
        rooms.add(room9);

        Room room10 = new Room();
        room10.setRoom(10, 3, true, true, 2800);
        rooms.add(room10);

        divide();
        System.out.println("Seznam dostupných pokojů: ");
        for (Room room : rooms) {
            System.out.println(room.toString());
        }

        divide();

        return rooms;
    }
    //endregion

    //region Pomocné funkce
    private static Room findRoomByNumber(List<Room> rooms, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private static String roomIsValidMsg(Room room, int roomNo){
        if (roomIsValid(room)) {
            return "Pokoj č. " + roomNo + " byl nalezen: " + room.getRoomSummary();
        } else {
            return "Pokoj č. " + roomNo + " nebyl nalezen.";
        }
    }

    private static boolean roomIsValid(Room room){
        if (room!= null) {
            return true;
        } else {
            return false;
        }
    }

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
                return booking.getBookingNo();
            }
        }
        return 0;
    }

    private static Booking findBookingByNumber(List<Booking> bookings, int bookingNumber) {
        for (Booking booking : bookings) {
            if (booking.getBookingNo() == bookingNumber) {
                return booking;
            }
        }
        return null;
    }

    private static void divide() {
        System.out.println("--------------------------------------------------------------------------------------");
    }
    //endregion

    //region Vytvoření rezervace
    private static void createBooking(List<Room> rooms, List<Booking> bookings, List<Guest> guests,
                                      int roomNo, String name, String surname, LocalDate dateOfBirth,
                                      LocalDate checkInDate, LocalDate checkOutDate, TypeOfStay typeOfStay) {
        Booking booking = new Booking();
        Guest guest = new Guest();
        guest.setGuest(name, surname, dateOfBirth);
        if (!guestExists(guests, guest)) {
            guests.add(guest);
        }
        Room selectedRoom = findRoomByNumber(rooms, roomNo);
        System.out.println("Vytvářím rezervaci: Pokoj č. " + roomNo + " pro hosta " + guest.getFullName());
        System.out.println(roomIsValidMsg(selectedRoom, roomNo));
        int highestBookingNo = 0;
        for (Booking existingBooking : bookings) {
            highestBookingNo = Math.max(existingBooking.getBookingNo(), highestBookingNo);
        }
        if (roomIsValid(selectedRoom)) {
            int existingBookingNo = bookingExists(bookings, selectedRoom, checkInDate, checkOutDate );
            if(existingBookingNo!=0){
                booking.setBooking(existingBookingNo, guest, selectedRoom);
                if(selectedRoom.getNoOfBeds()>1){
                    bookings.add(booking);
                    System.out.println("Do rezervace úspěšně přidán další host.");
                } else {
                    System.out.println("Na požadovaném pokoji není volná postel - rezervace odmítnuta.");
                }
            } else {
            booking.setBooking(highestBookingNo + 1, guest, selectedRoom, checkInDate, checkOutDate, typeOfStay);
            bookings.add(booking);
            System.out.println("Rezervace úspěšně vytvořena.");
            }
        } else {
            System.out.println("Rezervace nevytvořena - " + guest.getName() + "spí venku!");
        }
    }

    private static void createBooking(List<Room> rooms, List<Booking> bookings, List<Guest> guests,
                                      int roomNo, String name, String surname, LocalDate dateOfBirth) {
        Booking booking = new Booking();
        Guest guest = new Guest();
        guest.setGuest(name, surname, dateOfBirth);
        if (!guestExists(guests, guest)) {
            guests.add(guest);
        }
        Room selectedRoom = findRoomByNumber(rooms, roomNo);
        System.out.println("Vytvářím rezervaci: Pokoj č. " + roomNo + " pro hosta " + guest.getFullName());
        System.out.println(roomIsValidMsg(selectedRoom, roomNo));
        int highestBookingNo = 0;
        for (Booking existingBooking : bookings) {
            highestBookingNo = Math.max(existingBooking.getBookingNo(), highestBookingNo);
        }
        if (roomIsValid(selectedRoom)) {
            int existingBookingNo = bookingExists(bookings, selectedRoom, LocalDate.now(),LocalDate.now().plusDays(6) );
            if(existingBookingNo!=0){
                booking.setBooking(existingBookingNo, guest, selectedRoom);
                if(selectedRoom.getNoOfBeds()>1){
                    bookings.add(booking);
                    System.out.println("Do rezervace úspěšně přidán další host.");
                } else {
                    System.out.println("Na požadovaném pokoji není volná postel - rezervace odmítnuta.");
                }
            } else {
                booking.setBooking(highestBookingNo + 1, guest, selectedRoom);
                bookings.add(booking);
                System.out.println("Rezervace úspěšně vytvořena.");
            }
        } else {
            System.out.println("Rezervace nevytvořena - " + guest.getName() + " spí venku!");
        }
    }
    //endregion
}

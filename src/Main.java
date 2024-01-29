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
*/

import com.engeto.ja.Booking;
import com.engeto.ja.Room;
import com.engeto.ja.Guest;
import com.engeto.ja.TypeOfStay;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Room> rooms = createRooms();

        Booking booking1 = new Booking();
        Guest guest1 = new Guest();
        guest1.setGuest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        int selectedRoomNo = 1;
        Room selectedRoom = findRoomByNumber(rooms, selectedRoomNo);
        booking1.setBooking(1, guest1, selectedRoom,LocalDate.of(2024,7,19),LocalDate.of(2024,7,26),TypeOfStay.PRIVATE);

        if (selectedRoom != null) {
            System.out.println("Pokoj č. " + selectedRoomNo + " byl nalezen: " + selectedRoom.toString());
        } else {
            System.out.println("Pokoj č. " + selectedRoomNo + " nebyl nalezen.");
        }

        Booking booking2 = new Booking();
        Guest guest2 = new Guest();
        guest2.setGuest("John", "Smith", LocalDate.of(1990, 5, 15));
        Room selectedRoom2 = findRoomByNumber(rooms, 7);
        booking2.setBooking(2, guest2, selectedRoom2);

        // Vytiskneme seznam rezervací
        System.out.println(booking1.toString());
        System.out.println(booking2.toString());
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
        room6.setRoom(6, 2, true, false, 1100);
        rooms.add(room6);

        Room room7 = new Room();
        room7.setRoom(7, 3, true, true, 2900);
        rooms.add(room7);

        Room room8 = new Room();
        room8.setRoom(8, 1, false, false, 900);
        rooms.add(room8);

        Room room9 = new Room();
        room9.setRoom(9, 2, false, true, 1100);
        rooms.add(room9);

        Room room10 = new Room();
        room10.setRoom(10, 3, true, true, 1800);
        rooms.add(room10);

        /*
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
        */

        return rooms;
    }
    //endregion

    private static Room findRoomByNumber(List<Room> rooms, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNumber) {
                return room;
            }
        }
        return null; // Pokud pokoj není nalezen
    }
}

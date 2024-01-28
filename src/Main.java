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
    //region Room definition
    public static void main(String[] args) {
        Room room1 = new Room();
        room1.setRoom(1,2,true,true,1000);

        Room room2 = new Room();
        room2.setRoom(2,2,true,true,1000);

        Room room3 = new Room();
        room3.setRoom(3,3,false,true,2400);

        Room room4 = new Room();
        room4.setRoom(4, 1, false, false, 900);

        Room room5 = new Room();
        room5.setRoom(5, 2, false, true, 1200);

        Room room6 = new Room();
        room6.setRoom(6, 2, true, false, 1100);

        Room room7 = new Room();
        room7.setRoom(7, 3, true, true, 2900);

        Room room8 = new Room();
        room8.setRoom(8, 1, false, false, 900);

        Room room9 = new Room();
        room9.setRoom(9, 2, false, true, 1100);

        Room room10 = new Room();
        room10.setRoom(10, 3, true, true, 1800);
    }
    //endregion



}

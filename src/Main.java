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

import com.engeto.ja.hotel.TypeOfStay;

import java.time.LocalDate;
import java.time.Month;

import static com.engeto.ja.hotel.BookingManager.*;

public class Main {

    public static void main(String[] args) {

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

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Výpis všech vytvořených rezervací: ");
        getBookings();

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Seznam všech hostů v systému: ");
        getGuests();

        //Úkol z 3. lekce
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Počet hostů s rezervací pracovnícho pobytu: " + getNumberOfWorkingBookings());
        System.out.println("Počet hostů s rezervací soukromécho pobytu: " + getNumberOfPrivateBookings());
        System.out.println("Průměrný počet hostů na rezervaci: " + String.format("%.2f",getAverageGuests()));

        clearBookings();

        fillBookings();

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Počet rezervací pracovných pobytů: " + getNumberOfWorkingBookings());

    }

    /*Vytvoření testovacích dat
    Protože načítat data ze souboru se naučíme až v dalších lekcích, vytvoříme si zatím testovací data na začátku programu ručně.
    Ve třídě Main připrav metodu fillBookings s následujícím kódem:
    V praxi bychom tato data získali od uživatele, nebo je načetli ze vstupního souboru či z databáze.
    Vlož do evidence rezervací následující rezervace. Údaje, které chybí, si vymysli:

    Karel Dvořák, narozen 15. 5. 1990, si rezervuje pokoj číslo 3 od 1. 6. 2023 do 7. 6. 2023. Bude to pracovní pobyt.
    Jiný pan Karel Dvořák, narozen 3. 1. 1979, si rezervuje pokoj číslo 2 od 18. 7. 2023 do 21. 7. 2023. Bude to rekreační pobyt.

    Fyzioterapeutka Karolína Tmavá si pro své klienty rezervuje pokoj číslo 2 na dvoudenní pobyty v měsíci srpnu. Vytvoř 10 dvoudenních rezervací pro rekreační pobyty:
        První rezervace bude od 1. do 2. 8.,
        druhá od 3. do 4. 8.
        třetí od 5. do 6. 8.
        a tak dále. Poslední rezervace bude od 19. do 20. 6. Karolína bude uvedena jako jediný host.
    Fyzioterapeutka Karolína Tmavá z předchozího úkolu si dále rezervuje pokoj číslo 3 na celý srpen (od 1.8. do 31.8.).

     */
    public static void fillBookings(){
        addBooking(3,"Karel","Dvořák",LocalDate.of(1990, 5, 15),
                LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 7), TypeOfStay.BUSINESS);

        addBooking(2,"Karel","Dvořák",LocalDate.of(1979, 1, 3),
                LocalDate.of(2024, 7, 18), LocalDate.of(2024, 7, 21), TypeOfStay.PRIVATE);

        getBookings();
        for (long i = 0; i < 20; i=i+2) {
            int noOfNights = 1;
            LocalDate firstBookingDate = LocalDate.of(2024, 8,1);
            LocalDate checkInDate = firstBookingDate.plusDays(i);
            LocalDate checkOutDate = checkInDate.plusDays(noOfNights);

            addBooking(2,"Karolína","Tmavá",LocalDate.of(1987, 4, 9),
                    checkInDate, checkOutDate, TypeOfStay.PRIVATE);
        }

        addBooking(1,"Karolína","Tmavá",LocalDate.of(1987, 4, 9),
                LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31), TypeOfStay.BUSINESS);

    }
}

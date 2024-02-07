import java.time.LocalDate;
import java.time.Month;
import com.engeto.ja.hotel.TypeOfStay;
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
        printBookings();

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

        for (int i = 0; i < 20; i=i+2) {
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

    //V hlavní třídě projektu připrav metodu pro výpis seznam všech rezervací ve formátu:
    //datumOd až datumDo: jméno hlavního hosta (datum narození)[počet hostů, výhledNaMoře ano/ne] za cena

    //Připrav metodu pro výpis prvních 8 rezervací, které jsou určeny pro rekreaci (typ pobytu je rekreační). Pracovní pobyty při výpisu ignoruj/přeskoč.
    //Můžeš ji také zobecnit a počet vypisovaných rezervací zadat jako parametr metody.

    //Připrav v hlavní třídě metodu printGuestStatistics, která vypíše:
    //celkový počet rezervací s jedním/dvěma/více hosty.

}

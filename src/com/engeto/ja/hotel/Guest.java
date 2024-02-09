package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Guest {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    private void setName(String name){
        this.name = name;
    }
    private void setSurname(String surname){
        this.surname = surname;
    }
    private void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setGuest(String name, String surname, LocalDate dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return name+" "+surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Guest getGuest() {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setSurname(surname);
        guest.setDateOfBirth(dateOfBirth);

        return guest;
    }

    @Override public String toString(){
        return surname + " " + name + ", nar. " + dateOfBirth.format(DateTimeFormatter.ofPattern("d.M.y"));
    }
}

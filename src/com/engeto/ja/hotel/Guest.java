package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Guest {
    // Private fields to store guest details
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    // Setter methods for private fields
    private void setName(String name){
        this.name = name;
    }
    private void setSurname(String surname){
        this.surname = surname;
    }
    private void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    // Method to set guest details
    public void setGuest(String name, String surname, LocalDate dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    // Getter methods for private fields
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

    // Method to get a copy of the guest object
    public Guest getGuest() {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setSurname(surname);
        guest.setDateOfBirth(dateOfBirth);

        return guest;
    }

    // Override toString method to generate a string representation of the guest
    @Override public String toString(){
        return surname + " " + name + ", nar. " + dateOfBirth.format(DateTimeFormatter.ofPattern("d.M.y"));
    }
}

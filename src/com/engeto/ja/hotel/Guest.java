package com.engeto.ja.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Guest {
    // Private fields to store guest details
    private final String name;
    private final String surname;
    private final LocalDate dateOfBirth;

    // Constructor for the guest
    public Guest(String name, String surname, LocalDate dateOfBirth){
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


    // Override toString method to generate a string representation of the guest
    @Override public String toString(){
        return surname + " " + name + ", nar. " + dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    // Override equals and hashCode methods for proper comparison and hash calculation
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(surname, guest.surname) &&
                Objects.equals(dateOfBirth, guest.dateOfBirth);
    }

    @Override public int hashCode() {
        return Objects.hash(name, surname, dateOfBirth);
    }
}

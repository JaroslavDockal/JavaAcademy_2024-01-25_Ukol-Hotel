package com.engeto.ja.hotel;

import java.util.ArrayList;
import java.util.List;

public class Rooms {

    // Method to create a list of rooms
    public static List<Room> createRooms() {

        // Inicializace prázdného seznamu pokojů
        List<Room> rooms = new ArrayList<>();

        // Initialize an empty list of rooms
        Room room1 = new Room();
        room1.setRoom(1, 2, true, true, 1000);
        rooms.add(room1);

        Room room2 = new Room();
        room2.setRoom(2, 2, true, true, 1000);
        rooms.add(room2);

        Room room3 = new Room();
        room3.setRoom(3, 3, false, true, 2400);
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

        //printAllRooms(rooms);

        return rooms; // Return the list of created rooms
    }

    // Method to print all rooms in the list
    public static void printAllRooms(List<Room> rooms){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Seznam dostupných pokojů: ");
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Method to find a room by its number
    public static Room findRoomByNumber(List<Room> rooms, int roomNo) {
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) {
                return room;
            }
        }
        return null; // Return null if room with the given number is not found
    }

    // Method to check if a room is valid
    public static boolean roomIsValid(Room room){
        return room != null; // A room is valid if it is not null
    }

    // Method to return a validation status message for a room
    public static String roomValidationStatus(Room room, int roomNo){
        if (roomIsValid(room)) {
            return "Pokoj č. " + roomNo + " byl nalezen: " + room.getRoomSummary();
        } else {
            return "Pokoj č. " + roomNo + " nebyl nalezen.";
        }
    }
}

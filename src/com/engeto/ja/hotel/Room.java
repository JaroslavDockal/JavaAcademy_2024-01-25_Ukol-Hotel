package com.engeto.ja.hotel;

import java.math.BigDecimal;

public class Room {
    // Private fields to store room details
    private final int roomNo;
    private final int noOfBeds;
    private final boolean hasBalcony;
    private final boolean hasSeaView;
    private final BigDecimal pricePerNight;

    // Constructor to initialize the room details
    public Room(int roomNo, int noOfBeds, boolean hasBalcony, boolean hasSeaView,double pricePerNight){
        this.roomNo = roomNo;
        this.noOfBeds = noOfBeds;
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.pricePerNight = BigDecimal.valueOf(pricePerNight);
    }

    // Getter methods for private fields
    public int getRoomNo() {
        return roomNo;
    }

    public int getNoOfBeds() {
        return noOfBeds;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public boolean hasSeaView() {
        return hasSeaView;
    }

    // Override toString method to generate a summary of the room as a string
    @Override public String toString() {
        String extraStr;
        String bedsStr;
        // Determine additional features of the room
        if (hasBalcony && hasSeaView) {
            extraStr = " s balkónem a výhledem na moře, ";
        }else if(hasBalcony){
            extraStr = " s balkónem, ";
        }else if(hasSeaView){
            extraStr = " s výhledem na moře, ";
        }else{
            extraStr = ", ";
        }
        // Determine the bed configuration
        if (noOfBeds==1){
            bedsStr = " postel";
        } else if (noOfBeds <= 5){
            bedsStr = " postele";
        } else {
            bedsStr = " postelí";
        }

        return "Pokoj č. " + roomNo + ", " + noOfBeds + bedsStr + extraStr + pricePerNight.toString() + " CZK/noc." ;
    }
}

package com.engeto.ja.hotel;

import java.math.BigDecimal;

public class Room {
    // Private fields to store room details
    private int roomNo;
    private int noOfBeds;
    private boolean hasBalcony;
    private boolean hasSeaView;
    private BigDecimal pricePerNight;

    // Setter methods for private fields
    private void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    private void setNoOfBeds(int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    private void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    private void setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
    }

    private void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    // Method to set room details
    public void setRoom(int roomNo, int noOfBeds, boolean hasBalcony, boolean hasSeaView,double pricePerNight){
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

    // Method to get a copy of the room object
    public Room getRoom() {
        Room room = new Room();
        room.setRoomNo(roomNo);
        room.setNoOfBeds(noOfBeds);
        room.setHasBalcony(hasBalcony);
        room.setHasSeaView(hasSeaView);
        room.setPricePerNight(pricePerNight);

        return room;
    }

    // Method to generate a summary of the room
    public String getRoomSummary() {
        String extraStr;
        String bedsStr;
        // Determine additional features of the room
        if (hasBalcony && hasSeaView) {
            extraStr = "Pokoj s balkónem a výhledem na moře, ";
        }else if(hasBalcony){
            extraStr = "Pokoj s balkónem bez výhledu na moře, ";
        }else if(hasSeaView){
            extraStr = "Pokoj s výhledem na moře bez balkónu, ";
        }else{
            extraStr = "Pokoj bez balkónu a výhledu na moře, ";
        }
        // Determine the bed configuration
        if (noOfBeds==1){
            bedsStr = " postel, ";
        } else if (noOfBeds <= 5){
            bedsStr = " postele, ";
        } else {
            bedsStr = " postelí, ";
        }

        return extraStr + noOfBeds + bedsStr + pricePerNight.toString() + " CZK/noc.";
    }

    // Override toString method to generate a string representation of the room
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

package com.example.PARKING_LOT;

public class Ticket {
    private Car car;
    private ParkingSlot parkingSlot;

    public Ticket(Car car, ParkingSlot parkingSlot) {
        this.car = car;
        this.parkingSlot = parkingSlot;
    }

    public Car getCar() {
        return car;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }
}

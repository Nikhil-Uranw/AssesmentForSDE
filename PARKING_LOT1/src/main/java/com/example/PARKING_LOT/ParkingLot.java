package com.example.PARKING_LOT;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSlot> parkingSlots;

    public ParkingLot(int capacity) {
        parkingSlots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            parkingSlots.add(new ParkingSlot(i + 1));
        }
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public ParkingSlot findNearestAvailableSlot() {
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }

    public List<ParkingSlot> findSlotsByColor(String color) {
        List<ParkingSlot> slotsWithColor = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                slotsWithColor.add(slot);
            }
        }
        return slotsWithColor;
    }

    public ParkingSlot findSlotByRegistrationNumber(String registrationNumber) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return slot;
            }
        }
        return null;
    }

    public boolean parkCar(Car car) {
        ParkingSlot availableSlot = findNearestAvailableSlot();
        if (availableSlot != null) {
            availableSlot.parkCar(car);
            return true;
        }
        return false;
    }

    public boolean removeCar(int slotNumber) {
        if (slotNumber >= 1 && slotNumber <= parkingSlots.size()) {
            ParkingSlot slot = parkingSlots.get(slotNumber - 1);
            if (slot.isOccupied()) {
                slot.removeCar();
                return true;
            }
        }
        return false;
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    

}

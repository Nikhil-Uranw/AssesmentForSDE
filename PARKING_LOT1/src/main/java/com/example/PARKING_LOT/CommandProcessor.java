package com.example.PARKING_LOT;

import java.util.List;

public class CommandProcessor {
    private ParkingLot parkingLot;

    public CommandProcessor(int capacity) {
        parkingLot = new ParkingLot(capacity);
    }

    public void processCommandHandler(String Usercommand, String... args) {
        switch (Usercommand) {
            case "create_parking_lot":
                int capacity = Integer.parseInt(args[0]);
                parkingLot = new ParkingLot(capacity);
                System.out.println("Created a parking lot with " + capacity + " slots.");
                break;
            case "park":
                String registrationNumber = args[0];
                String color = args[1];
                parkCar(registrationNumber, color);
                break;
            case "leave":
                int slotNumber = Integer.parseInt(args[0]);
                removeCar(slotNumber);
                break;
            case "status":
            	ParkingStatus();
                break;
            case "registration_numbers_for_cars_with_colour":
            	RegistrationNumbersForColor(args[0]);
                break;
            case "slot_numbers_for_cars_with_colour":
            	SlotNumbersForColor(args[0]);
                break;
            case "slot_number_for_registration_number":
            	SlotNumberForRegistrationNumber(args[0]);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    private void SlotNumberForRegistrationNumber(String registrationNumber) {
        boolean found = false;
        List<ParkingSlot> parkingSlots = parkingLot.getParkingSlots();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                System.out.println("Slot number for registration number " + registrationNumber + " is: " + slot.getSlotNumber());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No car with registration number " + registrationNumber + " found in the parking lot.");
        }
    }

    private void SlotNumbersForColor(String color) {
        List<ParkingSlot> parkingSlots = parkingLot.getParkingSlots();
        StringBuilder sb = new StringBuilder();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                sb.append(slot.getSlotNumber()).append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); 
            System.out.println("Slot numbers for cars with color " + color + " are: " + sb.toString());
        } else {
            System.out.println("No cars with color " + color + " found in the parking lot.");
        }
    }

    private void parkCar(String registrationNumber, String color) {
        ParkingSlot slot = parkingLot.findNearestAvailableSlot();
        if (slot != null) {
            Car car = new Car(registrationNumber, color);
            slot.parkCar(car);
            System.out.println("Allocated slot number: " + slot.getSlotNumber());
        } else {
            System.out.println("Sorry, parking lot is full.");
        }
    }

    private void removeCar(int slotNumber) {
        ParkingSlot slot = parkingLot.getParkingSlots().get(slotNumber - 1);
        if (slot.isOccupied()) {
            slot.removeCar();
            System.out.println("Slot number " + slotNumber + " is free.");
        } else {
            System.out.println("Slot number " + slotNumber + " is already empty.");
        }
    }

    private void ParkingStatus() {
        List<ParkingSlot> parkingSlots = parkingLot.getParkingSlots();
        System.out.println("Slot No. Registration No Colour");
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied()) {
                Car car = slot.getCar();
                System.out.println(slot.getSlotNumber() + " " + car.getRegistrationNumber() + " " + car.getColor());
            }
        }
    }

    private void RegistrationNumbersForColor(String color) {
        List<ParkingSlot> slotsWithColor = parkingLot.findSlotsByColor(color);
        StringBuilder sb = new StringBuilder();
        
        for (ParkingSlot slot : slotsWithColor) {
            sb.append(slot.getCar().getRegistrationNumber()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); 
            System.out.println(sb.toString());
        } else {
            System.out.println("Not found.");
        }
    }
}

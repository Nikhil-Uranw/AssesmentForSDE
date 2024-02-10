package com.example.PARKING_LOT;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLot1Application {

	public static void main(String[] args) {
	    CommandProcessor commandProcessor = null;
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.print("$ ");
	        String input = scanner.nextLine();
	        String[] tokens = input.trim().split(" ");

	        if (tokens.length == 0) {
	            continue; 
	        }

	        String Usercommand = tokens[0];
	        String[] arguments = new String[tokens.length - 1];
	        System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);

	        if (Usercommand.equals("exit")) {
	            break;
	        }

	        if (Usercommand.equals("create_parking_lot")) {
	        	System.out.println("ParkingLot1Application.main()");
				
				 if (arguments.length != 1) {
				 System.out.println("Please provide the parking lot capacity."); 
				 
	           // int capacity = Integer.parseInt(arguments[0]);
	            int capacity  = scanner.nextInt();

	            //System.out.println("############################"+capacity );
	            commandProcessor = new CommandProcessor(capacity);
	           // commandProcessor.CommandProcessor1(capacity);
	            System.out.println("Created a parking lot with " + capacity + " slots.");
				 } } else if (commandProcessor != null) {
	            commandProcessor.processCommandHandler(Usercommand, arguments);
	        } else {
	            System.out.println("Parking lot is not created yet. Please create parking lot first.");
	        }
	    }

	    scanner.close();
	}
}
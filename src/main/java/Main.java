/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */



// Main class - entry point for QuickChat application
// Updated for Part 3 - Added arrays and report features

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner lets us read what the user types
        Scanner input = new Scanner(System.in);

        System.out.println("=== Welcome to QuickChat ===");
        System.out.println("Please register to continue.");
        System.out.println();

        // --- Get the user's first and last name ---
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        // --- Get username ---
        System.out.print("Enter a username (must have underscore, max 5 characters): ");
        String username = input.nextLine();

        // --- Get password ---
        System.out.print("Enter a password (8+ chars, capital, number, special character): ");
        String password = input.nextLine();

        // --- Get cell phone number ---
        System.out.print("Enter your cell phone number (e.g. +27831234567): ");
        String cellPhone = input.nextLine();

        // --- Create a Login object with the details the user entered ---
        Login newUser = new Login(firstName, lastName, username, password, cellPhone);

        System.out.println();
        System.out.println("=== Registration Results ===");

        // --- Check username and show message ---
        if (newUser.checkUserName()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        // --- Check password and show message ---
        if (newUser.checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        // --- Check cell phone and show message ---
        if (newUser.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        // --- Try to register the user ---
        String registrationResult = newUser.registerUser();
        System.out.println(registrationResult);

        // --- Login section - only runs if registration was successful ---
        if (registrationResult.equals("User registered successfully.")) {

            System.out.println();
            System.out.println("=== Please Log In ===");

            System.out.print("Enter your username: ");
            String enteredUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = input.nextLine();

            // Check if login details are correct
            boolean loginSuccess = newUser.loginUser(enteredUsername, enteredPassword);
            String loginMessage = newUser.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);

            // --- Only show menu if login was successful ---
            if (loginSuccess) {

                // Ask how many messages the user wants to send
                System.out.println();
                System.out.print("How many messages do you want to send? ");
                int numMessages = Integer.parseInt(input.nextLine());

                // This variable keeps track of total messages sent
                int totalMessagesSent = 0;

                // This is the main menu loop
                // It keeps running until the user chooses to quit
                boolean running = true;

                while (running) {

                    // Show the menu
                    System.out.println();
                    System.out.println("=== Welcome to QuickChat ===");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("4) Stored Messages");
                    System.out.print("Choose an option: ");

                    int menuChoice = Integer.parseInt(input.nextLine());

                    if (menuChoice == 1) {

                        // --- SEND MESSAGES ---
                        for (int i = 0; i < numMessages; i++) {

                            System.out.println();
                            System.out.println("--- Message " + (i + 1) + " of " + numMessages + " ---");

                            // Get recipient number
                            System.out.print("Enter recipient cell number (e.g. +27831234567): ");
                            String recipient = input.nextLine();

                            // Get message text
                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            // Create a new Message object
                            Message msg = new Message(i + 1, recipient, messageText);

                            // Check recipient number and show result
                            System.out.println(msg.checkRecipientCell());

                            // Check message length and show result
                            String lengthCheck = msg.checkMessageLength();
                            System.out.println(lengthCheck);

                            // Always show message options
                            System.out.println();
                            System.out.println("What would you like to do?");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");
                            System.out.print("Choose an option: ");

                            int sendChoice = Integer.parseInt(input.nextLine());

                            // Handle the choice
                            String result = msg.sentMessage(sendChoice);
                            System.out.println(result);

                            // If message was sent show details and increment counter
                            if (sendChoice == 1) {
                                totalMessagesSent++;
                                System.out.println(msg.printMessages());
                            }
                        }

                        // Show total messages sent
                        System.out.println();
                        System.out.println("Total messages sent: " + totalMessagesSent);

                    } else if (menuChoice == 2) {
                        // --- SHOW RECENTLY SENT MESSAGES ---
                        System.out.println("Coming Soon.");

                    } else if (menuChoice == 3) {
                        // --- QUIT ---
                        System.out.println("Goodbye! Thank you for using QuickChat.");
                        running = false;

                    } else if (menuChoice == 4) {
                        // --- STORED MESSAGES MENU ---
                        boolean storedRunning = true;

                        while (storedRunning) {
                            System.out.println();
                            System.out.println("=== Stored Messages Menu ===");
                            System.out.println("1) Display all sender and recipient details");
                            System.out.println("2) Display longest message");
                            System.out.println("3) Search for message by ID");
                            System.out.println("4) Search messages by recipient");
                            System.out.println("5) Delete a message using hash");
                            System.out.println("6) Display full report");
                            System.out.println("7) Back to main menu");
                            System.out.print("Choose an option: ");

                            int storedChoice = Integer.parseInt(input.nextLine());

                            if (storedChoice == 1) {
                                // Display sender and recipient of all stored messages
                                System.out.println("\n=== Sent Messages ===");
                                if (Message.sentCount == 0) {
                                    System.out.println("No sent messages.");
                                } else {
                                    for (int i = 0; i < Message.sentCount; i++) {
                                        System.out.println("Recipient: " + Message.sentRecipients[i] +
                                                           " | Message: " + Message.sentMessages[i]);
                                    }
                                }

                                System.out.println("\n=== Stored Messages ===");
                                if (Message.storedCount == 0) {
                                    System.out.println("No stored messages.");
                                } else {
                                    for (int i = 0; i < Message.storedCount; i++) {
                                        System.out.println("Recipient: " + Message.storedRecipients[i] +
                                                           " | Message: " + Message.storedMessages[i]);
                                    }
                                }

                            } else if (storedChoice == 2) {
                                // Display longest message
                                System.out.println("\nLongest message: " + Message.displayLongestMessage());

                            } else if (storedChoice == 3) {
                                // Search by message ID
                                System.out.print("Enter message ID to search: ");
                                String searchID = input.nextLine();
                                System.out.println(Message.searchByMessageID(searchID));

                            } else if (storedChoice == 4) {
                                // Search by recipient
                                System.out.print("Enter recipient number to search: ");
                                String searchRecipient = input.nextLine();
                                System.out.println(Message.searchByRecipient(searchRecipient));

                            } else if (storedChoice == 5) {
                                // Delete message by hash
                                System.out.print("Enter message hash to delete: ");
                                String hash = input.nextLine();
                                System.out.println(Message.deleteMessage(hash));

                            } else if (storedChoice == 6) {
                                // Display full report
                                System.out.println(Message.displayReport());

                            } else if (storedChoice == 7) {
                                // Go back to main menu
                                storedRunning = false;

                            } else {
                                System.out.println("Invalid option. Please choose 1-7.");
                            }
                        }

                    } else {
                        // Invalid menu option
                        System.out.println("Invalid option. Please choose 1, 2, 3 or 4.");
                    }
                }
            }
        }

        input.close();
    }
}
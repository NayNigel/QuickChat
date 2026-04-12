/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */




// Main class - entry point for QuickChat application
// Commit 3: Add Main class with user interface
// Commit 5: Add comments to Main class

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
        System.out.print("Enter a password (8+ characters, capital, number, special character): ");
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

            // Ask user to enter login details
            System.out.print("Enter your username: ");
            String enteredUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = input.nextLine();

            // Check if login details are correct
            boolean loginSuccess = newUser.loginUser(enteredUsername, enteredPassword);

            // Show login result message
            String loginMessage = newUser.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);
        }

        input.close(); // always close scanner when done
    }
}

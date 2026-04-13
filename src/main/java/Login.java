/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */
 // this just says which project this file belongs to


// Login class - handles registration and login for QuickChat
// Commit 1: Initial commit - Project setup
// Commit 2: Add Login class with registration methods
// Commit 4: Add comments to Login methods
// Updated - Added getters for all private variables

import java.util.Scanner;

public class Login {

    // These variables store the user's details
    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Constructor - sets up the Login object with the user's details
    public Login(String firstName, String lastName, String username, String password, String cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    // -------------------------------------------------------
    // METHOD 1: Check if the username is valid
    // Validates username format
    // Rules: must contain an underscore AND be 5 characters or less
    // -------------------------------------------------------
    public boolean checkUserName() {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // -------------------------------------------------------
    // METHOD 2: Check if the password is strong enough
    // Validates password complexity
    // Rules: 8+ characters, capital letter, number, special character
    // -------------------------------------------------------
    public boolean checkPasswordComplexity() {
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Go through the password one character at a time
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // -------------------------------------------------------
    // METHOD 3: Check if the cell phone number is valid
    // Validates cell phone format using regular expressions
    // Rules: starts with + followed by 10 or 11 digits
    // Reference: W3Schools. 2024. Java Regular Expressions. [Online].
    // Available: https://www.w3schools.com/java/java_regex.asp
    // [Accessed 10 April 2026]
    // -------------------------------------------------------
    public boolean checkCellPhoneNumber() {
        if (cellPhone.matches("\\+\\d{10,11}")) {
            return true;
        } else {
            return false;
        }
    }

    // -------------------------------------------------------
    // METHOD 4: Register the user and return a message
    // Returns appropriate message based on validation results
    // -------------------------------------------------------
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        // All checks passed - return all success messages
        return "Username successfully captured." +
               "\nPassword successfully captured." +
               "\nCell phone number successfully added.";
    }

    // -------------------------------------------------------
    // METHOD 5: Check if login details match what was registered
    // Verifies username and password match stored details
    // -------------------------------------------------------
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
            return true;
        } else {
            return false;
        }
    }

    // -------------------------------------------------------
    // METHOD 6: Return a login message based on success or failure
    // Returns welcome message or error message
    // -------------------------------------------------------
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters - let other classes read the private variables
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
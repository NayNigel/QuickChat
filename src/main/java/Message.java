/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */


// Message class - handles all message related features for QuickChat

import java.util.Random;

public class Message {

    // These variables store the message details
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor - sets up the message with all its details
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // -------------------------------------------------------
    // METHOD 1: Generate a random 10 digit message ID
    // -------------------------------------------------------
    private String generateMessageID() {
        // Random helps us generate random numbers
        Random random = new Random();
        // This creates a random number and converts it to a string
        long id = (long)(random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    // -------------------------------------------------------
    // METHOD 2: Check if the message ID is valid
    // Rules: must not be more than 10 characters
    // -------------------------------------------------------
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // -------------------------------------------------------
    // METHOD 3: Check if the recipient cell number is valid
    // Rules: starts with + and has international code
    // Reference: Adapted from https://www.w3schools.com/java/java_regex.asp
    // -------------------------------------------------------
    public String checkRecipientCell() {
        if (recipient.matches("\\+\\d{10,11}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // -------------------------------------------------------
    // METHOD 4: Create the message hash
    // Format: first 2 digits of ID : message number : first word + last word
    // Example: 00:0:HITHANKS
    // -------------------------------------------------------
    public String createMessageHash() {
        // Get first 2 characters of the message ID
        String first2 = messageID.substring(0, 2);

        // Split the message into words using space as separator
        String[] words = messageText.trim().split(" ");

        // Get the first word
        String firstWord = words[0];

        // Get the last word
        String lastWord = words[words.length - 1];

        // Remove any punctuation from last word
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        // Put it all together and make it uppercase
        String hash = first2 + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // -------------------------------------------------------
    // METHOD 5: Check message length and return result
    // -------------------------------------------------------
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // -------------------------------------------------------
    // METHOD 6: Handle sending, storing or discarding message
    // -------------------------------------------------------
    public String sentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // -------------------------------------------------------
    // METHOD 7: Print message details
    // -------------------------------------------------------
    public String printMessages() {
        return "\n--- Message Details ---" +
               "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    // -------------------------------------------------------
    // METHOD 8: Return total messages sent
    // -------------------------------------------------------
    public int returnTotalMessages(int total) {
        return total;
    }

    // Getters - let other classes read the private variables
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
}

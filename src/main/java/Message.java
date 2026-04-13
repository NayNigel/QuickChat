/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */


// Message class - handles all message related features for QuickChat
// Updated - Added array storage for sent, stored and disregarded messages

import java.util.Random;

public class Message {

    // These variables store the message details
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status; // "sent", "stored", "disregarded"

    // These arrays store all messages
    // We use a fixed size of 50 which is more than enough
    public static String[] sentMessages = new String[50];
    public static String[] disregardedMessages = new String[50];
    public static String[] storedMessages = new String[50];
    public static String[] messageHashes = new String[50];
    public static String[] messageIDs = new String[50];
    public static String[] sentRecipients = new String[50];
    public static String[] storedRecipients = new String[50];

    // These track how many messages are in each array
    public static int sentCount = 0;
    public static int disregardedCount = 0;
    public static int storedCount = 0;

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
        Random random = new Random();
        long id = (long)(random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    // -------------------------------------------------------
    // METHOD 2: Check if the message ID is valid
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
    // -------------------------------------------------------
    public String createMessageHash() {
        // Get first 2 characters of the message ID
        String first2 = messageID.substring(0, 2);

        // Split the message into words
        String[] words = messageText.trim().split(" ");

        // Get the first word
        String firstWord = words[0];

        // Get the last word and remove punctuation
        String lastWord = words[words.length - 1];
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        // Put it all together and make it uppercase
        String hash = first2 + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // -------------------------------------------------------
    // METHOD 5: Check message length
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
    // Also stores message in the correct array
    // -------------------------------------------------------
    public String sentMessage(int choice) {
        if (choice == 1) {
            // Add to sent arrays
            sentMessages[sentCount] = messageText;
            sentRecipients[sentCount] = recipient;
            messageHashes[sentCount] = messageHash;
            messageIDs[sentCount] = messageID;
            sentCount++;
            status = "sent";
            return "Message successfully sent.";
        } else if (choice == 2) {
            // Add to disregarded array
            disregardedMessages[disregardedCount] = messageText;
            disregardedCount++;
            status = "disregarded";
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            // Add to stored arrays
            storedMessages[storedCount] = messageText;
            storedRecipients[storedCount] = recipient;
            messageHashes[storedCount] = messageHash;
            messageIDs[storedCount] = messageID;
            storedCount++;
            status = "stored";
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

    // -------------------------------------------------------
    // METHOD 9: Display longest stored message
    // -------------------------------------------------------
    public static String displayLongestMessage() {
        // Start with the first message as the longest
        String longest = "";

        // Go through all sent messages
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }

        // Go through all stored messages
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }

        if (longest.equals("")) {
            return "No messages found.";
        }
        return longest;
    }

    // -------------------------------------------------------
    // METHOD 10: Search for a message by ID
    // -------------------------------------------------------
    public static String searchByMessageID(String searchID) {
        // Search through sent messages
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                return "Recipient: " + sentRecipients[i] + "\nMessage: " + sentMessages[i];
            }
        }
        return "Message not found.";
    }

    // -------------------------------------------------------
    // METHOD 11: Search all messages for a particular recipient
    // -------------------------------------------------------
    public static String searchByRecipient(String searchRecipient) {
        String result = "";

        // Search sent messages
        for (int i = 0; i < sentCount; i++) {
            if (sentRecipients[i] != null && sentRecipients[i].equals(searchRecipient)) {
                result += sentMessages[i] + "\n";
            }
        }

        // Search stored messages
        for (int i = 0; i < storedCount; i++) {
            if (storedRecipients[i] != null && storedRecipients[i].equals(searchRecipient)) {
                result += storedMessages[i] + "\n";
            }
        }

        if (result.equals("")) {
            return "No messages found for this recipient.";
        }
        return result;
    }

    // -------------------------------------------------------
    // METHOD 12: Delete a message using message hash
    // -------------------------------------------------------
    public static String deleteMessage(String hash) {
        // Search sent messages
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash.toUpperCase())) {
                String deletedMessage = sentMessages[i];
                // Remove by shifting remaining elements left
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    sentRecipients[j] = sentRecipients[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                sentCount--;
                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }

        // Search stored messages
        for (int i = 0; i < storedCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash.toUpperCase())) {
                String deletedMessage = storedMessages[i];
                // Remove by shifting remaining elements left
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                    storedRecipients[j] = storedRecipients[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                storedCount--;
                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        return "Message not found.";
    }

    // -------------------------------------------------------
    // METHOD 13: Display full report of all sent messages
    // -------------------------------------------------------
    public static String displayReport() {
        if (sentCount == 0) {
            return "No sent messages to display.";
        }

        String report = "\n=== Message Report ===\n";
        for (int i = 0; i < sentCount; i++) {
            report += "\nMessage Hash: " + messageHashes[i] +
                      "\nRecipient: " + sentRecipients[i] +
                      "\nMessage: " + sentMessages[i] +
                      "\n----------------------";
        }
        return report;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
}
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
// Updated - Added JSON storage for stored messages

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    // These variables store the message details
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;

    // These arrays store all messages
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

    // This is the name of the JSON file we will save messages to
    private static final String JSON_FILE = "stored_messages.json";

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
    // Validates cell phone format using regular expressions
    // Rules: starts with + followed by 10 or 11 digits
    // Reference: W3Schools. 2024. Java Regular Expressions. [Online].
    // Available: https://www.w3schools.com/java/java_regex.asp
    // [Accessed 10 April 2026]
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
            // Save to JSON file when message is stored
            storeMessage(this);
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
    // METHOD 9: Store message in JSON file
    // Reference: Google. 2024. Gson User Guide. [Online].
    // Available: https://github.com/google/gson
    // [Accessed 10 April 2026]
    // -------------------------------------------------------
    public static void storeMessage(Message msg) {
        Gson gson = new Gson();

        // First read existing messages from file
        ArrayList<MessageData> messages = readMessagesFromFile();

        // Create a simple data object to store
        MessageData data = new MessageData(
            msg.messageID,
            msg.recipient,
            msg.messageText,
            msg.messageHash
        );

        // Add the new message to the list
        messages.add(data);

        // Write the updated list back to the file
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            gson.toJson(messages, writer);
            System.out.println("Message successfully stored in JSON file.");
        } catch (IOException e) {
            System.out.println("Error saving message to file.");
        }
    }

    // -------------------------------------------------------
    // METHOD 10: Read messages from JSON file
    // Reference: Google. 2024. Gson User Guide. [Online].
    // Available: https://github.com/google/gson
    // [Accessed 11 April 2026]
    // -------------------------------------------------------
    public static ArrayList<MessageData> readMessagesFromFile() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(JSON_FILE)) {
            Type listType = new TypeToken<ArrayList<MessageData>>(){}.getType();
            ArrayList<MessageData> messages = gson.fromJson(reader, listType);

            if (messages == null) {
                return new ArrayList<>();
            }
            return messages;

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // -------------------------------------------------------
    // METHOD 11: Display longest stored message
    // -------------------------------------------------------
    public static String displayLongestMessage() {
        String longest = "";

        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }

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
    // METHOD 12: Search for a message by ID
    // -------------------------------------------------------
    public static String searchByMessageID(String searchID) {
        for (int i = 0; i < sentCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                return "Recipient: " + sentRecipients[i] + "\nMessage: " + sentMessages[i];
            }
        }
        return "Message not found.";
    }

    // -------------------------------------------------------
    // METHOD 13: Search all messages for a particular recipient
    // -------------------------------------------------------
    public static String searchByRecipient(String searchRecipient) {
        String result = "";

        for (int i = 0; i < sentCount; i++) {
            if (sentRecipients[i] != null && sentRecipients[i].equals(searchRecipient)) {
                result += sentMessages[i] + "\n";
            }
        }

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
    // METHOD 14: Delete a message using message hash
    // -------------------------------------------------------
    public static String deleteMessage(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash.toUpperCase())) {
                String deletedMessage = sentMessages[i];
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

        for (int i = 0; i < storedCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash.toUpperCase())) {
                String deletedMessage = storedMessages[i];
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
    // METHOD 15: Display full report of all sent messages
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

    // -------------------------------------------------------
    // Inner class to hold message data for JSON storage
    // Reference: Google. 2024. Gson User Guide. [Online].
    // Available: https://github.com/google/gson
    // [Accessed 11 April 2026]
    // -------------------------------------------------------
    public static class MessageData {
        String messageID;
        String recipient;
        String messageText;
        String messageHash;

        public MessageData(String messageID, String recipient, String messageText, String messageHash) {
            this.messageID = messageID;
            this.recipient = recipient;
            this.messageText = messageText;
            this.messageHash = messageHash;
        }
    }
}

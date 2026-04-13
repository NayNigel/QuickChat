/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



// MessageTest class - unit tests for Message class

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // -------------------------------------------------------
    // TEST 1: Message length is valid (under 250 characters)
    // -------------------------------------------------------
    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    // -------------------------------------------------------
    // TEST 2: Message length is too long (over 250 characters)
    // -------------------------------------------------------
    @Test
    public void testMessageLengthTooLong() {
        // This string is over 250 characters long
        String longMessage = "This is a very long message that is definitely going to exceed the two hundred and fifty character limit that has been set for this chat application and should therefore fail the length check completely without any doubt whatsoever at all yes it will fail";
        Message msg = new Message(1, "+27718693002", longMessage);
        int over = longMessage.length() - 250;
        assertEquals("Message exceeds 250 characters by " + over + "; please reduce the size.", msg.checkMessageLength());
    }

    // -------------------------------------------------------
    // TEST 3: Recipient number is correctly formatted
    // -------------------------------------------------------
    @Test
    public void testRecipientCorrectlyFormatted() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // -------------------------------------------------------
    // TEST 4: Recipient number is incorrectly formatted
    // -------------------------------------------------------
    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message msg = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // -------------------------------------------------------
    // TEST 5: Message hash is correct
    // Test data from rubric: should return 00:0:HITONIGHT
    // for message "Hi Mike, can you join us for dinner tonight?"
    // -------------------------------------------------------
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // Hash format: first 2 of ID : message number : first word + last word
        // We check it ends with the right words
        String hash = msg.createMessageHash();
        assertTrue(hash.endsWith(":HITONIGHT"));
    }

    // -------------------------------------------------------
    // TEST 6: Message ID is created and valid
    // -------------------------------------------------------
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    // -------------------------------------------------------
    // TEST 7: Send message returns correct message
    // -------------------------------------------------------
    @Test
    public void testSendMessage() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    // -------------------------------------------------------
    // TEST 8: Disregard message returns correct message
    // -------------------------------------------------------
    @Test
    public void testDisregardMessage() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    // -------------------------------------------------------
    // TEST 9: Store message returns correct message
    // -------------------------------------------------------
    @Test
    public void testStoreMessage() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}
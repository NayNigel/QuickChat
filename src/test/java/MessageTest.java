/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



// MessageTest class - unit tests for Message and Login classes
// Updated for Part 3 - Added array and report tests

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
    // -------------------------------------------------------
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
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

    // -------------------------------------------------------
    // PART 3 TESTS - Array and report tests
    // Using test data from the rubric
    // -------------------------------------------------------

    // -------------------------------------------------------
    // TEST 10: Sent messages array is correctly populated
    // Test data from rubric: Message 1 and Message 4
    // -------------------------------------------------------
    @Test
    public void testSentMessagesArrayPopulated() {
        // Reset arrays before test
        Message.sentCount = 0;

        // Add test data from rubric
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sentMessage(1); // send

        Message msg4 = new Message(4, "0838884567", "It is dinner time !");
        msg4.sentMessage(1); // send

        // Check array contains correct messages
        assertEquals("Did you get the cake?", Message.sentMessages[0]);
        assertEquals("It is dinner time !", Message.sentMessages[1]);
    }

    // -------------------------------------------------------
    // TEST 11: Display longest message
    // Test data from rubric: Message 2
    // -------------------------------------------------------
    @Test
    public void testDisplayLongestMessage() {
        // Reset arrays before test
        Message.sentCount = 0;
        Message.storedCount = 0;

        // Add test data from rubric
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sentMessage(1); // sent

        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage(3); // stored

        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        msg3.sentMessage(2); // disregarded

        Message msg4 = new Message(4, "0838884567", "It is dinner time !");
        msg4.sentMessage(1); // sent

        // The longest message should be message 2
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestMessage());
    }

    // -------------------------------------------------------
    // TEST 12: Search for message by ID
    // Test data from rubric: Message 4
    // -------------------------------------------------------
    @Test
    public void testSearchByMessageID() {
        // Reset arrays before test
        Message.sentCount = 0;

        // Add test data from rubric
        Message msg4 = new Message(4, "0838884567", "It is dinner time !");
        msg4.sentMessage(1); // sent

        // Search by the generated ID
        String id = Message.messageIDs[0];
        String result = Message.searchByMessageID(id);
        assertTrue(result.contains("It is dinner time !"));
    }

    // -------------------------------------------------------
    // TEST 13: Search messages by recipient
    // Test data from rubric: +27838884567
    // -------------------------------------------------------
    @Test
    public void testSearchByRecipient() {
        // Reset arrays before test
        Message.sentCount = 0;
        Message.storedCount = 0;

        // Add test data from rubric
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage(3); // stored

        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        msg5.sentMessage(3); // stored

        // Search by recipient
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // -------------------------------------------------------
    // TEST 14: Delete a message using message hash
    // Test data from rubric: Message 2
    // -------------------------------------------------------
    @Test
    public void testDeleteMessage() {
        // Reset arrays before test
        Message.sentCount = 0;
        Message.storedCount = 0;

        // Add test data from rubric
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage(3); // stored

        // Get the hash that was generated
        String hash = Message.messageHashes[0];

        // Delete using hash
        String result = Message.deleteMessage(hash);
        assertTrue(result.contains("successfully deleted"));
    }

    // -------------------------------------------------------
    // TEST 15: Display report
    // -------------------------------------------------------
    @Test
    public void testDisplayReport() {
        // Reset arrays before test
        Message.sentCount = 0;

        // Add test data
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sentMessage(1); // sent

        // Check report contains correct information
        String report = Message.displayReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("+27834557896"));
    }
}
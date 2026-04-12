/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // We create one Login object that all tests will use
    // This matches the test data from the rubric
    Login testUser = new Login(
        "John",           // first name
        "Doe",            // last name
        "kyl_1",          // username - from rubric test data
        "Ch&&sec@ke99!",  // password - from rubric test data
        "+27838968976"    // cell phone - from rubric test data
    );

    // -------------------------------------------------------
    // TEST 1: Username is correctly formatted
    // Test data from rubric: "kyl_1" should return true
    // -------------------------------------------------------
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    // -------------------------------------------------------
    // TEST 2: Username is incorrectly formatted
    // Test data from rubric: "kyle!!!!!!!" should return false
    // -------------------------------------------------------
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    // -------------------------------------------------------
    // TEST 3: Password meets complexity requirements
    // Test data from rubric: "Ch&&sec@ke99!" should return true
    // -------------------------------------------------------
    @Test
    public void testPasswordMeetsRequirements() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    // -------------------------------------------------------
    // TEST 4: Password does not meet complexity requirements
    // Test data from rubric: "password" should return false
    // -------------------------------------------------------
    @Test
    public void testPasswordDoesNotMeetRequirements() {
        Login user = new Login("John", "Doe", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    // -------------------------------------------------------
    // TEST 5: Cell phone is correctly formatted
    // Test data from rubric: "+27838968976" should return true
    // -------------------------------------------------------
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    // -------------------------------------------------------
    // TEST 6: Cell phone is incorrectly formatted
    // Test data from rubric: "08966553" should return false
    // -------------------------------------------------------
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    // -------------------------------------------------------
    // TEST 7: Login is successful
    // -------------------------------------------------------
    @Test
    public void testLoginSuccessful() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // -------------------------------------------------------
    // TEST 8: Login fails with wrong details
    // -------------------------------------------------------
    @Test
    public void testLoginFailed() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("wronguser", "wrongpass"));
    }

    // -------------------------------------------------------
    // TEST 9: Username correctly formatted returns correct message
    // Test data from rubric: "kyl_1" 
    // -------------------------------------------------------
    @Test
    public void testUsernameCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", user.registerUser());
    }

    // -------------------------------------------------------
    // TEST 10: Username incorrectly formatted returns correct message
    // Test data from rubric: "kyle!!!!!!!"
    // -------------------------------------------------------
    @Test
    public void testUsernameIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", user.registerUser());
    }

    // -------------------------------------------------------
    // TEST 11: Password meets requirements - correct message
    // Test data from rubric: "Ch&&sec@ke99!"
    // -------------------------------------------------------
    @Test
    public void testPasswordCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", user.registerUser());
    }

    // -------------------------------------------------------
    // TEST 12: Password does not meet requirements - correct message
    // Test data from rubric: "password"
    // -------------------------------------------------------
    @Test
    public void testPasswordIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.registerUser());
    }

    // -------------------------------------------------------
    // TEST 13: Cell number correctly formatted - correct message
    // Test data from rubric: "+27838968976"
    // -------------------------------------------------------
    @Test
    public void testCellCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", user.registerUser());
    }

    // -------------------------------------------------------
    // TEST 14: Cell number incorrectly formatted - correct message
    // Test data from rubric: "08966553"
    // -------------------------------------------------------
    @Test
    public void testCellIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", user.registerUser());
    }
}
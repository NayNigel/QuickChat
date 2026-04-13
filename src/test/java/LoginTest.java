/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsRequirements() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetRequirements() {
        Login user = new Login("John", "Doe", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("wronguser", "wrongpass"));
    }

    @Test
    public void testUsernameCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.registerUser().contains("Username successfully captured."));
    }

    @Test
    public void testUsernameIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
            user.registerUser()
        );
    }

    @Test
    public void testPasswordCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.registerUser().contains("Password successfully captured."));
    }

    @Test
    public void testPasswordIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "password", "+27838968976");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
            user.registerUser()
        );
    }

    @Test
    public void testCellCorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.registerUser().contains("Cell phone number successfully added."));
    }

    @Test
    public void testCellIncorrectMessage() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(
            "Cell phone number incorrectly formatted or does not contain international code.",
            user.registerUser()
        );
    }
}
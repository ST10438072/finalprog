/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.finalpoe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testCheckMessageID() {
    }

    @Test
    public void testCheckRecipientcell() {
    }

    @Test
    public void testCreateMessageHash() {
    }

    @Test
    public void testSentMsg() {
    }

    @Test
    public void testPrintMessages() {
    }

    @Test
    public void testCheckMessageLength() {
    }

    @Test
    public void testReturnTotalMessages() {
    }

    @Test
    public void testStoreMessage() {
    }
    
   @Test
    public void testMessageLengthValid() {

        String message = "Hello World";

        assertTrue(message.length() <= 250);
    }

    @Test
    public void testMessageLengthTooLong() {

        String message = "A".repeat(251);

        assertTrue(message.length() > 250);
    }

    @Test
    public void testMessageIDGeneration() {

        int messageID = 100000;

        assertEquals(6,
                String.valueOf(messageID).length());
    }

    @Test
    public void testHashGeneration() {

        String hash = "HASH123";

        assertTrue(hash.startsWith("HASH"));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.finalpoe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class UserLoginClassTest {
    
   public UserLoginClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class UserLoginClass.
     */
    @Test
    public void testCheckUserName() {
        boolean expected = true;
        boolean actual = UserLoginClass.checkUserName("kyl_1");
        assertEquals("Username test passed", expected, actual);
    }

    /**
     * Test of valid password
     */
    @Test
    public void testCheckPasswordComplexityValid() {
        boolean expected = true;
        boolean actual = UserLoginClass.checkPasswordComplexity("Ch&&sec@ke99");
        assertEquals("Valid password test succeded", expected, actual);
    }

    /**
     * Test of invalid password
     */
    @Test
    public void testCheckPasswordComplexityInvalid() {
        boolean expected = false;
        boolean actual = UserLoginClass.checkPasswordComplexity("password");
        assertEquals("Invalid password test failed", expected, actual);
    }

    /**
     * Test of phone number
     */
    @Test
    public void testCheckCellPhoneNumber() {
        boolean expected = true;
        boolean actual = UserLoginClass.checkCellPhoneNumber("+27831234567");
        assertEquals("Phone number test succeded", expected, actual);
    }

    /**
     * Test register user
     */
    @Test
    public void testRegisterUser() {
        String expected = "User has been registered successfully.\n"
                + "      Captured Details:\n"
                + "Phone Number: +27831234567\n"
                + "Password: Ch&&sec@ke99";

        String actual = UserLoginClass.registerUser(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99",
                "+27831234567"
        );

        assertEquals("Register user test failed", expected, actual);
    }

    /**
     * Test login user
     */
    @Test
    public void testLoginUser() {
        UserLoginClass.registerUser(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99",
                "+27831234567"
        );

        boolean expected = true;
        boolean actual = UserLoginClass.loginUser("kyl_1", "Ch&&sec@ke99");

        assertEquals("Login test succeded", expected, actual);
    }

    /**
     * Test return login status
     */
    @Test
    public void testReturnLoginStatus() {
        UserLoginClass.registerUser(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99",
                "+27831234567"
        );

        String expected = "Welcome Kyle Smith, it is great to see you.";
        String actual = UserLoginClass.returnLoginStatus(true);

        assertEquals("Login status test failed", expected, actual);
    }
}

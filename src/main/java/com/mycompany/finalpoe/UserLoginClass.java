/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalpoe;

/**
 *
 * @author lab_services_student
 */
public class UserLoginClass {
    static String storedFirstName;
    static String storedLastName;
    static String storedUsername;
    static String storedPassword;
    static String storedPhone;

    
    public UserLoginClass(String fname,String lname,String uname,String password,String phone ){
        this.storedFirstName = fname;
        this.storedLastName = lname;
        this.storedUsername = uname;
        this.storedPassword = password;
        this.storedPhone= phone;
    }
    
    
    // Check username
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean capital = false;
        boolean number = false;
        boolean special = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                capital = true;
            } 
            else if (Character.isDigit(ch)) {
                number = true;
            } 
            else if (!Character.isLetterOrDigit(ch)) {
                special = true;
            }
        }

        return capital && number && special;
    }

    // Check SA phone number
    public static boolean checkCellPhoneNumber(String phone) {
        return phone.matches("\\+27\\d{9}");
    }

    // Register user 
    public static String registerUser(String firstName, String lastName, String username, String password, String phone) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters long.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted.";
        }

        storedFirstName = firstName;
        storedLastName = lastName;
        storedUsername = username;
        storedPassword = password;
        storedPhone = phone;
        
         return "User has been registered successfully.\n"
             + "      Captured Details:\n"
             + "Phone Number: " + storedPhone + "\n"
             + "Password: " + storedPassword;
    }

    // Login user
    public static boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword) && password.equals(storedPassword);
    }

    // Return login status 
    public static String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + storedFirstName + " " + storedLastName + ", it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

}


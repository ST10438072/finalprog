/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalpoe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author lab_services_student
 */
public class Finalpoe {

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
        
        Message msg = new Message(); // assuming your class exists

   

        System.out.println("\n--- Registration ---");
        // Get user details
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.println("Captured Password: " + password);

        System.out.print("Enter South African phone number (+27): ");
        String phone = input.nextLine();

        System.out.println("Captured Phone Number: " + phone);

        Random random = new Random();

        System.out.println("\n--- Login ---");

        // Login attempt
        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        // Validation
        boolean validUsername = UserLoginClass.checkUserName(username);
        boolean validPassword = UserLoginClass.checkPasswordComplexity(password);
        boolean validPhone = UserLoginClass.checkCellPhoneNumber(phone);

        if (validUsername && validPassword && validPhone) {

            // Register user
            String message = UserLoginClass.registerUser(
                    firstName, lastName, username, password, phone);

            // Show registration result
            System.out.println("--- Registration ---");
            System.out.println(message);

            // Proceed to login ONLY if registration succeeded
            if (message.contains("success")) {

                boolean loginStatus = UserLoginClass.loginUser(
                        loginUsername, loginPassword);

                System.out.println("\n--- Login Result ---");
                System.out.println(
                        UserLoginClass.returnLoginStatus(loginStatus));

            } else {

                System.out.println(
                        "Login skipped due to registration failure.");
            }
        }

        System.out.println("\nLogin successful. Welcome " + username + "!");

        // =========================
        // MESSAGE LIMIT
        // =========================
        System.out.print("Enter number of messages you want to send: ");
        int maxMessages = input.nextInt();
        input.nextLine();

        int sentCount = 0;

        // =========================
        // DATA STRUCTURES (MERGED)
        // =========================
        ArrayList<String> sentMessages = new ArrayList<>();
        ArrayList<String> storedMessages = new ArrayList<>();
        ArrayList<String> disregardedMessages = new ArrayList<>();

        ArrayList<String> recipients = new ArrayList<>();
        ArrayList<String> messageIDs = new ArrayList<>();
        ArrayList<String> messageHashes = new ArrayList<>();

        int menuOption;

        do {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Send Message");
            System.out.println("2. Show Sent Messages");
            System.out.println("3. Show JSON File");
            System.out.println("4. Stored Messages Menu");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            menuOption = input.nextInt();
            input.nextLine();

            // =========================
            // SEND MESSAGE
            // =========================
            if (menuOption == 1) {

                if (sentCount >= maxMessages) {
                    System.out.println("Message limit reached.");
                    continue;
                }

                int messageID = 100000 + random.nextInt(900000);

                System.out.print("Enter recipient number: ");
                String recipient = input.nextLine();

                System.out.print("Enter message (max 250 chars): ");
                String message = input.nextLine();

                if (message.length() > 250) {
                    System.out.println("Message too long.");
                    continue;
                }

                String hash = "HASH" + (100 + random.nextInt(900));

                System.out.println("\n--- MESSAGE DETAILS ---");
                System.out.println("ID: " + messageID);
                System.out.println("Recipient: " + recipient);
                System.out.println("Message: " + message);
                System.out.println("Hash: " + hash);

                System.out.println("\n1. Send");
                System.out.println("2. Store");
                System.out.println("3. Disregard");

                int choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {

                    sentMessages.add(message);
                    recipients.add(recipient);
                    messageIDs.add(String.valueOf(messageID));
                    messageHashes.add(hash);

                    sentCount++;
                    System.out.println("Message sent.");

                } else if (choice == 2) {

                    storedMessages.add(message);
                    recipients.add(recipient);
                    messageIDs.add(String.valueOf(messageID));
                    messageHashes.add(hash);

                    try (FileWriter writer = new FileWriter("messages.json", true)) {

                        writer.write("{\n");
                        writer.write("\"MessageID\":\"" + messageID + "\",\n");
                        writer.write("\"Recipient\":\"" + recipient + "\",\n");
                        writer.write("\"Message\":\"" + message + "\",\n");
                        writer.write("\"Hash\":\"" + hash + "\"\n");
                        writer.write("}\n");

                        System.out.println("Message stored to file.");

                    } catch (IOException e) {
                        System.out.println("Error writing file.");
                    }

                } else if (choice == 3) {

                    disregardedMessages.add(message);
                    System.out.println("Message discarded.");
                }

            }

            // =========================
            // SENT MESSAGES
            // =========================
            else if (menuOption == 2) {

                System.out.println("\n--- SENT MESSAGES ---");

                for (String m : sentMessages) {
                    System.out.println(m);
                }
            }

            // =========================
            // JSON FILE
            // =========================
            else if (menuOption == 3) {

                try {

                    File file = new File("messages.json");

                    if (file.exists()) {

                        Scanner reader = new Scanner(file);

                        System.out.println("\n--- JSON CONTENT ---");

                        while (reader.hasNextLine()) {
                            System.out.println(reader.nextLine());
                        }

                        reader.close();

                    } else {
                        System.out.println("File not found.");
                    }

                } catch (IOException e) {
                    System.out.println("Error reading file.");
                }
            }

            // =========================
            // STORED MENU (FROM FIRST PROGRAM)
            // =========================
            else if (menuOption == 4) {

                int storedOption;

                do {

                    System.out.println("\n===== STORED MENU =====");
                    System.out.println("1. Display Sender & Recipient");
                    System.out.println("2. Longest Message");
                    System.out.println("3. Search by Message ID");
                    System.out.println("4. Search by Recipient");
                    System.out.println("5. Delete by Hash");
                    System.out.println("6. Full Report");
                    System.out.println("0. Back");

                    storedOption = input.nextInt();
                    input.nextLine();

                    if (storedOption == 1) {

                        for (int i = 0; i < storedMessages.size(); i++) {
                            System.out.println("Recipient: " + recipients.get(i));
                            System.out.println("Message: " + storedMessages.get(i));
                            System.out.println();
                        }

                    } else if (storedOption == 2) {

                        String longest = "";

                        for (String m : storedMessages) {
                            if (m.length() > longest.length()) longest = m;
                        }

                        System.out.println("Longest: " + longest);

                    } else if (storedOption == 3) {

                        System.out.print("Enter ID: ");
                        String id = input.nextLine();

                        boolean found = false;

                        for (int i = 0; i < messageIDs.size(); i++) {
                            if (messageIDs.get(i).equals(id)) {
                                System.out.println("Recipient: " + recipients.get(i));
                                System.out.println("Message: " + storedMessages.get(i));
                                found = true;
                            }
                        }

                        if (!found) System.out.println("Not found.");

                    } else if (storedOption == 4) {

                        System.out.print("Enter recipient: ");
                        String rec = input.nextLine();

                        for (int i = 0; i < recipients.size(); i++) {
                            if (recipients.get(i).equals(rec)) {
                                System.out.println(storedMessages.get(i));
                            }
                        }

                    } else if (storedOption == 5) {

                        System.out.print("Enter hash: ");
                        String hash = input.nextLine();

                        Iterator<String> it = messageHashes.iterator();
                        int i = 0;
                        boolean deleted = false;

                        while (it.hasNext()) {

                            it.next();

                            if (messageHashes.get(i).equals(hash)) {
                                storedMessages.remove(i);
                                it.remove();
                                System.out.println("Deleted.");
                                deleted = true;
                            }

                            i++;
                        }

                        if (!deleted) System.out.println("Hash not found.");

                    } else if (storedOption == 6) {

                        for (int i = 0; i < messageHashes.size(); i++) {
                            System.out.println("Hash: " + messageHashes.get(i));
                            System.out.println("Recipient: " + recipients.get(i));

                            if (i < storedMessages.size()) {
                                System.out.println("Message: " + storedMessages.get(i));
                            }

                            System.out.println();
                        }
                    }

                } while (storedOption != 0);
            }

        } while (menuOption != 5);

        System.out.println("Exiting program...");
        input.close();
    }
}
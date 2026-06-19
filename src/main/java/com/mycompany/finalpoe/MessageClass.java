/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalpoe;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author lab_services_student
 */
public class MessageClass {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        String correctUsername = "admin";
        String correctPassword = "1234";

        // LOGIN
        System.out.println("===== LOGIN =====");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

       // CHECK LOGIN
        if (username.equals(correctUsername)
                && password.equals(correctPassword)) {

            System.out.println("\nWelcome " + username + "!");
            System.out.println("Login successful.\n");
            
            // Ask user how many messages they want to send
            System.out.print("Enter the number of messages you want to send: ");
            int maxMessages = input.nextInt();
            input.nextLine(); // clear buffer

            int sentMessages = 0;

            int menuOption = 0;

            do {

                // MAIN MENU
                System.out.println("===== MAIN MENU =====");
                System.out.println("1. Send Message");
                System.out.println("2. Show Recently Sent Messages");
                System.out.println("3. Show JSON File");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                menuOption = input.nextInt();
                input.nextLine(); // clear buffer

                // OPTION 1: SEND MESSAGE
                if (menuOption == 1) {
                    
                      // Check message limit
                if (sentMessages >= maxMessages) {

                    System.out.println(
                            "\nYou have reached your message limit.");

                    System.out.println(
                            "No more messages can be sent.");

                    continue;
                }

                // Generate message ID
                int messageID =
                        100000 + random.nextInt(900000);

                // Recipient
                System.out.print(
                        "Enter recipient name: ");

                String recipient =
                        input.nextLine();

                // Recipient phone
                System.out.print(
                        "Enter recipient phone number (+27): ");

                String recipientPhone =
                        input.nextLine();

                // Validate recipient phone
                if (!UserLoginClass.checkCellPhoneNumber(
                        recipientPhone)) {

                    System.out.println(
                            "Invalid recipient phone number.");

                    continue;
                }

                // Message
                System.out.print(
                        "Enter your message "
                                + "(max 250 characters): ");

                String message =
                        input.nextLine();

                // Validate message length
                if (message.length() > 250) {

                    System.out.println(
                            "Message exceeds 250 characters.");

                    continue;
                }

                // Display details
                System.out.println(
                        "\n===== MESSAGE DETAILS =====");

                System.out.println(
                        "Message ID: " + messageID);

                System.out.println(
                        "Recipient: " + recipient);

                System.out.println(
                        "Recipient Phone: "
                                + recipientPhone);

                System.out.println(
                        "Message: " + message);

                // Send/store option
                System.out.println("\nChoose an option:");
                System.out.println("1. Send Message Now");
                System.out.println("2. Store Message To Send Later");

                int messageOption =
                        input.nextInt();

                input.nextLine();

                // =========================
                // SEND MESSAGE
                // =========================
                if (messageOption == 1) {

                    System.out.println(
                            "\nMessage successfully sent.");

                    sentMessages++;
                }

                // =========================
                // STORE MESSAGE
                // =========================
                else if (messageOption == 2) {

                    try {

                        FileWriter writer =
                                new FileWriter(
                                        "messages.json",
                                        true);

                        writer.write("{\n");
                        writer.write(
                                "\"MessageID\":\""
                                        + messageID + "\",\n");

                        writer.write(
                                "\"Recipient\":\""
                                        + recipient + "\",\n");

                        writer.write(
                                "\"RecipientPhone\":\""
                                        + recipientPhone + "\",\n");

                        writer.write(
                                "\"Message\":\""
                                        + message + "\"\n");

                        writer.write("}\n");

                        writer.close();

                        System.out.println(
                                "\nMessage successfully stored.");

                        System.out.println(
                                "Saved to messages.json");

                        sentMessages++;

                    } catch (IOException e) {

                        System.out.println(
                                "Error saving message.");
                    }
                }

                // Invalid message option
                else {

                    System.out.println(
                            "Invalid option.");
                }
            }

            // =========================
            // OPTION 2
            // =========================
            else if (menuOption == 2) {

                System.out.println(
                        "\nFeature not available yet.");

                System.out.println(
                        "Coming soon :)");
            }

            // =========================
            // OPTION 3: SHOW JSON FILE
            // =========================
            else if (menuOption == 3) {

                try {

                    File file =
                            new File("messages.json");

                    if (file.exists()) {

                        Scanner fileReader =
                                new Scanner(file);

                        System.out.println(
                                "\n===== JSON FILE CONTENT =====");

                        while (fileReader.hasNextLine()) {

                            System.out.println(
                                    fileReader.nextLine());
                        }

                        fileReader.close();

                    } else {

                        System.out.println(
                                "\nmessages.json file not found.");
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Error opening JSON file.");
                }
            }

            // =========================
            // OPTION 4: EXIT
            // =========================
            else if (menuOption == 4) {

                System.out.println(
                        "\nExiting program...");

                System.out.println(
                        "Goodbye!");
            }

            // =========================
            // INVALID MENU OPTION
            // =========================
            else {

                System.out.println(
                        "\nInvalid option. Try again.");
            }

        } while (menuOption != 4);

        input.close();
    }
  }
}
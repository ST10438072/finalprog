package com.mycompany.finalpoe;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author lab_services_student
 */
public class Message {
     private String msgID;
    private String recipient;
    private String message;
    private String msgHash;
    private int msgNum;
    
    private static int totalnumber = 0;

    public Message() {
    }
    
    public Message(String msgID, String recipient, String message, String msgHash, int msgNum) {
        this.msgID = msgID;
        this.recipient = recipient;
        this.message = message;
        this.msgHash = msgHash;
        this.msgNum = msgNum;
    }
    
    public boolean checkMessageID(){
        if(msgID.length() <= 10){
            return true;
        } else {
            return false;
        }
    }
    
    public String checkRecipientcell(){
        String phone = "^\\+27\\d{9}$";
        if(recipient.matches(phone)){
            return "the phone number is correct";
        }else{
            return "the phone number is incorrect";
        }
    }
    
    public String createMessageHash(){
        String digits = msgID.substring(0,2);
        String[] words = message.split("");
        
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        msgHash = digits +":"+msgNum+":"+firstWord+lastWord;
        return msgHash;
    }
    
    public String sentMsg(int choice){
        switch (choice){
            case 1: 
                totalnumber++;
                return"Message successfully sent";
            case 2:
                totalnumber++;
                return "Message successfully stored";
            case 3:
                return "press 0 to delete the message" + "Invalid selection";
            default :
                return "Please try again";
        }
    }
    
    public String printMessages() {
        String output = "[\n";
        output += "  {\n";
        output += "    \"MessageID\": \"" + this.msgID + "\",\n";
        output += "    \"MessageHash\": \"" + this.msgHash + "\",\n";  // hash second
        output += "    \"Recipient\": \"" + this.recipient + "\",\n";
        output += "    \"Message\": \"" + this.message + "\"\n";           // no Status
        output += "  }\n";
        output += "]";
        return output;
    }

    //check how long the message is
    public boolean checkMessageLength() {
        return message.length() <= 250;
    }
   
   

    public int returnTotalMessages() {
        return totalnumber; 
    }

    public String storeMessage() {

       
        String newEntry = "  {\n"
                + "    \"MessageID\": \"" + this.msgID + "\",\n"
                + "    \"MessageHash\": \"" + this.msgHash + "\",\n"
                + "    \"Recipient\": \"" + this.recipient + "\",\n"
                + "    \"Message\": \"" + this.message + "\"\n"
                + "  }";

        String filePath = "messages.json";
        String existingContent = "";

        
        try (FileReader reader = new FileReader(filePath)) {
            StringBuilder sb = new StringBuilder();
            int c;
            
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            existingContent = sb.toString().trim();
        } catch (IOException e) {
            
            existingContent = "";
        }

        // Step 3: Build the updated JSON array
        String updatedJson;

        if (existingContent.isEmpty()) {
            updatedJson = "[\n" + newEntry + "\n]";

        } else {
            int closingBracket = existingContent.lastIndexOf("]");
            String withoutClose = existingContent.substring(0, closingBracket).trim();

            updatedJson = withoutClose + ",\n" + newEntry + "\n]";
        }

        
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(updatedJson);
            return "Message successfully stored to messages.json";
        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        } 
    }
}


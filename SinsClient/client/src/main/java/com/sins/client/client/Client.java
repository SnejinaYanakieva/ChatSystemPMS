/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.client;

/**
 *
 * @author Lenovo
 */
import java.net.URI;
import java.util.Scanner;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

class Client {
public static String userID = null;

    public static final String SERVER = "ws://localhost:8080/ws/chat";

    public static void main(String[] args) throws Exception {
        ClientManager client = ClientManager.createClient();
        String message;

        // connect to server
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tiny Chat!");
        System.out.println("What's your name?");
        String user = scanner.nextLine();
        Session session = client.connectToServer(ClientEnd.class, new URI(SERVER));
        System.out.println("You are logged in as: " + user);

        // repeatedly read a message and send it to the server (until quit)
        do {

            message = scanner.nextLine();
            session.getBasicRemote().sendText(createUserJson(message));
        } while (!message.equalsIgnoreCase("quit"));
    }

    private static String createUserJson(String message) {
        return "{"
                + "\"type\" : \"USER\", "
                + "\"subtype\": \"register\", "
                + "\"clientid\": \"client\", "
                + "\"content\": "
                    + "{ \"message\": \""
                        + message
                    + "\"}"
               + "}";
    }

}

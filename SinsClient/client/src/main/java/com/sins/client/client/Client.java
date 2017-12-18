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
import com.sins.client.businessLogic.User;
import com.sins.client.model.CurrentClient;
import java.net.URI;
import java.util.Scanner;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class Client {
public static String userID = null;

    public static final String SERVER = "ws://localhost:8080/ws/chat";

    public static void startClientApplication() throws Exception {
        ClientManager client = ClientManager.createClient();
        String message;

        // connect to server
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tiny Chat!");
        System.out.println("What's your name?");
        Session session = client.connectToServer(ClientEnd.class, new URI(SERVER));
        ClientEnd.setSession(session);

        // repeatedly read a message and send it to the server (until quit)
            CurrentClient currClient = new CurrentClient();
         currClient.setNickname("userSINS2");
            currClient.setName("UserName");
           // new User().register(currClient, "password2");
         //   new User().login("userSINS", "password");
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

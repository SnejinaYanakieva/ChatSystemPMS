package com.sins.server.server;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author SYanakieva
 */
public class Server 
{
      public static void main(String[] args) {
        org.glassfish.tyrus.server.Server server;
        server = new org.glassfish.tyrus.server.Server("localhost", 8080, "/ws", new HashMap<String,Object>(), ServerEndpoint.class);
        Scanner scanner = new Scanner(System.in);
        String command = "";
        try {
            server.start();
            System.out.println("Type stop to stop the server..");
           do{
               command = scanner.nextLine();
           }while (!command.equals("stop"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}

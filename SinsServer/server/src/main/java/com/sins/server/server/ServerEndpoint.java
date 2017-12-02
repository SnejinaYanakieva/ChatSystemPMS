/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.server;

import java.io.IOException;
import static java.lang.String.format;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonObject;
import static javax.management.Query.value;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author SYanakieva
 */
@javax.websocket.server.ServerEndpoint(value = "/chat")
public class ServerEndpoint {
    
    static Map<String, Session> peers = Collections.synchronizedMap(new HashMap<String, Session>());
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("%s joined the chat room.", session.getId()));
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        
    }
    
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", session.getId()));
           peers.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), session))
                .map(Map.Entry::getKey)
                .forEach(clientid -> peers.remove(clientid));
    }

    public static void sendMessage(Map<String, JsonObject> messageJson) throws IOException{
        for(Map.Entry<String, JsonObject>message : messageJson.entrySet()){
             peers.get(message.getKey()).getBasicRemote().sendText(message.getValue().toString());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.server;

import com.sins.server.resolver.Resolver;
import java.io.IOException;
import java.io.StringReader;
import static java.lang.String.format;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        Resolver.INSTANCE.resolve(jsonMessage);
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

    public static void sendMessage(Map<String, JsonObject> messageJson) throws IOException {
        for (Map.Entry<String, JsonObject> message : messageJson.entrySet()) {
            peers.get(message.getKey()).getBasicRemote().sendText(message.getValue().toString());
        }
    }
}

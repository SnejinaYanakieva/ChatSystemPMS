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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
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
        peers.put(session.getId(), session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        try {
            Resolver.INSTANCE.resolve(jsonMessage);
        } catch (Exception e) {
            JsonObject jsonResponse = Json
                    .createObjectBuilder(jsonMessage)
                    .add("content", Json
                            .createObjectBuilder()
                            .add("errorMessage", "Server could not process your request!")
                            .build())
                    .add("success", false)
                    .build();
            Map<String, JsonObject> errorResponse = new HashMap<>();
            getClientIdsBySession(session)
                    .forEach(clientid -> {
                        errorResponse.put(clientid, jsonResponse);
                    });
            sendMessage(errorResponse);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", session.getId()));
        getClientIdsBySession(session)
                .forEach(clientid -> peers.remove(clientid));
    }

    public static void sendMessage(Map<String, JsonObject> messageJson) throws IOException {
        for (Map.Entry<String, JsonObject> message : messageJson.entrySet()) {
            peers.get(message.getKey()).getBasicRemote().sendText(message.getValue().toString());
        }
    }

    private List<String> getClientIdsBySession(Session session) {
        return peers.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), session))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

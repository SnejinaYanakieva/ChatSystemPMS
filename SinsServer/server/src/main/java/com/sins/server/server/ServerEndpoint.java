/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.server;

import com.sins.server.resolver.Resolver;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import static java.lang.String.format;
import java.nio.ByteBuffer;
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

    public static Map<String, Session> peers = Collections.synchronizedMap(new HashMap<String, Session>());
    public static Map<Session, String> filesTo = Collections.synchronizedMap(new HashMap<Session, String>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New session created.");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        try {
            Map<String, JsonObject> map = Resolver.INSTANCE.resolve(jsonMessage, session);
            if (!map.isEmpty()) {
                sendMessage(map);
            }
        } catch (Exception e) {
            JsonObject jsonResponse = Json
                    .createObjectBuilder(jsonMessage)
                    .add("content", Json
                            .createObjectBuilder()
                            .add("errorMessage", "Server could not process your request!")
                            .add("success", false)
                            .build())
                    .build();
            e.printStackTrace();
            sendMessage(session, jsonResponse);
        }
    }

    @OnMessage
    public void onMessage(ByteBuffer message, Session session) throws IOException, EncodeException {
        String receiverid = filesTo.get(session);
        message.flip();
        peers.get(receiverid).getAsyncRemote().sendBinary(message);
        filesTo.remove(session);

    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", session.getId()));
        getClientIdsBySession(session)
                .forEach(clientid -> peers.remove(clientid));
    }

    public static void sendMessage(Map<String, JsonObject> messageJson) throws IOException {
        for (Map.Entry<String, JsonObject> message : messageJson.entrySet()) {
            sendMessage(peers.get(message.getKey()), message.getValue());
        }
    }

    public static void sendMessage(Session session, JsonObject messageJson) throws IOException {
        session.getBasicRemote().sendText(messageJson.toString());
    }

    private List<String> getClientIdsBySession(Session session) {
        return peers.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), session))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

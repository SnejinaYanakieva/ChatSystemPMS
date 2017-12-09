/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author SYanakieva
 */
public class ChatService {

    private Map<String, JsonObject> response = new HashMap<>();

    Map<String, JsonObject> sendMessageToFriend(String receiverId, String message) {

        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("message", message)
                .build();

        response.put(receiverId, json);
        return response;
    }

    Map<String, JsonObject> sendFileAcceptRequest(String userId, String friendId) {
        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userId)
                .build();

        response.put(friendId, json);
        return response;
    }

    Map<String, JsonObject> sendFileAcceptResponse(String userId, String friendId, boolean accepted) {
        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userId)
                .add("accepted", accepted)
                .build();

        response.put(friendId, json);
        return response;
    }

    Map<String, JsonObject> sendMessageToGroup(String userId, List<String> friendIds, String message) {
        for (String friendId : friendIds) {
            JsonObject json = Json
                    .createObjectBuilder()
                    .add("success", true)
                    .add("senderid", userId)
                    .add("message", message)
                    .build();

            response.put(friendId, json);
        }
        return response;
    }
}

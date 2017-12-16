/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.math.BigDecimal;
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

    public Map<String, JsonObject> sendMessageToFriend(String receiverId, String userid, String message) {

        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userid)
                .add("message", message)
                .build();

        response.put(receiverId, json);
        return response;
    }

    public Map<String, JsonObject> sendFileToFriend(String receiverId, String userid, String message) {

        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userid)
                .add("message", message)
                .build();

        response.put(receiverId, json);
        return response;
    }

    public Map<String, JsonObject> sendFileAcceptRequest(String userId, String friendId) {
        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userId)
                .build();

        response.put(friendId, json);
        return response;
    }

    public Map<String, JsonObject> sendFileAcceptResponse(String userId, String friendId, boolean accepted) {
        JsonObject json = Json
                .createObjectBuilder()
                .add("success", true)
                .add("senderid", userId)
                .add("accepted", accepted)
                .build();

        response.put(friendId, json);
        return response;
    }

    public Map<String, JsonObject> sendMessageToGroup(String userId, String groupId, String message) {
        try {
            Group group = Store.Instance.getGroupDao().getGroupById(groupId);
            if (group != null) {
                for (Person friend : group.getParticipants()) {
                    JsonObject json = Json
                            .createObjectBuilder()
                            .add("success", true)
                            .add("groupid", group.getId())
                            .add("senderid", userId)
                            .add("message", message)
                            .build();

                    response.put(friend.getId(), json);
                }

            } else {
               JsonObject json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group does not exist.");
               response.put(userId, json);
            }
        } catch (DbException ex) {
            JsonObject json = JsonBuilder.INSTANCE.buildErrorJsonConent(ex.getDbErrorMessage());
            response.put(userId, json);
        }
        return response;
    }
}

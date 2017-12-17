/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.ChatClient;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author SYanakieva
 */
public class FriendService {

    private Map<String, JsonObject> response = new HashMap<>();

    public Map<String, JsonObject> getAllFriendsAndGroups(String userId) {
        JsonObject json = null;
        try {
            ChatClient client = Store.Instance.getFriendDao().getAllFriendsAndGroups(userId);
            if (client != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(client);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("chatClient", jsonMessage)
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Friends and groups info could not be red.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Friends and groups info could not be red.");
        }
        response.put(userId, json);
        return response;
    }

    public Map<String, JsonObject> searchNewFriend(String name, String userId) {
        JsonObject json = null;
        try {
            List<Person> clients = Store.Instance.getFriendDao().searchNewFriend(name);
            if (clients != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(clients);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("clients", jsonMessage)
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Clients info could not be red.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Clients info could not be red.");
        }
        response.put(userId, json);
        return response;
    }

    public Map<String, JsonObject> addNewFriend(String friendId, String userId) {
        JsonObject json = null;
        try {
            Person friend = Store.Instance.getFriendDao().addNewFriend(friendId, userId);
            if (friend != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(friend);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("friend", jsonMessage)
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Friend could not be added.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Friend could not be added.");
        }
        response.put(userId, json);
        return response;
    }

    public Map<String, JsonObject> removeFriend(String friendId, String userId) {
        JsonObject json = null;
        try {
            ChatClient success = Store.Instance.getFriendDao().removeFriend(friendId, userId);
            if (success != null) {
                json = JsonBuilder.INSTANCE.buildEmptySuccessJsonConent();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Remove failed!");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        }
        response.put(userId, json);
        return response;
    }
}

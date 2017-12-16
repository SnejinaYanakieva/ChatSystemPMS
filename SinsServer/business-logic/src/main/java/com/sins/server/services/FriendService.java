/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.ChatClient;
import com.sins.server.model.CurrentClient;
import com.sins.server.model.Group;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
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
}

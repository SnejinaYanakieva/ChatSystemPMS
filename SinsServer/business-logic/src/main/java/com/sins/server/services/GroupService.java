/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
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
public class GroupService {

    private Map<String, JsonObject> response = new HashMap<>();

    public Map<String, JsonObject> createGroup(String groupName, String userId) {
        JsonObject json = null;
        try {
            Group group = Store.Instance.getGroupDao().createGroup(groupName, userId);
            if (group != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(group);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("group", jsonMessage)
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group could not be created.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group could not be created");
        }
        response.put(userId, json);
        return response;
    }

    public Map<String, JsonObject> addFriendToGroup(String friendid, String groupid, String userId) {
         JsonObject json = null;
        try {
            Group group = Store.Instance.getGroupDao().addFriendToGroup(friendid, groupid);
            if (group != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(group);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("group", jsonMessage)
                        .build();
                
                response.put(friendid, json);
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group could not be created.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group could not be created");
        }
        response.put(userId, json);
        
        return response;
    }
    
     public Map<String, JsonObject> removeFriendFromGroup(String friendid, String groupId, String userId) {
         JsonObject json = null;
        try {
            Group group = Store.Instance.getGroupDao().removeFriendFromGroup(friendid, groupId);
            if (group != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(group);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
               
               for (Person friend : group.getParticipants()) {
               json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("groupid", group.getId())
                        .add("senderid", userId)
                        .add("group", jsonMessage)
                        .build();

                response.put(friend.getId(), json);
            }
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Group does not exist.");
                response.put(userId, json);
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());
            response.put(userId, json);

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("You could not be removed");
            response.put(userId, json);
        }
        
        
        return response;
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.CurrentClient;
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
public class UserService {

    private Map<String, JsonObject> response = new HashMap<>();

    public Map<String, JsonObject> register(CurrentClient user, String password) {
        List<String> errrors = user.validateItem();
        JsonObject json = null;
        if (errrors.isEmpty()) {
            try {
                Boolean success = Store.Instance.getUserDao().register(user, password);
                if (success) {
                    json = JsonBuilder.INSTANCE.buildEmptySuccessJsonConent();
                } else {
                    json = JsonBuilder.INSTANCE.buildErrorJsonConent("Register request could not be executed");
                }
            } catch (DbException e) {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

            }

        } else {
            StringBuilder sb = new StringBuilder();
            for (String error : errrors) {
                sb.append(error).append(", ");
            }
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(sb.toString());
        }
        response.put("-1", json);
        return response;
    }

    public Map<String, JsonObject> login(String username, String password) {
        JsonObject json = null;
        try {
            Person clientId = Store.Instance.getUserDao().login(username, password);
            if (clientId != null) {
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("clientid", clientId.getId())
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Login failed! Try again later.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        }
        response.put("-1", json);
        return response;
    }

    public Map<String, JsonObject> readPersonalInfo(String userId) {
        JsonObject json = null;
        try {
            CurrentClient client = Store.Instance.getUserDao().readPersonalInfo(userId);
            if (client != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonInString = objectMapper.writeValueAsString(client);
                JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                JsonObject jsonMessage = jsonReader.readObject();
                //convert json string to object
                json = Json
                        .createObjectBuilder()
                        .add("success", true)
                        .add("currentClient", jsonMessage)
                        .build();
            } else {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Personal information could not be red.");
            }
        } catch (DbException e) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

        } catch (IOException ex) {
            json = JsonBuilder.INSTANCE.buildErrorJsonConent("Personal information could not be red.");
        }
        response.put(userId, json);
        return response;
    }

    public Map<String, JsonObject> updatePersonalInfo(String userId, CurrentClient info) {
        List<String> errrors = info.validateItem();
        JsonObject json = null;
        if (errrors.isEmpty()) {
            try {
                CurrentClient client = Store.Instance.getUserDao().updatePersonalInfo(userId, info);
                if (client != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonInString = objectMapper.writeValueAsString(client);
                    JsonReader jsonReader = Json.createReader(new StringReader(jsonInString));
                    JsonObject jsonMessage = jsonReader.readObject();
                    //convert json string to object
                    json = Json
                            .createObjectBuilder()
                            .add("success", true)
                            .add("currentClient", jsonMessage)
                            .build();
                } else {
                    json = JsonBuilder.INSTANCE.buildErrorJsonConent("Personal information could not be updated.");
                }
            } catch (DbException e) {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

            } catch (IOException ex) {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Personal information could not be red.");
            }
        }
        response.put(userId, json);
        return response;
    }
    
      public Map<String, JsonObject> logout(String userId) {
        JsonObject json = null;
            try {
                Boolean success = Store.Instance.getUserDao().logout(userId);
                if (success) {
                    json = JsonBuilder.INSTANCE.buildEmptySuccessJsonConent();
                } else {
                    json = JsonBuilder.INSTANCE.buildErrorJsonConent("Logout failed!");
                }
            } catch (DbException e) {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

            }
        response.put(userId, json);
        return response;
    }
      
       public Map<String, JsonObject> deleteUser(String userId) {
        /*JsonObject json = null;
            try {
                Boolean success = Store.Instance.getUserDao().deleteUser(userId);
                if (success) {
                    json = JsonBuilder.INSTANCE.buildEmptySuccessJsonConent();
                } else {
                    json = JsonBuilder.INSTANCE.buildErrorJsonConent("User could not be deleted");
                }
            } catch (DbException e) {
                json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());

            }
        response.put(userId, json);*/
        return response;
    }
}

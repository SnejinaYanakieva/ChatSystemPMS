/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.CurrentClient;
import com.sins.server.server.ServerEndpoint;
import com.sins.server.services.ChatService;
import com.sins.server.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObject;
import javax.websocket.Session;

/**
 *
 * @author SYanakieva
 */
public class Resolver {

    public static final Resolver INSTANCE = new Resolver();
    private ObjectMapper mapper = new ObjectMapper();
    Map<String, JsonObject> responseContext;
    JsonObject content;

    public Map<String, JsonObject> resolve(JsonObject json, Session session) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
        String type = json.getString("type");
        switch (type) {
            case "USER":
                resolveUserTypeRequests(json, session, responseMap);
                break;
            case "FRIEND":
                resolveFriendTypeRequests(json, responseMap);
                break;
            case "GROUP":
                resolveGroupTypeRequests(json, responseMap);
                break;
            case "CHAT":
                resolveChatTypeRequests(json, responseMap);
                break;
            default:
                String errorContent = "Requested type not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }
        return responseMap;
    }

    private void resolveUserTypeRequests(JsonObject json, Session session, Map<String, JsonObject> responseMap) {
        UserService service = new UserService();

        String subtype = json.getString("subtype");
        try {
            switch (subtype) {
                case "register":
                    content = json.getJsonObject("content");
                    CurrentClient currenClient = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("currentClient")
                                    .toString(),
                                    CurrentClient.class);
                    String password = content.getString("password");
                    responseContext = service.register(currenClient, password);
                    for (String id : responseContext.keySet()) {
                        ServerEndpoint.sendMessage(session, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                    }
                    break;

                case "login":
                    content = json.getJsonObject("content");
                    String username = content.getString("username");
                    String pass = content.getString("password");
                    responseContext = service.login(username, pass);
                    for (String id : responseContext.keySet()) {
                        responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(id, session);
                    }

                    break;
                case "readPersonalInfo":
                    content = json.getJsonObject("content");
                    String clientid = content.getString("clientid");
                    responseContext = service.readPersonalInfo(clientid);
                    for (String id : responseContext.keySet()) {
                        responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(id, session);
                    }
                    break;
                case "updatePersonalInfo":
                    content = json.getJsonObject("content");
                    String clientId = content.getString("clientid");
                    CurrentClient info = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("currentClient")
                                    .toString(),
                                    CurrentClient.class);
                    responseContext = service.updatePersonalInfo(clientId, info);
                    for (String id : responseContext.keySet()) {
                        responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(id, session);
                    }
                    break;
                case "logout":
                    content = json.getJsonObject("content");
                    String client = content.getString("clientid");
                    responseContext = service.logout(client);
                    for (String id : responseContext.keySet()) {
                        responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(id, session);
                    }
                    break;
                case "deleteUser":
                    throw new UnsupportedOperationException();
                default:
                    String errorContent = "Requested USER type subtype not recognized!";
                    JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                    responseMap.put(json.getString("clientId"), response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorContent = "Server could not execute your request!";
            JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
            responseMap.put(json.getString("clientId"), response);
        }

    }

    private void resolveGroupTypeRequests(JsonObject json, Map<String, JsonObject> responseMap) {
        String subtype = json.getString("subtype");
        switch (subtype) {
            case "createGroup":
                break;
            case "addFriendToGroup":
                break;
            case "removeFriendFromGroup":
                break;
            case "getAllGroupParticipants":
                break;
            case "deleteGroup":
                break;
            default:
                String errorContent = "Requested GROUP type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }

    }

    private void resolveChatTypeRequests(JsonObject json, Map<String, JsonObject> responseMap) {
        String subtype = json.getString("subtype");
        ChatService service = new ChatService();
        switch (subtype) {
            case "sendMessageToFriend":
                content = json.getJsonObject("content");
                String userid = content.getString("userid");
                String receiverid = content.getString("receiverid");
                String message = content.getString("message");
                responseContext = service.sendMessageToFriend(receiverid, userid, message);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "sendFileAcceptRequest":
                break;
            case "sendFileAcceptResponse":
                break;
            case "sendFileToFriend":
                break;
            case "sendMessageToGroup":
                break;
            default:
                String errorContent = "Requested CHAT type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }

    }

    private void resolveFriendTypeRequests(JsonObject json, Map<String, JsonObject> responseMap) {
        String subtype = json.getString("subtype");
        switch (subtype) {
            case "getAllFriends":
                break;
            case "searchNewFriend":
                break;
            case "addNewFriend":
                break;
            case "removeFriend":
                break;
            default:
                String errorContent = "Requested FRIEND type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }

    }

}

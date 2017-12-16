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
import com.sins.server.services.FriendService;
import com.sins.server.services.GroupService;
import com.sins.server.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
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
                resolveChatTypeRequests(json, responseMap, session);
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
                         ServerEndpoint.sendMessage(session, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(responseContext.get(id).getString("clientid"), session);
                    }

                    break;
                case "readPersonalInfo":
                    content = json.getJsonObject("content");
                    String clientid = json.getString("clientid");
                    responseContext = service.readPersonalInfo(clientid);
                    for (String id : responseContext.keySet()) {
                        responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                        ServerEndpoint.peers.put(id, session);
                    }
                    break;
                case "updatePersonalInfo":
                    content = json.getJsonObject("content");
                    String clientId = json.getString("clientid");
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
                    String client = json.getString("clientid");
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
        String clientid;
        GroupService service = new GroupService();
        switch (subtype) {
            case "createGroup":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String groupName = content.getString("groupName");
                responseContext = service.createGroup(groupName, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "addFriendToGroup":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String groupid = content.getString("groupID");
                String friendid = content.getString("friendID");
                responseContext = service.addFriendToGroup(friendid, groupid, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "removeFriendFromGroup":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String groupId = content.getString("groupID");
                String friendId = content.getString("friendID");
                responseContext = service.removeFriendFromGroup(friendId, groupId, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
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

    private void resolveChatTypeRequests(JsonObject json, Map<String, JsonObject> responseMap, Session session) {
        String subtype = json.getString("subtype");
        ChatService service = new ChatService();
        String userid;
        String receiverid;
        switch (subtype) {
            case "sendMessageToFriend":
                content = json.getJsonObject("content");
                userid = json.getString("clientid");
                receiverid = content.getString("receiverid");
                String message = content.getString("message");
                responseContext = service.sendMessageToFriend(receiverid, userid, message);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "sendFileAcceptRequest":
                content = json.getJsonObject("content");
                userid = json.getString("clientid");
                receiverid = content.getString("receiverid");
                responseContext = service.sendFileAcceptRequest(userid, receiverid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "sendFileAcceptResponse":
                content = json.getJsonObject("content");
                userid = json.getString("clientid");
                receiverid = content.getString("receiverid");
                Boolean accepted = content.getBoolean("accepted");
                responseContext = service.sendFileAcceptResponse(userid, receiverid, accepted);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "sendFileToFriend":
                content = json.getJsonObject("content");
                userid = json.getString("clientid");
                receiverid = content.getString("receiverid");
                ServerEndpoint.filesTo.put(session, receiverid);
                responseMap.put(userid, JsonBuilder.INSTANCE.buildJson(json, true, Json
                        .createObjectBuilder()
                        .add("success", true)
                        .build()));

                break;
            case "sendMessageToGroup":
                content = json.getJsonObject("content");
                userid = json.getString("clientid");
                receiverid = content.getString("groupid");
                String groupmessage = content.getString("message");
                responseContext = service.sendMessageToGroup(userid, receiverid, groupmessage);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            default:
                String errorContent = "Requested CHAT type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }

    }

    private void resolveFriendTypeRequests(JsonObject json, Map<String, JsonObject> responseMap) {
        String subtype = json.getString("subtype");
        String clientid;
        FriendService service = new FriendService();
        switch (subtype) {
            case "getAllFriends":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                responseContext = service.getAllFriendsAndGroups(clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "searchNewFriend":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String searchName = content.getString("friendName");
                responseContext = service.searchNewFriend(searchName, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "addNewFriend":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String friendid = content.getString("friendID");
                responseContext = service.searchNewFriend(friendid, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            case "removeFriend":
                content = json.getJsonObject("content");
                clientid = json.getString("clientid");
                String friendId = content.getString("friendID");
                responseContext = service.searchNewFriend(friendId, clientid);
                for (String id : responseContext.keySet()) {
                    responseMap.put(id, JsonBuilder.INSTANCE.buildJson(json, true, responseContext.get(id)));
                }
                break;
            default:
                String errorContent = "Requested FRIEND type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }

    }

}

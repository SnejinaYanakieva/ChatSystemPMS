/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.resolver;

import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.server.ServerEndpoint;
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
        String subtype = json.getString("subtype");
        switch (subtype) {
            case "register":
                ServerEndpoint.peers.put(json.getString("clientid"), session);
                System.out.println(json.getJsonObject("content").toString());
                responseMap.put(json.getString("clientid"), JsonBuilder.INSTANCE.buildErrorJson(json, json.getJsonObject("content").toString()));
                break;
            case "login":
                break;
            case "readPersonalInfo":
                break;
            case "updatePersonalInfo":
                break;
            case "logout":
                break;
            case "deleteUser":
                break;
            default:
                String errorContent = "Requested USER type subtype not recognized!";
                JsonObject response =JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
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
        switch (subtype) {
            case "sendMessageToFriend":
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

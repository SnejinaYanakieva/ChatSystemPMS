/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.resolver;

import com.sins.server.bl.json.JsonBuilder;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author SYanakieva
 */
public class Resolver {

    public static final Resolver INSTANCE = new Resolver();

    public HashMap<String, JsonObject> resolve(JsonObject json) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
        String type = json.getString("type");
        switch (type) {
            case "USER":
                resolveUserTypeRequests(json);
                break;
            case "FRIEND":
                resolveFriendTypeRequests(json);
                break;
            case "GROUP":
                resolveGroupTypeRequests(json);
                break;
            case "CHAT":
                resolveChatTypeRequests(json);
                break;
            default:
                String errorContent = "Requested type not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }
        return responseMap;
    }

    private HashMap<String, JsonObject> resolveUserTypeRequests(JsonObject json) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
        String subtype = json.getString("subtype");
        switch (subtype) {
            case "register":
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
        return responseMap;
    }

    private HashMap<String, JsonObject> resolveFriendTypeRequests(JsonObject json) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
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
                String errorContent = "Requested USER type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }
        return responseMap;
    }

    private HashMap<String, JsonObject> resolveChatTypeRequests(JsonObject json) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
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
        return responseMap;
    }

    private HashMap<String, JsonObject> resolveGroupTypeRequests(JsonObject json) {
        HashMap<String, JsonObject> responseMap = new HashMap<String, JsonObject>();
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
                String errorContent = "Requested GROUP type subtype not recognized!";
                JsonObject response = JsonBuilder.INSTANCE.buildErrorJson(json, errorContent);
                responseMap.put(json.getString("clientId"), response);
        }
        return responseMap;
    }

}

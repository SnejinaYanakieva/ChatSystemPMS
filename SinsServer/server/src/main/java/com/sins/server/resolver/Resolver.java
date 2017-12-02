/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.resolver;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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
                break;
            case "FRIEND":
                break;
            case "GROUP":
                break;
            case "CHAT":
                break;
            default:
                String errorContent = "Requested type not recognized!";
                JsonObject response = buildJson(json, false, Json
                        .createObjectBuilder()
                        .add("errorMessage", errorContent)
                        .build());
                responseMap.put(json.getString("clientId"), response);
        }
        return responseMap;
    }

    private JsonObject buildJson(JsonObject requestJson, boolean success, JsonObject content) {
        return Json
                .createObjectBuilder()
                .add("type", requestJson.getString("type"))
                .add("subtype", requestJson.getString("subtype"))
                .add("senderId", requestJson.getString("clientId"))
                .add("success", success)
                .add("content", content)
                .build();
    }
}

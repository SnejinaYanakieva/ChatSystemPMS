/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sins.server.bl.json;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author SYanakieva
 */
public class JsonBuilder {

   public static JsonBuilder INSTANCE = new JsonBuilder();
    
    public JsonObject buildJson(JsonObject requestJson, boolean success, JsonObject content) {
        return Json
                .createObjectBuilder()
                .add("type", requestJson.getString("type"))
                .add("subtype", requestJson.getString("subtype"))
                .add("senderId", requestJson.getString("clientid"))
                .add("content", content)
                .build();
    }
    
    public JsonObject buildErrorJson(JsonObject requestJson , String errorContent){
       return buildJson(requestJson, false, Json
                        .createObjectBuilder()
                        .add("errorMessage", errorContent)
                        .add("success", false)
                        .build());
    }
    
    public JsonObject buildErrorJsonConent(String errorContent){
       return Json
                        .createObjectBuilder()
                        .add("errorMessage", errorContent)
                        .add("success", false)
                        .build();
    }
    
     public JsonObject buildEmptySuccessJsonConent(){
       return Json
                        .createObjectBuilder()
                        .add("success", true)
                        .build();
    }
}

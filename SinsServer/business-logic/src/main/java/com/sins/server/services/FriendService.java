/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.services;

import com.sins.server.bl.json.JsonBuilder;
import com.sins.server.model.Group;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author SYanakieva
 */
public class FriendService {
    
    private Map<String, JsonObject> response = new HashMap<>();
    
    public Map<String, JsonObject> createGroup(String userId, String groupName){
//         JsonObject json = null;
//        try {
//            Group group = Store.Instance.getGroupDao().createGroup(groupName, userId);
//            if (group != null) {
//                json = Json
//                        .createObjectBuilder()
//                        .add("success", true)
//                        .add("clientid", clientId)
//                        .build();
//            } else {
//                json = JsonBuilder.INSTANCE.buildErrorJsonConent("Login failed! Try again later.");
//            }
//        } catch (DbException e) {
//            json = JsonBuilder.INSTANCE.buildErrorJsonConent(e.getDbErrorMessage());
//
//        }
//        response.put("-1", json);
        return response;
    }
}

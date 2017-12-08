/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.sins.client.client.Client;
import com.sins.client.client.ClientEnd;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Стефан
 */
public class Group {

    public void createGroup(String groupName) throws IOException {//CreatorID e userID глобалка :)
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("groupName", groupName)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "GROUP")
                .add("subtype", "createGroup")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void addFriendToGroup(String friendID, String groupID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", friendID)
                .add("groupID", groupID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "GROUP")
                .add("subtype", "addFriendToGroup")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);

    }

    public void removeFriendFromGroup(String friendID, String groupID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", friendID)
                .add("groupID", groupID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "GROUP")
                .add("subtype", "removeFriendFromGroup")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void getAllGroupParticipants(String groupID) {

    }

    public void deleteGroup(String groupID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("groupID", groupID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "GROUP")
                .add("subtype", "deleteGroup")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

}

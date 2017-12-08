/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.sins.client.client.Client;
import com.sins.client.client.ClientEnd;
import com.sins.client.model.ChatClient;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Стефан
 */
public class Friend {

    public void getAllFriends() throws IOException {
        // ID-то на текущия е глобална променлива декларирана в Client.java

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "FRIEND")
                .add("subtype", "getAllFriends")
                .add("clientid", Client.userID)
                .add("content", "")
                .build();
        ClientEnd.sendMessage(json);
    }

    public void searchNewFriend(String name) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendName", name)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "FRIEND")
                .add("subtype", "searchNewFriend")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void addNewFriend(String newFriendID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", newFriendID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "FRIEND")
                .add("subtype", "addNewFriend")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void removeFriend(String removeFriendID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", removeFriendID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "FRIEND")
                .add("subtype", "removeFriend")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

}

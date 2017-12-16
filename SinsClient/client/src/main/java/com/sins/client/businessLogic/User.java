/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.client.client.Client;
import com.sins.client.client.ClientEnd;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Стефан
 */
public class User {

    public void register(CurrentClient currentClient, String password) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String ggbg = objectMapper.writeValueAsString(currentClient);
        JsonReader jsonReader = Json.createReader(new StringReader(ggbg));
        JsonObject jsnMsg = jsonReader.readObject();

        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("currentClient", jsnMsg)
                .add("password", password)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "register")
                .add("clientid", "-1")
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void login(String user, String password) throws IOException {
        //ID-то е глобално в Client.java userID; трябва да се set-не!!!!!!!!!!!!!!!!
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("username", user)
                .add("password", password)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "login")
                .add("clientid", "-1")
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void readPersonalInfo() throws IOException {
        //ID-то е глобално в Client.java userID;

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "readPersonalInfo")
                .add("clientid", Client.userID)
                .add("content", "")
                .build();
        ClientEnd.sendMessage(json);
    }

    public void updatePersonalInfo(CurrentClient personalInfo) throws IOException {
        //ID-то е глобално в Client.java userID;
        ObjectMapper objectMapper = new ObjectMapper();
        String ggbg = objectMapper.writeValueAsString(personalInfo);
        JsonReader jsonReader = Json.createReader(new StringReader(ggbg));
        JsonObject jsnMsg = jsonReader.readObject();

        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("currentClient", jsnMsg)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "updatePersonalInfo")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void logout() throws IOException {
        //ID-то е глобално в Client.java userID;
        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "logout")
                .add("clientid", Client.userID)
                .add("content", "")
                .build();
        ClientEnd.sendMessage(json);
    }

    public void deleteUser() throws IOException {
        //ID-то е глобално в Client.java userID;
        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "deleteUser")
                .add("clientid", Client.userID)
                .add("content", "")
                .build();
        ClientEnd.sendMessage(json);
    }

}

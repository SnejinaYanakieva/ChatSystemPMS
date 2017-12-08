/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.client.client.ClientEnd;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
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
                .build();
        
        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "USER")
                .add("subtype", "register")
                .add("clientid", "1")
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void login(String user, String password) {
        //ID-то е глобално в Client.java userID; трябва да се set-не!!!!!!!!!!!!!!!!
    }

    public void readPersonalInfo() {
        //ID-то е глобално в Client.java userID;
    }

    public void updatePersonalInfo(CurrentClient personalInfo) {
        //ID-то е глобално в Client.java userID;
    }

    public void logout() {
        //ID-то е глобално в Client.java userID;
    }

    public void deleteUser() {
        //ID-то е глобално в Client.java userID;
    }

}

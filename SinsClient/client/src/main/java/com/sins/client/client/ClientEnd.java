/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.testwebsocket.message.Message;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 *
 * @author Lenovo
 */
@ClientEndpoint()
public class ClientEnd {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @OnMessage
    public void onMessage(String message) throws IOException {
         JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(jsonMessage);
    }

}

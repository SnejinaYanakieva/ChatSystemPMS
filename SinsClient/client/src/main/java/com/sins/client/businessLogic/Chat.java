/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.client.client.Client;
import com.sins.client.client.ClientEnd;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Стефан
 */
public class Chat {
  PrintWriter chatHistoryWriter;
  
    public void sendMessageToFriend(String recieverID, String message) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", recieverID)
                .add("message", message)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "CHAT")
                .add("subtype", "sendMessageToFriend")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
        // добавяне на съобщение в файл, history;
          chatHistoryWriter = new PrintWriter("../webapp/target/ChatWith"+recieverID+".txt", "UTF-8");
          chatHistoryWriter.append("Send to :" + recieverID + "\n");  
          chatHistoryWriter.append(message);

           chatHistoryWriter.close();
    }

    public void sendFileAcceptRequest(String recieverID) throws IOException {
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", recieverID)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "CHAT")
                .add("subtype", "sendFileAcceptRequest")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

    public void sendFileToFriend(String recieverID, File file) throws IOException {
        //Няма да използваме sendMessage ,a ще напишем sendFile binary!!!!!!!!!!!!!!!!!!!!!!
/* JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("friendID", recieverID)
               // .add("file", file)
                .build();
        
        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "CHAT")
                .add("subtype", "sendFileToFriend")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);*/
    }

    public void sendMessageToGroup(Group group, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String ggbg = objectMapper.writeValueAsString(group);
        JsonReader jsonReader = Json.createReader(new StringReader(ggbg));
        JsonObject jsnMsg = jsonReader.readObject();
        JsonObject jsonContent = Json
                .createObjectBuilder()
                .add("group", jsnMsg)
                .add("message", message)
                .build();

        JsonObject json = Json
                .createObjectBuilder()
                .add("type", "CHAT")
                .add("subtype", "sendMessageToGroup")
                .add("clientid", Client.userID)
                .add("content", jsonContent)
                .build();
        ClientEnd.sendMessage(json);
    }

}

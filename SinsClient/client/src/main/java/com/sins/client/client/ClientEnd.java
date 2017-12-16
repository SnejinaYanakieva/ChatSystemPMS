/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.client;

import com.sins.client.resolver.ResolverClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.ClientEndpoint;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;

/**
 *
 * @author Lenovo
 */
@ClientEndpoint()
public class ClientEnd {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private static Session session;
    private static Boolean fileFlag = false;

    @OnMessage
    public void onMessage(String message) throws IOException {
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        ResolverClient.INSTANCE.resolve(jsonMessage);
        System.out.println(jsonMessage);
    }

    @OnMessage
    public void onMessage(ByteBuffer message) throws IOException {
        File file = new File("filename");
        boolean append = false;
        FileChannel wChannel = new FileOutputStream(file, append).getChannel();
        wChannel.write(message);
        System.out.println("file accepted");
        wChannel.close();
    }

    public static void sendMessage(JsonObject message) throws IOException {
        session.getBasicRemote().sendText(message.toString());
    }

    public static void sendMessage(File message) throws IOException {
       
        ByteBuffer buf = ByteBuffer.allocateDirect((int) message.length());
        FileInputStream is = new FileInputStream(message);
        is.getChannel().read(buf);
          buf.flip();
        session.getAsyncRemote().sendBinary(buf);
    }

    public static void setSession(Session session) {
        ClientEnd.session = session;
    }

    public static void setFlag(boolean flag) {
        ClientEnd.fileFlag = flag;
    }

}

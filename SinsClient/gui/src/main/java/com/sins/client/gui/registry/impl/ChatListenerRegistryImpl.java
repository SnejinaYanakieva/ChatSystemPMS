package com.sins.client.gui.registry.impl;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.ChatListenerRegistry;
import com.sins.client.model.message.ServerMessage;
import java.io.File;
import java.util.Map;

public class ChatListenerRegistryImpl implements ChatListenerRegistry{

    @Override
    public ResponseListener<ServerMessage<String>> getMessageFromFriend() {
        return new ResponseListener<ServerMessage<String>>(){
            @Override
            public void onSuccess(ServerMessage<String> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<ServerMessage<String>> getFileAcceptRequest() {
        return new ResponseListener<ServerMessage<String>>(){
            @Override
            public void onSuccess(ServerMessage<String> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<ServerMessage<Boolean>> getFileAcceptResponse() {
        return new ResponseListener<ServerMessage<Boolean>>(){
            @Override
            public void onSuccess(ServerMessage<Boolean> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<ServerMessage<File>> getFileFromFriend() {
        return new ResponseListener<ServerMessage<File>>(){
            @Override
            public void onSuccess(ServerMessage<File> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<ServerMessage<Map<String, String>>> getMessageFromGroup() {
        return new ResponseListener<ServerMessage<Map<String, String>>>(){
            @Override
            public void onSuccess(ServerMessage<Map<String, String>> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
}

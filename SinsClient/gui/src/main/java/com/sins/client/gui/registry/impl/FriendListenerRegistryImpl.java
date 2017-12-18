package com.sins.client.gui.registry.impl;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.FriendListenerRegistry;
import com.sins.client.model.ChatClient;
import com.sins.client.model.Person;
import java.util.List;

public class FriendListenerRegistryImpl implements FriendListenerRegistry{

    @Override
    public ResponseListener<ChatClient> getAllFriends() {
        return new ResponseListener<ChatClient>(){
            @Override
            public void onSuccess(ChatClient response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<List<Person>> searchNewFriend() {
        return new ResponseListener<List<Person>>(){
            @Override
            public void onSuccess(List<Person> response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<Person> addNewFriend() {
        return new ResponseListener<Person>(){
            @Override
            public void onSuccess(Person response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<String> removeFriend() {
        return new ResponseListener<String>(){
            @Override
            public void onSuccess(String response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
}

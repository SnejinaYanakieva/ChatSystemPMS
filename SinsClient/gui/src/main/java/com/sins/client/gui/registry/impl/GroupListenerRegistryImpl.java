package com.sins.client.gui.registry.impl;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.GroupListenerRegistry;
import com.sins.client.model.Group;

public class GroupListenerRegistryImpl implements GroupListenerRegistry{

    @Override
    public ResponseListener deleteGroup() {
        return new ResponseListener(){
            
            @Override
            public void onSuccess(Object response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<Group> createGroup() {
          return new ResponseListener<Group>() {
            @Override
            public void onSuccess(Group response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<Group> addFriendToGroup() {
        return new ResponseListener<Group>(){
            @Override
            public void onSuccess(Group response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<Group> removeFriendFromGroup() {
        return new ResponseListener<Group>(){
            @Override
            public void onSuccess(Group response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
}

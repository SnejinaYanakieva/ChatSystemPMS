/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.gui.registry.impl;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.GroupListenerRegistry;
import com.sins.client.model.Group;

/**
 *
 * @author SYanakieva
 */
public class GroupListenerRegistryImpl implements GroupListenerRegistry{

    

    @Override
    public ResponseListener deleteGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseListener<Group> removeFriendFromGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

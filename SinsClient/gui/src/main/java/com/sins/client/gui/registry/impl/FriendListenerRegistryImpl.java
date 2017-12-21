/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.gui.registry.impl;

import com.sins.client.gui.MainFXMLController;
import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.FriendListenerRegistry;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.ChatClient;
import com.sins.client.model.CurrentClient;
import com.sins.client.model.Person;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sSYanakieva
 */
public class FriendListenerRegistryImpl implements FriendListenerRegistry{

    @Override
    public ResponseListener<ChatClient> getAllFriends() {
        return new ResponseListener<ChatClient>(){
            @Override
            public void onSuccess(ChatClient response) {
                MainFXMLController.friendsList = response;
                MainFXMLController.getAllFriends.run();
            }

            @Override
            public void onError(String error) {
                MainFXMLController.error = error;
            }
            
        };
    }

    @Override
    public ResponseListener<List<Person>> searchNewFriend() {
        return new ResponseListener<List<Person>>(){
            @Override
            public void onSuccess(List<Person> response) {
                MainFXMLController.searchList.clear();
                MainFXMLController.searchList.addAll(response);
                MainFXMLController.searchFriend.run();
            }

            @Override
            public void onError(String error) {
                MainFXMLController.error = error;
                MainFXMLController.searchFriendError.run();
            }
            
        };
    }

    @Override
    public ResponseListener<Person> addNewFriend() {
        return new ResponseListener<Person>(){
            @Override
            public void onSuccess(Person response) {
                MainFXMLController.person = response;
                MainFXMLController.addFriend.run();
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }

    @Override
    public ResponseListener<String> removeFriend() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
  
}

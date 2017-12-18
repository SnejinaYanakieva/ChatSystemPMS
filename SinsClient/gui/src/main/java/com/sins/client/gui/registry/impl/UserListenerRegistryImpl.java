/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.gui.registry.impl;

import com.sins.client.gui.LoginFXMLController;
import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SYanakieva
 */
public class UserListenerRegistryImpl implements UserListenerRegistry{

    @Override
    public ResponseListener<String> getRegisterListener() {
         return new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
              
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    

    @Override
    public ResponseListener<String> getLoginListener() {
        return new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                    LoginFXMLController.login.run();
                    // Hide this current window (if this is what you want)
               
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public ResponseListener<CurrentClient> getShowPersonalInfoListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseListener<String> getLogoutListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseListener<String> getDeleteUserListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

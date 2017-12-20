package com.sins.client.gui.registry.impl;

import com.sins.client.gui.LoginFXMLController;
import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.CurrentClient;

public class UserListenerRegistryImpl implements UserListenerRegistry{

    @Override
    public ResponseListener<String> getRegisterListener() {
         return new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                LoginFXMLController.register.run();
            }

            @Override
            public void onError(String error) {
                LoginFXMLController.register_failed.run();
            }
        };
    }
    
    @Override
    public ResponseListener<String> getLoginListener() {
        return new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                LoginFXMLController.login.run();
            }

            @Override
            public void onError(String error) {
                LoginFXMLController.login_failed.run();
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

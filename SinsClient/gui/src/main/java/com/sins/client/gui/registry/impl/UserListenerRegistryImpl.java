package com.sins.client.gui.registry.impl;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.CurrentClient;

public class UserListenerRegistryImpl implements UserListenerRegistry{

    @Override
    public ResponseListener<String> getRegisterListener() {
        return new ResponseListener<String>() {
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

    @Override
    public ResponseListener<String> getLoginListener() {
        return new ResponseListener<String>() {
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

    @Override
    public ResponseListener<CurrentClient> getShowPersonalInfoListener() {
        return new ResponseListener<CurrentClient>() {
            @Override
            public void onSuccess(CurrentClient response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }  
        };
    }

    @Override
    public ResponseListener<String> getLogoutListener() {
        return new ResponseListener<String>() {
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

    @Override
    public ResponseListener<String> getDeleteUserListener() {
        return new ResponseListener<String>() {
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

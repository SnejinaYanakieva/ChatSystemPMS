/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener.registry;

import com.sins.client.listener.ResponseListener;
import com.sins.client.model.CurrentClient;

/**
 *
 * @author Стефан
 */
public interface UserListenerRegistry {

    public ResponseListener getRegisterListener();

    public ResponseListener getLoginListener();

    public ResponseListener getShowPersonalInfoListener(CurrentClient personalInfo);

    public ResponseListener getLogoutListener();

    public ResponseListener getDeleteUserListener();

}

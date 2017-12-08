/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener.registry;

import com.sins.client.listener.ResponseListener;
import com.sins.client.model.Group;

/**
 *
 * @author Стефан
 */
public interface GroupListenerRegistry {

    public ResponseListener createGroup(Group friend);

    public ResponseListener addFriendToGroup(Group friend);

    public ResponseListener removeFriendFromGroup(String serverResponse);

   // public ResponseListener getAllGroupParticipants(); -> Не ни трябва, има го при addFriendToGroup. 

    public ResponseListener deleteGroup();

}

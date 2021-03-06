/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener.registry;

import com.sins.client.listener.ResponseListener;
import com.sins.client.model.ChatClient;
import com.sins.client.model.Person;
import java.util.List;

/**
 *
 * @author Стефан
 */
public interface FriendListenerRegistry {

    public ResponseListener<ChatClient> getAllFriends();

    public ResponseListener<List<Person>> searchNewFriend();

    public ResponseListener<Person> addNewFriend();

    public ResponseListener<String> removeFriend();

}

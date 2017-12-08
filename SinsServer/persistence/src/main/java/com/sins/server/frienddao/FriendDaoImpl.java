package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    
    
    @Override
    public ChatClient getAllFriends(String userid) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient searchNewFriend(String name) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient addNewFriend(String id, String userid) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient removeFriend(String id, String userid) {     
        throw new UnsupportedOperationException();
    }
    
}
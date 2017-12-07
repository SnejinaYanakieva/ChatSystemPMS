package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    
    private Connection connect() {
        String url = "jdbc:sqlite:sinsdatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    @Override
    public ChatClient getAllFriends(int userid) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient searchNewFriend(String name) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient addNewFriend(int id, int userid) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ChatClient removeFriend(int id, int userid) {     
        throw new UnsupportedOperationException();
    }
    
}
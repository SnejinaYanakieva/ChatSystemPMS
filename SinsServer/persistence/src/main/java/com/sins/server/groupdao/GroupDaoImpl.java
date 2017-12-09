package com.sins.server.groupdao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    
    @Override
    public Group createGroup(String groupName, String userid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Group addFriendToGroup(int friendid, int groupid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Group removeFriendFromGroup(int friendid, int groupid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ChatClient> getAllGroupParticipants(int groupid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteGroup(int groupid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Group getGroupById(String groupId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

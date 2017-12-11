
package com.sins.server.groupdao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import com.sins.server.persistence.DbException;
import java.util.List;

public interface GroupDao {
    
    Group createGroup(String groupName, String userid) throws DbException;
    Group addFriendToGroup(int friendid, int groupid) throws DbException;
    Group removeFriendFromGroup(int friendid,int groupid) throws DbException;
    List<ChatClient> getAllGroupParticipants(int groupid) throws DbException;
    boolean deleteGroup(int groupid) throws DbException;
    Group getGroupById(String groupId) throws DbException;
    
}

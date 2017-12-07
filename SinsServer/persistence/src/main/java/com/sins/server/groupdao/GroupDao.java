
package com.sins.server.groupdao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import java.util.List;

public interface GroupDao {
    
    Group createGroup(String groupName, int userid);
    Group addFriendToGroup(int friendid, int groupid);
    Group removeFriendFromGroup(int friendid,int groupid);
    List<ChatClient> getAllGroupParticipants(int groupid);
    boolean deleteGroup(int groupid);
    
}

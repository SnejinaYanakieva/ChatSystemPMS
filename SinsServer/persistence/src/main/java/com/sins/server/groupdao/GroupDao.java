
package com.sins.server.groupdao;

import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import java.util.List;

public interface GroupDao {
    
    Group createGroup(String groupName, String userid) throws DbException;
    Group addFriendToGroup(String friendid, String groupid) throws DbException;
    Group removeFriendFromGroup(String friendid,String groupid) throws DbException;
    List<Person> getAllGroupParticipants(String groupid) throws DbException;
    boolean deleteGroup(String groupid) throws DbException;
    Group getGroupById(String groupId) throws DbException;
}

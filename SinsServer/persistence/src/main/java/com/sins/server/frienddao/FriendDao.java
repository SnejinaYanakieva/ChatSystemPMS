package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import java.util.List;

public interface FriendDao {
    
    ChatClient getAllFriendsAndGroups(String userid) throws DbException;
    List<Person> searchNewFriend(String name, String userid) throws DbException;
    Person addNewFriend(String id, String userid) throws DbException;
    boolean removeFriend(String id, String userid) throws DbException;
    
}

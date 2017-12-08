package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Person;
import java.util.List;

public interface FriendDao {
    
    ChatClient getAllFriends(String userid);
    List<Person> searchNewFriend(String name);
    Person addNewFriend(String id, String userid);
    boolean removeFriend(String id, String userid);
    
}

package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import java.util.List;

public interface FriendDao {
    
    ChatClient getAllFriends(String userid);
    ChatClient searchNewFriend(String name);
    ChatClient addNewFriend(String id, String userid);
    ChatClient removeFriend(String id, String userid);
    
}

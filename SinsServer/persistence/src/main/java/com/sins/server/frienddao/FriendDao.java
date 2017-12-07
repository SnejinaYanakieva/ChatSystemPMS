package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import java.util.List;

public interface FriendDao {
    
    ChatClient getAllFriends(int userid);
    ChatClient searchNewFriend(String name);
    ChatClient addNewFriend(int id, int userid);
    ChatClient removeFriend(int id, int userid);
    
}

package com.sins.client.model;

import java.util.List;

public class ChatClient extends Person {
    
    private List<Person> friendList;
    private List<Group> groupList;

    public List<Person> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Person> friendList) {
        this.friendList = friendList;
    }

    public List<Group> getGroupLIst() {
        return groupList;
    }

    public void setGroupLIst(List<Group> groupLIst) {
        this.groupList = groupLIst;
    }
    
}

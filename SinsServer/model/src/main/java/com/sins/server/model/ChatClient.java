package com.sins.server.model;

import java.util.List;

public class ChatClient extends Person {
    
    private List<Person> friendList;
    private List<Group> groupLIst;

    public List<Person> getFriendList() {
        int a;
        return friendList;
    }

    public void setFriendList(List<Person> friendList) {
        this.friendList = friendList;
    }

    public List<Group> getGroupLIst() {
        return groupLIst;
    }

    public void setGroupLIst(List<Group> groupLIst) {
        this.groupLIst = groupLIst;
    }
    
}

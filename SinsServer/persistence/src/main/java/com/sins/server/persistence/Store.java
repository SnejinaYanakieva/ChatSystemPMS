package com.sins.server.persistence;

import com.sins.server.frienddao.FriendDao;
import com.sins.server.frienddao.FriendDaoImpl;
import com.sins.server.groupdao.GroupDao;
import com.sins.server.groupdao.GroupDaoImpl;
import com.sins.server.userdao.UserDao;
import com.sins.server.userdao.UserDaoImpl;

public class Store {
    
    public static Store Instance = new Store();
    
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
    
    public FriendDao getFriendDao() {
        return new FriendDaoImpl();
    }
    
    public GroupDao getGroupDao() {
        return new GroupDaoImpl();
    }
}

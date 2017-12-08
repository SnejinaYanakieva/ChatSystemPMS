package com.sins.server.persistence;

import com.sins.server.frienddao.FriendDao;
import com.sins.server.frienddao.FriendDaoImpl;
import com.sins.server.groupdao.GroupDao;
import com.sins.server.groupdao.GroupDaoImpl;
import com.sins.server.userdao.UserDao;
import com.sins.server.userdao.UserDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Store {
    
    public static Store Instance = new Store();
    
    private Connection conn;
    
    public Store() {
        String url = "jdbc:sqlite:target/sinsdatabase.db";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
     
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

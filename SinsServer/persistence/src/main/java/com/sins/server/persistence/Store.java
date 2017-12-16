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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Store {

    public static Store Instance = new Store();

    private Connection conn = null;

    public Connection getConnection() {
        String url = "jdbc:sqlite:sinsdatabase.db";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
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

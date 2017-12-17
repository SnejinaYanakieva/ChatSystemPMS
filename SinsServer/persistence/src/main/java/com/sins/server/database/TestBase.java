/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.database;

import static com.sins.server.database.CreateSinsDatabase.closeConnection;
import static com.sins.server.database.CreateSinsDatabase.conn;
import static com.sins.server.database.CreateSinsDatabase.createConnection;
import static com.sins.server.database.CreateSinsDatabase.createTableGROUPS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSERS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSER_PERSONAL_INFO;
import com.sins.server.frienddao.FriendDaoImpl;
import com.sins.server.groupdao.GroupDaoImpl;
import com.sins.server.model.CurrentClient;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import com.sins.server.userdao.UserDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author name
 */
public class TestBase {
    
    public static void main(String[] args) throws Exception {

        CurrentClient currCl = new CurrentClient();
        
        CurrentClient testCl = new CurrentClient();
        
        currCl.setId("111");
        currCl.setNickname("zeedor4o");
        currCl.setName("Ivelin Petkov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        
        testCl.setNickname("zeedor4o");
        testCl.setName("Ivelin Petkov");
        testCl.setEmail("naseleto@abv.bg");
        testCl.setPhone("0888888888");
        testCl.setCity("Sofia");
        
        String password = "1234";
                
        UserDaoImpl usrDao = new UserDaoImpl();
        FriendDaoImpl frDao = new FriendDaoImpl();
        GroupDaoImpl grDao = new GroupDaoImpl();
        
        /*conn = createConnection();
        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS();
        closeConnection();*/
        //usrDao.register(currCl, password);
        //System.out.println(usrDao.login("zeedor4o", "1234").getId());
        //System.out.println(usrDao.readPersonalInfo("c3b2d067-1a77-4a39-b60a-d2a8d9688f46").getId());
        //usrDao.logout("5cc89e53-9e23-4f15-962a-cfa85d5f1865");
        //System.out.println(usrDao.readPersonalInfo("5cc89e53-9e23-4f15-962a-cfa85d5f1865").getisActive());
        
        
        //System.out.println(frDao.getAllFriendsAndGroups("c5fb657d-4123-4cc8-93dd-0ff41917409a").getId());
        //System.out.println(frDao.addNewFriend("52c2dd01-0141-4f3a-b625-f0c74e034318", "c5fb657d-4123-4cc8-93dd-0ff41917409a").getFriendList().get(1).getName());
        //frDao.addNewFriend("c5fb657d-4123-4cc8-93dd-0ff41917409a", "c5fb657d-4123-4cc8-93dd-0ff41917409a");
        
        grDao.createGroup("Grupa 2", "52c2dd01-0141-4f3a-b625-f0c74e034318");
        
    }   
}

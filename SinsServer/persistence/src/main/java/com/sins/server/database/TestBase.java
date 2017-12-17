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
        
        testCl.setNickname("naseleto");
        testCl.setName("Atanas Petkov");
        testCl.setEmail("naseleto@abv.bg");
        testCl.setPhone("0888888888");
        testCl.setCity("Sofia");
        
                
        UserDaoImpl usrDao = new UserDaoImpl();
        FriendDaoImpl frDao = new FriendDaoImpl();
        GroupDaoImpl grDao = new GroupDaoImpl();
        
        //frDao.addNewFriend("09c76ac7-a4e8-420e-a176-b15b2c53c3c1", "5873fc2f-4701-4fe6-a60d-a6a17a79f284");
        //frDao.removeFriend("09c76ac7-a4e8-420e-a176-b15b2c53c3c1", "5873fc2f-4701-4fe6-a60d-a6a17a79f284");
        
        //grDao.deleteGroup("3fc036af-05af-4187-81d8-07136a274179");
        
        
       
    }   
}

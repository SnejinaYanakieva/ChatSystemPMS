package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    
  
    public static List<Person> getListPerson(String list) {
        
        List<Person> friendList = new ArrayList<Person>();
        
        Connection conn = Store.Instance.getConnection();
                
        try {
            
            List<String> frList = new ArrayList<String>(Arrays.asList(list.split(", ")));
                        
            for (int i = 0; i < frList.size(); i++) {
                
                Person person = new Person();
 
                String sql = "SELECT USERS.ID, USER_PERSONAL_INFO.NAME, IS_ACTIVE "
                   + "FROM USERS "
                   + "INNER JOIN USER_PERSONAL_INFO ON USER_PERSONAL_INFO.ID = USERS.ID  "
                   + "WHERE USERS.ID = ?";
                
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1, frList.get(i));
                                
                ResultSet rs = pstmt.executeQuery();
                    
                while (rs.next()) {
                    person.setId(rs.getString("ID"));
                    person.setName(rs.getString("NAME"));
                    person.setIsActive(rs.getBoolean("IS_ACTIVE"));
                }
                                
                friendList.add(person);
            }
            
            return friendList;
            
        } catch (SQLException e) {
            return null;
        }
        
    }
    
    public static List<Group> getListGroup(String list) {
        
        List<Group> groupList = new ArrayList<Group>();
        
        Connection conn = Store.Instance.getConnection();
        
        try {
            
            List<String> grList = new ArrayList<String>(Arrays.asList(list.split(", ")));  

            for (int i = 0; i < grList.size(); i++) {
                
                Group group = new Group();
                
                String sql = "SELECT ID, NAME, OWNER_ID, PARTICIPANT_LIST "
                           + "FROM GROUPS "
                           + "WHERE ID = ?";
                
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1, grList.get(i));
                
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                        group.setId(rs.getString("ID"));
                        group.setName(rs.getString("NAME"));                 
                        group.setOwner(FriendDaoImpl.getListPerson(rs.getString("OWNER_ID")).get(0)); 
                        group.setParticipants(FriendDaoImpl.getListPerson(rs.getString("PARTICIPANT_LIST")));
                }
                
                groupList.add(group);

            }
            
            return groupList;
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    @Override
    public ChatClient getAllFriendsAndGroups(String userid) throws DbException {
        
        ChatClient chClient = new ChatClient();
                
        String sql = "SELECT ID, NAME, FRIEND_LIST, GROUP_LIST "
                   + "FROM USER_PERSONAL_INFO "
                   + "WHERE ID = ?";
        
        String fList = "";
        String gList = "";
        
        Connection conn = Store.Instance.getConnection();
        
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            
                pstmt.setString(1, userid);
                pstmt.executeQuery();
            
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    chClient.setId(rs.getString("ID"));
                    chClient.setId(rs.getString("NAME"));
                    fList = rs.getString("FRIEND_LIST");
                    gList = rs.getString("GROUP_LIST");
                }                
                
                chClient.setFriendList(this.getListPerson(fList));
                chClient.setGroupLIst(this.getListGroup(gList));
                
                return chClient;
                
        } catch (SQLException e) {
            throw new DbException("Unable to get Friend and Group List.");
        }
    }
    
    @Override
    public List<Person> searchNewFriend(String name) throws DbException {
        
        List<Person> listPerson = new ArrayList();
        
        Person person = new Person();
        
        String sql = "SELECT USERS.ID, USERS.IS_ACTIVE, USER_PERSONAL_INFO.NAME "
                   + "FROM USER_PERSONAL_INFO JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                   + "WHERE NAME = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql)) {
          
            pstmt.setString(1, name);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                person.setId(rs.getString("ID"));
                person.setName(rs.getString("NAME"));
                person.setIsActive(rs.getBoolean("IS_ACTIVE"));
                listPerson.add(person);
            }
            
            return listPerson;
            
        } catch (SQLException e) {
            throw new DbException("Unable to find friend.");
        }

}
    
    @Override
    public ChatClient addNewFriend(String id, String userid) throws DbException {
        
        String fList = "";
              
        String sql = "SELECT FRIEND_LIST "
                   + "FROM USER_PERSONAL_INFO "
                   + "WHERE ID = ?";
        
        String sql2 = "UPDATE USER_PERSONAL_INFO "
                    + "SET FRIEND_LIST = ? "
                    + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2)) {
        
        pstmt.setString(1, userid);
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
                fList = rs.getString("FRIEND_LIST");
            }
        
        if(fList.length() == 0) {
            fList = id;
        }
        else {
            fList = fList + ", " + id;
        }
        
        pstmt2.setString(1, fList);
        pstmt2.setString(2, userid);
        pstmt2.executeUpdate();
                
        return this.getAllFriendsAndGroups(userid);
                
        } catch (SQLException e) {
            throw new DbException("Unable to add new friend.");
        }
    }
    
    @Override
    public ChatClient removeFriend(String id, String userid) throws DbException {
        
        String fList = "";
        
        String sql = "SELECT FRIEND_LIST "
                   + "FROM USER_PERSONAL_INFO "
                   + "WHERE ID = ?";
        
        String sql2 = "UPDATE USER_PERSONAL_INFO "
                    + "SET FRIEND_LIST = ? "
                    + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2)) {
        
        pstmt.setString(1, userid);
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
                fList = rs.getString("FRIEND_LIST");
            }
        
        if(fList.contains(id + ", ")) {
            fList = fList.replace(id + ", ", "");
        }
        else if(fList.contains(", " + id)) {
            fList = fList.replace(", " + id, "");
        }
        else if(fList.contains(id)) {
            fList = fList.replace(id, "");
        }

        pstmt2.setString(1, fList);
        pstmt2.setString(2, userid);
        pstmt2.executeUpdate();
        
        return this.getAllFriendsAndGroups(userid);
        
        } catch (SQLException e) {
            throw new DbException("Unable to remove friend.");
        }
    }
    
}
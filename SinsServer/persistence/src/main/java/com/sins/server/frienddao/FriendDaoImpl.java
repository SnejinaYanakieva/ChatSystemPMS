package com.sins.server.frienddao;

import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    
  
    public List<Person> getListPerson(String list) {
        
        List<Person> friendList = new ArrayList<Person>();
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement()) {
            
            List<String> frList = new ArrayList<String>(Arrays.asList(list.split(",")));
            
            for (int i = 0; i < frList.size(); i++) {
                Person person = new Person();
                String sql = "SELECT ID, NAME, IS_ACTIVE "
                            + "FROM USER_PERSONAL_INFO INNER JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                            + "WHERE ID = " + frList.get(i);
                ResultSet rs = stmt.executeQuery(sql);
                    
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
    
    public List<Group> getListGroup(String list) {
        
        List<Group> groupList = new ArrayList<Group>();
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement()) {
            
            List<String> grList = new ArrayList<String>(Arrays.asList(list.split(",")));  

            for (int i = 0; i < grList.size(); i++) {
                Group group = new Group();
                String sql = "SELECT ID, NAME, OWNER_ID, PARTICIPANT_LIST "
                                + "FROM GROUPS "
                                + "WHERE ID = " + grList.get(i);
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                        group.setId(rs.getString("ID"));
                        group.setName(rs.getString("NAME"));
                        group.setOwner(this.getListPerson(rs.getString("OWNER_ID")).get(0));
                        group.setParticipants(this.getListPerson(rs.getString("PARTICIPANT_LIST")));
                }

                groupList.add(group);
            }
        
            return groupList;
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    @Override
    public ChatClient getAllFriendsAndGroups(String userid) {
        
        ChatClient chClient = new ChatClient();
        
        String sql = "SELECT FRIEND_LIST, GROUP_LIST"
                + "FROM USER_PERSONAL_INFO"
                + "WHERE ID = " + userid;
        
        String fList = "";
        String gList = "";
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
                while (rs.next()) {
                    fList = rs.getString("FRIEND_LIST");
                    gList = rs.getString("GROUP_LIST");
                }                
                
                chClient.setFriendList(this.getListPerson(fList));
                chClient.setGroupLIst(this.getListGroup(gList));
                
                return chClient;
                
        } catch (SQLException e) {
            return null;
        }
    }
    
    @Override
    public List searchNewFriend(String name) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Person addNewFriend(String id, String userid) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean removeFriend(String id, String userid) {     
        throw new UnsupportedOperationException();
    }
    
}
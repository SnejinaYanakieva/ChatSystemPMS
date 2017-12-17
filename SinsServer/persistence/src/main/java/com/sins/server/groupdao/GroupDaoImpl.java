package com.sins.server.groupdao;

import com.sins.server.frienddao.FriendDaoImpl;
import com.sins.server.model.ChatClient;
import com.sins.server.model.Group;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GroupDaoImpl implements GroupDao {
    
    @Override
    public Group getGroupById(String groupId) throws DbException {
        
        Group group = new Group();
        
        Person person = new Person();
        
        String ownerId = "";
        
        String sql = "SELECT ID, NAME, OWNER_ID, PARTICIPANT_LIST "
                   + "FROM GROUPS "
                   + "WHERE ID = ?";
        
        String sql2 = "SELECT USERS.ID, NAME, IS_ACTIVE "
                    + "FROM USER_PERSONAL_INFO INNER JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                    + "WHERE USERS.ID = ?";
        
        Connection conn = Store.Instance.getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            
            pstmt.setString(1, groupId);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                group.setId(rs.getString("ID"));
                group.setName(rs.getString("NAME"));
                ownerId = rs.getString("OWNER_ID");
            }
                        
            pstmt2.setString(1, ownerId);
            
            ResultSet rs2 = pstmt2.executeQuery();
            
            while (rs2.next()) {
                person.setId(rs2.getString("ID"));
                person.setName(rs2.getString("NAME"));
                person.setIsActive(rs2.getBoolean("IS_ACTIVE"));
            }           
            group.setOwner(person);
            
            return group;
            
        } catch (SQLException e) {
            throw new DbException("Unable to get group.");
        }
        
    }
    
    @Override
    public Group createGroup(String groupName, String userid) throws DbException {
        
        String sql = "INSERT INTO GROUPS (ID, NAME, OWNER_ID) VALUES(?,?,?)";
               
        String sql2 = "SELECT GROUP_LIST "
                    + "FROM USER_PERSONAL_INFO "
                    + "WHERE ID = ?";
        
        String sql3 = "UPDATE USER_PERSONAL_INFO "
                    + "SET GROUP_LIST = ? "
                    + "WHERE ID = ?";
        
        String grList = "";
        
        try(Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2);
                PreparedStatement pstmt3  = conn.prepareStatement(sql3)) {
            
            String groupID = UUID.randomUUID().toString();
            
            pstmt.setString(1, groupID);
            pstmt.setString(2, groupName);
            pstmt.setString(3, userid);
            pstmt.executeUpdate();
            
            pstmt2.setString(1, userid);
        
            ResultSet rs = pstmt2.executeQuery();
        
            while (rs.next()) {
                grList = rs.getString("GROUP_LIST");
            }
            
            if(grList.length() == 0) {
                grList = groupID;
            }
            else {
                grList = grList + ", " + groupID;
            }
            
            pstmt3.setString(1, grList);
            pstmt3.setString(2, userid);
            pstmt3.executeUpdate();
            
            return getGroupById(groupID);
            
        } catch (SQLException e) {
            throw new DbException("Unable to create a group.");
        }
        
    }

    @Override
    public Group addFriendToGroup(String friendid, String groupid) throws DbException {
        
        String parList = "";
              
        String sql = "SELECT PARTICIPANT_LIST "
                   + "FROM GROUPS "
                   + "WHERE ID = ?";
        
        String sql2 = "UPDATE GROUPS "
                    + "SET PARTICIPANT_LIST = ? "
                    + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2)) {
            
            pstmt.setString(1, groupid);
        
            ResultSet rs = pstmt.executeQuery();
        
            while (rs.next()) {
                parList = rs.getString("PARTICIPANT_LIST");
            }
            
            System.out.println("Parlist:" + parList + ":endParlist");
            if(parList.length() == 0) {
                parList = friendid;
            }
            else {
                parList = parList + ", " + friendid;
            }
            
            pstmt2.setString(1, parList);
            pstmt2.setString(2, groupid);
            pstmt2.executeUpdate();
            
            return getGroupById(groupid);
            
        } catch (SQLException e) {
            throw new DbException("Unable to add friend in group.");
        }
    }

    @Override
    public Group removeFriendFromGroup(String friendid, String groupid) throws DbException {
        
        String parList = "";
              
        String sql = "SELECT PARTICIPANT_LIST "
                   + "FROM GROUPS "
                   + "WHERE ID = ?";
        
        String sql2 = "UPDATE GROUPS "
                    + "SET PARTICIPANT_LIST = ? "
                    + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2)) {
            
            pstmt.setString(1, groupid);
        
            ResultSet rs = pstmt.executeQuery();
        
            while (rs.next()) {
                parList = rs.getString("PARTICIPANT_LIST");
            }
            
            if(parList.contains(friendid + ", ")) {
                parList = parList.replace(friendid + ", ", "");
            }
            else if(parList.contains(", " + friendid)) {
                parList = parList.replace(", " + friendid, "");
            }
            else if(parList.contains(friendid)) {
                parList = parList.replace(friendid, "");
            }
            
            pstmt2.setString(1, parList);
            pstmt2.setString(2, groupid);
            pstmt2.executeUpdate();
            
            return getGroupById(groupid);
            
        } catch (SQLException e) {
            throw new DbException("Unable to remove friend from group.");
        }
        
    }

    @Override
    public List<Person> getAllGroupParticipants(String groupid) throws DbException {
        
        String parList = "";
        
        String sql = "SELECT PARTICIPANT_LIST "
                   + "FROM GROUPS "
                   + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, groupid);
        
            ResultSet rs = pstmt.executeQuery();
        
            while (rs.next()) {
                parList = rs.getString("PARTICIPANT_LIST");
            }
            
            return FriendDaoImpl.getListPerson(parList);
            
        } catch (SQLException e) {
            throw new DbException("Unable to get all participants.");
        }
        
    }

    @Override
    public boolean deleteGroup(String groupid) throws DbException {
        
        String sql = "DELETE FROM GROUPS WHERE ID = ?";
        
        String sql2 = "SELECT GROUP_LIST "
                    + "FROM USER_PERSONAL_INFO "
                    + "WHERE ID = ?";
        
        String sql3 = "UPDATE USER_PERSONAL_INFO "
                    + "SET GROUP_LIST = ? "
                    + "WHERE ID = ?";
        
        String grList = "";
        
        String grOwner = this.getGroupById(groupid).getOwner().getId();
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2  = conn.prepareStatement(sql2);
                PreparedStatement pstmt3  = conn.prepareStatement(sql3)) {
            
            pstmt.setString(1, groupid);
            pstmt.executeUpdate();
            
            pstmt2.setString(1, grOwner);
        
            ResultSet rs = pstmt2.executeQuery();
        
            while (rs.next()) {
                grList = rs.getString("GROUP_LIST");
            }
            System.out.println(grList);
            if(grList.contains(groupid + ", ")) {
                grList = grList.replace(groupid + ", ", "");
            }
            else if(grList.contains(", " + groupid)) {
                grList = grList.replace(", " + groupid, "");
            }
            else if(grList.contains(groupid)) {
                grList = grList.replace(groupid, "");
            }
            System.out.println(grList);
            
            pstmt3.setString(1, grList);
            pstmt3.setString(2, grOwner);
            pstmt3.executeUpdate();
            
            return true;            
            
        } catch (SQLException e) {
            throw new DbException("Unable to delete group.");
        }
        
    }
    
}

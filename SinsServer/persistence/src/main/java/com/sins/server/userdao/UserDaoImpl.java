package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;
import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDaoImpl implements UserDao {

    @Override
    public boolean register(CurrentClient curClient, String password) {
        
        String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD) VALUES(?,?,?)";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(curClient.getId()));
            pstmt.setString(2, curClient.getNickname());
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Person login(String username, String password) {
        
        Person person = new Person();
        
        String sql = "UPDATE USERS "
                   + "SET IS_ACTIVE = TRUE "
                   + "WHERE USERNAME = " + username + " AND PASSWORD = " + password;
        
        String sql2 = "SELECT ID, NAME, IS_ACTIVE "
                    + "FROM USER_PERSONAL_INFO INNER JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                    + "WHERE USERNAME = " + username;
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement()) {
            
            stmt.executeUpdate(sql);
            
            ResultSet rs = stmt.executeQuery(sql2);
        
            while (rs.next()) {
                person.setId(rs.getString("ID"));
                person.setName(rs.getString("NAME"));
                person.setIsActive(rs.getBoolean("IS_ACTIVE"));
            }
            
            return person;
            
        } catch (SQLException e) {
            return null;
        }
        
    }

    @Override
    public CurrentClient readPersonalInfo(String userid) {
        
        CurrentClient curClient = new CurrentClient();
       
        String sql = "SELECT ID, NAME, USERNAME, EMAIL, PHONE, CITY "
                   + "FROM USER_PERSONAL_INFO INNER JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                   + "WHERE ID = " + userid;
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
                while (rs.next()) {
                    curClient.setId(rs.getString("ID"));
                    curClient.setName(rs.getString("NAME"));
                    curClient.setNickname(rs.getString("USERNAME"));
                    curClient.setEmail(rs.getString("EMAIL"));
                    curClient.setPhone(rs.getString("PHONE"));
                    curClient.setCity(rs.getString("CITY"));
                }
                return curClient;
        } catch (SQLException e) {
            return null;
        }
       
    }

    @Override
    public CurrentClient updatePersonalInfo(String userid, CurrentClient curClient) {
        
        String sql = "USER_PERSONAL_INFO "
                   + "SET NAME = ? ,  EMAIL = ? , PHONE = ? , CITY = ? "
                   + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, curClient.getName());
            pstmt.setString(2, curClient.getEmail());
            pstmt.setString(3, curClient.getPhone());
            pstmt.setString(4, curClient.getCity());
            pstmt.setString(5, userid);

            pstmt.executeUpdate();
            
            return readPersonalInfo(userid);
            
        } catch (SQLException e) {
            return null;
        }     
    }

    @Override
    public boolean logout(String userid) {
        
        String sql = "UPDATE USERS "
                   + "SET IS_ACTIVE = FALSE "
                   + "WHERE ID = " + userid;
        
        try (Connection conn = Store.Instance.getConnection();
                Statement stmt  = conn.createStatement()) {
            
            stmt.executeUpdate(sql);
            return true;
            
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String userid) {
        
        String sql1 = "DELETE FROM USER_PERSONAL_INFO "
                   + "WHERE ID = ?";
        
        String sql2 = "DELETE FROM USERS "
                   + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            
            pstmt1.setString(1, userid);
            pstmt1.executeUpdate();
            
            pstmt2.setString(1, userid);
            pstmt2.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            return false;
        }
    } 
}

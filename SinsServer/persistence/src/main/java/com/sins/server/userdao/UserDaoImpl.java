package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;
import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class UserDaoImpl implements UserDao {

    @Override
    public boolean register(CurrentClient curClient, String password) throws DbException {
        
        String sql = "INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES(?,?,?)";
        String sql2 = "INSERT INTO USER_PERSONAL_INFO (ID, NAME, EMAIL, PHONE, CITY) VALUES(?,?,?,?,?)";
        
        try(Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2))  {
            String userID = UUID.randomUUID().toString();
            pstmt.setString(1, userID);
            pstmt.setString(2, curClient.getNickname());
            pstmt.setString(3, password);
            pstmt.executeUpdate();// -- INSERT into USERS
            //---------------------------------
            pstmt2.setString(1, userID);
            pstmt2.setString(2, curClient.getName());
            pstmt2.setString(3, curClient.getEmail());
            pstmt2.setString(4, curClient.getPhone());
            pstmt2.setString(5, curClient.getCity());
            pstmt2.executeUpdate(); //-- INSERT into USER_PERSONAL_INFO
            //----------------------------------------------------
            
            return true;
        } catch (SQLException e) {
            throw new DbException("Registration failed. User with the same username already exists.");
        }
    }

    @Override
     public Person login(String username, String password) throws DbException {
        
        Person person = new Person();
        
        String sql = "UPDATE USERS "
                   + "SET IS_ACTIVE = 1 "
                   + "WHERE USERNAME = ? AND PASSWORD = ?";
        
        String sql2 = "SELECT USERS.ID, NAME, IS_ACTIVE "
                    + "FROM USER_PERSONAL_INFO INNER JOIN USERS ON USER_PERSONAL_INFO.ID = USERS.ID "
                    + "WHERE USERNAME = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();// -- UPDATE USERS
            
            pstmt2.setString(1, username);
            
            ResultSet rs = pstmt2.executeQuery();
            
            if(!rs.isBeforeFirst()) {
                throw new DbException("Username or password is wrong.");
            }
            
            while (rs.next()) {
                person.setId(rs.getString("ID"));
                person.setName(rs.getString("NAME"));
                person.setIsActive(rs.getBoolean("IS_ACTIVE"));
            }
                     
            return person;
            
        } catch (SQLException e) {
            throw new DbException("Unable to login.");
        }
        
    }

    @Override
    public CurrentClient readPersonalInfo(String userid) throws DbException {
        
        CurrentClient curClient = new CurrentClient();
       
        String sql = "SELECT USERS.ID, USER_PERSONAL_INFO.NAME, USERS.USERNAME, USER_PERSONAL_INFO.EMAIL, USER_PERSONAL_INFO.PHONE, USER_PERSONAL_INFO.CITY, IS_ACTIVE "
                   + "FROM USERS "
                   + "INNER JOIN USER_PERSONAL_INFO ON USER_PERSONAL_INFO.ID = USERS.ID  "
                   + "WHERE USERS.ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, userid);
            
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    curClient.setId(rs.getString("ID"));
                    curClient.setName(rs.getString("NAME"));
                    curClient.setNickname(rs.getString("USERNAME"));
                    curClient.setEmail(rs.getString("EMAIL"));
                    curClient.setPhone(rs.getString("PHONE"));
                    curClient.setCity(rs.getString("CITY"));
                    curClient.setIsActive(rs.getBoolean("IS_ACTIVE"));
                }
                
                return curClient;
        } catch (SQLException e) {
            throw new DbException("Unable to read Personal information.");
        }
       
    }

    @Override
    public CurrentClient updatePersonalInfo(String userid, CurrentClient curClient) throws DbException {
        
        String sql = "UPDATE USER_PERSONAL_INFO "
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
            
        } catch (SQLException e) {
            throw new DbException("Unable to update Personal information.");
        }     
        
        return this.readPersonalInfo(userid);
    }

    @Override
    public boolean logout(String userid) throws DbException {
        
        String sql = "UPDATE USERS "
                   + "SET IS_ACTIVE = 0 "
                   + "WHERE ID = ?";
        
        try (Connection conn = Store.Instance.getConnection();
                PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userid);
            pstmt.executeUpdate();
            return true;
                        
        } catch (SQLException e) {
            throw new DbException("Unable to logout.");
        }
    }

    /*@Override
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
    } */
}

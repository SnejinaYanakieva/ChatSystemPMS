package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDaoImpl implements UserDao {

    private Connection connect() {
        String url = "jdbc:sqlite:sinsdatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    @Override
    public boolean register(CurrentClient curClient, String password) {
        
        String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD) VALUES(?,?,?)";
        
        try (Connection conn = this.connect();
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
    public String login(String username, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CurrentClient readPersonalInfo(String userid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CurrentClient updatePersonalInfo(String userid, CurrentClient curClient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean logout(String userid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteUser(String userid) {
        throw new UnsupportedOperationException();
    }
    
}

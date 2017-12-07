package com.sins.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSinsDatabase {
    
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
    
    public void insert(int id, String username, String password) {
        String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD) VALUES(?,?,?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    /*public static void Main(String[] args) {
        
        TestSinsDatabase app = new TestSinsDatabase();

        app.insert(1, "username1", "1234");
        
    }*/
}

package com.sins.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSinsDatabase {
    
    public static void createTableUSERS() {
        
        String url = "jdbc:sqlite:sinsdatabase.db";
        
        String query = "CREATE TABLE IF NOT EXISTS USERS (\n"
                + "	ID INTEGER,\n"
                + "	USERNAME TEXT NOT NULL,\n"
                + "	PASSWORD TEXT NOT NULL, \n"
                + "     IS_ACTIVE BOOLEAN, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createTableUSER_PERSONAL_INFO() {
        
        String url = "jdbc:sqlite:sinsdatabase.db";
        
        String query = "CREATE TABLE IF NOT EXISTS USER_PERSONAL_INFO (\n"
                + "	ID INTEGER,\n"
                + "	NAME TEXT NOT NULL,\n"
                + "	EMAIL TEXT, \n"
                + "     PHONE TEXT, \n"
                + "     CITY TEXT, \n"
                + "     FRIEND_LIST TEXT, \n"
                + "     GROUP_LIST TEXT, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createTableGROUPS() {
        
        String url = "jdbc:sqlite:sinsdatabase.db";
        
        String query = "CREATE TABLE IF NOT EXISTS GROUPS (\n"
                + "	ID INTEGER,\n"
                + "	NAME TEXT NOT NULL,\n"
                + "	OWNER_ID INTEGER NOT NULL, \n"
                + "     PARTICIPANT_LIST TEXT, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        
        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS();
        
        TestSinsDatabase app = new TestSinsDatabase();

        app.insert(1, "username1", "1234");
        
    }
    
}

package com.sins.server.database;

import com.sins.server.persistence.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSinsDatabase {

    static String url = "jdbc:sqlite:target/sinsdatabase.db";
    public static Connection conn = null;

    public static Connection createConnection() throws Exception {
        return Store.Instance.getConnection();
    }

    public static void closeConnection() throws Exception {
        conn.close();
    }

    public static void createTableUSERS() {

        String query = "CREATE TABLE IF NOT EXISTS USERS (\n"
                + "	ID TEXT,\n"
                + "	USERNAME TEXT NOT NULL,\n"
                + "	PASSWORD TEXT NOT NULL, \n"
                + "     IS_ACTIVE BOOLEAN, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";

        try (
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTableUSER_PERSONAL_INFO() {

        String query = "CREATE TABLE IF NOT EXISTS USER_PERSONAL_INFO (\n"
                + "	ID TEXT,\n"
                + "	NAME TEXT NOT NULL,\n"
                + "	EMAIL TEXT, \n"
                + "     PHONE TEXT, \n"
                + "     CITY TEXT, \n"
                + "     FRIEND_LIST TEXT, \n"
                + "     GROUP_LIST TEXT, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";

        try (
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTableGROUPS() {

        String query = "CREATE TABLE IF NOT EXISTS GROUPS (\n"
                + "	ID TEXT,\n"
                + "	NAME TEXT NOT NULL,\n"
                + "	OWNER_ID TEXT NOT NULL, \n"
                + "     PARTICIPANT_LIST TEXT, \n"
                + "     PRIMARY KEY (ID)\n"
                + ");";

        try (
                Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        createConnection();
        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS();
        closeConnection();

    }

}

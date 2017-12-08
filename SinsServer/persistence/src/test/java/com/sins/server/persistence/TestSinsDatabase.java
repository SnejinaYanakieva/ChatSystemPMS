package com.sins.server.persistence;

import static com.sins.server.database.CreateSinsDatabase.createTableGROUPS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSERS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSER_PERSONAL_INFO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.Assert;

public class TestSinsDatabase {

    private static Connection connect() {

        String url = "jdbc:sqlite:sinsdatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean insert(String id, String username, String password) {
        String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD, IS_ACTIVE) VALUES(?,?,?,?)";

        try (Connection conn = TestSinsDatabase.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setBoolean(4, true);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void testInsert() throws SQLException {
        String query = "DROP TABLE IF EXISTS USERS";
        Connection conn = TestSinsDatabase.connect();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS USER_PERSONAL_INFO";
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS GROUPS";
        st.executeUpdate(query);

        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS();

        Assert.assertTrue("A record could not be inserted", insert("IvoEBot", "username1", "1234"));

    }

}

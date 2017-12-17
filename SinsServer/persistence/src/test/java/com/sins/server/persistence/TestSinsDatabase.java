package com.sins.server.persistence;

import com.sins.server.database.CreateSinsDatabase;
import static com.sins.server.database.CreateSinsDatabase.createTableGROUPS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSERS;
import static com.sins.server.database.CreateSinsDatabase.createTableUSER_PERSONAL_INFO;
import com.sins.server.frienddao.FriendDaoImpl;
import com.sins.server.groupdao.GroupDaoImpl;
import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;
import com.sins.server.userdao.UserDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;

public class TestSinsDatabase {
    
    private static Connection conn;

    private static void connect() throws Exception {

        conn =  Store.Instance.getConnection();
    }
    
    private static void prepareTest() throws Exception {
        
        String query = "DROP TABLE IF EXISTS USERS";
        TestSinsDatabase.connect();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS USER_PERSONAL_INFO";
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS GROUPS";
        st.executeUpdate(query);

        CreateSinsDatabase.conn = conn;
        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS(); 
       
        
    }

    /*public static boolean insert(String id, String username, String password) {
        String sql = "INSERT INTO USERS(ID, USERNAME, PASSWORD, IS_ACTIVE) VALUES(?,?,?,?)";

        try (
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

    public void testInsert() throws SQLException, Exception {
        String query = "DROP TABLE IF EXISTS USERS";
        TestSinsDatabase.connect();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS USER_PERSONAL_INFO";
        st.executeUpdate(query);
        query = "DROP TABLE IF EXISTS GROUPS";
        st.executeUpdate(query);

        CreateSinsDatabase.conn = conn;
        createTableUSERS();
        createTableUSER_PERSONAL_INFO();
        createTableGROUPS();

        Assert.assertTrue("A record could not be inserted", insert("IvoEBot", "username1", "1234"));
        CreateSinsDatabase.closeConnection();
    }*/
    
    UserDaoImpl usrDao = new UserDaoImpl();
    FriendDaoImpl frDao = new FriendDaoImpl();
    GroupDaoImpl grDao = new GroupDaoImpl();
    
    public void testRegister() throws SQLException, Exception {
        
        prepareTest();
        CurrentClient currCl = new CurrentClient();
        currCl.setId("111");
        currCl.setNickname("zeedor4o");
        currCl.setName("Ivelin Petkov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        currCl.setIsActive(true);
        Assert.assertTrue("Registration failed", usrDao.register(currCl, "1234"));
    }
        
    public void testLogin() throws SQLException, Exception {
        prepareTest();
        CurrentClient currCl = new CurrentClient();
        currCl.setId("111");
        currCl.setNickname("zeedor4o");
        currCl.setName("Ivelin Petkov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        currCl.setIsActive(true);
        Person expectedPerson = new Person();
        expectedPerson.setIsActive(true);
        usrDao.register(currCl, "1234");
        Assert.assertEquals(expectedPerson.getisActive(), usrDao.login("zeedor4o", "1234").getisActive());
    
    }
    
    public void testReadPersonalInfo() throws SQLException, Exception {
        prepareTest();
        CurrentClient currCl = new CurrentClient();
        currCl.setNickname("zeedor4o");
        currCl.setName("Ivelin Petkov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        currCl.setIsActive(true);
        usrDao.register(currCl, "1234");
        String userId = usrDao.login("zeedor4o", "1234").getId();
        currCl.setId(userId);
        
        Assert.assertEquals(currCl.getId(), usrDao.readPersonalInfo(userId).getId());
        Assert.assertEquals(currCl.getName(), usrDao.readPersonalInfo(userId).getName());
        Assert.assertEquals(currCl.getNickname(), usrDao.readPersonalInfo(userId).getNickname());
        Assert.assertEquals(currCl.getEmail(), usrDao.readPersonalInfo(userId).getEmail());
        Assert.assertEquals(currCl.getPhone(), usrDao.readPersonalInfo(userId).getPhone());
        Assert.assertEquals(currCl.getCity(), usrDao.readPersonalInfo(userId).getCity());
        Assert.assertEquals(currCl.getisActive(), usrDao.readPersonalInfo(userId).getisActive());
    }
    
    public void testUpdatePersonalInfo() throws SQLException, Exception {
        prepareTest();
        CurrentClient currCl = new CurrentClient();
        currCl.setNickname("zeedor4o");
        currCl.setName("Genadi Hristov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        currCl.setIsActive(true);
        usrDao.register(currCl, "1234");
        String userId = usrDao.login("zeedor4o", "1234").getId();
        currCl.setId(userId);
        
        Assert.assertEquals(currCl.getName(), usrDao.updatePersonalInfo(userId, currCl).getName());
        Assert.assertEquals(currCl.getEmail(), usrDao.updatePersonalInfo(userId, currCl).getEmail());
        Assert.assertEquals(currCl.getPhone(), usrDao.updatePersonalInfo(userId, currCl).getPhone());
        Assert.assertEquals(currCl.getCity(), usrDao.updatePersonalInfo(userId, currCl).getCity());
    }
    
    public void testLogout() throws SQLException, Exception {
        prepareTest();
        CurrentClient currCl = new CurrentClient();
        currCl.setId("111");
        currCl.setNickname("zeedor4o");
        currCl.setName("Ivelin Petkov");
        currCl.setEmail("valada@abv.bg");
        currCl.setPhone("0879587795");
        currCl.setCity("Varna");
        currCl.setIsActive(true);
        Person expectedPerson = new Person();
        expectedPerson.setIsActive(false);
        usrDao.register(currCl, "1234");
        String userId = usrDao.login("zeedor4o", "1234").getId();
        Assert.assertTrue("Logout failed", usrDao.logout(userId));
    }
    
}

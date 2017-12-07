package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;

public interface UserDao {
    
    boolean register(CurrentClient curClient, String password);
    Person login(String username, String password); 
    CurrentClient readPersonalInfo(int userId);
    CurrentClient updatePersonalInfo(int userId, CurrentClient curClient);
    boolean logout(int userId);
    boolean deleteUser(int userId);
    
}

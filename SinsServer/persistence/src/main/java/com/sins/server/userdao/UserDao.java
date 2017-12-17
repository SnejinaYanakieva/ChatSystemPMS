package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.model.Person;
import com.sins.server.persistence.DbException;

public interface UserDao {
    
    boolean register(CurrentClient curClient, String password) throws DbException;
    Person login(String username, String password) throws DbException; 
    CurrentClient readPersonalInfo(String userid) throws DbException;
    CurrentClient updatePersonalInfo(String userid, CurrentClient curClient) throws DbException;
    boolean logout(String userid) throws DbException;
    //boolean deleteUser(String userid) throws DbException;
    
}

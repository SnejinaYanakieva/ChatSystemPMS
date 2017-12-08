package com.sins.server.userdao;

import com.sins.server.model.CurrentClient;
import com.sins.server.persistence.DbException;

public interface UserDao {
    
    boolean register(CurrentClient curClient, String password) throws DbException;
    String login(String username, String password) throws DbException; 
    CurrentClient readPersonalInfo(int userId) throws DbException;
    CurrentClient updatePersonalInfo(int userId, CurrentClient curClient) throws DbException;
    boolean logout(int userId) throws DbException;
    boolean deleteUser(int userId) throws DbException;
    
}

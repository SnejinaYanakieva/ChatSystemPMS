/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.businessLogic;

import com.sins.client.model.CurrentClient;

/**
 *
 * @author Стефан
 */
public class User {
    public void register(CurrentClient currentClient, String password){
    
    }
    
    public void login(String user, String password){
      //ID-то е глобално в Client.java userID; трябва да се set-не!!!!!!!!!!!!!!!!
    }
    
    public void readPersonalInfo(){
        //ID-то е глобално в Client.java userID;
    }
    
    public void updatePersonalInfo(CurrentClient personalInfo ){
        //ID-то е глобално в Client.java userID;
    }
    
    public void logout(){
        //ID-то е глобално в Client.java userID;
    }
    
    public void deleteUser(){
    //ID-то е глобално в Client.java userID;
    }
    
    
    
    
}
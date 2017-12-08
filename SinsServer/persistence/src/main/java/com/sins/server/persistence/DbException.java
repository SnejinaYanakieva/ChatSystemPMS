/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.server.persistence;

/**
 *
 * @author SYanakieva
 */
public class DbException extends Exception{

    private String dbErrorMessage;
    
    public DbException(String dbErrorMessage) {
       this.dbErrorMessage = dbErrorMessage;
    }

    public String getDbErrorMessage() {
        return dbErrorMessage;
    }
    
}

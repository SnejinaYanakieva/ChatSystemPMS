package com.sins.server.persistence;


public class DbException extends Exception{

    private String dbErrorMessage;
    
    public DbException(String dbErrorMessage) {
       this.dbErrorMessage = dbErrorMessage;
    }

    public String getDbErrorMessage() {
        return dbErrorMessage;
    }
    
}

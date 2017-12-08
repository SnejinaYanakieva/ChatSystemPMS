/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener;

/**
 *
 * @author Стефан
 */
public interface ResponseListener<T> {
    
    public void onSuccess(T response);
    public void onError(String error);
    
}

package com.sins.client.model.message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Стефан
 */
public class ServerMessage<T> {

    String senderId;
    T message;

    public ServerMessage(String senderId, T message) {
        this.senderId = senderId;
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public T getMessage() {
        return message;
    }
}

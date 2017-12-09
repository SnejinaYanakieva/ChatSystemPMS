/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener.registry;

import com.sins.client.listener.ResponseListener;
import com.sins.client.model.message.ServerMessage;
import java.io.File;
import java.util.Map;

/**
 *
 * @author Стефан
 */
public interface ChatListenerRegistry {

    public ResponseListener<ServerMessage<String>> getMessageFromFriend();

    public ResponseListener<ServerMessage<String>> getFileAcceptRequest();

    public ResponseListener<ServerMessage<Boolean>> getFileAcceptResponse();

    public ResponseListener<ServerMessage<File>> getFileFromFriend();

    public ResponseListener<ServerMessage<Map<String,String>>> getMessageFromGroup();

}

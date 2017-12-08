/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.listener.registry;

import com.sins.client.listener.ResponseListener;
import java.io.File;

/**
 *
 * @author Стефан
 */
public interface ChatListenerRegistry {

    public ResponseListener getMessageFromFriend(String senderID, String message);

    public ResponseListener getFileAcceptRequest(String senderID);

    public ResponseListener getFileAcceptResponse(String senderID, boolean response);

    public ResponseListener getFileFromFriend(String senderID, File file);

    public ResponseListener getMessageFromGroup(String groupID, String message);

}

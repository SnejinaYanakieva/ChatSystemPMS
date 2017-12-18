package com.sins.client.clientapp;

import com.sins.client.client.Client;
import com.sins.client.gui.App;
import com.sins.client.gui.registry.impl.ChatListenerRegistryImpl;
import com.sins.client.gui.registry.impl.FriendListenerRegistryImpl;
import com.sins.client.gui.registry.impl.GroupListenerRegistryImpl;
import com.sins.client.gui.registry.impl.UserListenerRegistryImpl;
import com.sins.client.listener.registry.ChatListenerRegistry;
import com.sins.client.listener.registry.FriendListenerRegistry;
import com.sins.client.resolver.ResolverClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class SinsChatCilent 
{
    public static void main( String[] args )
    {
        try {
            ResolverClient.INSTANCE.setListenerRegistries(new UserListenerRegistryImpl(),
                    new FriendListenerRegistryImpl(),
                    new GroupListenerRegistryImpl(),
                    new ChatListenerRegistryImpl());
            Client.startClientApplication();
             App.startGuiApplication(args);
        } catch (Exception ex) {
            Logger.getLogger(SinsChatCilent.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}

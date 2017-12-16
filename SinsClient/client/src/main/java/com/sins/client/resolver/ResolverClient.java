/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sins.client.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sins.client.client.Client;
import com.sins.client.listener.registry.ChatListenerRegistry;
import com.sins.client.listener.registry.FriendListenerRegistry;
import com.sins.client.listener.registry.GroupListenerRegistry;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.ChatClient;
import com.sins.client.model.CurrentClient;
import com.sins.client.model.Group;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.JsonObject;
import com.sins.client.model.Person;
import com.sins.client.model.message.ServerMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.json.JsonArray;
import javax.json.JsonValue;

/**
 *
 * @author Стефан
 */
public class ResolverClient {

    public static final ResolverClient INSTANCE = new ResolverClient();
    private ObjectMapper mapper = new ObjectMapper();
    private UserListenerRegistry userRegistry;
    private FriendListenerRegistry friendRegistry;
    private GroupListenerRegistry groupRegistry;
    private ChatListenerRegistry chatRegistry;

    public void setListenerRegistries(UserListenerRegistry userRegistry,
            FriendListenerRegistry friendRegistry,
            GroupListenerRegistry groupRegistry,
            ChatListenerRegistry chatRegistry) {
        this.userRegistry = userRegistry;
        this.friendRegistry = friendRegistry;
        this.groupRegistry = groupRegistry;
        this.chatRegistry = chatRegistry;
    }

    public void resolve(JsonObject json) throws IOException {

        String type = json.getString("type");
        switch (type) {
            case "USER":
                resolveUserTypeResponse(json);
                break;
            case "FRIEND":
                resolveFriendTypeResponse(json);
                break;
            case "GROUP":
                resolveGroupTypeResponse(json);
                break;
            case "CHAT":
                resolveChatTypeResponse(json);
                break;
            default:
                String errorContent = "Response type not recognized!";

        }

    }

    private void resolveUserTypeResponse(JsonObject json) throws IOException {
        String subtype = json.getString("subtype");
        boolean success = json.getJsonObject("content").getBoolean("success");

        switch (subtype) {
            case "register":

                if (success) {
                   /*userRegistry.getRegisterListener()
                            .onSuccess("Successfully registered");*/
                    System.out.println("Successfully registred !");
                } else {
                    userRegistry.getRegisterListener()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                    System.out.println(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "login":
                if (success) {
                  Client.userID = json.getJsonObject("content").getString("clientid");
                  /*   userRegistry.getLoginListener()
                            .onSuccess("Successfully loggedin");*/
                    System.out.println("Successfully LOGEDIN !");
                } else {
                    userRegistry.getLoginListener()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "readPersonalInfo":
            case "updatePersonalInfo":
                if (success) {
                    CurrentClient currenClient = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("currentClient")
                                    .toString(),
                                    CurrentClient.class);
                    userRegistry.getShowPersonalInfoListener()
                            .onSuccess(currenClient);
                } else {
                    userRegistry.getShowPersonalInfoListener()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;

            case "logout":
                if (success) {

                    userRegistry.getLogoutListener()
                            .onSuccess("Successfully logout");
                } else {
                    userRegistry.getLogoutListener()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;

            case "deleteUser":
                if (success) {

                    userRegistry.getDeleteUserListener()
                            .onSuccess("User deleted successfully ");
                } else {
                    userRegistry.getDeleteUserListener()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;

            default:
                String errorContent = "Response USER type subtype not recognized!";

        }

    }

    private void resolveGroupTypeResponse(JsonObject json) throws IOException {
        String subtype = json.getString("subtype");
        boolean success = json.getJsonObject("content").getBoolean("success");
        Group group;
        String serverResponse;
        switch (subtype) {
            case "createGroup":
                if (success) {
                    group = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("group")
                                    .toString(),
                                    Group.class);
                    groupRegistry.createGroup()
                            .onSuccess(group);
                } else {
                    groupRegistry.createGroup()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }

                break;
            case "addFriendToGroup":
                if (success) {
                    group = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("group")
                                    .toString(),
                                    Group.class);
                    groupRegistry.addFriendToGroup()
                            .onSuccess(group);
                } else {
                    groupRegistry.addFriendToGroup()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "removeFriendFromGroup":
                if (success) {
                    group = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("group")
                                    .toString(),
                                    Group.class);
                    groupRegistry.removeFriendFromGroup()
                            .onSuccess(group);
                } else {
                    groupRegistry.removeFriendFromGroup()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }

                break;
            case "getAllGroupParticipants":

                break;
            case "deleteGroup":
                if (success) {
                    groupRegistry.deleteGroup()
                            .onSuccess("Successfully deleted");
                } else {
                    groupRegistry.deleteGroup()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            default:
                String errorContent = "Response GROUP type subtype not recognized!";

        }

    }

    private void resolveChatTypeResponse(JsonObject json) throws IOException {
        String subtype = json.getString("subtype");
        boolean success = json.getJsonObject("content").getBoolean("success");
        String senderID = "";
        String groupID = "";
        String message = "";
        Boolean accept;
        PrintWriter chatHistoryWriter;
        PrintWriter groupchatHistoryWriter;
        switch (subtype) {
            case "recieveMessageFromFriend":
                if (success) {
                    message = json.getJsonObject("content")
                            .getJsonObject("message")
                            .toString();
                    senderID = json.getJsonObject("content")
                            .getJsonObject("senderid")
                            .toString();

                    chatRegistry.getMessageFromFriend()
                            .onSuccess(new ServerMessage<String>(senderID, message));
                } else {
                    chatRegistry.getMessageFromFriend()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                // добавяне на съобщение в файл, history;
                chatHistoryWriter = new PrintWriter("../webapp/target/ChatWith" + senderID + ".txt", "UTF-8");
                chatHistoryWriter.append("Message from :" + senderID + "\n");
                chatHistoryWriter.append(message);
                chatHistoryWriter.close();
                break;
            case "recieveFileAcceptRequest":

                if (success) {
                    senderID = json.getJsonObject("content").getString("senderid");
                    chatRegistry.getFileAcceptRequest()
                            .onSuccess(new ServerMessage<String>(senderID, "Do you want to accept file ? "));
                } else {
                    chatRegistry.getFileAcceptRequest()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "recieveFileAcceptResponse":
                if (success) {
                    senderID = json.getJsonObject("content").getString("senderid");
                    accept = json.getJsonObject("content").getBoolean("accepted");
                    chatRegistry.getFileAcceptResponse()
                            .onSuccess(new ServerMessage<Boolean>(senderID, accept));
                } else {
                    chatRegistry.getFileAcceptResponse()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "recieveFileFromFriend":
                /*    senderID = json.getJsonObject("content").getJsonObject("senderID").toString();
                accept = json.getJsonObject("content").getJsonObject("file").toString();// нужна имплементация ИЗПРАЩАНЕ НА ФАЙЛ
                 */
                break;
            case "recieveMessageFromGroup":
                if (success) {
                    message = json.getJsonObject("content")
                            .getString("message");

                    groupID = json.getJsonObject("content")
                            .getString("groupid");

                    senderID = json.getJsonObject("content")
                            .getString("senderid");

                    HashMap map = new HashMap<String, String>();
                    map.put(senderID, message);

                    chatRegistry.getMessageFromGroup().onSuccess(new ServerMessage<Map<String, String>>(groupID, map));
                } else {
                    chatRegistry.getMessageFromGroup().onError(json.getJsonObject("content").getString("errorMessage"));
                }
                // добавяне на съобщение в файл, history;
                groupchatHistoryWriter = new PrintWriter("../webapp/target/GroupChatWith" + groupID + ".txt", "UTF-8");
                groupchatHistoryWriter.append("Message from :" + senderID + "\n");
                groupchatHistoryWriter.append(message);
                groupchatHistoryWriter.close();
                break;

            default:
                String errorContent = "Response CHAT type subtype not recognized!";

        }

    }

    private void resolveFriendTypeResponse(JsonObject json) throws IOException {
        String subtype = json.getString("subtype");
        boolean success = json.getJsonObject("content").getBoolean("success");
        ChatClient chatClient;
        List<Person> listFriends = new ArrayList<>();
        Person newFriend;
        String serverResponse;
        switch (subtype) {
            case "getAllFriends":
                if (success) {
                    chatClient = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("chatClient")
                                    .toString(),
                                    ChatClient.class);
                    friendRegistry.getAllFriends()
                            .onSuccess(chatClient);
                } else {
                    friendRegistry.getAllFriends()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }

                break;
            case "searchNewFriend":
                if (success) {
                    JsonArray arrayPersons = json.getJsonObject("content")
                            .getJsonObject("friends").asJsonArray();/// нз Снжина как го е именовала ????
                    listFriends = Arrays.asList(mapper.readValue(json.getJsonObject("content").getJsonArray("friends").toString(), Person[].class));

                    friendRegistry.searchNewFriend()
                            .onSuccess(listFriends);
                } else {
                    friendRegistry.searchNewFriend()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "addNewFriend":

                if (success) {
                    newFriend = mapper
                            .readValue(json.getJsonObject("content")
                                    .getJsonObject("friend")/// нз Снжина как го е именовала ????
                                    .toString(),
                                    Person.class);
                    friendRegistry.addNewFriend()
                            .onSuccess(newFriend);
                } else {
                    friendRegistry.addNewFriend()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            case "removeFriend":
                if (success) {
                    friendRegistry.removeFriend()
                            .onSuccess("Successfully removed");
                } else {
                    friendRegistry.removeFriend()
                            .onError(json.getJsonObject("content").getString("errorMessage"));
                }
                break;
            default:
                String errorContent = "Response FRIEND type subtype not recognized!";

        }

    }

}

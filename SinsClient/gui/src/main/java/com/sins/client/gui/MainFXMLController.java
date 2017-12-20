package com.sins.client.gui;

import com.sins.client.businessLogic.Friend;
import com.sins.client.businessLogic.User;
import com.sins.client.gui.bubble.BubbleSpec;
import com.sins.client.gui.bubble.BubbleLabel;
import com.sins.client.model.ChatClient;
import com.sins.client.model.CurrentClient;
import com.sins.client.model.Person;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {

    public static Thread readInfo, readInfoError, getAllFriends, addFriend, searchFriend, searchFriendError;
    public static CurrentClient info;
    public static ChatClient friendsList; //getAllFriends thread
    public static Person person;
    public static List<Person> searchList; // searchFriend thread
    public static Friend friend;
    public static String error;
    
    @FXML private Hyperlink logout_hlink;
    @FXML private ListView<Person> listViewFriends, listViewSearch;
    @FXML private TextField searchBar, serverChat;
    @FXML private ListView chatPanel;
    @FXML private Button sendButton, sendFileButton, addFriendButton, searchButton;
    @FXML private TextArea messageBox;
    @FXML private ChoiceBox statusBar;
    @FXML private Label userName, serverMessage;
    
    public ObservableList<Person> friends, searchFriends;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusBar.setItems(FXCollections.observableArrayList(
            "Online", "Away", "Busy", new Separator(), "Offline"));
        statusBar.getSelectionModel().selectFirst();
        
        
        readInfo = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            userName.setText(info.getNickname());
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        readInfoError = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverMessage.setText(error);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        getAllFriends = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            friends = FXCollections.observableArrayList (friendsList.getFriendList());
                            listViewFriends.setItems(friends);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
    }    
    
    @FXML
    private void handleHyperlinkAction(ActionEvent event)
    {
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("com/sins/client/gui/LoginFXML.fxml"));
                Stage stage = new Stage();
                stage.setTitle("SINS - Login");
                stage.setScene(new Scene(root));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    @FXML
    private void sendMessageButton(){
        if (!messageBox.getText().isEmpty()) {
            Image image = new Image(getClass().getClassLoader().getResource("com/sins/client/gui/images/home2.png").toString());
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            BubbleLabel bl6 = new BubbleLabel();
                    
            bl6.setText(messageBox.getText());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
            HBox x = new HBox();
            x.setMaxWidth(chatPanel.getWidth() - 20);
            x.setAlignment(Pos.TOP_RIGHT);
            bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
            x.getChildren().addAll(bl6, profileImage);
                    
            chatPanel.getItems().add(x);
        }
        messageBox.setText("");
        messageBox.requestFocus();
    }
    
    @FXML
    private void addFriendButton(KeyEvent event){
        if(((Control)event.getSource()).getId().equals(addFriendButton.getId())){
            if(listViewSearch.isFocused()){
                try {
                    friend.addNewFriend(listViewSearch.getSelectionModel().getSelectedItem().getId());
                } catch (IOException ex) {
                    Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(((Control)event.getSource()).getId().equals(searchButton.getId())){
            try {
                friend.searchNewFriend(searchBar.getText());
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        addFriend = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            friends = FXCollections.observableArrayList (friendsList.getFriendList());
                            listViewFriends.setItems(friends);
                            
                            new User().readPersonalInfo();
                            new Friend().getAllFriends();
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        searchFriend = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            searchFriends = FXCollections.observableArrayList(searchList);
                            listViewSearch.setItems(searchFriends);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        searchFriendError = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverMessage.setText(error);
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
    }
    
    @FXML
    private void sendFileButton(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            userName.setText("File selected: " + selectedFile.getName());
        }
        else {
            userName.setText("File selection cancelled.");
        }
    }
      
    @FXML
    private void sendMessageOnEnter(KeyEvent event)
    {
        if(((Control)event.getSource()).getId().equals(serverChat.getId())){
            if(event.getCode() == KeyCode.ENTER){
                if (!serverChat.getText().isEmpty()) {
                    Image image = new Image(getClass().getClassLoader().getResource("com/sins/client/gui/images/account.png").toString());
                    ImageView profileImage = new ImageView(image);
                    profileImage.setFitHeight(32);
                    profileImage.setFitWidth(32);
                    BubbleLabel bl6 = new BubbleLabel();
                    
                    bl6.setText(serverChat.getText());
                    bl6.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                    HBox x = new HBox();
                    bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                    x.getChildren().addAll(profileImage, bl6);
                    
                    chatPanel.getItems().add(x);
            
                    serverChat.setText("");
                }
            }
        }
        
        if(((Control)event.getSource()).getId().equals(messageBox.getId())){
            if(event.getCode() == KeyCode.ENTER){
                if (!messageBox.getText().isEmpty()) {
                    Image image = new Image(getClass().getClassLoader().getResource("com/sins/client/gui/images/home2.png").toString());
                    ImageView profileImage = new ImageView(image);
                    profileImage.setFitHeight(32);
                    profileImage.setFitWidth(32);
                    BubbleLabel bl6 = new BubbleLabel();
                    
                    bl6.setText(messageBox.getText());
                    bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                    HBox x = new HBox();
                    x.setMaxWidth(chatPanel.getWidth() - 20);
                    x.setAlignment(Pos.TOP_RIGHT);
                    bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                    x.getChildren().addAll(bl6, profileImage);
                    
                    chatPanel.getItems().add(x);
                }
                messageBox.setText("");
            }
        }
    }
}

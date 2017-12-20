package com.sins.client.gui;

import com.sins.client.gui.bubble.BubbleSpec;
import com.sins.client.gui.bubble.BubbleLabel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {

    @FXML private Hyperlink logout_hlink;
    @FXML private ListView<String> listViewFriends, listViewConversations;
    @FXML private TextField searchBar, serverChat;
    @FXML private ListView chatPanel;
    @FXML private Button sendButton, sendFileButton;
    @FXML private TextArea messageBox;
    @FXML private ChoiceBox statusBar;
    
    private List<Label> messages = new ArrayList<>();
    private int index = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> friends = FXCollections.observableArrayList (
            "Lois", "Ben", "Marco Polo", "The dude in black xD", "MEGAS", "a", "b", "c", "d", "e", "f", "kk", "asd", "sad", "sad");
        listViewFriends.setItems(friends);
        
        ObservableList<String> chats = FXCollections.observableArrayList (
            "I just took the biggest shit..", "...and then he did...", "Last night was AWESOME!...", "mom HELP...");
        listViewConversations.setItems(chats);
        
        statusBar.setItems(FXCollections.observableArrayList(
            "Online", "Away", "Busy", new Separator(), "Offline"));
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
    private void sendButtonAction() throws IOException {
        
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
    private void sendFileButton(){
        
    }
      
    @FXML
    private void sendMessageOnEnter(KeyEvent event)
    {
        if(((Control)event.getSource()).getId() == serverChat.getId()){
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
        
        if(((Control)event.getSource()).getId() == messageBox.getId()){
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

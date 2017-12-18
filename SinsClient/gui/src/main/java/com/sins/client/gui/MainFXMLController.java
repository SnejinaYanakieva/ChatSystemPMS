package com.sins.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {

    @FXML
    private Hyperlink logout_hlink;
    @FXML
    private ListView<String> listViewFriends, listViewConversations;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> friends = FXCollections.observableArrayList (
            "Lois", "Ben", "Marco Polo", "The dude in black xD", "MEGAS", "a", "b", "c", "d", "e", "f", "kk", "asd", "sad", "sad");
        listViewFriends.setItems(friends);
        
        ObservableList<String> chats = FXCollections.observableArrayList (
            "I just took the biggest shit..", "...and then he did...", "Last night was AWESOME!...", "mom HELP...");
        listViewConversations.setItems(chats);
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
    
}

package com.sins.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {

    @FXML
    private Hyperlink logout_hlink;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

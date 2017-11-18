package com.sins.client.gui.javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {
    
    private boolean myBool = true;
    
    @FXML
    private ImageView userButton, friendsButton, settingsButton, powerButton;
    
    @FXML
    private AnchorPane bodyPane, userPane, friendsPane, settingsPane;
    
    @FXML
    private void handleButtonAction(MouseEvent event)
    {
        if(event.getTarget() == userButton)
        {
            userPane.setVisible(true);
            friendsPane.setVisible(false);
            settingsPane.setVisible(false);
        }
        else if(event.getTarget() == friendsButton)
        {
            userPane.setVisible(false);
            friendsPane.setVisible(true);
            settingsPane.setVisible(false);
        }
        else if(event.getTarget() == settingsButton)
        {
            userPane.setVisible(false);
            friendsPane.setVisible(false);
            settingsPane.setVisible(true);
        }
        else if(event.getTarget() == powerButton)
        {
            myBool = !myBool;
            bodyPane.setVisible(myBool);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

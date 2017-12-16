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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable {
    
    private boolean myBool = true;
    
    @FXML
    private Pane log_pane, reg_pane;
    
    @FXML
    private Hyperlink log_hlink, reg_hlink;
    
    @FXML
    private Button reg_btn, log_btn;
    
    @FXML
    private Label log_label;
    
    @FXML
    private CheckBox log_checkbox;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        if(event.getTarget() == reg_btn)
        {
            reg_pane.setVisible(false);
            reg_pane.setManaged(false);
            
            log_pane.setVisible(true);
            log_pane.setManaged(true);
        }
        if(event.getTarget() == log_btn)
        {         
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("com/sins/client/gui/MainFXML.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Welcome to SINS - The Best Chat Platform Available.");
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void handleHyperlinkAction(ActionEvent event)
    {
        if(event.getTarget() == reg_hlink)
        {
            reg_pane.setVisible(false);
            reg_pane.setManaged(false);
            
            log_pane.setVisible(true);
            log_pane.setManaged(true);
        }
        if(event.getTarget() == log_hlink)
        {
            log_pane.setVisible(false);
            log_pane.setManaged(false);
            
            reg_pane.setVisible(true);
            reg_pane.setManaged(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

package com.sins.client.gui;

import com.sins.client.listener.ResponseListener;
import com.sins.client.listener.registry.UserListenerRegistry;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable, UserListenerRegistry{
   
    @FXML private Pane reg_pane, log_pane;
    @FXML private Hyperlink reg_hlink, log_hlink;
    @FXML private TextField reg_user, reg_pass, reg_repass, reg_name, reg_email, reg_phone, reg_city, log_user, log_pass;
    @FXML private Button reg_btn, log_btn;
    @FXML private Label errorMessage;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        if(event.getTarget() == reg_btn)
        {
            if(reg_user.getText().isEmpty() 
                    || reg_pass.getText().isEmpty()
                    || reg_repass.getText().isEmpty()
                    || reg_name.getText().isEmpty()
                    || reg_email.getText().isEmpty()
                    || reg_phone.getText().isEmpty()
                    || reg_city.getText().isEmpty()){
                errorMessage.setText("Please fill all the fields!");
            }
            else if(!reg_pass.getText().equals(reg_repass.getText())){
                errorMessage.setText("Please enter same passwords!");
            }
            else if(!validateEmail(reg_email.getText())){
                errorMessage.setText("Please enter a valid email!");
            }
            else if(!reg_phone.getText().matches("[0-9]*")){
                errorMessage.setText("Please enter only digits for phone!");
            }
            else{
                errorMessage.setText("");
            }
        }
        if(event.getTarget() == log_btn)
        {
            if(log_user.getText().isEmpty() || log_pass.getText().isEmpty()){
                errorMessage.setText("Invalid credentials!");
            }
            else{
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
            
            errorMessage.setText("");
        }
        if(event.getTarget() == log_hlink)
        {
            log_pane.setVisible(false);
            log_pane.setManaged(false);
            
            reg_pane.setVisible(true);
            reg_pane.setManaged(true);
            
            errorMessage.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    @Override
    public ResponseListener<String> getRegisterListener() {
        return new ResponseListener<String>(){
            @Override
            public void onSuccess(String response) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(String error) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }

    @FXML
    @Override
    public ResponseListener<String> getLoginListener() {
        return new ResponseListener<String>(){
            @Override
            public void onSuccess(String response) {
                errorMessage.setText(response);
            }

            @Override
            public void onError(String error) {
                errorMessage.setText(error);
            }
            
        };
    }

    @Override
    public ResponseListener<CurrentClient> getShowPersonalInfoListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseListener<String> getLogoutListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseListener<String> getDeleteUserListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

package com.sins.client.gui;

import com.sins.client.businessLogic.User;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable {

    private final User user = new User();
    public static Stage stage;
    public static Thread register, register_failed, login, login_failed;
    public static final Button loginButton = new Button();


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
    private void handleButtonAction(ActionEvent event) throws IOException {
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
                
                CurrentClient newCurrentClient = new CurrentClient();
                
                newCurrentClient.setNickname(reg_user.getText().toString());
                newCurrentClient.setName(reg_name.getText().toString());
                newCurrentClient.setEmail(reg_email.getText().toString());
                newCurrentClient.setPhone(reg_phone.getText().toString());
                newCurrentClient.setCity(reg_city.getText().toString());
                
                user.register(newCurrentClient, reg_pass.getText().toString());
                
                reg_pane.setVisible(false);
                reg_pane.setManaged(false);

                log_pane.setVisible(true);
                log_pane.setManaged(true);
            }
        }
        if (event.getTarget() == log_btn) {
            if(log_user.getText().isEmpty() || log_pass.getText().isEmpty()){
                errorMessage.setText("Enter data!");
            }
            else{
                login(event);
            }
        }
        
        login = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Parent root;
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("com/sins/client/gui/MainFXML.fxml"));
                            stage = new Stage();
                            stage.setTitle("Welcome to SINS - The Best Chat Platform Available.");
                            stage.setScene(new Scene(root));
                            stage.show();
                            ((Node)(event.getSource())).getScene().getWindow().hide();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        register = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            errorMessage.setText("Account Created!");
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        register_failed = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            errorMessage.setText("Account already exists!");
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
        
        login_failed = new Thread(){
           @Override
           public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            errorMessage.setText("Wrong username/password!");
                        } catch (Exception ex) {
                            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
           }
        };
    }

    private void login(ActionEvent event) {
        try {
            user.login(log_user.getText().toString(), log_pass.getText().toString());
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleHyperlinkAction(ActionEvent event) {
        if (event.getTarget() == reg_hlink) {
            reg_pane.setVisible(false);
            reg_pane.setManaged(false);

            log_pane.setVisible(true);
            log_pane.setManaged(true);
            
            errorMessage.setText("");
        }
        if (event.getTarget() == log_hlink) {
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

}

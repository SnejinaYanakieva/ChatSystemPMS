package com.sins.client.gui;

import com.sins.client.businessLogic.User;
import com.sins.client.model.CurrentClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable {

    private final User user = new User();
    public static Stage stage;
    public static Thread login;
    public static final Button loginButton = new Button();

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
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getTarget() == reg_btn) {
            reg_pane.setVisible(false);
            reg_pane.setManaged(false);

            log_pane.setVisible(true);
            log_pane.setManaged(true);

            CurrentClient newCurrentClient = new CurrentClient();
            newCurrentClient.setCity("Varna");
            newCurrentClient.setEmail("alabala");
            newCurrentClient.setNickname("alabala");
            newCurrentClient.setName("ggbg");
            newCurrentClient.setPhone("123456");
            user.register(newCurrentClient, "ggbg");
        }
        if (event.getTarget() == log_btn) {
            login(event);
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
           

    }

    private void login(ActionEvent event) {

        try {
            user.login("alabala", "ggbg");
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
        }
        if (event.getTarget() == log_hlink) {
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

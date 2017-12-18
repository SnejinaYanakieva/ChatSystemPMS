package com.sins.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        
        //primaryStage.initStyle(StageStyle.TRANSPARENT); // removes the window buttons
        
        //Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0)); // sets transparent background x1 (x2 is in fxml)
        
        primaryStage.setTitle("SINS - Login");
        Scene scene = new Scene(root);
        
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!\n my name Jeff" );
        launch(args);
    }
}

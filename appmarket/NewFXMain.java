/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppMarket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author sarra
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI.acceuil/FXMLacceuil.fxml"));
                //Media pick = new Media("video.mp4");
        //MediaPlayer player = new MediaPlayer(pick);
        //MediaView mediaView = new MediaView(player);
        //Group root try {t = new Group(mediaView);
        //Scene scene = new Scene(root, 500, 200);
        //primaryStage.setTitle("Media Player");
        //primaryStage.setScene(scene);
        //primaryStage.show();
        //player.play();
        
    
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/login/FXMLlogin.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/promo/FXMLPromo.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("../gui/registr/FXMLregistr.fxml"));
           
          
          
          
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Acceuil");
            primaryStage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
            //primaryStage.setTitle("promo");
            //primaryStage.setTitle("login");
            //primaryStage.setTitle("registr");
            
            
          //  primaryStage.getIcons().add(new Image("/img/foody.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
           Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args) {
        
        
    
        launch(args);
    }
    
}

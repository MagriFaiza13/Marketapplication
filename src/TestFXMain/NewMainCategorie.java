/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFXMain;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sarra
 */
public class NewMainCategorie extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AfficheCategorie.fxml"));
        
        Scene scene= new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("categorie");
        primaryStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.acceuil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-112
 */
public class FXMLscene4Controller implements Initializable {

    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private Button idbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   private void loadnext(ActionEvent event) throws IOException { 
       Parent root = FXMLLoader.load(getClass().getResource("../../GUI.acceuil/FXMLscene1.fxml"));
        Scene scene = idbtn.getScene();
        
       root.translateXProperty().set(scene.getHeight());
       parentContainer.getChildren().add(root);
       
       Timeline timeline = new Timeline();
       KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
       KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
       timeline.getKeyFrames().add(kf);
       timeline.setOnFinished(event1 -> {
           parentContainer.getChildren().remove(anchorRoot);
       });
       timeline.play();
    }
    
}

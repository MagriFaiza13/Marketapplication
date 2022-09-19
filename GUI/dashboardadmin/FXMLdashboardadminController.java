/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboardadmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class FXMLdashboardadminController implements Initializable {

    
    @FXML
    private Button IDhome;
    @FXML
    private Button idusers;
    @FXML
    private ImageView m1;
    @FXML
    private Button IDproduit;
    @FXML
    private Button IDcatégorie;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
/*
    private void Produit(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDproduit.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/promo/FXMLpromo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Promo");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
        stage.show();
    } */

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Statics.current_user.setNom(null);
        Stage stage = (Stage) IDhome.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.acceuil/FXMLacceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Acceuil");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));

        stage.show();
    }

    @FXML
    private void listeutilisateurs(ActionEvent event) throws IOException {
        Stage stage = (Stage) idusers.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.dashboardadmin/FXMLbanir.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("liste des utilisateurs");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
        stage.show();
    }
/*
    @FXML
    private void categorie(ActionEvent event)throws IOException {
        Stage stage = (Stage) IDcatégorie.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../gui/promo/FXMLpromo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Promo");
        stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
        stage.show();
    }*/
    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.panier;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.Produit;
import models.Panier;
import models.Utilisateurs;
import utils.MaConnexion;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMajoutpanierController implements Initializable {
private final ObservableList<Produit> listevent = FXCollections.observableArrayList();
    @FXML
    private TextField nbrticket;
    @FXML
    private Button reserver;
    @FXML
    private Button annul;
    @FXML
    private TextField idreserv;
private int idevent;
    @FXML
    private Label x;
    @FXML
    private TextField nbrticket1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //nbrticket1.setText(String.valueOf(Statics.evt.getId()));
       // Preferences userPreferences = Preferences.userRoot();
        //idevent=Integer.parseInt(userPreferences.get("idEvent", "root"));
        //System.out.println(idevent);
    }    
    private Button suivant;
    Connection con = null;
    ResultSet  rs  = null;
    PreparedStatement pst = null;
   

    
    @FXML
    private void reserver(ActionEvent event) {
         
    }

    @FXML
    private void annul(ActionEvent event) {
    }
    
}

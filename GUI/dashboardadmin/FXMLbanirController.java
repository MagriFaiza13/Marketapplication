/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboardadmin;


import service.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Utilisateur;

import service.UtilisateurService;

import utils.Notification;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-112
 */
public class FXMLbanirController implements Initializable {
     int index = -1;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, Integer> iduser;
    @FXML
    private TableColumn<Utilisateur, String> idname;
    @FXML
    private TableColumn<Utilisateur, String> idsurname;
    @FXML
    private TableColumn<Utilisateur, String> idmail;
    @FXML
    private TableColumn<Utilisateur, String> idlogin;

    @FXML
    private TableColumn<Utilisateur, Integer> idtel;
    @FXML
    private TableColumn<Utilisateur, String> idadresse;
    
    private ObservableList<Utilisateur> listuser = FXCollections.observableArrayList();
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private TextField champ1;
    @FXML
    private TextField champ2;
    @FXML
    private TextField champ3;
    @FXML
    private TextField champ4;
    @FXML
    private TextField champ5;
    @FXML
    private TextField champ6;
    @FXML
    private TextField champ7;
    @FXML
    private Button idbanir;
    @FXML
    private Button idactivé;
    @FXML
    private Button idretour;
    @FXML
    private TextField champ8;
    @FXML
    private TableColumn<Utilisateur, String> idetat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showuser();
        
    }
     public void showuser() {
        IUtilisateur x = UtilisateurService.getInstance();
        listuser = x.DisplayAllusers();
        
        iduser.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        idname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idsurname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        idmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        idlogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        idtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        idadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        
        table.setItems(listuser);
    
}
      void getSelected(MouseEvent event) {

        
    }

    @FXML
    private void getselected(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        champ1.setText(String.valueOf(iduser.getCellData(index)));
        champ2.setText(idname.getCellData(index));
        champ3.setText(idsurname.getCellData(index));
        champ4.setText(idmail.getCellData(index));
        champ5.setText(idlogin.getCellData(index));
        champ6.setText(String.valueOf(idtel.getCellData(index)));
        champ7.setText(idadresse.getCellData(index));
        champ8.setText(idetat.getCellData(index));
        
        
       
       

    }

    @FXML
    private void Banir(ActionEvent event) throws SQLException {
         PreparedStatement ps;
        ResultSet rs;
        String x = "Banner";
        String xx = champ1.getText();
        String yy = "update   utilisateur set  etat ='" + x + "' where id_utilisateur = '" + xx + "' ";
        ps = cnx.prepareStatement(yy);
        ps.execute();
        showuser();
        idbanir.setVisible(true);
        idactivé.setVisible(true);
        Notification.notificationSuccess("compte Banner avec succée", "");
        //showMessageDialog(null, "compte Banner !");
        
    }

    @FXML
    private void Activé(ActionEvent event) throws SQLException {
         PreparedStatement ps;
        ResultSet rs;
        String x = "actif";
        String xx = champ1.getText();
        String yy = "update   utilisateur set  etat ='" + x + "' where id_utilisateur = '" + xx + "' ";
        ps = cnx.prepareStatement(yy);
        ps.execute();
        showuser();
        idbanir.setVisible(true);
        idactivé.setVisible(true);
        //showMessageDialog(null, "compte activé !");
        Notification.notificationSuccess("compte Activer avec succée", "");
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) idretour.getScene().getWindow();
         stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("../../GUI.dashboardadmin/FXMLdashboardadmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin ");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
            stage.show();
    }
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import models.categorie;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javax.swing.JOptionPane.showMessageDialog;
import service.Servicecategorie;

import service.interfacecategorie;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class AfficheCategorieController implements Initializable {

    int index = -1;
    @FXML
    private Label label;
    @FXML
    private TextField tfidcat;
    @FXML
    private TextField tfnomcat;
    @FXML
    private TextField tfdesc;
    @FXML
    private TableView<categorie> tvbox;
    @FXML
    private TableColumn<categorie, Integer> colidcat;
    @FXML
    private TableColumn<categorie, String> colnomcat;
    @FXML
    private TableColumn<categorie, String> coldesc;
    //ObservableList<String> dataList = FXCollections.observableArrayList();
    ObservableList<categorie> listcategories = FXCollections.observableArrayList();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Button btnajouter;

    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnactualiser;
    private int id ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        interfacecategorie x = Servicecategorie.getInstance();
        listcategories = x.DisplayAllcategorie();
        colidcat.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        colnomcat.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tvbox.setItems(listcategories);

    }


    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);
        if (tfidcat.getText().equals("")) {

            showMessageDialog(null, "tfidcat  text field cannot be blank.");
            tfidcat.requestFocus();
        } else if (tfnomcat.getText().equals("")) {
            showMessageDialog(null, "tfnomcat text field cannot be blank.");
            tfnomcat.requestFocus();
        } else if (tfdesc.getText().equals("")) {
            showMessageDialog(null, "tfdesc text field cannot be blank.");
            tfdesc.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    void getSelected(MouseEvent event) {
        index = tvbox.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfidcat.setText(colidcat.getCellData(index).toString());
        tfnomcat.setText(colnomcat.getCellData(index));
        tfdesc.setText(coldesc.getCellData(index));

    }

    @FXML
    private void ajouter(javafx.event.ActionEvent event) {
        try {

            PreparedStatement ps = null;
            ResultSet rs;

            String yy = "SELECT * FROM categories WHERE nomCategorie ='" + tfnomcat.getText() + "'";

            try {
                ps = cnx.prepareStatement(yy);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                showMessageDialog(null, "deja existe");
                tfnomcat.requestFocus();
            } else {
                categorie cat = new categorie();
                cat.setNomCategorie(tfnomcat.getText());
                cat.setDescription(tfdesc.getText());

                interfacecategorie x = Servicecategorie.getInstance();
                x.insertcategorie(cat);

                System.out.println("PS : produit ajoutée avec succés!");

                showMessageDialog(null, "categorie ajouter avec succes");

                interfacecategorie xxx = Servicecategorie.getInstance();
                listcategories = xxx.DisplayAllcategorie();
                colidcat.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
                colnomcat.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
                coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
                tvbox.setItems(listcategories);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void modifier(javafx.event.ActionEvent event) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String xxx = this.tfidcat.getText();
        String xx = tfnomcat.getText();
        String x = tfdesc.getText();

        String yy = "update   categories set nomCategorie = '" + xx + "' , description='" + x + "' where idCategorie = '" +this.id+ "' ";
        ps = cnx.prepareStatement(yy);
        ps.executeUpdate();

        showMessageDialog(null, "categories modifier avec succes");
    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
       // String tfidcat = this.tfidcat.getText();

        String sql = "delete   from  categories where idCategorie = ? ";
        ps = cnx.prepareStatement(sql);
        ps.setInt(1, this.id );
        ps.execute();
        
        this.tfidcat.clear();
        tfnomcat.clear();
        tfdesc.clear();

        showMessageDialog(null, "Categories supprimer avec succes");
        interfacecategorie x = Servicecategorie.getInstance();
        listcategories = x.DisplayAllcategorie();
        colidcat.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        colnomcat.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tvbox.setItems(listcategories);
    }

    @FXML
    private void actualiser(javafx.event.ActionEvent event) {
        listcategories.clear();

        interfacecategorie x = (interfacecategorie) Servicecategorie.getInstance();
        listcategories = x.DisplayAllcategorie();

        tvbox.setItems(listcategories);
        showMessageDialog(null, "Refresh avec succes");
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = tvbox.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        this.id = colidcat.getCellData(index) ;
        System.out.println(this.id);
        tfnomcat.setText(colidcat.getCellData(index).toString());

        tfdesc.setText(coldesc.getCellData(index));

    }

}

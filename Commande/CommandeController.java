/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commande;

import Interface.InterfaceUser;
import Interface.interfaceCommande;
import Service.ServiceCommande;
import Service.ServiceUser;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Commande;
import model.Users;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-049
 */
public class CommandeController implements Initializable {

    int index = -1;
    @FXML
    private Label montantCommande;
    @FXML
    private Label etat;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Users> combouser;
    @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableColumn<Commande, LocalDate> coldate;
    @FXML
    private TableColumn<Commande, Integer> colmontant;
    @FXML
    private TableColumn<Commande, String> coletat;
    @FXML
    private TableColumn<Commande, Users> colutilsateur;
    Connection cnx = MaConnexion.getInstance().getCnx();
    ObservableList<Commande> Commandelist = FXCollections.observableArrayList();
    private final ObservableList<Users> dataList = FXCollections.observableArrayList();
    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TextField montant;
    @FXML
    private TextField etata;
    @FXML
    private TextField idd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String tt = "SELECT * FROM `utilisateurs`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String x = queryoutput.getString("nom");

                dataList.add(new Users(x));
                combouser.setItems(dataList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaceCommande x = ServiceCommande.getInstance();
        Commandelist = x.DisplayAllCommande();

        colid.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        colmontant.setCellValueFactory(new PropertyValueFactory<>("montantCommande"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat_de__commande"));
        //   idimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colutilsateur.setCellValueFactory(new PropertyValueFactory<>("id_user"));

//
        tableCommande.setItems(Commandelist);

    }

    private void refreshTable() {
        dataList.clear();
        interfaceCommande x = ServiceCommande.getInstance();
        Commandelist = x.DisplayAllCommande();
        tableCommande.setItems(Commandelist);
        String tt = "SELECT * FROM `commandes`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String xx = queryoutput.getString("nom");

                dataList.add(new Users(xx));
                combouser.setItems(dataList);
            }
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) montantCommande.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/menuinterfacegraphique/Menu.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.getIcons().add(new Image("/img/cc.jpg"));
        stage.show();

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (this.isValidated()) {

            String s = combouser.getSelectionModel().getSelectedItem().toString();
            PreparedStatement ps, cat;
            ResultSet rs, rs2;

            Commande commande = new Commande();
            commande.setDateCommande(date.getValue());
            commande.setEtat_de__commande(etata.getText());
            commande.setMontantCommande(Float.parseFloat(montant.getText()));
            InterfaceUser xx = ServiceUser.getInstance();
            commande.setId(xx.findUserBynom(s));
            interfaceCommande aa = ServiceCommande.getInstance();
            aa.insertCommande(commande);

            System.out.println("PS : Commande ajoutée avec succés!");

            montant.clear();
            etata.clear();
            refreshTable();
            showMessageDialog(null, "Commande ajouter avec succes");

        }

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {

        if (etata.getText().equals("") &&  combouser.getSelectionModel().isSelected(-1) ) {
            showMessageDialog(null, "you must select Commande");
        } else {

            PreparedStatement cat;
            ResultSet rs2;
            String s = combouser.getSelectionModel().getSelectedItem().toString();
            InterfaceUser x = ServiceUser.getInstance();
            String query = "select * from commandes WHERE nom = ?";
            cat = cnx.prepareStatement(query);
            cat.setString(1, s);
            rs2 = cat.executeQuery();
            if (rs2.next()) {

                String s1 = rs2.getString("id_user");
                PreparedStatement ps;
                ResultSet rs;

                String xx = idd.getText();
                LocalDate myDate = date.getValue();

                String yy = "  update commandes set  dateCommande ='" + myDate + "' , montantCommande ='" + montant.getText() + "', etat_de__commande ='" + etata.getText() + "' , id_user ='" + s1 + "'  where idCommande = '" + xx + "' ";
                ps = cnx.prepareStatement(yy);

                ps.execute();
                showMessageDialog(null, "commande  modifier avec succes");
                refreshTable();
            }
        }

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        if (etata.getText().equals("")) {
            showMessageDialog(null, "you must select Commande");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer Commande");
            alert.setHeaderText("You're about to delete Commande!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                ResultSet rs;
                Integer id = Integer.parseInt(idd.getText());

                String yy = "delete   from  commandes where idCommande = '" + id + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "commande  supprimer avec succes");
                etata.clear();
                montant.clear();

                refreshTable();

            }
        }
    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();

        LocalDate myDate = date.getValue();
        System.out.println(myDate);
        String number = "[0-9]+";

        Pattern x = Pattern.compile(number);
        if (montant.getText().equals("")) {

            showMessageDialog(null, "montant text field cannot be blank.");
            montant.requestFocus();
        } else if (etat.getText().equals("")) {
            showMessageDialog(null, "etat text field cannot be blank.");
            etat.requestFocus();
        } else if (etata.getText().equals("")) {
            showMessageDialog(null, "etat text field cannot be blank.");
            etata.requestFocus();
        } else if (myDate == null) {
            showMessageDialog(null, "date  field cannot be blank.");
            etat.requestFocus();
        } else if (!x.matcher(montant.getText()).matches()) {
            showMessageDialog(null, "montant contains only number.");
            montant.requestFocus();
        } else if (x.matcher(etata.getText()).matches()) {
            showMessageDialog(null, "etat contains only txt.");
            etata.requestFocus();

        } else if (combouser.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "utilisateur  must be selected");
            combouser.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    @FXML
    void getSelected(MouseEvent event) {

        index = tableCommande.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idd.setText(String.valueOf(colid.getCellData(index)));
        date.setValue(coldate.getCellData(index));
        montant.setText(String.valueOf(colmontant.getCellData(index)));
        etata.setText(String.valueOf(coletat.getCellData(index)));

        Users xx = colutilsateur.getCellData(index);

        combouser.getSelectionModel().select(xx);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.xml.bind.DatatypeConverter;
import utils.MaConnexion;
import utils.Notification;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-006
 */
public class FXMLconfirmnewpasswordController implements Initializable {

    @FXML
    private PasswordField IDnouveau;
    @FXML
    private PasswordField IDconfirmpassword;
    @FXML
    private Button IDconfirmer;
    @FXML
    private Button IDretour;
    Connection cnx = MaConnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Confirmer(ActionEvent event) throws SQLException, IOException {
        if (this.isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "select * from utilisateur WHERE mail = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, Statics.current_user.getMail());

            rs = ps.executeQuery();

            if (rs.next()) {

                PreparedStatement pss;
                ResultSet rss;
                int xx = rs.getInt("id_utilisateur");
                String x = IDnouveau.getText();
                String z = "";
                String yy = "update   utilisateur set  mdp ='" + getHash(x.getBytes(), "SHA-1") + "' , code ='" + z + "' where id_utilisateur = '" + xx + "' ";
                pss = cnx.prepareStatement(yy);
                pss.execute();
                Notification.notificationSuccess(" mot de passe modifier avec succès. ", "");
                //showMessageDialog(null, "mot de passe modifier avec succès.");

                Stage stage = (Stage) IDretour.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLlogin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
                stage.show();

            } else {

            }
        }
    }

    public static String getHash(byte[] inputBytes, String algorithme) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithme);
            messageDigest.update(inputBytes);
            byte[] digesteBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digesteBytes).toLowerCase();

        } catch (Exception e) {

        }
        return hashValue;
    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();

        if (IDnouveau.getText().equals("")) {

            Notification.notificationError("le champ mot de passe ne peut pas etre vide");
            //showMessageDialog(null, "mot de passe field cannot be blank.");
            IDnouveau.requestFocus();

        } else if (IDconfirmpassword.getText().equals("")) {

            Notification.notificationError(" le champ confirmer mot de passe ne peut pas etre vide ");
            //showMessageDialog(null, "confirme mot de passe field cannot be blank.");
            IDconfirmpassword.requestFocus();
        } else if (!IDnouveau.getText().equals(IDconfirmpassword.getText())) {
            Notification.notificationError(" saisir le meme mot de passe pour le confirmer ");

            //showMessageDialog(null, "saisir le meme mot de passe pour le confirmer ");
            IDnouveau.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLcodeverification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Recuperer mot de passe");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
        stage.show();
    }

}

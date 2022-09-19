/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import com.mysql.jdbc.MySQLConnection;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import utils.MaConnexion;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-006
 */
public class FXMLcodeverificationController implements Initializable {

    @FXML
    private TextField IDcode;

    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Button IDretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Verifier(ActionEvent event) throws SQLException, IOException {

        if (this.isValidated()) {

            PreparedStatement ps;
            ResultSet rs;
            String query = "select * from utilisateur WHERE code = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, IDcode.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setHeaderText("code");
                alert.setContentText("code valide");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    Stage stage = (Stage) IDcode.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLconfirmnewpassword.fxml"));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setTitle("changer mot de passe");
                    // stage.getIcons().add(new Image("/img/mm.png"));
                    stage.show();

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("confirmation");
                alert.setHeaderText("code");
                alert.setContentText("code invalide try again");
                if (alert.showAndWait().get() == ButtonType.OK) {

                }
            }
        }

    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);
        if (IDcode.getText().equals("")) {
            Notification.notificationError("champ vide ! taper le code de verification ");
            //showMessageDialog(null, "code text field cannot be blank.");
            IDcode.requestFocus();

        } else if (!x.matcher(IDcode.getText()).matches()) {
            Notification.notificationError("le code contient seulement des chiffres ");
            //showMessageDialog(null, "code contains only number.");
            IDcode.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLemail.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Recuperer mot de passe");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
        stage.show();
    }

}

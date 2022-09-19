/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.stage.Stage;
import javax.mail.MessagingException;
import static javax.swing.JOptionPane.showMessageDialog;
import utils.MaConnexion;
import utils.Notification;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-006
 */
public class FXMLemailController implements Initializable {

    @FXML
    private Button IDretour;
    @FXML
    private TextField txtemail;
    Connection cnx = MaConnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Envoyermail(ActionEvent event) throws SQLException, IOException, MessagingException {
         String email = txtemail.getText();
        if (this.isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "select * from utilisateur WHERE mail = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                Random rand = new Random(); //instance of random class
                int upperbound = 10000;
                int int_random = rand.nextInt(upperbound);
                PreparedStatement pss;
                ResultSet rss;
                int xx = rs.getInt("id_utilisateur");
                String yy = "update   utilisateur set  code ='" + int_random + "' where id_utilisateur = '" + xx + "' ";
                pss = cnx.prepareStatement(yy);
                pss.execute();

                //   String x = rs.getString("code");
                
              
                //ResetPasswordEmail a = new ResetPasswordEmail();
                //a.sendemailwelcom(email, int_random, rs.getString("nom"));
                  ResetPasswordEmail mail = new ResetPasswordEmail();
        mail.setupServerProperties();
        mail.draftEmail(email, rs.getString("nom"), int_random);
        mail.sendEmail();
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setHeaderText("code");
                alert.setContentText("nous avons envoyé votre code par e-mail");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    
                }
                   Stage stage = (Stage) IDretour.getScene().getWindow();
                stage.close();
                Statics.current_user.setMail(email);
                Parent root = FXMLLoader.load(getClass().getResource("FXMLcodeverification.fxml"));

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Recuperer mot de passe");
                stage.getIcons().add(new Image("gui/dashboardadmin/Untitled design (2).png"));
              
                stage.show();

            } else {
                Notification.notificationError("email  n'existe pas ");
                //showMessageDialog(null, "Email not existe try again !");
              

            }
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) IDretour.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../../GUI.login/FXMLlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login ");
        stage.getIcons().add(new Image("GUI.dashboardadmin/Untitled design (2).png"));
        stage.show();
        
        
    }
    
      private boolean isValidated() {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if (txtemail.getText().equals("")) {
          showMessageDialog(null, "email text field cannot be blan");
            txtemail.requestFocus();

        } else if (!pattern.matcher(txtemail.getText()).matches()) {
            showMessageDialog(null, "Email invalid");
            txtemail.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    
}

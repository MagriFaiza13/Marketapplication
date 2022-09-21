/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.panier;

import utils.MaConnexion;
import utils.Statics;
//import Model.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import models.Panier;
import appmarket.NewFXMain;
import static javax.swing.JOptionPane.showMessageDialog;
import static jdk.nashorn.internal.runtime.Debug.id;
import models.Produit;
//import sprint1.Run;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class PanierController implements Initializable {
    
    @FXML
    private ImageView logo;
    @FXML
    private TextField recherche;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextFlow txtarea;
    @FXML
    private Button btnupdate11;
    @FXML
    private Spinner<Integer> number;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnupdate1;
    @FXML
    private Button btndelete;
    @FXML
    private Label showstock;
    @FXML
    private Button addcart;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtidproduit;
    @FXML
    private TextField quantite;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField prix;
    @FXML
    private ImageView img;
    @FXML
    private Button totalpanier;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Label labelnom;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView image;
    @FXML
    private Button btnContact;
    @FXML
    private HBox profilehbox;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnAccueil;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Connection cnx = MaConnexion.getInstance().getCnx();
    private List<Panier> e = new ArrayList<>();   

     private Mypanier myListener;

    /**
     * Initializes the controller class.
     */
    private List<Panier> getData() throws SQLException  {
        List<Panier> e = new ArrayList<>();
        Panier panier;
        String tt = "SELECT * FROM `paniers`  where id_user ='" + Statics.current_user.getId_user()+ "'";
        
        Statement statement;
        
        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            panier = new Panier();
            panier.setIdpanier(Integer.parseInt(queryoutput.getString("idpanier")));
            panier.setQuantite(Integer.parseInt(queryoutput.getString("quantite")));
            panier.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            panier.setId_user(Integer.parseInt(queryoutput.getString("id_user")));
            panier.setId_produit(Integer.parseInt(queryoutput.getString("id_produit")));
           
            
           
           
            
          
            e.add(panier);
            
        }
        
        return e;
    }
    
    private void setChosenFruit(Panier p) {
       // fruitNameLable.setText(p.getNom_produit());
        fruitPriceLabel.setText(p.getPrix()+ NewFXMain.CURRENCY);
        quantite.setText(String.valueOf(p.getQuantite()));
        String path;
//        txtimage.setText(fruit.getImage());
        //  txtarea.setTextAlignment(TextAlignment.valueOf(fruit.getDescription()));
        txtarea.getChildren().clear();
        //Text t1 = new Text("Description : " + p.getDescription());
        //txtarea.getChildren().add(t1);
        
        txtidproduit.setText(String.valueOf(p.getId_produit()));
//        idproduit.setText(String.valueOf(fruit.getId()));
        txtid.setText(String.valueOf(p.getIdpanier()));
        //txtcat.setText(String.valueOf(p.getId_categorie()));
        //nomproduit.setText(p.getNom_produit());
        //txtdes.setText(p.getDescription());
        prix.setText(String.valueOf(p.getPrix()));

//        path = fruit.getImage();
//        Image aa = new Image("file:" + path);
//        // System.out.println(image);
//        fruitImg.setImage(aa);
 String xx = "B0E0E6";
        chosenFruitCard.setStyle("-fx-background-color: #" +xx + ";\n"
                + "    -fx-background-radius: 30;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            e.addAll(getData());
            if (e.size() > 0) {
                setChosenFruit(e.get(0));

                myListener = this::setChosenFruit;
                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < e.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("../GUI/panier/itemController.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        ItemControllerController aa = fxmlLoader.getController();
                        aa.setData(e.get(i), myListener);

                        if (column == 3) {
                            column = 0;
                            row++;
                        }

                        grid.add(anchorPane, column++, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }

        } catch (SQLException ex) {
        }
          try {
            totalpanier.setText(String.valueOf(refreshpanier()));
            System.out.println(refreshpanier());
        } catch (SQLException ex) {
 
        }

            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        number.setValueFactory(valueFactory);

        
    }
    
    @FXML
    private void rechercherproduit(ActionEvent event) {
    }
    
    @FXML
    private void updateproduit(ActionEvent event) throws SQLException, IOException {
        
          PreparedStatement ps, ps2, ps3, ps4;
        ResultSet rs, rs2 = null;
        // reservation
        String y = "select * from paniers WHERE idpanier = ?";
        ps4 = cnx.prepareStatement(y);
        ps4.setString(1, txtid.getText());
        rs2 = ps4.executeQuery();
        while (rs2.next()) {
            Integer s1 = rs2.getInt("quantite");
            Integer x = number.getValue() + s1;
            Integer yy = number.getValue();
            String query = "select * from produits WHERE id_produit = ?";
            ps = cnx.prepareStatement(query);
            ps.setString(1, txtidproduit.getText());

            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("quantite") >= yy) {
                    System.out.println("update succes");
                    String xx = "update produits set qunatite = quantite - ? where id_produit =? ";
                    ps2 = cnx.prepareStatement(xx);
                    ps2.setInt(1, yy);
                    ps2.setString(2, txtidproduit.getText());
                    ps2.execute();
                    String xxx = "update paniers set quantite =?  , prix =? * ?  where idpanier =? and id_user =?";
                    ps3 = cnx.prepareStatement(xxx);
                    ps3.setInt(1, x);
                    ps3.setInt(2, x);
                    ps3.setString(3, quantite.getText());
                    ps3.setString(4, txtid.getText());
                    ps3.setInt(5, Statics.current_user.getId_user());
                    ps3.execute();

                    totalpanier.setText(String.valueOf(refreshpanier()));
                     Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../GUI/panier/Panier.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

//        
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Produit");

                    alert.setContentText("produit hors stock ");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        System.out.println("produit hors stock");
                    }

                }
            }
        }

        
    }
    
    @FXML
    private void deleteproduit(ActionEvent event) throws SQLException, IOException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" produit");

        alert.setContentText("delete ");
        if (alert.showAndWait().get() == ButtonType.OK) {

            PreparedStatement ps2, ps3, ps4;
            ResultSet rs, rs2 = null;
            String y = "select * from paniers WHERE idpanier = ?";

            ps4 = cnx.prepareStatement(y);
            ps4.setString(1, txtid.getText());
            rs2 = ps4.executeQuery();
            while (rs2.next()) {
                Integer s1 = rs2.getInt("quantite");
                System.out.println(s1);

                String xxx = "update produits set quantite =quantite +? where id_produit =? ";

                ps2 = cnx.prepareStatement(xxx);
                ps2.setInt(1, s1);

                ps2.setString(2, txtidproduit.getText());
                ps2.execute();

            }

            PreparedStatement ps;
            //   ResultSet rs;
            String id = txtid.getText();

            String yy = "delete   from  paniers where idpanier = '" + id + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();

              Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../GUI/panier/Panier.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

//        
            totalpanier.setText(String.valueOf(refreshpanier()));
//            
        }

        
    }
     public Integer refreshpanier() throws SQLException {

        int x = 0;
        Statement stmt = cnx.createStatement();
        String query = "select SUM(quantite) from paniers where id_user='" + Statics.current_user.getId_user()+ "'";

        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        x = rs.getInt(1);
        return x;

    }
    @FXML
    private void ajouterpanier(ActionEvent event) throws SQLException, IOException {
                    System.out.println("hiii");
       if (Statics.current_user.getNom()== null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("login");
            alert.setHeaderText("login required!");
            alert.setContentText("you must login  ");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Stage stage = (Stage) addcart.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../GUI/login/FXMLlogin.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(500);

                stage.setMaxWidth(600);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

            }
        } else {
            PreparedStatement ps, psx;
            ResultSet rs, rsx;
            String id_user = String.valueOf(Statics.current_user.getId_user());
            
            System.out.println(Statics.current_user.getId_user());
            //String produit = txtcanomproduit.getText();
            
           String nom_produit = nomproduit.getText();
           System.out.println(quantite.getText());
            float Prix = Float.parseFloat( quantite.getText());
            //String image = txtimage.getText();
         
                        String id_produit = txtid.getText();
            int Quantite = 1;
            //String nbr_ticket = txtcat.getText();
            //  String xx = id.getText();
            // String yy = "delete   from  categories where name = '" + nom + "' ";
            //String yy = "INSERT INTO evennement(date_debut,date_fin,prix_ticket,nom_evenement,image_event,nbrticket) VALUES (?,?,?,?,?,?)";
            String yy = "INSERT INTO paniers(`id_user`,`id_produit`,`quantite`,`nom_produit`,`prix`) VALUES (?,?,?,?,?)";
            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yyy = "SELECT * FROM paniers WHERE id_user ='" + id_user + "'  and  nom_produit='" + nom_produit + "'  ";
            psx = cnx.prepareStatement(yyy);

            rsx = psx.executeQuery();
            if (rsx.next()) {

                showMessageDialog(null, "produit deja ajouter au paneir");
            } else {

                ps = cnx.prepareStatement(yy);
                System.out.println(ps);
                ps.setString(1, id_user);
                ps.setString(2,id_produit);
                ps.setInt(3, Quantite);
               ps.setString(4, nom_produit);
               ps.setFloat(5, Prix);
                System.out.println(id_user);
                System.out.println(id_produit);
                System.out.println(quantite);
                System.out.println(nom_produit);
               
                System.out.println(prix);
                ps.executeUpdate();
               
                PreparedStatement ps4, ps5;
                ResultSet rs5;

                String xxx = "update produits set quantite =quantite-? where id_produit =? ";

                ps5 = cnx.prepareStatement(xxx);
                ps5.setInt(1, 1);

                ps5.setString(2, id_produit);
                System.out.println(txtidproduit.getText());
                ps5.execute();

                showMessageDialog(null, "produit  ajouter avec succes au panier");
                try {
                    totalpanier.setText(String.valueOf(refreshpanier()));
                    System.out.println(refreshpanier());
                } catch (SQLException ex) {
                }
//                Stage stage = (Stage) register.getScene().getWindow();
//                stage.close();
//
//                Parent root = FXMLLoader.load(getClass().getResource("../../gui/Categorie_Client/Categorie_Client.fxml"));
//
//                Scene scene = new Scene(root);
//                stage.setMaxHeight(1000);
//                stage.setMaxWidth(1500);
//                stage.setScene(scene);
//                stage.setResizable(true);
//
//                stage.setTitle("Login");
//                //
//                stage.show();
            }
        }
    }
    
    @FXML
    private void showpanier(MouseEvent event) {
    }
    
    @FXML
    private void totalpanier(ActionEvent event) {
    }
    
    @FXML
    private void register(ActionEvent event) {
    }
    
    @FXML
    private void login(ActionEvent event) {
    }
    
    @FXML
    private void btnSignout(ActionEvent event) {
    }
    
    
    @FXML
    private void contact(ActionEvent event) {
    }
    
    
    @FXML
    private void Profile(ActionEvent event) {
    }
    
    
    @FXML
    private void Accueil(ActionEvent event) {
    }
    
}

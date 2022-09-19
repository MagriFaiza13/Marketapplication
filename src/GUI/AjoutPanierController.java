/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Button;
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
import static javax.swing.JOptionPane.showMessageDialog;
import models.Produit;
import utils.MaConnexion;
import utils.Statics;
import models.Panier;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class AjoutPanierController implements Initializable {
    
    @FXML
    private Button addcart;
    @FXML
    private Button btnprofile;
    @FXML
    private Button idcat;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button totalpanier;
    @FXML
    private ImageView img;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Connection cnx = MaConnexion.getInstance().getCnx();
    private List<Produit> e = new ArrayList<>();
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
    private Label showstock;
    @FXML
    private TextField quantite;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField prix;
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
    private HBox avishbox;
    @FXML
    private Button avis;
    @FXML
    private HBox profilehbox;
    private Mylistenner myListener;
    @FXML
    private Button enstock;
    @FXML
    private Button horsstock;
    @FXML
    private Spinner<Integer> numberSpinner;
    //SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(i:1, i1:10, i2:1); {
       // @OverridespinnerValue
        public void decrement(int steps) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void increment(int steps) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    

    /**
     * Initializes the controller class.
     */
    
    private List<Produit> getData() throws SQLException {
        List<Produit> e = new ArrayList<>();
        Produit prdouit;
        String tt = "SELECT * FROM `produits`";
        
        Statement statement;
        
        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            prdouit = new Produit();
            prdouit.setNom_Produit(queryoutput.getString("nom_produit"));
            prdouit.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            
            prdouit.setIdproduit(Integer.parseInt(queryoutput.getString("idproduit")));
            //  panier.setStock_ticket(Integer.parseInt(queryoutput.getString("stock_ticket")));
            prdouit.setDescription(queryoutput.getString("Description"));
            prdouit.setImage(queryoutput.getString("image"));
            e.add(prdouit);
            
        }
        
        return e;
    }

    private void setChosenFruit(Produit e) {
        fruitNameLable.setText(e.getNom_Produit());
        fruitPriceLabel.setText(e.getPrix() + "tnd");
        quantite.setText(String.valueOf(e.getQuantite()));
        String path;
        // txtimage.setText(fruit.getImage());
        //  txtarea.setTextAlignment(TextAlignment.valueOf(fruit.getDescription()));
        txtarea.getChildren().clear();
        Text t1 = new Text("Description : " + e.getDescription());
        txtarea.getChildren().add(t1);
        
        idproduit.setText(String.valueOf(e.getIdproduit()));
        // txtcat.setText(String.valueOf(e.getId_categorie()));
        nomproduit.setText(e.getNom_Produit());
        txtdes.setText(e.getDescription());
        prix.setText(String.valueOf(e.getPrix()));
        //  System.out.println(quantite.getText());
//        if (quantite.getText().equals("0")) {
//            horsstock.setVisible(true);
//
//            enstock.setVisible(false);
//            addcart.setVisible(false);
//            showstock.setVisible(false);
//            //    ajouterpanierr.setVisible(false);
//        } else {
//            enstock.setVisible(true);
//
//            addcart.setVisible(true);
//            showstock.setVisible(true);
//            showstock.setText("il vous reste " + quantite.getText() + " Event");
//            horsstock.setVisible(false);
//
//            //   ajouterpanierr.setVisible(true);
//        }
        //   this.img.setImage(image);
        path = e.getImage();
        Image aa = new Image("file:" + path);
        System.out.println(path);
        fruitImg.setImage(aa);
        String xx = "B0E0E6";
        chosenFruitCard.setStyle("-fx-background-color: #" + xx + ";\n"
                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberSpinner.setValueFactory(SpinnerValueFactory);
        try {
            e.addAll(getData());
            if (e.size() > 0) {
                setChosenFruit(e.get(0));
                
                myListener = this::setChosenFruit;
                
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < e.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../GUI/Item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(e.get(i), myListener);
                    
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
        } catch (SQLException ex) {
            Logger.getLogger(AjoutPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void ajouterpanier(ActionEvent event) throws SQLException {
          PreparedStatement ps, psx;
            ResultSet rs, rsx;
            String id_user = String.valueOf(Statics.current_user.getId_utilisateur());
            System.out.println(Statics.current_user.getId_utilisateur());
            String produit = nomproduit.getText();
            String desc = txtdes.getText();
            String prix = this.prix.getText();
            // String image = txtimage.getText();
            String id_produit = idproduit.getText();
            Integer quantiter = 1;
            String categorie = txtcat.getText();
            //  String xx = id.getText();
            // String yy = "delete   from  categories where name = '" + nom + "' ";
            String yy = "INSERT INTO paniers (idpanier,quantite,id_user, id_produit) VALUES ( ?,?,?,?)";

            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yyy = "SELECT * FROM paniers WHERE id_user ='" + id_user + "'  and  id_produit ='" + produit + "'  ";
            psx = cnx.prepareStatement(yyy);

            rsx = psx.executeQuery();
            if (rsx.next()) {

                showMessageDialog(null, "produit deja ajouter au paneir");
            } else {

                ps = cnx.prepareStatement(yy);
                ps.setString(1, produit);

                ps.setString(2, prix);
                ps.setString(3, desc);

                ps.setInt(4, quantiter);

                ps.setString(5, id_user);
                ps.setString(6, id_produit);
                ps.setString(7, categorie);
                System.out.println(id_produit);
                ps.execute();

                PreparedStatement ps4, ps5;
                ResultSet rs5;

                String xxx = "update paniers set idpanier =quantite-? where idpanier =? ";

                ps5 = cnx.prepareStatement(xxx);
                ps5.setInt(1, 1);

                ps5.setString(2, id_produit);
                System.out.println(idproduit.getText());
                ps5.execute();

                showMessageDialog(null, "produit  ajouter avec succes au panier");
                try {
                    totalpanier.setText(String.valueOf(refreshpanier()));
                    System.out.println(refreshpanier());
                } catch (SQLException ex) {
                    System.out.println("total panier");
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
    
    @FXML
    private void totalpanier(ActionEvent event) {
    }
    
    @FXML
    private void rechercherproduit(ActionEvent event) {
    }
    
    @FXML
    private void gotopanier(MouseEvent event) {
    }
    
    @FXML
    private void showpanier(MouseEvent event) {
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
    private void categorie(ActionEvent event) {
    }
    
    @FXML
    private void avis(ActionEvent event) {
    }
    
    @FXML
    private void Profile(ActionEvent event) {
    }
    
    
    @FXML
    private void Accueil(ActionEvent event) {
    }
    
}

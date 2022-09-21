package GUI.panier;

import GUI.produit.FXMLCategorieController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import Interface.MyListener;
import models.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import appmarket.NewFXMain;
import utils.MaConnexion;
import utils.Statics;
import models.Produit;
import Interface.MyListener;
import GUI.panier.Mypanier;
public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    private Label fruitPriceLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private int id;
    private List<Produit> event = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    Connection cnx = MaConnexion.getInstance().getCnx();
    private Label aa;
    private Label bonplansNameLable;
    private Label categorieLabel;
    private ImageView imgbonolans;
    private Label Descriptionlabel;
    private Label eventNameLable;
    private Label prixLabel;
    private ImageView imgevent;
    private Label labeldd;
    private Label labeldf;
    private Button Reserver;
    @FXML
    private TextField rechevent;
  ObservableList<Produit> eventlist = FXCollections.observableArrayList();
    private Label idevent;
    @FXML
    private Button enstock;
    @FXML
    private TextField quantite;
    @FXML
    private Button horsstock;
    private Label showstock;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button addcart;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField txtcanomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField prix;
    @FXML
    private Button register;
    @FXML
    private Button totalpanier;
    private List<Produit> getData() throws SQLException {
        List<Produit> events = new ArrayList<>();
        Produit event;
        String tt = "SELECT * FROM produits`";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet xx = statement.executeQuery(tt);
        while (xx.next()) {
            event = new Produit();
            event.setNom_produit(xx.getString("nom_produit"));
            event.setImage(xx.getString("image_event"));
            
            event.setId_produit(xx.getInt("id_produit"));
            
            event.setPrix((int) xx.getFloat("prix"));

            events.add(event);

        }

        return events;
    }

    private void setChosenBonplans(Produit event) {
       // id = event.getId();
        //Preferences userpreferences = Preferences.userRoot();
        //userpreferences.put("idEve", String.valueOf(id));
       txtcanomproduit.setText(event.getNom_produit());
        //quantite.setText(String.valueOf(event.getPrix_ticket()));
          quantite.setText(String.valueOf(event.getQuantite()));
        idproduit.setText(String.valueOf(event.getId_produit()));
      
      // txtimage.setText(String.valueOf(event.getDate_debut()));
    //   txtdes.setText(String.valueOf(event.getDate_fin()));
       String path = event.getImage();
       Image imagebp = new Image("file:" + path);
       fruitImg.setImage(imagebp);
       
        
      chosenFruitCard.setStyle("-fx-background-color: #" + event.getImage() + ";\n"
                + "    -fx-background-radius: 30;");
      
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            event.addAll(getData());
      
        if (event.size() > 0) {
            setChosenBonplans(event.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit event) {
                    setChosenBonplans(event);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../GUI/panier/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(event.get(i), myListener);
                
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
        }  } catch (SQLException ex) {
            
        }
        
    }
    
     private void setChosenFruit(Produit fruit){
          
        //  System.out.println(quantite.getText());
        if (quantite.getText().equals("0")) {
            horsstock.setVisible(true);

            enstock.setVisible(false);
            Reserver.setVisible(false);
            showstock.setVisible(false);
            //    ajouterpanierr.setVisible(false);
        } else {
            enstock.setVisible(true);

            Reserver.setVisible(true);
            showstock.setVisible(true);
            showstock.setText("il reste " + quantite.getText() + " produits");
            horsstock.setVisible(false);

     }
  
        
    
  }



    
   
    
    private void gotopanier(MouseEvent event) throws IOException {
        
            Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../compte/market.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();
            }



    @FXML
    private void ajouterPanier(ActionEvent event) throws SQLException, IOException {
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
            
           String nom_produit = txtcanomproduit.getText();
           System.out.println(quantite.getText());
            float prix = Float.parseFloat( quantite.getText());
            //String image = txtimage.getText();
         
            String id_produit = idproduit.getText();
            int quantite = 1;
            //String nbr_ticket = txtcat.getText();
            //  String xx = id.getText();
            // String yy = "delete   from  categories where name = '" + nom + "' ";
            //String yy = "INSERT INTO evennement(date_debut,date_fin,prix_ticket,nom_evenement,image_event,nbrticket) VALUES (?,?,?,?,?,?)";
            String yy = "INSERT INTO paniers(`id_user`,`id_produit`,`quantite`,`nom_produit`,`prix`) VALUES (?,?,?,?,?)";
            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yyy = "SELECT * FROM paniers WHERE id_user ='" + id_user + "'  and  nom_produit='" + id + "'  ";
            psx = cnx.prepareStatement(yyy);

            rsx = psx.executeQuery();
            if (rsx.next()) {

                showMessageDialog(null, "produit deja ajouter au paneir");
            } else {

                ps = cnx.prepareStatement(yy);
                System.out.println(ps);
                ps.setString(1, id_user);
                ps.setString(2,id_produit);
                ps.setInt(3, quantite);
               ps.setString(4, nom_produit);
               ps.setFloat(5, prix);
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
                System.out.println(idproduit.getText());
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


    


}
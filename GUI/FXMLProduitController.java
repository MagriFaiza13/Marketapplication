/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categorie;
import entities.Produit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javax.swing.JOptionPane;
import service.ServiceCategorie;
import service.ServiceProduit;
import utiles.Maconnexion;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLProduitController implements Initializable {

    @FXML
    private TextField TFId;

    @FXML
    private TextField TFNom;

    

    @FXML
    private TextField TFQuantite;

    @FXML
    private TextField TFReference;

    @FXML
    private TextField TFPrix;

    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<Produit, Integer> col_id;

    @FXML
    private TableColumn<Produit, String> col_nom;

    @FXML
    private TableColumn<Produit, String> col_reference;

    @FXML
    private TableColumn<Produit, ComboBox> col_categorie;

    @FXML
    private TableColumn<Produit, Integer> col_quantite;

    @FXML
    private TableColumn<Produit, Integer> col_prix;
     @FXML
    private TextField TFFilter;

ObservableList<Produit> listM;
ObservableList<Produit> dataList;
int index=-1;
Connection cnx=null;
ResultSet rs=null;
PreparedStatement pst =null;
    @FXML
    private Button btn_quitter;
    @FXML
    private ComboBox type;
   
 

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<String> ids= new ArrayList<>();
        // TODO
         try{
            
            cnx= Maconnexion.getInstance().getCnx();
            
            String sql="select id,nom from categorie";
            PreparedStatement st = cnx.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            int index=0;
            while(rs.next()){
                type.getItems().add(index,rs.getString("nom"));
                ids.add(index, String.valueOf(rs.getInt("id")));
                index++;
            }
    
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        updateTable();
        searchProduit();
       
       
    }    
    

    @FXML
    private void AddProduit(ActionEvent event) {
       ServiceProduit sp = new ServiceProduit();
       Produit p = new Produit();
        p.setNom(TFNom.getText());
        
        p.setReference(TFReference.getText());
        p.setCategorie(type.getValue().toString());
        p.setQuantite(Integer.parseInt(TFQuantite.getText()));
        p.setPrix(Integer.parseInt(TFPrix.getText()));
       
        try {
            sp.add(p);
            updateTable();
            searchProduit();
          JOptionPane.showMessageDialog(null, "le produit à été  avec succes");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        TFNom.setText("");
        TFReference.setText("");
        TFQuantite.setText("");
        TFPrix.setText(" ");
        type.setValue(" ");
        
    }
/////methode select produits///
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
    TFReference.setText(col_reference.getCellData(index).toString());
   TFNom.setText(col_nom.getCellData(index).toString());
   TFReference.setText(col_reference.getCellData(index).toString());
     type.setValue(col_categorie.getCellData(index));
   
   TFQuantite.setText(col_quantite.getCellData(index).toString());
   TFPrix.setText(col_prix.getCellData(index).toString());
   

    }
    public void updateTable(){
      col_reference.setCellValueFactory(new PropertyValueFactory<Produit,String>("reference"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
       
        col_categorie.setCellValueFactory(new PropertyValueFactory<Produit, ComboBox>("categorie"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
          col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
         try {
             listM=ServiceProduit.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceProduit sp = new ServiceProduit();
        Produit p= new Produit();
        
         p.setNom(TFNom.getText());
        p.setReference(TFReference.getText());
        p.setCategorie(type.getValue().toString());
        p.setQuantite(Integer.parseInt(TFQuantite.getText()));
        p.setPrix(Integer.parseInt(TFPrix.getText()));
//        p.setId(Integer.parseInt(TFId.getText()));
       
        try{
        
         sp.update(p);
         updateTable();
        
        JOptionPane.showMessageDialog(null, "Le Produit à ete modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        TFNom.setText("");
        TFReference.setText(" ");
        TFQuantite.setText(" ");
         TFPrix.setText("");
        type.setValue("");
    
    }
     public void searchProduit(){
    
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        col_reference.setCellValueFactory(new PropertyValueFactory<Produit,String>("reference"));
        col_categorie.setCellValueFactory( new PropertyValueFactory<Produit, ComboBox>("categorie"));
        
         col_quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
         try{
             dataList=ServiceProduit.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Produit> filtredData = new FilteredList<>(dataList, b -> true);
          TFFilter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(Produit-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(Produit.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(Produit.getReference().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(Produit.getId()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Produit> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
          
          
     }

    @FXML
    private void Delete(ActionEvent event) {
      
       ServiceProduit sp= new ServiceProduit();
       Produit p = new Produit();
     
        try{
           
           sp.deleteProduit(Integer.parseInt(TFId.getText()));
           
           
            JOptionPane.showMessageDialog(null,"le produit a été supprimer avec succes");
            updateTable();
            searchProduit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        TFNom.setText("");
        TFReference.setText(" ");
        TFQuantite.setText(" ");
        TFPrix.setText("");
        type.setValue(" ");
    }

    @FXML
    private void Quitter(ActionEvent event) throws Exception{
        try{
            btn_quitter.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
   
       
    }

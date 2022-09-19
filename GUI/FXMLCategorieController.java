
package GUI;

import entities.Categorie;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ServiceCategorie;

import utiles.Maconnexion;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField TFId;

    @FXML
    private TextField TFNom;

    @FXML
    private TextField TFReference;

    @FXML
    private TextField TFQuantite;

     @FXML
    private TableView<Categorie> tableview;
     @FXML
    private TableColumn<Categorie, Integer> col_id;

    @FXML
    private TableColumn<Categorie, String> col_nom;

    @FXML
    private TableColumn<Categorie, String> col_reference;

       @FXML
    private TableColumn<Categorie, Integer> col_quantite;

    
ObservableList<Categorie> listM;
ObservableList<Categorie> dataList;
int index=-1;
Connection cnx=null;
ResultSet rs=null;
PreparedStatement pst =null;
    @FXML
    private TextField TFRecherche;
    @FXML
    private Button btn_quitter;
    @FXML
    
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_id.getCellData(index).toString());
   TFNom.setText(col_nom.getCellData(index).toString());
   TFReference.setText(col_reference.getCellData(index).toString());
  
   TFQuantite.setText(col_quantite.getCellData(index).toString());
 

}
    public void updateTable(){
         col_id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom"));
        col_reference.setCellValueFactory(new PropertyValueFactory<Categorie,String>("reference"));
      
         col_quantite.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("quantite"));
          
         try {
             listM=ServiceCategorie.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        searchProduit();
        updateTable();
        
    }  
    @FXML
    void addCategorie(ActionEvent event) {
        ServiceCategorie sc =new ServiceCategorie();
        Categorie C =new Categorie();
       
        C.setNom(TFNom.getText());
        C.setReference(TFReference.getText());
        C.setQuantite(Integer.parseInt(TFQuantite.getText()));
        
        try{
            sc.add(C);
            updateTable();
             searchProduit();
            JOptionPane.showMessageDialog(null, "Categorie a ete ajouter avec succes");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       
        TFNom.setText("");
        TFReference.setText(" ");
        TFQuantite.setText(" ");
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceCategorie SC =new ServiceCategorie();
        Categorie C =new Categorie();
            C.setNom(TFNom.getText());
            C.setReference(TFReference.getText());
            C.setQuantite(Integer.parseInt(TFQuantite.getText()));  
            C.setId(Integer.parseInt(TFId.getText()));
                
        try{
          
          SC.updateCategorie(C);
          updateTable();
         searchProduit();
        JOptionPane.showMessageDialog(null, "Le calégorie à été modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        TFNom.setText("");
        TFReference.setText(" ");
        TFQuantite.setText(" ");
    }
    public void searchProduit(){
        
        col_id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom"));
        col_reference.setCellValueFactory(new PropertyValueFactory<Categorie,String>("reference"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("quantite"));
          
         try{
          dataList=ServiceCategorie.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Categorie> filtredData = new FilteredList<>(dataList, b -> true);
          TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(person.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(person.getReference().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(person.getQuantite()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Categorie> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
    }
    @FXML
    private void Delete(ActionEvent event) {

        try{
        ServiceCategorie SC =new ServiceCategorie();
        Categorie C =new Categorie();
        
          SC.delete(TFReference.getText());
            JOptionPane.showMessageDialog(null,"le categorie a été supprimer avec succes");
            updateTable();
             searchProduit();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
      
        }
        TFNom.setText("");
        TFReference.setText(" ");
        TFQuantite.setText(" ");
    }

    @FXML
    private void Quitter(ActionEvent event) throws Exception {
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

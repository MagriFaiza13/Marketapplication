/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.Maconnexion;


/**
 *
 * @author asus
 */
public class ServiceProduit {
 
    
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceProduit instance;
    
    
    
    public static ServiceProduit getInstance(){
        if(instance==null)
            instance=new ServiceProduit();
        return instance;
    }
    public void add(Produit p  ) throws SQLException{
        
        String req="insert into produit (nom,reference,categorie,quantite,prix) values(?,?,?,?,?)";
       
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getReference());
            pst.setString(3, p.getCategorie());
            pst.setInt(4, p.getQuantite());
            pst.setInt(5, p.getPrix());
            pst.execute();
         
       
        
         }
     public ObservableList<Produit> getAll() throws SQLException {
    ObservableList<Produit> result = FXCollections.observableArrayList();
     String req = "SELECT * FROM `produit`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Produit p = new Produit();

         p.setNom(rst.getString("nom"));
           p.setReference(rst.getString("reference"));
         p.setCategorie(rst.getString("categorie"));
         p.setQuantite(rst.getInt("quantite"));
           p.setPrix(rst.getInt("prix"));
         p.setId(rst.getInt("id"));
         result.add(p);
     }

     return result;
    } 
public void update(Produit p) throws SQLException{
    String req="UPDATE `produit` SET nom=? , reference = ?, categorie=?, quantite =?, prix=? WHERE id=?";
    PreparedStatement pst =cnx.prepareStatement(req);
    pst.setString(1,p.getNom());
    pst.setString(2, p.getReference());
    pst.setString(3, p.getCategorie());
    pst.setInt(4, p.getQuantite());
    pst.setInt(5,p.getPrix());
   
    pst.setInt(6, p.getId());
    pst.executeUpdate();
    
}
public void deleteProduit(int id)throws SQLException{
    

    String req= "DELETE FROM `produit` WHERE id= ?";
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setInt(1, id);
         pst.executeUpdate();
       
}
}
    
     

    


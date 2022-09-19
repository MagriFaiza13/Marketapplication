/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entities.Categorie;
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
public class ServiceCategorie {
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceCategorie instance;
    
    
    
    public static ServiceCategorie getInstance(){
        if(instance==null)
            instance=new ServiceCategorie();
        return instance;
    }
    public void add(Categorie c) throws SQLException{
        
     String req= "INSERT INTO `categorie` (nom,reference,quantite) VALUES ('"
                + c.getNom()+"', '"
                + c.getReference()+"','"
                + c.getQuantite()+"')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
    }
     public ObservableList<Categorie> getAll() throws SQLException {
     ObservableList<Categorie> result =  FXCollections.observableArrayList();
     String req = "SELECT * FROM `categorie`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Categorie C = new Categorie();

         C.setId(rst.getInt("id"));
         C.setNom(rst.getString("nom"));
         C.setReference(rst.getString("reference"));
         C.setQuantite(rst.getInt("quantite"));
         result.add(C);
     }

     return result;
    } 
    public void updateCategorie(Categorie C) throws SQLException{
        String req = "UPDATE `categorie` SET nom = ?, reference = ?, quantite= ? WHERE id= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, C.getNom());
         pst.setString(2, C.getReference());
         pst.setInt(3, C.getQuantite());
         pst.setInt(4, C.getId());
         pst.executeUpdate();
    }
   public void delete(String reference) throws SQLException{
        String req= "DELETE FROM `categorie` WHERE  reference= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, reference);
         pst.executeUpdate();
        
    }
   
   
   
   
   
   
   



     
     
}

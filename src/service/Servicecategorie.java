/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MaConnexion;

/**
 *
 * @author sarra
 */
public class Servicecategorie implements interfacecategorie {
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void insertcategorie(categorie st) {
        
        String requete = "insert into categories (nomCategorie, description) values (?,?)";
        try {
            //      
            PreparedStatement ps = cnx.prepareStatement(requete);
  
            ps.setString(1, st.getNomCategorie());
            ps.setString(2, st.getDescription());
            
            
            ps.executeUpdate();
             System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }   
    }

    public ObservableList<categorie> DisplayAllcategorie() {
       ObservableList<categorie> listedepots = FXCollections.observableArrayList();
        String requete = "select * from categories";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            

            while (resultat.next()) {
                categorie pr = new categorie();
                pr.setIdCategorie(resultat.getInt(1));
                pr.setNomCategorie(resultat.getString(2));
                pr.setDescription(resultat.getString(3));
                
                listedepots.add(pr);
            }
            return listedepots;
        } catch (SQLException ex) {
          
            System.out.println("erreur lors du chargement des categories " + ex.getMessage());
            return null;
        }
    }
    private static interfacecategorie Servicecategorie;
    public static interfacecategorie getInstance() {
        if (Servicecategorie == null) {
            Servicecategorie = new Servicecategorie();
        }
        return Servicecategorie;
    }

    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.IUtilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Utilisateur;
import utils.MaConnexion;

/**
 *
 * @author wissem
 */
public class UtilisateurService implements IUtilisateur{
    Connection cnx = MaConnexion.getInstance().getCnx();

    
    
    
    @Override
    public Utilisateur findUserById(int id) {
      Utilisateur user = new Utilisateur();
        String requete = "select * from utilisateur where id_utilisateur=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                user.setId_utilisateur(resultat.getInt(1));
                user.setNom(resultat.getString(2));
            }
            return user;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du users " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur findUserBynom(String nom) {
        Utilisateur user = new Utilisateur();
        String requete = "select * from utilisateur where nom=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                user.setId_utilisateur(resultat.getInt(1));
                user.setNom(resultat.getString(2));
            }
            return user;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du user " + ex.getMessage());
            return null;
        }
    }

  
    @Override
    public ObservableList<Utilisateur> DisplayAllusers() {
        ObservableList<Utilisateur> listeutilisateur = FXCollections.observableArrayList();
        
        
        String requete = "select * from utilisateur where role='client'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while(resultat.next()){
           
                Utilisateur ut = new Utilisateur();
                ut.setId_utilisateur(resultat.getInt("id_utilisateur"));
                ut.setNom(resultat.getString("nom"));
                ut.setPrenom(resultat.getString("prenom"));
                ut.setMail(resultat.getString("mail"));
                ut.setLogin(resultat.getString("login"));
                ut.setTel(resultat.getInt("tel"));
                ut.setAdresse(resultat.getString("adresse"));
                ut.setEtat(resultat.getString("etat"));
                
                listeutilisateur.add(ut);
            }
            return listeutilisateur;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des utilisateurs " + ex.getMessage());
            return null;
        }
    }



      private static IUtilisateur IUtilisateur;
    
    public static IUtilisateur getInstance() {
        if (IUtilisateur == null) {
            IUtilisateur = new UtilisateurService();
        }
        return IUtilisateur;
    }    
}

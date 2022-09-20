/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interface.InterfaceUser;
import Interface.interfaceCommande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Commande;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-010
 */
public class ServiceCommande implements interfaceCommande {
    
    Connection cnx = MaConnexion.getInstance().getCnx();
    
    @Override
    public void insertCommande(Commande st) {
        String requete = "INSERT INTO `commandes`(`dateCommande`,`montantCommande`,`etat_de__commande`,`id_user`) VALUES ( ?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setDate(1, Date.valueOf(st.getDateCommande()));
            ps.setFloat(2, st.getMontantCommande());
            ps.setString(3, st.getEtat_de__commande());
            ps.setInt(4, st.getId().getId());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
    
    @Override
    public ObservableList<Commande> DisplayAllCommande() {
        ObservableList<Commande> listedepots = FXCollections.observableArrayList();
        String requete = "select * from commandes";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            
            InterfaceUser x = UtilisateurService.getInstance();
            while (resultat.next()) {
                Commandes pr = new Commandes();
                pr.setIdCommande(resultat.getInt(1));
                pr.setDateCommande(LocalDate.parse(resultat.getString(2)));
                pr.setMontantCommande(resultat.getFloat(3));
                pr.setEtat_de__commande(resultat.getString(4));
                pr.setId(x.findUserById(resultat.getInt(5)));
                
                listedepots.add(pr);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
    private static interfaceCommande interfaceCommande;
    
    public static interfaceCommande getInstance() {
        if (interfaceCommande == null) {
            interfaceCommande = new ServiceCommande();
        }
        return interfaceCommande;
    }
    
}

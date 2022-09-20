/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Elife-Kef-049
 */
public class Commande {

    private int idCommande;
    private LocalDate dateCommande;

    private Float montantCommande;
    private String etat_de__commande;
// cler etranger de table user
    private int id_user;

    public Commande() {
    }

    public Commande(LocalDate dateCommande, Float montantCommande, String etat_de__commande, int id_user) {
        this.dateCommande = dateCommande;
        this.montantCommande = montantCommande;
        this.etat_de__commande = etat_de__commande;
        this.id_user = id_user;
    }

    public Commande(int idCommande, LocalDate dateCommande, Float montantCommande, String etat_de__commande, int id_user) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.montantCommande = montantCommande;
        this.etat_de__commande = etat_de__commande;
        this.id_user = id_user;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Float getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(Float montantCommande) {
        this.montantCommande = montantCommande;
    }

    public String getEtat_de__commande() {
        return etat_de__commande;
    }

    public void setEtat_de__commande(String etat_de__commande) {
        this.etat_de__commande = etat_de__commande;
    }

    public Utlisateurs getId() {
        return id_user;
    }

    public void setId(Utilisateurs id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", dateCommande=" + dateCommande + ", montantCommande=" + montantCommande + ", etat_de__commande=" + etat_de__commande + ", id=" + id + '}';
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author sarra
 */
public class Panier {
    private int idpanier;
    private  int quantite;
    private int id_user;
    private int id_produit;

    public Panier() {
    }
    

    public Panier(int idpanier, int quantite, int id_user, int id_produit) {
        this.idpanier = idpanier;
        this.quantite = quantite;
        this.id_user = id_user;
        this.id_produit = id_produit;
    }

    public Panier(int idpanier, int quantite) {
        this.idpanier = idpanier;
        this.quantite = quantite;
    }

    public int getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(int idpanier) {
        this.idpanier = idpanier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "Panier{" + "idpanier=" + idpanier + ", quantite=" + quantite + ", id_user=" + id_user + ", id_produit=" + id_produit + '}';
    }
    
    
   
}

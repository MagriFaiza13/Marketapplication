/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Produit {

    
    //les attributs du classe produit
    private int id;
    private String nom;
    private String reference;
    private String categorie;
    private int quantite;
    private int prix;
   
  
    //constructeur paramétré
    public Produit(String nom, String categorie, int quantite, int prix, String reference) {
        
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prix=prix;
        this.reference=reference;
        
    }
     //constructeur par default
    public Produit(){
        
    }
    //getters and setters
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
//to String
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", quantite=" + quantite + '}';
    }
    
    
}

    


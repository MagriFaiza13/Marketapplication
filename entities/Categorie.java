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
public class Categorie {
 
    //les attributs
    private int id;
    private String nom;
    private String reference;
    private int quantite;
    

    //constructeur
    public Categorie(int id, String nom, String reference, int quantite) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.quantite = quantite;
    }
//constructeur par default
    public Categorie() {
    }
//to string
    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + ", reference=" + reference + ", quantiteProduit=" + quantite + '}';
    }
// getters and setters
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
    
}

    


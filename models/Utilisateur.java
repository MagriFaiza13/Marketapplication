/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wissem
 */
public class Utilisateur {
     private int id_utilisateur;
    private String nom;
    private String prenom;
    private String mail;
    private String login;
    private String mdp;
    private int tel;
    private String role;
    private String adresse;
    private String etat;

    public Utilisateur() {
    }

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    public Utilisateur(int id_utilisateur, String nom, String prenom, String mail, String login, String mdp, int tel, String role, String adresse, String etat) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.tel = tel;
        this.role = role;
        this.adresse = adresse;
        this.etat = etat;
    }

   
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public int getTel() {
        return tel;
    }

    public String getRole() {
        return role;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEtat() {
        return etat;
    }
    

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
   
  
    

    @Override
    public String toString() {
        return nom ;
    }
    
    
    
}

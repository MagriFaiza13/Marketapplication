/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.util.Objects;


public class Publication1 {
      private   Integer id_pub;
    private Integer id_user;
    private  String nom_pub;
    private  String type;

    private String picture;

    public Publication1(){}

    public Publication1(int id_pub, int id_user, String nom_pub, String type, String picture) {
        this.id_pub = id_pub;
        this.id_user = id_user;
        this.nom_pub = nom_pub;
        this.type = type;
        this.picture = picture;
    }

    public int getId_pub() {
        return id_pub;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom_pub() {
        return nom_pub;
    }

    public String getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_pub(String nom_pub) {
        this.nom_pub = nom_pub;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_pub;
        hash = 41 * hash + this.id_user;
        hash = 41 * hash + Objects.hashCode(this.nom_pub);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.picture);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publication1 other = (Publication1) obj;
        if (this.id_pub != other.id_pub) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom_pub, other.nom_pub)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication1{" + "id_pub=" + id_pub + ", id_user=" + id_user + ", nom_pub=" + nom_pub + ", type=" + type + ", picture=" + picture + '}';
    }
        
    }

    
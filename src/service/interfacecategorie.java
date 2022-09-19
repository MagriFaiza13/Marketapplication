/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.categorie;
import javafx.collections.ObservableList;

/**
 *
 * @author sarra
 */
public interface interfacecategorie {
     void insertcategorie(categorie st);

    ObservableList<categorie> DisplayAllcategorie();
    
}

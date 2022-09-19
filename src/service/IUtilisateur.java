/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javafx.collections.ObservableList;
import model.Utilisateur;

/**
 *
 * @author wissem
 */
public interface IUtilisateur {

    Utilisateur findUserById(int id);

    Utilisateur findUserBynom(String nom);

    ObservableList<Utilisateur> DisplayAllusers();
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import model.Utilisateurs;

/**
 *
 * @author Elife-Kef-010
 */
public interface InterfaceUser {



    Utilisateurs findUserById(int id_user);

    Utilisateurs findUserBynom(String nom);

    List<Utilisateurs> DisplayAllusers();
}

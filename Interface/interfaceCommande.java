/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.collections.ObservableList;
import model.Commande;

/**
 *
 * @author Elife-Kef-010
 */
public interface interfaceCommande {
     void insertCommande(Commande st);

    ObservableList<Commande> DisplayAllCommande();
}

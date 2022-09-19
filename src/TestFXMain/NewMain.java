/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFXMain;

import models.categorie;
import service.Servicecategorie;

/**
 *
 * @author sarra
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       categorie cat = new categorie("cat", "catcat");
        Servicecategorie sc = new Servicecategorie();
        sc.insertcategorie(cat);
    }
    
}

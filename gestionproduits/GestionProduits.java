/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduits;
import entities.Produit;
import java.sql.SQLException;
import service.ServiceProduit;

/**
 *
 * @author asus
 */
public class GestionProduits {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         ServiceProduit sp= new ServiceProduit();
       
        
       
        
       
    
     try{
            for(Produit P: sp.getAll()){
                System.out.println(P);
            }
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
}}


    
    


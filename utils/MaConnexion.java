/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sarra
 */
public class MaConnexion {
     public final String url ="jdbc:Mysql://localhost:3306/market_db";
   public final String user="root";
   public final String mdp ="";
   private Connection cnx;
   public static MaConnexion ct;
   MaConnexion() {
   
   try{
      cnx = DriverManager.getConnection(url, user, mdp);
      System.out.println("cnx etablie");
      
   }catch(SQLException e){
    System.out.println(e.getMessage());
}
   }

  public static MaConnexion getInstance() {
  if(ct==null)
  {
   ct = new MaConnexion();
  }
 
    return ct;
}
  public Connection getCnx(){
   return cnx;

}}


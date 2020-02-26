/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Allee;
import com.esprit.Entite.Emplacement;
import com.esprit.Entite.Famille;
import com.esprit.IService.IServiceEmplacement;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class ServiceEmplacement implements IServiceEmplacement<Emplacement> {

    private Connection con;
    private Statement ste;

    public ServiceEmplacement() throws ClassNotFoundException {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Emplacement t) throws SQLException {
     ste = con.createStatement();
        String requeteInsert = "INSERT INTO `SmartTruck`.`Emplacement` (`codeEmp`, `intitule`, `fkAllee`,`etat`) VALUES "
                + "( '" + t.getCodeEmp()+ "', '" + t.getIntitule()+ "', '" + t.getAllee().getCodeAllee() +"', '"+t.getEtatEmp()+ "'" + ");";
        ste.executeUpdate(requeteInsert);
    }
    @Override
    public void delete(Emplacement t) throws SQLException {
  ste = con.createStatement();
        String req = "DELETE FROM Emplacement where refEmp='" + "'";
        ste.executeUpdate(req);
    }
    
    public void deleteEmp(String code) throws SQLException {
          ste = con.createStatement();
        String req = "DELETE FROM Emplacement where codeEmp='" +code+ "'";
        ste.executeUpdate(req);
    }

    @Override
    public void updateEmp(Emplacement t) throws SQLException {
 
       ste = con.createStatement();
        String req = "UPDATE Emplacement "
                + "SET etat='" + t.getEtatEmp() + "WHERE codeEmp='" + t.getCodeEmp() + "'";

        ste.executeUpdate(req);

    }

   
    public void updateEmp(String a ,int x ) throws SQLException {
        ste = con.createStatement();
        String req = "UPDATE Emplacement "+ "SET fkFamille='"+x+"'"+"WHERE fkAllee='"+a+"'";
        ste.executeUpdate(req);
    }
    
    
    
    
    @Override
    public List<Emplacement> readAll() throws SQLException {
 
        List<Emplacement> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Emplacement");
        while (rs.next()) {
            String ref = rs.getString(1);
            String intit= rs.getString(2);
          
            String etat = rs.getString(4);              
            Emplacement p = new Emplacement(ref, intit,etat);
            arr.add(p);
        }
        return arr;
    }   
//      
//    public List<Emplacement> readEmp(String code) throws SQLException {
// 
//        List<Emplacement> arr = new ArrayList<>();
//        ste = con.createStatement();
//        ResultSet rs = ste.executeQuery("select * from Emplacement where fkAllee='" +code+ "'");
//        while (rs.next()) {
//            String ref = rs.getString(1);
//            String intit= rs.getString(2);
//           // Allee allee = rs.getString(4);       
//            Emplacement p = new Emplacement(ref, intit);
//            arr.add(p);
//        }
//        return arr;
//    }
    
      public void delete(String code) throws SQLException {
          ste = con.createStatement();
        String req = "DELETE FROM Emplacement where fkAllee='" +code+ "'";
        ste.executeUpdate(req);
    }
      
       public Emplacement chercherParCode2(String code) throws SQLException {
        ste = con.createStatement();
        String req = "select * FROM Emplacement where codeEmp='" + code + "'";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            String ref = rs.getString(1);
            String it = rs.getString(2);
            String etat =rs.getString(4);
            int fkFam = rs.getInt("fkFamille"); 
            Famille f= new Famille(fkFam);
            Emplacement p = new Emplacement(code, it,etat, f);
            if (rs.getString(1).equals(code)) {
                return p;
            }
            else{
                System.out.println("Emplacement introuvable");
            }

        }
        return null;
    }


    public List<Emplacement> readFamille() throws SQLException {

        List<Emplacement> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select codeEmp,intitule ,etat ,codeFamille ,fkFamille,fkAllee,nomFamille "
                + "from Emplacement,famille"
                + " where fkFamille=codeFamille" );
        while (rs.next()) {
            String ref = rs.getString("codeEmp");
            String allee = rs.getString("fkAllee");   
            String nomF = rs.getString("nomFamille");      
           String etat = rs.getString("etat");   
           String intit = rs.getString("intitule");

           Allee a =new Allee(allee);
           Famille f= new Famille(nomF);
            Emplacement p = new Emplacement(ref, intit, etat, f);
            arr.add(p);
        }
        return arr;
    }
    
     public List<Emplacement> readEmpAllee(String code) throws SQLException {

        List<Emplacement> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `codeEmp`, `intitule`, `fkAllee`,`etat`"
                + "from Emplacement where `fkAllee`='" +code+ "'"
        );
        while (rs.next()) {
            String ref = rs.getString("codeEmp");
            String allee = rs.getString("fkAllee");   
           String etat = rs.getString("etat");   
           String intit = rs.getString("intitule");
           Allee a =new Allee(allee);
           
            Emplacement p = new Emplacement(ref,intit, a, etat);
            arr.add(p);
            System.out.println(p);
        }
        return arr;
    }
   
}

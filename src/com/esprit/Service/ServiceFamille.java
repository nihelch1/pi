/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Allee;
import com.esprit.Entite.Famille;
import com.esprit.IService.IServiceFamille;
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
public class ServiceFamille implements IServiceFamille{
    
     private Connection con;
    private Statement ste;
    
    
     public ServiceFamille () throws ClassNotFoundException {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Object t) throws SQLException {
    }

    @Override
    public void delete(Object t) throws SQLException {
    }

    @Override
    public void updateEmp(Object t) throws SQLException {
    }

    @Override
    public List <String>readFamille() throws SQLException {
           List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select nomFamille from famille");
        while (rs.next()) {
            //Integer idFam= Integer.parseInt(rs.getString(1));
             String nomFam= rs.getString("nomFamille");
           // Famille p = new Famille(nomFam);
            arr.add(nomFam);
        }
                    System.out.println(arr);

        return arr;
    }
    
    
    public int getCode(String nom) throws SQLException{
        ste = con.createStatement();
        int codeF=0;
        String query = "select codeFamille from famille where nomFamille='" + nom + "'";
        ResultSet rs = ste.executeQuery(query);
        while (rs.next()) {
            codeF = rs.getInt(1);
            
        }
    
        return codeF;
    }
        
        
    }



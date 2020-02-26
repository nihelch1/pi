/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Stock;
import com.esprit.Entite.Palette;
import com.esprit.Utils.DataBase;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.esprit.Entite.At_Stock;
import com.esprit.IService.IServiceStock;
import com.esprit.IService.IServiceStock;

public class ServiceStock implements IServiceStock<Stock> {

    private Connection con;
    private Statement ste;

    public ServiceStock() {
        con = DataBase.getInstance().getConnection();

    }



    public List<At_Stock> afficherStock() throws SQLException {

        List<At_Stock> ls = new ArrayList<>();

        ste = con.createStatement();
        String query="SELECT  Palette.num_lot ,article.ref_article ,article.designation"
                + ",article.seuil_min,article.seuil_max, SUM(Palette.qte) \n" +
"                FROM article,Palette" +
"                WHERE article.ref_article=Palette.ref_article\n" +
"                GROUP BY article.ref_article";
        
        
//        String query="SELECT palette.num_lot,stock.etat,article.ref_article,article.designation,article.seuil_min,article.seuil_max, SUM(palette.qte) \n" +
//"                FROM article,palette,stock\n" +
//"                WHERE article.ref_article=palette.ref_article AND stock.num_palette=palette.num_lot\n" +
//"                GROUP BY article.ref_article";
        
//        String query = "SELECT palette.num_lot,article.reference,article.designation,article.seuil_min,article.seuil_max, SUM(palette.qte) \n"
//                + "FROM article,palette\n"
//                + "WHERE article.reference=palette.ref_article\n"
//                + "GROUP BY article.reference";
        
        
//        String query = "SELECT stock.id_stock, article.designation,article.reference,stock.etat,palette.num_lot,SUM(palette.qte),article.seuil_min,article.seuil_max"
//                + " FROM article,palette,stock "
//                + "WHERE article.reference=palette.ref_article AND palette.num_lot=stock.num_palette";
        ResultSet rs = ste.executeQuery(query);

        while (rs.next()) {
           // int id_stock = rs.getInt("id_stock");
            String designation = rs.getString("designation");
            String reference = rs.getString("ref_article");
            int num_lot = rs.getInt("num_lot");
            int qte_physique = rs.getInt("SUM(Palette.qte)");
            int seuil_min = rs.getInt("seuil_min");
            int seuil_max = rs.getInt("seuil_max");
            //String etat = rs.getString("etat");
   String etat="";
            if (qte_physique < seuil_min) {
                etat = "STOCK ALERTE";
            } else if (qte_physique > seuil_max) {
                etat = "SURSTOCKAGE";
            } else {
                etat = "Stock en sécurité";
            }

            Palette p =new Palette(num_lot);
            At_Stock c = new At_Stock(designation, reference,p, qte_physique, seuil_min, seuil_max,etat);
            ls.add(c);
//            System.out.println(c);
                        
   
        }
        
        return ls;
       
    }
    
     public int recherche(Palette num) throws SQLException{
        ste = con.createStatement();
        int idF=0;
        String query = "select num_lot from Palette where num_lot='" + num.getNum_lot() + "'";
        ResultSet rs = ste.executeQuery(query);
        while (rs.next()) {
            idF = rs.getInt(1);   
        }
    
        return idF;
    }
    
    public void AjoutStock() throws SQLException
    {
        List<At_Stock> l=afficherStock();
        for (int i=0 ;i<l.size();i++)
        {
          int  n=recherche(l.get(i).getNum_lot());
            ajouter(l.get(i).getQte(),l.get(i).getEtat(),n);
        }
        
    }
    

    @Override
    public void ajouter(int qté_physique, String etat,int num_palette) throws SQLException {
        
  String requeteInsert = "INSERT INTO SmartTruck.`stock` (qte_physique,etat,num_palette) "
            + "VALUES ( '" + qté_physique+ "', '" + etat+ "', '" + num_palette +  "');";
        ste.executeUpdate(requeteInsert);
       
    }
}

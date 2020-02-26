/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.GUI.AjoutArticleController;
import com.esprit.IService.IService_Article;
import com.esprit.Entite.Famille;
import com.esprit.Entite.Article;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.esprit.Entite.Fournisseur;



public class ServiceArticle implements IService_Article {
    private Connection con;
    private Statement ste;

    public ServiceArticle() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    public void ajouter(Article a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO SmartTruck.`article` (ref_article,designation, code_barres, "
                + "prix_achat,prix_vente, unite,seuil_min,seuil_max,fournisseur,famille) "
            + "VALUES ( '" + a.getRef_article() + "', '" + a.getDesignation() + "', '" 
                + a.getCode() + "', '" + a.getPrix_achat() + "','"+a.getPrix_vente()
                +"','"+a.getUnité()+"','"+a.getSeuil_min()+"', '" + a.getSeuil_max() 
                + "', '" + a.getFournisseur().getId() + "', '" + a.getFamille().getCodeFamille() +  "');";
        ste.executeUpdate(requeteInsert);
    
        
    }

  

    public void delete(String code) throws SQLException {
         ste = con.createStatement();
         String req="DELETE FROM article WHERE code_barres='"+code+"'";
        
         ste.executeUpdate(req);
        
        
    }

    public void update(String ref_article, String  designation , String code, float prix_achat, float prix_vente, String unité,int seuil_min,int seuil_max, Fournisseur fournisseur, Famille famille) throws SQLException {
        ste = con.createStatement();
        Article a=new Article();
        
        String req="UPDATE article  "
                + "SET ref_article='" + ref_article + "'"
                + ",designation='" + designation+ "'"
                + ",code_barres='" + code + "'"
                + ",prix_achat='" + prix_achat + "'"
                + " ,prix_vente='" + prix_vente + "'"
                + ",unite='" + unité + "'"
                + ",seuil_min='" + seuil_min + "'"
                + ",seuil_max='" + seuil_max + "'"
                + ",fournisseur='" + fournisseur.getId() + "'"
                + ",famille='" + famille.getCodeFamille() + "'"+
                "WHERE ref_article='"+ref_article+"'";
                
                
                
        ste.executeUpdate(req);

      

        
    }
    
    
    
    public int getIDf(String nomFr) throws SQLException{
        ste = con.createStatement();
        int idF=0;
        String query = "select id from fournisseur where nomSociete='" + nomFr + "'";
        ResultSet rs = ste.executeQuery(query);
        while (rs.next()) {
            idF = rs.getInt(1);

            
        }
    
        return idF;
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
    
    public List<String> getFournisseur()
    {List<String> fournisseurs= new ArrayList<>();
         try {
           ste = con.createStatement();
           String query= "select nomSociete FROM Fournisseur";
           ResultSet rs = ste.executeQuery(query);
           
           while (rs.next()){
             String nomF=(rs.getString("nomSociete"));
             fournisseurs.add(nomF);
           }
          

       } catch (SQLException ex) {
           Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
       }
         return fournisseurs;
    }
   
    public List<String> getFamille()
    {List<String> familles= new ArrayList<>();
         try {
           ste = con.createStatement();
           String query= "select nomFamille FROM famille";
           ResultSet rs = ste.executeQuery(query);
           
           while (rs.next()){
             String nom=(rs.getString("nomFamille"));
             familles.add(nom);
           }
          

       } catch (SQLException ex) {
           Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
       }
         return familles;
    }

    public List<Article> readAll() throws SQLException {
        List<Article> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT article.ref_article, article.designation,article.code_barres,article.prix_achat,article.prix_vente,article.unite,article.seuil_min,article.seuil_max,fournisseur.nomSociete,famille.nomFamille\n" +
"FROM article,fournisseur,famille\n" +
"WHERE article.fournisseur=fournisseur.id AND article.famille=famille.codeFamille");
     while (rs.next()) {                
               String référence=rs.getString(1);
               String désignation=rs.getString(2);
               String code=rs.getString(3);
               float prix_a=rs.getFloat(4);
               float prix_v=rs.getFloat(5);
               String unité=rs.getString(6);
               int seuil_min=rs.getInt(7);
               int seuil_max=rs.getInt(8);
               String four= rs.getString(9);
               
               String fam= rs.getString(10);
               Fournisseur fr = new Fournisseur(four);
               Famille f =new Famille(fam);
        
               Article a=new Article(référence,désignation, code, prix_a, prix_v, unité,seuil_min,seuil_max, fr, f);
     arr.add(a);
     }
    return arr;
    }
   
    
   
    
    
    
    
    
}

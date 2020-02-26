/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;


public class At_Stock {
    private int id_stock;
    private String designation;
    private String ref_article;
    private Palette num_lot;

    
    private int qte;
    private int seuil_min;
    private int seuil_max;
    private String etat;

    public At_Stock(int id_stock, String designation, String ref_article, int num_lot, int qte, int seuil_min, int seuil_max, String etat) {
        this.id_stock = id_stock;
        this.designation = designation;
        this.ref_article = ref_article;
       // this.num_lot = num_lot;
        this.qte = qte;
        this.seuil_min = seuil_min;
        this.seuil_max = seuil_max;
        this.etat = etat;
    }
    
     public At_Stock( String designation, String ref_article, Palette  num_lot, int qte, int seuil_min, int seuil_max, String etat) {
        
        this.designation = designation;
        this.ref_article = ref_article;
        
        this.qte = qte;
        this.seuil_min = seuil_min;
        this.seuil_max = seuil_max;
        this.num_lot=num_lot;
        this.etat = etat;
    }
     

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }

    public Palette getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(Palette num_lot) {
        this.num_lot = num_lot;
    }

   

    

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

   
    public int getSeuil_min() {
        return seuil_min;
    }

    public void setSeuil_min(int seuil_min) {
        this.seuil_min = seuil_min;
    }

    public int getSeuil_max() {
        return seuil_max;
    }

    public void setSeuil_max(int seuil_max) {
        this.seuil_max = seuil_max;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "At_Stock{" + "id_stock=" + id_stock + ", designation=" + designation + ", ref_article=" + ref_article + ", num_lot=" + num_lot + ", qte=" + qte + ", seuil_min=" + seuil_min + ", seuil_max=" + seuil_max + ", etat=" + etat + '}';
    }
    
  
    
}

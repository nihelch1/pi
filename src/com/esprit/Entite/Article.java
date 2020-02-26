/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nannn
 */
public class Article {
    
    
    private SimpleStringProperty ref_article;
    private SimpleStringProperty designation;
    private SimpleStringProperty code;
    private SimpleFloatProperty prix_achat;
    private SimpleFloatProperty prix_vente;
    private SimpleStringProperty unité;
    private SimpleIntegerProperty seuil_min;
    private SimpleIntegerProperty seuil_max;
    private Fournisseur fournisseur;
    private Famille famille;
  

    public Article() {
    }
    
    
    public Article(String ref_article) {
        this.ref_article = new SimpleStringProperty(ref_article);
    }
    
    
     public Fournisseur getFrs() {
        return fournisseur;
    }

    public String getRef_article() {
        return ref_article.get();
    }

    public void setRef_article(String ref_article) {
        this.ref_article.set(ref_article);
    }

    public String getDesignation() {
        return designation.get();
    }

    public void setDesignation(String designation) {
        this.designation.set(designation);
    }

    

    public int getSeuil_min() {
        return seuil_min.get();
    }

    public void setSeuil_min(int seuil_min) {
        this.seuil_min.set(seuil_min);
    }

    public int getSeuil_max() {
        return seuil_max.get();
    }

    public void setSeuil_max(int seuil_max) {
        this.seuil_max.set(seuil_max);
    }


    

    public String getCode() {
        return code.get();
    }


   

    public void setCode(String code) {
        this.code.set(code);
    }

   

    public void setUnité(String unité) {
        this.unité.set(unité);
    }

  
    public Famille getFamille() {
        return famille;    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

   

    public String getUnité() {
        return unité.get();
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Article(String ref_article, String designation, String code, float prix_achat, float prix_vente, String unité, Fournisseur fournisseur, Famille famille) {
        this.ref_article = new SimpleStringProperty(ref_article);
        this.designation = new SimpleStringProperty(designation);
        this.code = new SimpleStringProperty(code);
        this.prix_achat =new SimpleFloatProperty( prix_achat);
        this.prix_vente =new SimpleFloatProperty( prix_vente);
        this.unité =new SimpleStringProperty(unité);
        this.fournisseur = fournisseur;
        this.famille = famille;
    }

   

   

   

    public Article(String ref_article, String  designation , String code, float prix_achat, float prix_vente, String unité,int seuil_min,int seuil_max, Fournisseur fournisseur, Famille famille) {
        
        this.ref_article = new SimpleStringProperty(ref_article);
        this.designation = new SimpleStringProperty(designation);
        this.code = new SimpleStringProperty(code);
        this.prix_achat = new SimpleFloatProperty(prix_achat);
        this.prix_vente =new SimpleFloatProperty(prix_vente);
        this.unité = new SimpleStringProperty(unité);
        this.seuil_min = new SimpleIntegerProperty(seuil_min);
        this.seuil_max = new SimpleIntegerProperty(seuil_max);
        this.fournisseur =fournisseur;
        this.famille = famille;
    }

    public Float getPrix_achat() {
        return prix_achat.get();
    }

    public void setPrix_achat(Float prix_achat) {
        this.prix_achat.set(prix_achat);
    }

    public Float getPrix_vente() {
        return prix_vente.get();
    }

    public void setPrix_vente(Float prix_vente) {
        this.prix_vente.set(prix_vente);
    }
    
      public String getRef() {
        return ref_article.get();
    }

    public void setRef(String ref_article) {
        this.ref_article.set(ref_article);
    }

    @Override
    public String toString() {
        return "Article{" + "ref_article=" + ref_article + ", designation=" + designation + ", code=" + code + ", prix_achat=" + prix_achat + ", prix_vente=" + prix_vente + ", unit\u00e9=" + unité + ", seuil_min=" + seuil_min + ", seuil_max=" + seuil_max + ", fournisseur=" + fournisseur + ", famille=" + famille + '}';
    }

   



}
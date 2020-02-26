/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author aa
 */
public class CommandeFournisseur {
    
    private String num_commande,ref_article,nomSociete,etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    private int qte,id_commande;

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
    private Date date_commande,date_livraison;

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }
    private float montant;

    public String getNum_commande() {
        return num_commande;
    }

    public void setNum_commande(String num_commande) {
        this.num_commande = num_commande;
    }

    public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public CommandeFournisseur(String num_commande, String ref_article, int qte, String nom_ste, Date date_commande, float montant, Date date_livraison,String etat) {
        this.num_commande = num_commande;
        this.ref_article = ref_article;
        this.nomSociete = nom_ste;
        this.qte = qte;
        this.date_commande = date_commande;
        this.montant = montant;
        this.date_livraison = date_livraison;
        this.etat=etat;

    }

    public CommandeFournisseur(String num_commande, String ref_article, String nom_ste, int qte, int id_commande, Date date_commande, Date date_livraison, float montant) {
        this.num_commande = num_commande;
        this.ref_article = ref_article;
        this.nomSociete = nom_ste;
        this.qte = qte;
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.montant = montant;
    }
    
    
    
    
    
    

                
}

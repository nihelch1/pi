/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import com.esprit.Entite.Palette;


public class Stock {
    private int id_stock;
    private int qté_physique; 
    private String etat;
    private Palette num_palette;
   

    public Stock() {

    }

    public Stock( int qté_physique, String etat, Palette num_palette) {
        
        this.qté_physique = qté_physique;
        this.etat = etat;
        this.num_palette = num_palette;
    }
    
    public Stock(int id_stock, int qté_physique, String etat) {
        this.id_stock = id_stock;
        this.qté_physique = qté_physique;
        this.etat = etat;
        
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public int getQté_physique() {
        return qté_physique;
    }

    public void setQté_physique(int qté_physique) {
        this.qté_physique = qté_physique;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Palette getNum_palette() {
        return num_palette;
    }

    public void setNum_palette(Palette num_palette) {
        this.num_palette = num_palette;
    }

    @Override
    public String toString() {
        return "Stock{" + "id_stock=" + id_stock + ", qt\u00e9_physique=" + qté_physique + ", etat=" + etat + ", num_palette=" + num_palette + '}';
    }

    
}

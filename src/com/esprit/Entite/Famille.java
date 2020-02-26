/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class Famille {
    private int codeFamille ;
    private String nomFamille ;
 

    public Famille(String nomFamille) {
        this.nomFamille = nomFamille;
    }
     public Famille(int codeFamille, String nomFamille) {
        this.codeFamille = codeFamille;
        this.nomFamille = nomFamille;
    }

    public Famille(int codeFamille) {
        this.codeFamille = codeFamille;
    }
     
     
    
    
       public int getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(int codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

   

    @Override
    public String toString() {
        return this.nomFamille;
    }
    
}

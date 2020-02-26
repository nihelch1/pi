/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author mac
 */
public class Allee {
    private String codeAllee;
    private int nbTravees;
    private int niv;
    
    
    public Allee(String codeAllee, int nbTravees, int niv) {
        this.codeAllee = codeAllee;
        this.nbTravees = nbTravees;
        this.niv = niv;
    }
    
     public Allee(String codeAllee) {
        this.codeAllee = codeAllee;
     
    }
    
    
    public String getCodeAllee() {
        return codeAllee;
    }

    public void setCodeAllee(String codeAllee) {
        this.codeAllee = codeAllee;
    }

    public int getNbTravees() {
        return nbTravees;
    }

    public void setNbTravees(int nbTravees) {
        this.nbTravees = nbTravees;
    }

    public int getNiv() {
        return niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }
    
    
        @Override
    public String toString()
    {
        return codeAllee ;
    }
    
         
   
    
}

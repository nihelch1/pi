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
public class Emplacement {
    private String codeEmp;
    private String intitule ;
    private String etatEmp;
    private Allee  allee;
    private Famille famille ;
public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public Emplacement(String codeEmp) {
        this.codeEmp = codeEmp;
    }
    
    public Emplacement() {
        this.etatEmp="Disponible";
    }

    public String getCodeEmp() {
        return codeEmp;
    }

  
    
      public Emplacement(String codeEmp, String intitule, String etat) {
        this.codeEmp = codeEmp;
        this.intitule = intitule;
        this.etatEmp = etat;
    }
      
        
      public Emplacement(String codeEmp, String intitule,  Allee allee,String etatEmp ) {
        this.codeEmp = codeEmp;
        this.intitule = intitule;
        this.etatEmp = etatEmp;
        this.allee = allee;
    }
      
       public Emplacement(String codeEmp, String intitule,String etatEmp,Famille fam) {
        this.codeEmp = codeEmp;
        this.intitule = intitule;
        this.etatEmp = etatEmp;
        this.famille = fam;
    }
       
          public Emplacement(String codeEmp, Allee allee, Famille famille) {
        this.codeEmp = codeEmp;
        this.intitule = intitule;
       // this.etatEmp = etatEmp;
       this.famille =famille;
        this.allee = allee;
    }

    public String getEtatEmp() {
        return etatEmp;
    }

    public void setEtatEmp(String etatEmp) {
        this.etatEmp = etatEmp;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }
      
      
      
     public Emplacement(String codeEmp, String intitule) {
        this.codeEmp = codeEmp;
        this.intitule = intitule;
       // this.etatEmp = etatEmp;
    }
   
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

   
    public Allee getAllee() {
        return allee;
    }

    public void setAllee(Allee allee) {
        this.allee = allee;
    }

    @Override
    public String toString() {
        return "Emplacement{" + "codeEmp=" + codeEmp + ", intitule=" + intitule + ", etatEmp=" + etatEmp + ", allee=" + allee + ", famille=" + famille + '}';
    }

  
           
    
}

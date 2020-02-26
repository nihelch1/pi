/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

public class Fournisseur {

    private int id;
    private String cin;
    private String email;
    private String adresse;
    private int telephone;
    private int fax;
    private String nomSociete;

    public Fournisseur(int id, String cin, String email, String adresse, int telephone, int fax, String nomSociete) {
        this.id = id;
        this.cin = cin;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.fax = fax;
        this.nomSociete = nomSociete;
    }

    public Fournisseur() {
    }

    public Fournisseur(int id, String nomSociete) {
        this.id = id;
        this.nomSociete = nomSociete;
    }

    public Fournisseur(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", cin=" + cin + ", email=" + email + ", adresse=" + adresse + ", telephone=" + telephone + ", fax=" + fax + ", nomSociete=" + nomSociete + '}';
    }

}

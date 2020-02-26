/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IService;

import com.esprit.Entite.Article;
import java.sql.SQLException;

import java.util.List;
import com.esprit.Entite.Famille;
import com.esprit.Entite.Fournisseur;

/**
 *
 * @author nannn
 */
public interface IService_Article<A> {
    void delete(String référence) throws SQLException;
    void update(String référence, String  désignation , String code, float prix_achat, float prix_vente, String unité,int seuil_min,int seuil_max, Fournisseur fournisseur, Famille famille) throws SQLException;
    List<A> readAll() throws SQLException;
    void ajouter(Article a) throws SQLException;
}

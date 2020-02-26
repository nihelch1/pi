/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IService;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import com.esprit.Entite.At_Stock;
import com.esprit.Entite.Palette;


public interface IServiceStock <S> {
   void ajouter(int qté_physique,String etat, int num_palette) throws SQLException;
   List<At_Stock> afficherStock() throws SQLException;
}

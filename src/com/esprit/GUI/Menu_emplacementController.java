/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class Menu_emplacementController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private Button btnRetour1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficheGestionAllee(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGesAllee.fxml"));
            Parent root = loader.load();
            InterfaceGesAlleeController dpc = loader.getController();
            btnRetour.getScene().setRoot(root);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AfficheGestionFamille(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffecterFamille.fxml"));
            Parent root = loader.load();
            AffecterFamilleController dpc = loader.getController();
            btnRetour.getScene().setRoot(root);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

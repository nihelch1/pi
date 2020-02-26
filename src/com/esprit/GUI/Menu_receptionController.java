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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author aa
 */
public class Menu_receptionController implements Initializable {

    @FXML
    private Button btnCommandes;
    @FXML
    private Button btnPalette;
    @FXML
    private Button btnGR;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    


    @FXML
    private void PageGestionCommande(ActionEvent event) throws IOException {
        
            
             FXMLLoader loader= new FXMLLoader(getClass().getResource("Commande.fxml"));
           
            Parent root= loader.load();
            
             CommandeController acc= loader.getController();
           btnCommandes.getScene().setRoot(root);
    }

    @FXML
    private void PageGestionPalette(ActionEvent event) throws IOException {
         
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterPalette.fxml"));
           
            Parent root= loader.load();
            
             AjouterPaletteController acc= loader.getController();
           btnCommandes.getScene().setRoot(root);

    }

    @FXML
    private void AfficherMenu(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("menu_reception.fxml"));
           
            Parent root= loader.load();
            
             Menu_receptionController acc= loader.getController();
           btnCommandes.getScene().setRoot(root);
    }
    
}

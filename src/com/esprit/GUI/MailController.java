/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Service.EnvoiMail;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aa
 */
public class MailController implements Initializable {

    @FXML
    private Button btnEnvoyer;
    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtAdresse;
    @FXML
    private Button btnGR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }    

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        
        String msg =txtMessage.getText() ;
        EnvoiMail e = new EnvoiMail();
        e.envoyer(txtAdresse.getText(), msg);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Mail envoyé avec succée");
         Optional <ButtonType> result=alert.showAndWait();
        
    }
    public void setAdresse(String message)
        {
            
            txtAdresse.setText(message);
        }

    @FXML
    private void AfficherMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_reception.fxml"));

        Parent root = loader.load();

        Menu_receptionController acc = loader.getController();
        txtAdresse.getScene().setRoot(root);
    }

    @FXML
    private void Back(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheCommande.fxml"));

        Parent root = loader.load();

        AfficheCommandeController acc = loader.getController();
        txtAdresse.getScene().setRoot(root);
    }
   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Article;
import com.esprit.Entite.Emplacement;
import com.esprit.Entite.Palette;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServicePalette;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author aa
 */
public class AjouterPaletteController implements Initializable {

    @FXML
    private Label labelDate;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtqte;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private DatePicker dateE;
    @FXML
    private ComboBox<String> comboEmp;
    @FXML
    private TextField txtC;
    @FXML
    private Button idGP;
    final ObservableList<String> options = FXCollections.observableArrayList();
        ServicePalette s = new ServicePalette();
    @FXML
    private TextField txtDesignation;
    @FXML
    private Button btnAffichePalette;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new AutoCompleteComboBoxListener<>(combo);

        comboEmp.getItems().addAll("A", "B", "C", "D");
        try {

            fillCombo();
        } catch (SQLException ex) {
            Logger.getLogger(AddStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ServiceCommande s = new ServiceCommande();
            int num = s.last_num_lot() + 1;
            txtC.setText(String.valueOf(num));
            txtC.setDisable(true);
            txtC.setStyle("-fx-text-inner-color: grey;");

        } catch (SQLException ex) {
            Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }    

    @FXML
     private void ajouter(ActionEvent event) throws SQLException, ParseException {

        ServicePalette s = new ServicePalette();
        LocalDate datee = dateE.getValue();
        java.util.Date date = java.sql.Date.valueOf(datee);
        Article a = new Article(combo.getValue());
        Emplacement emp = new Emplacement(comboEmp.getValue());
        Palette p = new Palette(Integer.parseInt(txtC.getText()),Integer.parseInt(txtqte.getText()), date, a, emp);
        s.ajouter2(p);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout avec succés ");
        alert.setHeaderText(null);
        alert.setContentText("Palette ajoutée avec succés");
    }

    public void fillCombo() throws SQLException {
        List<String> articles = s.getAllArticles(combo.getValue());
        for (int i = 0; i < articles.size(); i++) {
            options.add(articles.get(i));
        }
        combo.setItems(options);

    }


    @FXML
    private void fillDesignation(ActionEvent event) throws SQLException {
         ServiceCommande s = new ServiceCommande();

        String des = s.getDesignation(combo.getSelectionModel().getSelectedItem());
        System.out.println(s);
        txtDesignation.setText(des);
        txtDesignation.setEditable(false);
        txtDesignation.setStyle("-fx-text-inner-color: grey;");

    }

    @FXML
    private void AffichePalette(ActionEvent event) throws IOException {
       
            
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AddStock.fxml"));
           
            Parent root= loader.load();
            
             AddStockController acc= loader.getController();
           txtC.getScene().setRoot(root);
    }

    @FXML
    private void AfficherMenu(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("menu_reception.fxml"));
           
            Parent root= loader.load();
            
             Menu_receptionController acc= loader.getController();
           txtC.getScene().setRoot(root);
    }
    
}

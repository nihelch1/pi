/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.esprit.Entite.Article;
import com.esprit.Entite.Famille;
import com.esprit.Entite.Fournisseur;
import com.esprit.Service.ServiceArticle;
import com.esprit.Utils.DataBase;

/**
 * FXML Controller class
 *
 * @author nannn
 */
public class InterfaceModifController implements Initializable {

    @FXML
    private TextField txtRef;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtVente;
    @FXML
    private TextField min;
    @FXML
    private ComboBox<String> comboFour;
    @FXML
    private TextField txtDes;
    @FXML
    private TextField txtAchat;
    @FXML
    private TextField txtUnite;
    @FXML
    private TextField max;
    @FXML
    private ComboBox<String> combof;
    @FXML
    private Label unite;
    @FXML
    private Label prix_vente;
    @FXML
    private Label prix_achat;
    @FXML
    private Label des;
    @FXML
    private Label code;
    @FXML
    private Label famille;
    @FXML
    private Label four;
    public Connection con;
    public Statement ste;
    
    ObservableList<Article> articles = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList optionsFr = FXCollections.observableArrayList();
    @FXML
    private Button btnmodif;

    public InterfaceModifController() {
         con = DataBase.getInstance().getConnection();
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fillCombo();
        fillComboFour();
        
    }

    void setAdresse(String m1, String m2, String m3,String m4,String m5, String m6, String m7, String m8, String m9, String m10) {
        
        txtRef.setEditable(false);
        txtRef.setStyle("-fx-text-inner-color: grey;");
        txtRef.setText(m1);
        txtDes.setText(m2);
        txtCode.setText(m3);
        min.setText(String.valueOf(m4));
        max.setText(String.valueOf(m5));
        txtAchat.setText(String.valueOf(m6));
        txtVente.setText(String.valueOf(m7));
        txtUnite.setText(m8);
        combof.setValue(m9);
        comboFour.setValue(m10);

    }
    
     public void fillComboFour() {
        ServiceArticle s = new ServiceArticle();
        List<String> fournisseurs = s.getFournisseur();
        for (int i = 0; i < fournisseurs.size(); i++) {
            optionsFr.add(fournisseurs.get(i));
        }
        comboFour.setItems(optionsFr);

    }

    public void fillCombo() {
        ServiceArticle s = new ServiceArticle();
        List<String> familles = s.getFamille();
        for (int i = 0; i < familles.size(); i++) {
            options.add(familles.get(i));
        }
        combof.setItems(options);

    }

    @FXML
    private void modif(ActionEvent event) throws IOException  {
       
        try {
            ServiceArticle sa=new ServiceArticle();
            
            String nom = combof.getValue();
            int code = sa.getCode(nom);
            
            String nomFr = comboFour.getValue();
            int id = sa.getIDf(nomFr);
            
            Fournisseur fr = new Fournisseur(id, nomFr);
            Famille f = new Famille(code, nom);
            
            sa.update(txtRef.getText(), txtDes.getText(), txtCode.getText(), 
                    parseFloat(txtAchat.getText()), parseFloat(txtVente.getText()),
                    txtUnite.getText(), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), fr, f);
            System.out.println(txtCode.getText());
            
            Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Succès");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Article modifié avec succès");
        Optional<ButtonType> answer = dialogC.showAndWait();

        if (answer.get() == ButtonType.OK) {
            
       FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherArticle.fxml"));
           
            Parent root= loader.load();
            
             AfficherArticleController aac= loader.getController();
             txtCode.getScene().setRoot(root);}
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void backToListe(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherArticle.fxml"));
           
            Parent root= loader.load();
            
             AfficherArticleController aac= loader.getController();
             txtCode.getScene().setRoot(root);
    }

   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Allee;
import com.esprit.Entite.Emplacement;
import com.esprit.Entite.Famille;
import com.esprit.Service.ServiceAllee;
import com.esprit.Service.ServiceEmplacement;
import com.esprit.Service.ServiceFamille;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class AffecterFamilleController implements Initializable {

    private Connection con;
    private Statement ste;
    @FXML
    private ComboBox<String> comboFam;
    @FXML
    private ComboBox<String> comboAllee;

    final ObservableList<String> optionsFam = FXCollections.observableArrayList();
    final ObservableList<String> optionsAllee = FXCollections.observableArrayList();
    @FXML
    private Button update;
    @FXML
    private TextField txtTrav;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnRetour1;
    @FXML
    private Button btnAffFamille;
    @FXML
    private Label msg;
    @FXML
    private TableView<Emplacement> tab;
    @FXML
    private TableColumn<Emplacement, String> colEmp;
    @FXML
    private TableColumn<Emplacement, String> colIntit;

        ObservableList<Emplacement> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            fillComboFamille();
            fillComboAllee();
            
       
        } catch (SQLException ex) {
        } 

    }

    public void fillComboFamille() throws SQLException {
        try {
            ServiceFamille s = new ServiceFamille();
            List<String> familles = s.readFamille();
            for (int i = 0; i < familles.size(); i++) {
                optionsFam.add(familles.get(i));
            }
            comboFam.setItems(optionsFam);
        } catch (ClassNotFoundException ex) {
        }

    }

    public void fillComboAllee() throws SQLException {
        try {
            ServiceAllee s = new ServiceAllee();
            List<String> familles = s.getListAllee();
            for (int i = 0; i < familles.size(); i++) {
                optionsAllee.add(familles.get(i));
            }
            comboAllee.setItems(optionsAllee);
        } catch (ClassNotFoundException ex) {
        }
    }

    @FXML
    private void updateemp(ActionEvent event) throws ClassNotFoundException, SQLException {

        ServiceFamille s = new ServiceFamille();
        ServiceAllee sa = new ServiceAllee();
        String nom = comboFam.getValue();
        int code = s.getCode(nom);

        String codeA = comboAllee.getValue();

        ServiceEmplacement ser = new ServiceEmplacement();
        ser.updateEmp(codeA, code);
    }

    

    @FXML
    private void fillTrav(ActionEvent event) throws ClassNotFoundException {
         String codeA = comboAllee.getValue();
        ServiceAllee sa = new ServiceAllee();
        System.out.println(codeA);
        
        try {
            System.out.println(codeA);
            Allee a = sa.getAllee(codeA);
            System.out.println(a);
            txtTrav.setText(String.valueOf(a.getNbTravees()));
           
        } catch (SQLException ex) 
        {
        }
    }

    @FXML
    private void back(MouseEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_emplacement.fxml"));
            Parent root = loader.load();
            Menu_emplacementController dpc = loader.getController();
            txtTrav.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void AffEmpParFamille(ActionEvent event) {
        
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichEmpParFam.fxml"));
            Parent root = loader.load();
            AffichEmpParFamController dpc = loader.getController();
            txtTrav.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        
        
    }
    
    
   public void affich(String code) throws ClassNotFoundException, SQLException{
   
       
   }
}

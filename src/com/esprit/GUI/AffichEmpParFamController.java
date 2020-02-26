/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package com.esprit.GUI;


import com.esprit.Entite.Emplacement;
import com.esprit.Entite.Famille;
import com.esprit.Service.ServiceEmplacement;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class AffichEmpParFamController implements Initializable {

    public static DataBase db;

    @FXML
    private Button btnRetour1;
    @FXML
    private TableView<Emplacement> tabFam;
    @FXML
    private TableColumn<Emplacement, String> colCode;
    @FXML
    private TableColumn<Emplacement, String> colIintit;
    @FXML
    private TableColumn<Emplacement, String> colEtat;
    @FXML
    private TableColumn<Emplacement, String> colFamille;

    ObservableList<Emplacement> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField txtRech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Afficher();
        
          txtRech.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                filterEmpList((String) oldValue, (String) newValue);
            }
        });
    }

    public void Afficher() {
        try {
            ServiceEmplacement ser = new ServiceEmplacement();
            List l = ser.readFamille();

            for (int i = 0; i < l.size(); i++) {

                Emplacement p = new Emplacement(ser.readFamille().get(i).getCodeEmp(), ser.readFamille().get(i).getIntitule(),
                        ser.readFamille().get(i).getEtatEmp(), ser.readFamille().get(i).getFamille()
                );
                oblist.add(p);
                System.out.println(p);

            }

            colCode.setCellValueFactory(new PropertyValueFactory<>("codeEmp"));
            colIintit.setCellValueFactory(new PropertyValueFactory<>("intitule"));
            colEtat.setCellValueFactory(new PropertyValueFactory<>("etatEmp"));
            colFamille.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Emplacement, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Emplacement, String> param) {
                    return new SimpleStringProperty(param.getValue().getFamille().getNomFamille());
                }
            });

            tabFam.setItems(oblist);

            
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void Back(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffecterFamille.fxml"));
            Parent root = loader.load();
            AffecterFamilleController dpc = loader.getController();
            btnRetour1.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

    
    
    
     public void filterEmpList(String oldValue, String newValue) {
             ObservableList<Emplacement> filteredList2 = FXCollections.observableArrayList();

        if (txtRech == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabFam.setItems(oblist);
        } else {
            newValue = newValue.toUpperCase();
            for (Emplacement Emplacement : tabFam.getItems()) {
                String filterAllee = Emplacement.getCodeEmp();
                String fam = Emplacement.getFamille().getNomFamille();
                String etat =Emplacement.getEtatEmp();

                if (filterAllee.toUpperCase().contains(newValue)||fam.toUpperCase().contains(newValue)
                        ||etat.toUpperCase().contains(newValue)) {
                    filteredList2.add(Emplacement);
                }
                

            }
            tabFam.setItems(filteredList2);
        }
    }
}

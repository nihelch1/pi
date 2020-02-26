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
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class InterfaceGesAlleeController implements Initializable {

    public static DataBase db;

    @FXML
    private TableView<Allee> tabAllee;
    @FXML
    private TableColumn<Allee, Integer> colNbTrav;
    @FXML
    private TableColumn<Allee, Integer> colNbNiv;
    @FXML
    private TableColumn<Allee, String> colAll;
    @FXML
    private Button btnRetour;
    @FXML
    private ImageView img;
    @FXML
    private TextField filterFiled;
    ObservableList<Allee> oblist = FXCollections.observableArrayList();
    @FXML
    private ImageView addImg;
    @FXML
    private TextField a;
    @FXML
    private TextField txtNbTrav;
    @FXML
    private TextField txtNbNiv;
    @FXML
    private TextField txtAllee;
    @FXML
    private Button btnRetour1;
    @FXML
    private Button listEmp;
    @FXML
    private Label erreur;

    @FXML
    private AnchorPane tabtest;
    @FXML
    private Label erreur1;
    @FXML
    private Label erreur2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficherTab();

        filterFiled.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                filterAlleeList((String) oldValue, (String) newValue);
            }
        });

    }

    public void filterAlleeList(String oldValue, String newValue) {
        ObservableList<Allee> filteredList = FXCollections.observableArrayList();
        if (filterFiled == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabAllee.setItems(oblist);
        } else {
            newValue = newValue.toUpperCase();
            for (Allee Allee : tabAllee.getItems()) {
                String filterAllee = Allee.getCodeAllee();
                int filterNbTrav = Allee.getNbTravees();
                int filterFrs = Allee.getNiv();

                if (filterAllee.toUpperCase().contains(newValue)) {
                    filteredList.add(Allee);
                }
                

            }
            tabAllee.setItems(filteredList);
        }

    }

    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            FXMLDocumentController dpc = loader.getController();
            tabAllee.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

    private void afficherTab() {
        Connection myConn = null;
        try {
            ServiceAllee ser = new ServiceAllee();
            List l = ser.readAll();

            for (int i = 0; i < l.size(); i++) {

                oblist.add(new Allee(ser.readAll().get(i).getCodeAllee(), ser.readAll().get(i).getNbTravees(),
                        ser.readAll().get(i).getNiv()));
            }

            colAll.setCellValueFactory(new PropertyValueFactory<>("codeAllee"));
            colNbTrav.setCellValueFactory(new PropertyValueFactory<>("nbTravees"));
            colNbNiv.setCellValueFactory(new PropertyValueFactory<>("niv"));

            tabAllee.setItems(oblist);

            tabAllee.setEditable(true);
            //colAll.setCellFactory(TextFieldTableCell.forTableColumn());
            //colNbNiv.setCellFactory( TextFieldTableCell.forTableColumn(new IntegerStringConverter() ));
            colNbTrav.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

            colNbTrav.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Allee, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Allee, Integer> t) {
                    ((Allee) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNbTravees(t.getNewValue());

                    System.out.println(t.getNewValue());
                    try {
                        ser.updateNbTrav(((Allee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).getCodeAllee(), t.getNewValue(), ((Allee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).getNiv());
                    } catch (SQLException ex) {
                    } catch (ClassNotFoundException ex) {
                    }

                }
            });

        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void supprimer(MouseEvent event) throws ClassNotFoundException, SQLException {

        // img.setImage(new Image("/smarttruckapplication/search_1.png"));
        System.out.println("hiii");
        int selected = tabAllee.getSelectionModel().getSelectedIndex(); // TODO Auto-generated catch block
        if (tabAllee.getSelectionModel().isSelected(selected)) {
            String code = tabAllee.getItems().get(selected).getCodeAllee();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerte de confirmation");
            alert.setHeaderText("Êtes-vous sûr de supprimer cet allée?"
                    + " Ceci va entrainer des modifications des emplacements ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServiceAllee ser = new ServiceAllee();
                ServiceEmplacement se = new ServiceEmplacement();
                se.delete(tabAllee.getItems().get(selected).getCodeAllee());
                ser.delete(tabAllee.getItems().get(selected).getCodeAllee());
                tabAllee.getItems().remove(tabAllee.getItems().get(selected));
            } else {
            }

        } else {
            System.out.println("selectionner un élement" + selected);

        }

    }

    private void showEmp(MouseEvent event) throws IOException {

    }

    @FXML
    private void AffichEmp(ActionEvent event) throws ClassNotFoundException, SQLException {
        Connection myConn = null;
        ServiceAllee ser = new ServiceAllee();
        ServiceEmplacement serEmp = new ServiceEmplacement();

        if (!(ser.chercherParCode(txtAllee.getText()))) {
            Allee a = new Allee(txtAllee.getText(), Integer.parseInt(txtNbTrav.getText()), Integer.parseInt(txtNbNiv.getText()));
            //int n =Integer.parseInt(txtalee.getText())*Integer.parseInt(txttrav.getText());
            //label.setText("Soit "+n+" emplacements a codifier");
            tabAllee.getItems().add(a);

            try {
                ser.ajouter(a);
                System.out.print(a);

                int i, j, k;
                String ch, ch1, code = "";

                for (j = 1; j <= Integer.parseInt(txtNbTrav.getText()); j++) {
                    for (k = 1; k <= Integer.parseInt(txtNbNiv.getText()); k++) {
                        code = a.getCodeAllee() + "-0" + j + "-0" + k;
                        ch1 = "Allee " + a.getCodeAllee() + " Travee " + j + " Niveau " + k;

                        Emplacement e1 = new Emplacement(code, ch1, a, "Disponible");
                        serEmp.ajouter(e1);

                        System.out.println(e1);

                    }
                }

            } catch (SQLException ex) {

                System.out.println(ex);
            }
        } else {
            System.out.println(" Allee existe deja!!");
            erreur.setText("Code allee existant !!");

        }

//          try {
//
//        } catch (IOException ex) {
//        }
    }

    @FXML
    private void AfficherListeEmp(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root = loader.load();
            AffichageController dpc = loader.getController();
            txtAllee.getScene().setRoot(root);

            System.out.println("hiii");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

//    @FXML
//    private void AffichFormFam(ActionEvent event) throws ClassNotFoundException {
//
//        Connection myConn = null;
//
//        ObservableList<Emplacement> oblist = FXCollections.observableArrayList();
//
//        try {
//            ServiceEmplacement ser = new ServiceEmplacement();
//            List<Emplacement> l = ser.readFamille();
//            List<String> l2 = new ArrayList<>();
//
//            for (int i = 0; i < l.size(); i++) {
//                Emplacement p = new Emplacement(ser.readFamille().get(i).getCodeEmp(),
//                ser.readFamille().get(i).getAllee(), ser.readFamille().get(i).getFamille());
//                oblist.add(p);
//
//            }
//
//            tabb.setItems(oblist);
//
//            a1.setCellValueFactory(new PropertyValueFactory<>("codeEmp"));
//            a2.setCellValueFactory(new PropertyValueFactory<>("famille"));
//            a3.setCellValueFactory(new PropertyValueFactory<>("allee"));
//
//        } catch (ClassNotFoundException ex) {
//        } catch (SQLException ex) {
//        }
//
//    }

    @FXML
    private void back(MouseEvent event) {
 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_emplacement.fxml"));
            Parent root = loader.load();
            Menu_emplacementController dpc = loader.getController();
            txtAllee.getScene().setRoot(root);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

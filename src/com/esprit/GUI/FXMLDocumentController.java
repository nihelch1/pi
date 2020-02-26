/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Allee;
import com.esprit.Entite.Emplacement;
import com.esprit.Service.ServiceAllee;
import com.esprit.Service.ServiceEmplacement;
import com.esprit.Utils.DataBase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author mac
 */
public class FXMLDocumentController implements Initializable {

    public static DataBase db;

    private Label label;
    @FXML
    private Button btn;
    @FXML
    private Label nbEmp;
    private ComboBox<String> combo;
    @FXML
    private TableView<Allee> tabAllee;
    @FXML
    private TableColumn<Allee, Integer> colNbTrav;
    @FXML
    private TableColumn<Allee, Integer> colNbNiv;
    @FXML
    private TableColumn<Allee, String> colNbPos;
    @FXML
    private TableColumn<Allee, String> colAll;
    @FXML
    private TextField txtNbTrav;
    @FXML
    private TextField txtNbNiv;
    @FXML
    private TextField txtPos;
    @FXML
    private Button fill;
    @FXML
    private TextField txtAllee;

    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     afficherTab();


    }

    @FXML
    private void AddEmp(ActionEvent event) throws ClassNotFoundException, SQLException {

        Connection myConn = null;

        ServiceAllee ser = new ServiceAllee();
        ServiceEmplacement serEmp = new ServiceEmplacement();
        
        
       if(!(ser.chercherParCode(txtAllee.getText())))
       {Allee a = new Allee(txtAllee.getText(), Integer.parseInt(txtNbTrav.getText()), Integer.parseInt(txtNbNiv.getText()));
        //int n =Integer.parseInt(txtalee.getText())*Integer.parseInt(txttrav.getText());
        //label.setText("Soit "+n+" emplacements a codifier");
        try {
            ser.ajouter(a);
            System.out.print(a);

            int i, j, k;
            String ch, ch1, code = "";

            for (j = 1; j <= Integer.parseInt(txtNbTrav.getText()); j++) {
                for (k = 1; k <= Integer.parseInt(txtNbNiv.getText()); k++) {
                    code = a.getCodeAllee() + "-0" + j + "-0" + k;
                    ch1 = "Allee " + a.getCodeAllee() + " Travee " + j + " Niveau " + k;

                    Emplacement e1 = new Emplacement(code, ch1, a,"Disponible");
                    serEmp.ajouter(e1);

                    System.out.println(e1);

                }
            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }}else{
           System.out.println(" Allee existe deja!!");
       }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Second Window");
            stage.show();

        } catch (IOException ex) {
        }
    }


    

    @FXML
    private void fill(ActionEvent event) {

        Allee aa = tabAllee.getSelectionModel().getSelectedItem();
        System.out.println(aa);
       txtNbTrav.setText(String.valueOf(aa.getNbTravees()));
       txtNbNiv.setText(String.valueOf(aa.getNiv()));
       //combo.setSelectionModel(aa.getCodeAllee());

   
        
    }

    
    
    private void afficherTab()
    {
        Connection myConn = null;
        ObservableList<Allee> oblist = FXCollections.observableArrayList();
        try {
            ServiceAllee ser = new ServiceAllee();
            List l = ser.readAll();

            for (int i = 0; i< l.size(); i++) 
            {

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
            colNbTrav.setCellFactory( TextFieldTableCell.forTableColumn(new IntegerStringConverter() ));
             
             colNbTrav.setOnEditCommit(new EventHandler<CellEditEvent<Allee, Integer>>() {
                @Override
                public void handle(CellEditEvent<Allee, Integer> t) {
                      ((Allee) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNbTravees(t.getNewValue());
                      
                      System.out.println(t.getNewValue() );
                    try {
                        ser.updateNbTrav( ((Allee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).getCodeAllee(), t.getNewValue(), ((Allee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).getNiv());
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
    private void openFile(MouseEvent event) {
        
   
    }

}

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
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Callback;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class AffichageController implements Initializable {

    public static DataBase db;

    @FXML
    private TableView<Emplacement> tab;
    @FXML
    private TableColumn<Emplacement, String> colCode;
    @FXML
    private TableColumn<Emplacement, String> colIntit;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnRetour1;
    @FXML
    private TextField txtRech;
    
    ObservableList<Emplacement> filteredList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Emplacement, String> colEtat;
    @FXML
    private Button qrbtn;
    @FXML
    private Button pdf;
            
    
    ObservableList<Emplacement> oblist = FXCollections.observableArrayList();
    @FXML
    private Button FamEmp;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();

        txtRech.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                filterEmpList((String) oldValue, (String) newValue);
            }
        });
    }

    public void afficher() {
        Connection myConn = null;


        try {
            ServiceEmplacement ser = new ServiceEmplacement();
            List l = ser.readAll();

            for (int i = 0; i < l.size(); i++) {

                Emplacement p = new Emplacement(ser.readAll().get(i).getCodeEmp(), ser.readAll().get(i).getIntitule(),
                         ser.readAll().get(i).getEtatEmp()
                );
                oblist.add(p);
                System.out.println(p);

            }

            colCode.setCellValueFactory(new PropertyValueFactory<>("codeEmp"));
            colIntit.setCellValueFactory(new PropertyValueFactory<>("intitule"));
            colEtat.setCellValueFactory(new PropertyValueFactory<>("etatEmp"));
           
            
          
            tab.setItems(oblist);
            tab.setEditable(true);
            
            colEtat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Emplacement, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Emplacement, String> event) {
                    event.getTableView().getItems().get(event.getTablePosition().getRow()).setEtatEmp(event.getNewValue());
                }
            });
            

        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
//        try {
//            ServiceEmplacement ser = new ServiceEmplacement();
//            List<Emplacement> l = ser.readFamille();
//            List<String> l2 = new ArrayList<>();
//
//            for (int i = 0; i < l.size(); i++) {
//
//                Emplacement p = new Emplacement(ser.readFamille().get(i).getCodeEmp(),
//                         ser.readFamille().get(i).getAllee(), ser.readFamille().get(i).getFamille());
//
//                oblist.add(p);
//
//            }
//
//            tab.setItems(oblist);
//
//            colCode.setCellValueFactory(new PropertyValueFactory<>("codeEmp"));
//            colFamille.setCellValueFactory(new PropertyValueFactory<>("famille"));
//           // a3.setCellValueFactory(new PropertyValueFactory<>("allee"));
//
//        } catch (ClassNotFoundException ex) {
//        } catch (SQLException ex) {
//        }

    }



@FXML
        private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGesAllee.fxml"));
            Parent root = loader.load();
            InterfaceGesAlleeController dpc = loader.getController();

            tab.getScene().setRoot(root);

        } catch (IOException ex) {
        }
    }

    public void filterEmpList(String oldValue, String newValue) {
             ObservableList<Emplacement> filteredList2 = FXCollections.observableArrayList();

        if (txtRech == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tab.setItems(oblist);
        } else {
            newValue = newValue.toUpperCase();
            for (Emplacement Emplacement : tab.getItems()) {
                String filterAllee = Emplacement.getCodeEmp();
                String filterNbTrav = Emplacement.getIntitule();

                if (filterAllee.toUpperCase().contains(newValue)) {
                    filteredList2.add(Emplacement);
                }

            }
            tab.setItems(filteredList2);
        }
    }
        
//          public void filterAlleeList(String oldValue, String newValue) {
//        ObservableList<Allee> filteredList = FXCollections.observableArrayList();
//        if(txtRech == null || (newValue.length() < oldValue.length()) || newValue == null) {
//            tab.setItems(filteredList);
//        }
//        else {
//            newValue = newValue.toUpperCase();
//            for(Allee Allee : tabAllee.getItems()) {
//                String filterAllee = Allee.getCodeAllee();
//                int filterNbTrav = Allee.getNbTravees();
//               int filterFrs = Allee.getNiv();
//                
//                if(filterAllee.toUpperCase().contains(newValue) ) {
//                    filteredList.add(Allee);
//                }
//                
//            
//            }
//            tabAllee.setItems(filteredList);
//        }
//    }

//    @FXML
//        private void QrCode(ActionEvent event) throws SQLException {
//        QrCodeController q = new QrCodeController();
//
//        if (tab.getSelectionModel().getSelectedItem() == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("WARNING");
//            alert.setContentText("Aucun element n'est selectionné");
//            alert.showAndWait();
//        } else {
//            q.ini(tab.getSelectionModel().getSelectedItem());
//        }
//    }
//
//    @FXML
//        private void print(ActionEvent event) throws DocumentException, FileNotFoundException, SQLException {
//        Document d = new Document();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        QrCodeController q = new QrCodeController();
//
//        Date date = new Date();
//        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
//        PdfWriter.getInstance(d, new FileOutputStream("doc.pdf"));
//        d.open();
//        d.add(new Phrase("                  "
//                + "                    "
//                + "Liste des emplacements" + "       "
//                + "                        "
//                + "     " + dateFormat.format(date) + "\n\n"));
//        for (int i = 0; i < tab.getItems().size(); i++) {
//            Emplacement a = new Emplacement("", "");
//            a = tab.getItems().get(i);
//            d.add(new Phrase("\nEmplacement= " + a.getCodeEmp() + "         ⏐     Intitule= " + a.getIntitule()
//                    + "\n-----------------------------------------------"
//                    + "-----------------------------------------------------"
//            ));
//        }
//        d.close();
//    }

    private void AffichFormFam(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffecterFamille.fxml"));
            Parent root = loader.load();
            AffecterFamilleController dpc = loader.getController();
            tab.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

    private void AffEmpParFamille(ActionEvent event) {
        
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichEmpParFam.fxml"));
            Parent root = loader.load();
            AffichEmpParFamController dpc = loader.getController();
            tab.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void AffichageFamille(ActionEvent event) throws ClassNotFoundException, SQLException {
        
            
        
        
    }

    @FXML
    private void print(ActionEvent event) {
    }

}

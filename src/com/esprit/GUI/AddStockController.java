/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Article;
import com.esprit.Entite.CommandeFournisseur;
import com.esprit.Entite.Emplacement;
import com.esprit.Entite.Palette;
import com.esprit.Service.ServiceCommande;
import com.esprit.Service.ServicePalette;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddStockController implements Initializable {

    @FXML
    private TextField txtqte;

    @FXML
    private DatePicker dateE;

    final ObservableList<String> options = FXCollections.observableArrayList();

    private Connection con;
    private Statement ste;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TableView<Palette> tableStock;
    @FXML
    private TableColumn<Palette, Integer> numero;
    @FXML
    private TableColumn<Palette, Integer> qte;
    @FXML
    private TableColumn<Palette, Date> date;
    ObservableList<Palette> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField search;

    ServicePalette s = new ServicePalette();
    @FXML
    private ComboBox<String> comboEmp;
    @FXML
    private TableColumn<?, ?> cellArticle;
    @FXML
    private TableColumn<?, ?> cellEmp;
    private TextField txtRef;
    @FXML
    private TextField txtC;
    @FXML
    private Label labelDate;
    @FXML
    private Button btnGR;

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
            fillTable();
        } catch (SQLException ex) {
            Logger.getLogger(AddStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        search();

        setCellValueFromTableToTextField();
        try {
            ServiceCommande s = new ServiceCommande();
            int num = s.last_num_lot() + 1;
            txtC.setText(String.valueOf(num));
        } catch (SQLException ex) {
            Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    public void fillCombo() throws SQLException {
        List<String> articles = s.getAllArticles(combo.getValue());
        for (int i = 0; i < articles.size(); i++) {
            options.add(articles.get(i));
        }
        combo.setItems(options);

    }

    public void fillTable() throws SQLException {
        oblist.clear();
        List<Palette> lcf = s.readAll();

        for (int i = 0; i < lcf.size(); i++) {
            oblist.add(new Palette(s.readAll().get(i).getNum_lot(), lcf.get(i).getQte(), lcf.get(i).getDate_expiration(), lcf.get(i).getRef(), lcf.get(i).getCodeEmp()));

        }

        numero.setCellValueFactory(new PropertyValueFactory<>("num_lot"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        cellArticle.setCellValueFactory(new PropertyValueFactory<>("ref"));
        cellEmp.setCellValueFactory(new PropertyValueFactory<>("codeEmp"));

        tableStock.setItems(oblist);

    }

    public void search() {
        String pattern = "MM/dd/yyyy";

        DateFormat df = new SimpleDateFormat(pattern);

        FilteredList<Palette> filteredData = new FilteredList<>(oblist, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Palette>) Stock -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Integer.toString(Stock.getNum_lot()).contains(newValue)) {
                        return true;
                    } else if (Integer.toString(Stock.getQte()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (df.format(Stock.getDate_expiration()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Palette> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableStock.comparatorProperty());
            tableStock.setItems(sortedData);

        });
    }

//    public void SetCellValueFromTableToTextField()
//    {
//        tableStock.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("hiii");
//                Palette s= tableStock.getItems().get(tableStock.getSelectionModel().getSelectedIndex());
//                txtqte.setText(Integer.toString(s.getQte()));
//                combo.setValue(Integer.toString(s.getNum_lot()));
//                dateE.setValue(LocalDate.parse((CharSequence) s.getDate_expiration()));
//                
//                
//            }
//        });
//        
//    }
    public void setCellValueFromTableToTextField() {
        tableStock.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtC.setEditable(false);
                combo.setVisible(true);
                dateE.setVisible(true);
                labelDate.setVisible(true);
                Palette p = tableStock.getItems().get(tableStock.getSelectionModel().getSelectedIndex());
                txtqte.setText(String.valueOf(p.getQte()));
                java.util.Date sqlDate = p.getDate_expiration();
                
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                String strDate = dateFormat.format(sqlDate);  
                
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                  LocalDate localDate = LocalDate.parse(strDate, formatter);

                
                 dateE.setValue(localDate);
                combo.setValue(p.getRef());
                combo.setEditable(true);

                txtC.setText(String.valueOf(p.getNum_lot()));
                comboEmp.setValue(p.getCodeEmp());

            }
        });
    }

    @FXML
    private void delete(MouseEvent event) throws SQLException {

        Alert alerts = new Alert(Alert.AlertType.CONFIRMATION);
        alerts.setTitle("Confirmer la suppression");
        alerts.setHeaderText(null);
        alerts.setContentText("Voulez vous vraiment supprimer cete commande?");
        Optional<ButtonType> result = alerts.showAndWait();
        if (result.get() == ButtonType.OK) {

            s.delete(Integer.parseInt(txtC.getText()));
            fillTable();
            txtqte.setText("");
            comboEmp.setValue("");
            txtC.setText("");
            combo.setVisible(true);
            txtC.setEditable(false);
            dateE.setValue(null);
            combo.setValue("");

            dateE.setVisible(true);
            labelDate.setVisible(true);
        }

    }

    @FXML
    private void edit(MouseEvent event) throws SQLException {

        int qte = Integer.parseInt(txtqte.getText());
        ServicePalette s = new ServicePalette();
        s.update(qte, Integer.parseInt(txtC.getText()),java.sql.Date.valueOf(dateE.getValue()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification avec succée ");
        alert.setHeaderText(null);
        alert.setContentText("Palette modifiée avec succée");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            fillTable();

            txtqte.setText("");
            comboEmp.setValue("");
            txtC.setText("");
            combo.setVisible(true);
            txtC.setEditable(false);
            dateE.setValue(null);
            combo.setValue("");

            dateE.setVisible(true);
            labelDate.setVisible(true);

        }

    }

    private void load(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStock.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void afficheCommande(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void Back(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterPalette.fxml"));
           
            Parent root= loader.load();
            
             AjouterPaletteController acc= loader.getController();
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

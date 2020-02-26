/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.CommandeFournisseur;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Palette;
import com.esprit.Service.EnvoiMail;
import com.esprit.Service.ServiceCommande;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author aa
 */
public class AfficheCommandeController implements Initializable {

    @FXML
    private TableView<CommandeFournisseur> tableCommande;
    @FXML
    private TableColumn<CommandeFournisseur, String> cellNum;
    @FXML
    private TableColumn<CommandeFournisseur, String> cellRef;
    @FXML
    private TableColumn<CommandeFournisseur, Integer> cellQte;
    @FXML
    private TableColumn<CommandeFournisseur, String> cellFrs;
    @FXML
    private TableColumn<CommandeFournisseur, java.sql.Date> cellDate;
    @FXML
    private TableColumn<CommandeFournisseur, Float> cellTotal;

    ObservableList<CommandeFournisseur> obliste = FXCollections.observableArrayList();
    ServiceCommande s = new ServiceCommande();
    @FXML
    private TextField txtNum;
    @FXML
    private TextField txtRef;
    @FXML
    private TextField txtQte;
    @FXML
    private TextField txtFrs;
    private TextField txtDate;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtRecherche;
    private TextField txtDateL;
    @FXML
    private ImageView imgEdit;
    @FXML
    private ImageView imgDelete;
    @FXML
    private TableColumn<?, ?> cellDateL;
    @FXML
    private TableColumn<CommandeFournisseur, String> cellEtat;
    @FXML
    private DatePicker pickerDateC;
    @FXML
    private DatePicker pickerDateL;
    @FXML
    private ComboBox<String> comboEtat;
    @FXML
    private ComboBox<String> comboFilter;
    @FXML
    private Button btnGR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new AutoCompleteComboBoxListener<>(comboEtat);

        comboEtat.getItems().addAll("En cours", "Annulée", "Arrivée");
        comboFilter.getItems().addAll("Commande annulée", "Commande en cours", "Commande arrivée", "Date commande", "Date livraison", "Tout");

        try {
            afficherTableau();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellValueFromTableToTextField();
        txtRecherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterStudentList((String) oldValue, (String) newValue);

            }

        });

    }

    @FXML
    public void filter() {
       

//    else  if (comboFilter.getValue().equals("Date commande"))
//        {
//             try {
//                 afficherCommandeParDateCommande();
//             } catch (SQLException ex) {
//                 Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
//             }
//        }
//     else if (comboFilter.getValue().equals("Date livraison"))
//        {
//             try {
//                 afficherCommandeParDateLivraison();
//             } catch (SQLException ex) {
//                 Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
//             }
//        }
//     else if (comboFilter.getValue().equals("Tout"))
//        {
//             try {
//                 afficherTableau();
//             } catch (SQLException ex) {
//                 Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
//             }
//        }
    }

    public void afficherTableau() throws SQLException {
        obliste.clear();
        List<CommandeFournisseur> lcf = s.afficherCommande();

        for (int i = 0; i < lcf.size(); i++) {
            obliste.add(new CommandeFournisseur(s.afficherCommande().get(i).getNum_commande(), s.afficherCommande().get(i).getRef_article(), s.afficherCommande().get(i).getQte(), s.afficherCommande().get(i).getNomSociete(), s.afficherCommande().get(i).getDate_commande(), s.afficherCommande().get(i).getMontant(), s.afficherCommande().get(i).getDate_livraison(), s.afficherCommande().get(i).getEtat()));

        }
        cellNum.setCellValueFactory(new PropertyValueFactory<>("num_commande"));
        cellRef.setCellValueFactory(new PropertyValueFactory<>("ref_article"));

        cellQte.setCellValueFactory(new PropertyValueFactory<>("qte"));

        cellFrs.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));

        cellDate.setCellValueFactory(new PropertyValueFactory<>("date_commande"));

        cellTotal.setCellValueFactory(new PropertyValueFactory<>("montant"));
        cellDateL.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        cellEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));

        tableCommande.setItems(obliste);
    }

    


    public void setCellValueFromTableToTextField() {
        tableCommande.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                CommandeFournisseur cm = tableCommande.getItems().get(tableCommande.getSelectionModel().getSelectedIndex());
                txtNum.setText(cm.getNum_commande());
                txtNum.setEditable(false);
                txtNum.setStyle("-fx-text-inner-color: grey;");
                txtRef.setText(cm.getRef_article());
                txtRef.setEditable(false);
                txtRef.setStyle("-fx-text-inner-color: grey;");

                txtFrs.setText(cm.getNomSociete());
                txtFrs.setStyle("-fx-text-inner-color: grey;");

                java.util.Date sqlDate = cm.getDate_commande();
                java.util.Date sqlDate2 = cm.getDate_livraison();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(sqlDate);
                String strDate2 = dateFormat.format(sqlDate2);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate localDate = LocalDate.parse(strDate, formatter);
                LocalDate localDate2 = LocalDate.parse(strDate2, formatter);

                pickerDateC.setValue(localDate);
                pickerDateL.setValue(localDate2);

                comboEtat.setValue(cm.getEtat());
                comboEtat.setEditable(true);

                txtTotal.setText(String.valueOf(cm.getMontant()));

                txtQte.setText(String.valueOf(cm.getQte()));
                txtQte.setEditable(false);
                txtQte.setStyle("-fx-text-inner-color: grey;");

            }
        });
    }
//
//    @FXML
//    private void modifierCommande(ActionEvent event) throws ParseException, SQLException {
//       
//        String date_commande = txtDate.getText();
//                java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date_commande);
//                java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
//        s.modifierCommande( sqlDate, Float.valueOf(txtTotal.getText()), txtNum.getText());
//    }
//   
//    @FXML
//    private void suuprimerCommande(ActionEvent event) throws SQLException {
//        s.DeleteCommande(txtNum.getText());
//        afficherTableau();
//    }

    public void filterStudentList(String oldValue, String newValue) {
        ObservableList<CommandeFournisseur> filteredList = FXCollections.observableArrayList();
        if (txtRecherche == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableCommande.setItems(obliste);
        } else {
            newValue = newValue.toUpperCase();
            for (CommandeFournisseur CommandeFournisseur : tableCommande.getItems()) {
                String filterNum = CommandeFournisseur.getNum_commande();
                String filterRef = CommandeFournisseur.getRef_article();
                String filterFrs = CommandeFournisseur.getNomSociete();

                java.util.Date d1 = CommandeFournisseur.getDate_commande();
                java.sql.Date d2 = new java.sql.Date(d1.getTime());

                Date filterDate = d2;

                if (filterNum.toUpperCase().contains(newValue) || filterRef.toUpperCase().contains(newValue)
                        || filterFrs.toUpperCase().contains(newValue)) {
                    filteredList.add(CommandeFournisseur);
                }
            }
            tableCommande.setItems(filteredList);
        }
    }

    @FXML
    private void modifCommande(MouseEvent event) throws ParseException, SQLException {

        s.modifierCommande(Date.valueOf(pickerDateC.getValue()), Float.valueOf(txtTotal.getText()), txtNum.getText(), Date.valueOf(pickerDateL.getValue()), comboEtat.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout avec succée ");
        alert.setHeaderText(null);
        alert.setContentText("Commande modifiée avec succée");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            afficherTableau();
            txtNum.setText("");
            txtRef.setText("");
            comboEtat.setValue("");
            pickerDateC.setValue(null);
            pickerDateL.setValue(null);

            txtFrs.setText("");

            txtTotal.setText("");

            txtQte.setText("");

        }

    }

    @FXML
    private void supprimeCommande(MouseEvent event) throws SQLException {

        Alert alerts = new Alert(Alert.AlertType.CONFIRMATION);
        alerts.setTitle("Confirmer la suppression");
        alerts.setHeaderText(null);
        alerts.setContentText("Voulez vous vraiment supprimer cete commande?");
        Optional<ButtonType> result = alerts.showAndWait();
        if (result.get() == ButtonType.OK) {

            s.DeleteCommande(txtNum.getText());
            afficherTableau();
            txtNum.setText("");
            txtRef.setText("");
            comboEtat.setValue("");
            pickerDateC.setValue(null);
            pickerDateL.setValue(null);

            txtFrs.setText("");

            txtTotal.setText("");

            txtQte.setText("");
        }

    }

    private void mail(MouseEvent event) throws SQLException {

    }

    private void envoiMail(MouseEvent event) throws SQLException {

    }

    @FXML
    private void envoiMailfrs(MouseEvent event) throws SQLException, IOException {
        CommandeFournisseur cm = tableCommande.getItems().get(tableCommande.getSelectionModel().getSelectedIndex());
        String nom = cm.getRef_article();
        s.getMail(nom);
        
        String mail = s.getMail(nom);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mail.fxml"));

        Parent root = loader.load();
      

//        
            MailController mc= loader.getController();
            mc.setAdresse(mail);
            txtNum.getScene().setRoot(root);

    }

    @FXML
    private void Back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));

        Parent root = loader.load();

        CommandeController acc = loader.getController();
        txtNum.getScene().setRoot(root);
    }

    @FXML
    private void AfficherMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_reception.fxml"));

        Parent root = loader.load();

        Menu_receptionController acc = loader.getController();
        txtNum.getScene().setRoot(root);
    }

}





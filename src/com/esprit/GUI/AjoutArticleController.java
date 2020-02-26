/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.awt.event.MouseEvent;
import com.esprit.Service.ServiceArticle;
import com.esprit.Entite.Famille;
import com.esprit.Entite.Article;
import com.esprit.Utils.DataBase;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.attribute.standard.Severity;
import com.esprit.Entite.Fournisseur;
import com.esprit.Service.ServiceStock;

/**
 * FXML Controller class
 *
 * @author nannn
 */
public class AjoutArticleController {

    @FXML
    public TextField txtRef;
    @FXML
    public TextField txtDes;
    @FXML
    public TextField txtCode;
    @FXML
    public TextField txtAchat;
    @FXML
    public TextField txtVente;
    @FXML
    public TextField txtUnite;
    public TextField txtFour;


    public Connection con;
    public Statement ste;
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

    ObservableList<Article> articles = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList optionsFr = FXCollections.observableArrayList();
    ServiceArticle sa = new ServiceArticle();
    @FXML
    private ComboBox<String> combof;
    @FXML
    private ComboBox<String> comboFour;
    @FXML
    private TextField min;
    @FXML
    private TextField max;
    @FXML
    private Button btnliste;
    @FXML
    private Button gestion_art;
    @FXML
    private Button gestion_stock;

    public AjoutArticleController() {
        con = DataBase.getInstance().getConnection();
    }

    @FXML
    public void ajouter(ActionEvent event) throws SQLException, IOException {

        fillComboFour();
        fillCombo();
        ste = con.createStatement();

        String nom = combof.getValue();
        int code = sa.getCode(nom);

        String nomFr = comboFour.getValue();
        int id = sa.getIDf(nomFr);

        Fournisseur fr = new Fournisseur(id, nomFr);
        Famille f = new Famille(code, nom);

        Article a = new Article(txtRef.getText(), txtDes.getText(), txtCode.getText(), parseFloat(txtAchat.getText()), parseFloat(txtVente.getText()),
                txtUnite.getText(), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), fr, f);
        ServiceArticle sa = new ServiceArticle();
        sa.ajouter(a);

        articles.add(new Article(txtRef.getText(), txtDes.getText(), txtCode.getText(), parseFloat(txtAchat.getText()), parseFloat(txtVente.getText()),
                txtUnite.getText(), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), fr, f));

       // afficher(event);
       Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Succès");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Article ajouté avec succès");
        Optional<ButtonType> answer = dialogC.showAndWait();

        if (answer.get() == ButtonType.OK) {

       
       FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherArticle.fxml"));
           
            Parent root= loader.load();
            
             AfficherArticleController aac= loader.getController();
             txtCode.getScene().setRoot(root);}
       
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

//     txtqte.setText(String.valueOf(p.getQte()));
//               
//
//                
//                
//                combof.setValue(a.getFamille());
//                combof.setEditable(true);
//
//                txtC.setText(String.valueOf(p.getNum_lot()));
//                comboEmp.setValue(p.getCodeEmp());
//    public void setCellValueFromTableToTextField() {
//        table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(javafx.scene.input.MouseEvent event) {
//
//                Article a = table.getItems().get(table.getSelectionModel().getSelectedIndex());
//
//                txtDes.setText(a.getDésignation());
//                txtRef.setText(a.getRéférence());
//                txtCode.setText(a.getCode());
//                min.setText(String.valueOf(a.getSeuil_min()));
//                max.setText(String.valueOf(a.getSeuil_max()));
//                txtAchat.setText(String.valueOf(a.getPrix_achat()));
//                txtVente.setText(String.valueOf(a.getPrix_vente()));
//                txtUnite.setText(a.getUnité());
//                combof.setValue(a.getFamille().getNomFamille());
//                comboFour.setValue(a.getFournisseur().getNomSociete());
//
//            }
//        });
//    }

    public void initialize() throws SQLException {
//        setCellValueFromTableToTextField();
        fillCombo();
        fillComboFour();

//        ServiceArticle s = new ServiceArticle();
//        List<Article> ar = s.readAll();
//        for (int i = 0; i < ar.size(); i++) {
//            articles.add(new Article(s.readAll().get(i).getRéférence(), s.readAll().get(i).getDésignation(), s.readAll().get(i).getCode(),
//                    s.readAll().get(i).getPrix_achat(), s.readAll().get(i).getPrix_vente(), s.readAll().get(i).getUnité(),
//                    s.readAll().get(i).getSeuil_min(), s.readAll().get(i).getSeuil_max(),
//                    s.readAll().get(i).getFournisseur(),
//                    s.readAll().get(i).getFamille()));
//        }
//
//        Réf.setCellValueFactory(new PropertyValueFactory<>("Référence"));
//        Dés.setCellValueFactory(new PropertyValueFactory<>("Désignation"));
//        Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
//        Prix_a.setCellValueFactory(new PropertyValueFactory<>("Prix_achat"));
//        Prix_v.setCellValueFactory(new PropertyValueFactory<>("Prix_vente"));
//        Unité.setCellValueFactory(new PropertyValueFactory<>("Unité"));
//        Fournisseur.setCellValueFactory(new Callback<CellDataFeatures<Article, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFournisseur().getNomSociete());
//            }
//        });
//
//        Famille.setCellValueFactory(new Callback<CellDataFeatures<Article, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFamille().getNomFamille());
//            }
//        });
//
//        table.setItems(articles);
//
//        FilteredList<Article> filteredData = new FilteredList<>(articles, b -> true);
//
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(article -> {
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (article.getDésignation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name.
//                } else if (article.getRéférence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.
//                } else if (String.valueOf(article.getCode()).indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//
//        SortedList<Article> sortedData = new SortedList<>(filteredData);
//
//        sortedData.comparatorProperty().bind(table.comparatorProperty());
//
//        table.setItems(sortedData);

    }
//
//    @FXML
//    void delete(ActionEvent event) {
//
//        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
//        dialogC.setTitle("Confimation");
//        dialogC.setHeaderText(null);
//        dialogC.setContentText("voulez vous supprimer ?");
//        Optional<ButtonType> answer = dialogC.showAndWait();
//
//        if (answer.get() == ButtonType.OK) {
//
//            try {
//
//                int selected = table.getSelectionModel().getSelectedIndex(); // TODO Auto-generated catch block
//                if (table.getSelectionModel().isSelected(selected)) {
//                    String code = table.getItems().get(selected).getCode();
//
//                    ServiceArticle s = new ServiceArticle();
//                    s.delete(code);
//                    afficher(event);
//                    table.getItems().remove(table.getItems().get(selected));
//
//                } else {
//                    System.out.println("selectionner un élement" + selected);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    void afficher(ActionEvent event) throws SQLException {
//        ObservableList<Article> articles = FXCollections.observableArrayList();
//
//        ServiceArticle s = new ServiceArticle();
//        List<Article> ar = s.readAll();
//        for (int i = 0; i < ar.size(); i++) {
//            articles.add(new Article(s.readAll().get(i).getRéférence(), s.readAll().get(i).getDésignation(), s.readAll().get(i).getCode(),
//                    s.readAll().get(i).getPrix_achat(), s.readAll().get(i).getPrix_vente(), s.readAll().get(i).getUnité(), s.readAll().get(i).getSeuil_min(), s.readAll().get(i).getSeuil_max(), s.readAll().get(i).getFournisseur(),
//                    s.readAll().get(i).getFamille()));
//        }
//
//        Réf.setCellValueFactory(new PropertyValueFactory<>("Référence"));
//        Dés.setCellValueFactory(new PropertyValueFactory<>("Désignation"));
//        Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
//        Prix_a.setCellValueFactory(new PropertyValueFactory<>("Prix_achat"));
//        Prix_v.setCellValueFactory(new PropertyValueFactory<>("Prix_vente"));
//        Unité.setCellValueFactory(new PropertyValueFactory<>("Unité"));
//        Fournisseur.setCellValueFactory(new Callback<CellDataFeatures<Article, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFournisseur().getNomSociete());
//            }
//        });
//
//        Famille.setCellValueFactory(new Callback<CellDataFeatures<Article, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFamille().getNomFamille());
//            }
//        });
//
//        table.setItems(articles);
//
//    }
//
//    @FXML
//    private void update(ActionEvent event) throws SQLException {
//        setCellValueFromTableToTextField();
//
//        String nom = combof.getValue();
//        int code = sa.getCode(nom);
//
//        String nomFr = comboFour.getValue();
//        int id = sa.getIDf(nomFr);
//
//        Fournisseur fr = new Fournisseur(id, nomFr);
//        Famille f = new Famille(code, nom);
//
//        sa.update(txtRef.getText(), txtDes.getText(), txtCode.getText(), parseFloat(txtAchat.getText()), parseFloat(txtVente.getText()),
//                txtUnite.getText(), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), fr, f);
//
//        afficher(event);
//    }

    @FXML
    private void afficherListe(ActionEvent event) throws IOException {
    
    FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherArticle.fxml"));
           
            Parent root= loader.load();
            
             AfficherArticleController aac= loader.getController();
             txtCode.getScene().setRoot(root);
    
    }

    @FXML
    private void gestionArticle(ActionEvent event) throws IOException {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutArticle.fxml"));
           
            Parent root= loader.load();
            
             AjoutArticleController aac= loader.getController();
             txtCode.getScene().setRoot(root);
    }

    @FXML
    private void gestionStock(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("VisualiserStock.fxml"));
           
            Parent root= loader.load();
            
             VisualiserStockController vsc= loader.getController();
             txtCode.getScene().setRoot(root);
    }

}

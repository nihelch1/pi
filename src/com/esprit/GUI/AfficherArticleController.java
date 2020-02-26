/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import com.esprit.Entite.Article;
import com.esprit.Service.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author nannn
 */
public class AfficherArticleController implements Initializable {

    @FXML
    private TableView<Article> table;
    @FXML
    private TableColumn<Article, String> Réf;
    @FXML
    private TableColumn<Article, String> Dés;
    @FXML
    private TableColumn<Article, String> Code;
    @FXML
    private TableColumn<Article, Float> Prix_a;
    @FXML
    private TableColumn<Article, Float> Prix_v;
    @FXML
    private TableColumn<Article, String> Unité;
    @FXML
    private TableColumn<Article, String> Fournisseur;
    @FXML
    private TableColumn<Article, String> Famille;
    @FXML
    private TextField filterField;
   
    @FXML
    private Button btndelete;
    ObservableList<Article> articles = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            afficher();
            
            FilteredList<Article> filteredData = new FilteredList<>(articles, b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(article -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (article.getDesignation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (article.getRef_article().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(article.getCode()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            SortedList<Article> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(table.comparatorProperty());

            table.setItems(sortedData);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

//     public void setCellValueFromTableToTextField() {
//        table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(javafx.scene.input.MouseEvent event) {
//
//                Article a = table.getItems().get(table.getSelectionModel().getSelectedIndex());
//
//                a.getDésignation();
//                a.getRéférence();
//                a.getCode();
//                String.valueOf(a.getSeuil_min());
//                String.valueOf(a.getSeuil_max());
//                String.valueOf(a.getPrix_achat());
//                String.valueOf(a.getPrix_vente());
//                a.getUnité();
//                a.getFamille().getNomFamille();
//                a.getFournisseur().getNomSociete();
//
//            }
//        });
//    }
    
    


    @FXML
    private void delete(ActionEvent event) {

        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("Confimation");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Voulez vous vraiment supprimer cet article ?");
        Optional<ButtonType> answer = dialogC.showAndWait();

        if (answer.get() == ButtonType.OK) {

            try {

                int selected = table.getSelectionModel().getSelectedIndex(); // TODO Auto-generated catch block
                if (table.getSelectionModel().isSelected(selected)) {
                    String code = table.getItems().get(selected).getCode();

                    ServiceArticle s = new ServiceArticle();
                    s.delete(code);

//                    table.getItems().remove(table.getItems().get(selected));
afficher();

                } else {
                    System.out.println("selectionner un élement" + selected);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void afficher() throws SQLException {
        
        
        
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
//        Fournisseur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFournisseur().getNomSociete());
//            }
//        });
//
//        Famille.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
//                return new SimpleStringProperty(param.getValue().getFamille().getNomFamille());
//            }
//        });
//
//        table.setItems(articles);

 articles.clear();
        
            ServiceArticle s = new ServiceArticle();
            List<Article> ar = s.readAll();
            for (int i = 0; i < ar.size(); i++) {
                articles.add(new Article(s.readAll().get(i).getRef_article(), s.readAll().get(i).getDesignation(), s.readAll().get(i).getCode(),
                        s.readAll().get(i).getPrix_achat(), s.readAll().get(i).getPrix_vente(), s.readAll().get(i).getUnité(),
                        s.readAll().get(i).getSeuil_min(), s.readAll().get(i).getSeuil_max(),
                        s.readAll().get(i).getFournisseur(),
                        s.readAll().get(i).getFamille()));
            }

            Réf.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            Dés.setCellValueFactory(new PropertyValueFactory<>("Designation"));
            Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
            Prix_a.setCellValueFactory(new PropertyValueFactory<>("Prix_achat"));
            Prix_v.setCellValueFactory(new PropertyValueFactory<>("Prix_vente"));
            Unité.setCellValueFactory(new PropertyValueFactory<>("Unité"));
            Fournisseur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                    return new SimpleStringProperty(param.getValue().getFournisseur().getNomSociete());
                }
            });

            Famille.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                    return new SimpleStringProperty(param.getValue().getFamille().getNomFamille());
                }
            });

            table.setItems(articles);

    }
    
    @FXML
    private void modifier(MouseEvent event) throws IOException {
           Article a = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                    
                    String d = a.getDesignation();
                    String r = a.getRef_article();
                    String c = a.getCode();
                    String m = String.valueOf(a.getSeuil_min());
                    String mx = String.valueOf(a.getSeuil_max());
                    String pa = String.valueOf(a.getPrix_achat());
                    String pv = String.valueOf(a.getPrix_vente());
                    String u = a.getUnité();
                    String f = a.getFamille().getNomFamille();
                    String fr = a.getFournisseur().getNomSociete();
                    
                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceModif.fxml"));
                    
                    Parent root = loader.load();
                    
                    InterfaceModifController imc = loader.getController();
                    imc.setAdresse(r,d,c,m,mx,pa,pv,u,f,fr);
                    filterField.getScene().setRoot(root);
        
    }

    @FXML
    private void backToAjout(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("AjoutArticle.fxml"));
           
            Parent root= loader.load();
            
             AjoutArticleController aac= loader.getController();
             table.getScene().setRoot(root);
        
    }

}

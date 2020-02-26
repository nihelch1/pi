/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;


import java.io.IOException;
import com.esprit.Entite.At_Stock;
import com.esprit.Service.ServiceStock;
import com.esprit.Utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nannn
 */
public class VisualiserStockController implements Initializable {

    @FXML
    private TableView<At_Stock> tableStock;
  //  TableColumn<At_Stock, String> etat;
    
    private TableColumn<At_Stock, String> id_c;
    @FXML
    private TableColumn<At_Stock, String> des_c;
    @FXML
    private TableColumn<At_Stock, String> ref_c;
    @FXML
    private TableColumn<At_Stock, String> qte_c;
    @FXML
    private TableColumn<At_Stock, String> min_c;
    @FXML
    private TableColumn<At_Stock, String> max_c;
    @FXML
    private TableColumn<At_Stock, String> etat_c;

    public Connection con;
    public Statement ste;
        ObservableList<At_Stock> stocks = FXCollections.observableArrayList();
        ServiceStock ss = new ServiceStock();
    @FXML
    private TextField filter;

    public VisualiserStockController() {
        con = DataBase.getInstance().getConnection();
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
         customiseFactory(etat_c);
      
        
        try {
            ServiceStock ss=new ServiceStock();
            List<At_Stock> ls;
            
            ls = ss.afficherStock();
             ss.AjoutStock();
            
            for(int i=0;i<ls.size();i++)
            {
                stocks.add(new At_Stock(ls.get(i).getDesignation(),
                        ls.get(i).getRef_article(),ls.get(i).getNum_lot(),ls.get(i).getQte(),
                        ls.get(i).getSeuil_min(),ls.get(i).getSeuil_max(),ls.get(i).getEtat()));
                
            }
            

            //id_c.setCellValueFactory(new PropertyValueFactory<>("id_stock"));
            des_c.setCellValueFactory(new PropertyValueFactory<>("designation"));
            ref_c.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
            qte_c.setCellValueFactory(new PropertyValueFactory<>("qte"));
            min_c.setCellValueFactory(new PropertyValueFactory<>("seuil_min"));
            max_c.setCellValueFactory(new PropertyValueFactory<>("seuil_max"));
            etat_c.setCellValueFactory(new PropertyValueFactory<>("etat"));
            tableStock.setItems(stocks);
            
   
            
        } catch (SQLException ex) {
            Logger.getLogger(VisualiserStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<At_Stock> filteredData = new FilteredList<>(stocks, b -> true);
		
		
		filter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(stock -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (stock.getDesignation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (stock.getRef_article().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		
		SortedList<At_Stock> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableStock.comparatorProperty());
		
		
		tableStock.setItems(sortedData);
        
    }
    
     
  
    
//        
//    TableView<>.setRowFactory(tv -> new TableRow<CustomItem>() {
//    @Override
//    protected void updateItem(CustomItemitem, boolean empty) {
//        super.updateItem(item, empty);
//        if (item == null || item.getValue() == null)
//            setStyle("");
//        else if (item.getValue() > 0)
//            setStyle("-fx-background-color: #baffba;");
//        else if (item.getValue() < 0)
//            setStyle("-fx-background-color: #ffd7d1;");
//        else
//            setStyle("");
//    }
//});


    void customiseFactory(TableColumn<At_Stock, String> calltypel) {
    calltypel.setCellFactory(column -> {
        return new TableCell<At_Stock, String>() {
            @Override
            protected void updateItem(String etat, boolean empty) {
                super.updateItem(etat, empty);

                setText(empty ? "" : getItem().toString());
                setGraphic(null);

                TableRow<At_Stock> currentRow = getTableRow();

                if (!isEmpty()) {

                    if(etat.equals("SURSTOCKAGE")||etat.equals("STOCK ALERTE")) 
                        currentRow.setStyle("-fx-background-color:red");
                    
                }
            }
        };
    });
}

    @FXML
    private void Statistiques(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("BarChart.fxml"));
           
            Parent root= loader.load();
            
             BarChartController bcc= loader.getController();
             filter.getScene().setRoot(root);
        
        
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import com.esprit.Utils.DataBase;

/**
 * FXML Controller class
 *
 * @author nannn
 */
public class BarChartController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    public Connection con;
    public Statement ste;

    public BarChartController() {
        con = DataBase.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ste = con.createStatement();
//            String query ="SELECT article.designation,SUM(palette.qte)\n" +
//"FROM article,palette\n" +
//"WHERE article.ref_article=palette.ref_article \n" +
//"GROUP by article.ref_article\n" +
//"ORDER BY SUM(palette.qte) DESC LIMIT 3";
            String query = "SELECT article.designation,palette.qte FROM article,palette "
                    + "WHERE article.ref_article=palette.ref_article ORDER BY `palette`.`qte` DESC LIMIT 5";
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                String des = rs.getString(1);
                int qte = rs.getInt(2);
                
        
            XYChart.Series se1 =new XYChart.Series<>();
            se1.getData().add(new XYChart.Data(des,qte));
            se1.getData().add(new XYChart.Data(des,qte));
            se1.getData().add(new XYChart.Data(des,qte));
            se1.getData().add(new XYChart.Data(des,qte));
            se1.getData().add(new XYChart.Data(des,qte));
            se1.getData().add(new XYChart.Data(des,qte));
            
            barchart.getData().addAll(se1);}
        } catch (SQLException ex) {
            Logger.getLogger(BarChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

    @FXML
    private void backToStock(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("VisualiserStock.fxml"));
           
            Parent root= loader.load();
            
             VisualiserStockController vsc= loader.getController();
             barchart.getScene().setRoot(root);
    }

    

}

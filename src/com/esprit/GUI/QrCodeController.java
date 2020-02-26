/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Emplacement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class QrCodeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Emplacement cas;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML
    public void ini(Emplacement cas) throws SQLException{
      /*
        ConsulterCoursActiviteController c=new ConsulterCoursActiviteController();
        CoursActivite ca;
                ca=c.envCA();*/
      
//        String myWeb = "Emplacement " + cas.getCodeEmp()+ "\n intitule "+cas.getIntitule() ;
//        
//         QRCodeWriter qrCodeWriter = new QRCodeWriter();
//         
//         List l =new ArrayList();
//        
//        int width = 300;
//        int height = 300;
//        String fileType = "png";
//         
//        BufferedImage bufferedImage = null;
//        try {
//            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
//            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            bufferedImage.createGraphics();
//             
//            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
//            graphics.setColor(Color.WHITE);
//            graphics.fillRect(0, 0, width, height);
//            graphics.setColor(Color.BLACK);
//             
//            for (int i = 0; i < height; i++) {
//                for (int j = 0; j < width; j++) {
//                    if (byteMatrix.get(i, j)) {
//                        graphics.fillRect(i, j, 1, 1);
//                    }
//                }
//            }
//             
//            System.out.println("Success...");
//             
//        } catch (WriterException ex) {
//            Logger.getLogger(QrCodeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//        ImageView qrView = new ImageView();
//        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
//             
//       
        
         
//        StackPane root = new StackPane();
//        root.getChildren().add(qrView);
//        Scene scene = new Scene(root, 350, 350);
//        Stage primaryStage=new Stage();
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
        
        
         
  
    }
    
}

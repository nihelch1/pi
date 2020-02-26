/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Allee;
import com.esprit.Entite.Emplacement;
import com.esprit.IService.IServiceAllee;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author mac
 */
public class ServiceAllee implements IServiceAllee<Allee> {

    private Connection con;
    private Statement ste;

    public ServiceAllee() throws ClassNotFoundException {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Allee a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `SmartTruck`.`Allee` (`codeAllee`, `nbTravees`, `niv`) VALUES "
                + "( '" + a.getCodeAllee() + "', '" + a.getNbTravees() + "', '" + a.getNiv() + "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public void delete(Allee t) throws SQLException {
        ste = con.createStatement();
        String req = "DELETE FROM Allee where codeAllee='" + t.getCodeAllee() + "'";
        ste.executeUpdate(req);
    }

    @Override
    public void update(Allee t) throws SQLException {

        ste = con.createStatement();
        String req = "UPDATE Allee "
                + "SET nbTravees='" + t.getNbTravees() + "',niv='" + t.getNiv() + "' "
                + "WHERE codeAllee='" + t.getCodeAllee() + "'";

        ste.executeUpdate(req);

    }

    public void updateNbTrav(String codeAllee, int nbtr, int niv) throws SQLException, ClassNotFoundException {
        ste = con.createStatement();
        boolean test = false;
        Allee a = chercherParCode2(codeAllee);

        String req = "UPDATE Allee "
                + "SET nbTravees='" + nbtr + "' "
                + "WHERE codeAllee='" + codeAllee + "'";

        ste.executeUpdate(req);
        ArrayList<String> listCode = new ArrayList<>();

        ServiceEmplacement sc = new ServiceEmplacement();
        List<Emplacement> l = sc.readAll();
        for (int i = 0; i < l.size(); i++) {

            if (l.get(i).getCodeEmp().charAt(0) == codeAllee.charAt(0) && nbtr
                    < Integer.parseInt(l.get(i).getCodeEmp().substring(3, 4))) {
                {
                    listCode.add(l.get(i).getCodeEmp());
                    test = true;
                }
            }
        }

        if (test == true) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Alerte de confirmation");
            alert.setHeaderText("Êtes-vous sûr de supprimer cet allée?"
                    + " Ceci va entrainer des modifications des emplacements ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                for (int k = 0; k <= listCode.size() - 1; k++) {
                    System.out.println(listCode.get(k));
                    sc.deleteEmp(listCode.get(k));
                }
            } else {
            }
        }

        if (nbtr > a.getNbTravees()) {
            int n = nbtr - a.getNbTravees();
            {
                for (int o = (a.getNbTravees() + 1); o <= nbtr; o++) {
                    for (int j = 1; j <= niv; j++) {
                        {
                            System.out.println(" " + j);
                            String ch = codeAllee + "-0" + o + "-0" + j;
                            String ch1 = "Allee " + codeAllee + " Travee " + j + " Niveau " + j;
                            Emplacement e = new Emplacement(ch, ch1, a, "Disponible");
                            sc.ajouter(e);
                            System.out.println(e);
                        }
                    }
                }

            }
        }
    }

    @Override
    public List<Allee> readAll() throws SQLException {
        List<Allee> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Allee");
        while (rs.next()) {
            String codeAllee = rs.getString(1);
            Integer nbTrav = Integer.parseInt(rs.getString(2));
            Integer nbNiv = Integer.parseInt(rs.getString(3));

            // Allee allee = rs.getString(4);
            Allee p = new Allee(codeAllee, nbTrav, nbNiv);
            arr.add(p);
        }
        return arr;
    }

    public boolean chercherParCode(String code) throws SQLException {
        ste = con.createStatement();
        String req = "select * FROM Allee where codeAllee='" + code + "'";
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {

            String codeA = rs.getString(1);
            if (codeA.equals(code)) {
                return true;
            }

        }
        return false;
    }

    public Allee chercherParCode2(String code) throws SQLException {
        ste = con.createStatement();
        String req = "select * FROM Allee where codeAllee='" + code + "'";
        ResultSet rs = ste.executeQuery(req);
        List<Allee> arr = new ArrayList<>();

        while (rs.next()) {

            String ref = rs.getString(1);
            int alle = Integer.parseInt(rs.getString(2));
            int travee = Integer.parseInt(rs.getString(2));
            //int niveau = rs.getInt("niveau"); 
            Allee p = new Allee(ref, alle, travee);
            if (rs.getString(1).equals(code)) {
                return p;
            }

        }
        return null;
    }

    public void delete(String code) throws SQLException {
        ste = con.createStatement();
        String req = "DELETE FROM Allee where codeAllee='" + code + "'";
        ste.executeUpdate(req);
    }

    public List<String> getListAllee() throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select codeAllee from Allee");
        while (rs.next()) {
            //Integer idFam= Integer.parseInt(rs.getString(1));
            String codeAllee = rs.getString("codeAllee");
            // Famille p = new Famille(nomFam);
            arr.add(codeAllee);
        }
        System.out.println(arr);

        return arr;
    }

    public String getCode(String code) throws SQLException {
        ste = con.createStatement();
        String codeF = "";

        String query = "select codeAllee from Allee where codeAllee='" + code + "'";
        ResultSet rs = ste.executeQuery(query);
        while (rs.next()) {
            codeF = rs.getString(1);

        }

        return codeF;
    }

    public Allee getAllee(String code) throws SQLException {
        Allee a;
        ste = con.createStatement();
        String codeF = "";
        List<Allee> arr = new ArrayList<>();

        String query = "select * from Allee where codeAllee='" + code + "'";
        ResultSet rs = ste.executeQuery(query);
        while (rs.next()) {
            codeF = rs.getString(1);
            int nbTrav = rs.getInt(2);
            int nbniv = rs.getInt(3);

            a = new Allee(codeF, nbTrav, nbniv);
            if (rs.getString(1).equals(code)) {
                return a;
            } else {
                System.out.println("Emplacement introuvable");
            }
        }
        return null;

    }

}

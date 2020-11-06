/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MVente.Livraison;

import entities.MProduit.Produit;
import entities.MVente.Livraison.Livraison;
import entities.MVente.Panier.Panier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author user
 */
public class CrudLivraison {

    public List<Livraison> listpb(int id) {
        List<Livraison> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM livraison WHERE UserId='" + id + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Livraison p = new Livraison();
                p.setId(rs.getInt(1));
                p.setDateDepart(rs.getDate(2));
                p.setIdPanier(rs.getInt(3));
                p.setIdProduit(rs.getInt(4));
                p.setUserId(rs.getInt(5));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List<Livraison> listp() {
        List<Livraison> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM livraison WHERE UserId='" + LoginController.us.getId() + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Livraison p = new Livraison();
                p.setId(rs.getInt(1));
                p.setDateDepart(rs.getDate(2));
                p.setIdPanier(rs.getInt(3));
                p.setIdProduit(rs.getInt(4));
                p.setUserId(rs.getInt(5));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public Panier getpaniertorlist(int id) {
        Panier p = new Panier();
        try {
            String requete1 = "SELECT * FROM panier WHERE panier.id=" + id + "";
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(requete1);
            while (rs.next()) {

                p.setId(rs.getInt(1));
                p.setQtpanier(rs.getInt(2));
                p.setValid(rs.getInt(3));
                p.setIdProduit(rs.getInt(4));
                p.setIdUser(rs.getInt(5));
                p.setIdLivraison(rs.getInt(6));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public Produit getproduitorlist(int id) {
        Produit p = new Produit();
        try {
            String requete1 = "SELECT * FROM produit WHERE produit.id=" + id + "";
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(requete1);
            while (rs.next()) {
                p.setId_produit(rs.getInt(1));
                p.setId_user(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getFloat(5));
                p.setQuantite(rs.getInt(6));
                p.setDevis_name1(rs.getString(7));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public void reclam() {
        System.out.println("Acces aux reclamations");
    }

    public void ajouterLivraison() {

        List<Panier> myList = new ArrayList();

        try {
            String requete1 = "SELECT * FROM panier WHERE IdUser='" + LoginController.us.getId() + "'";
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(requete1);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId(rs.getInt(1));
                p.setQtpanier(rs.getInt(2));
                p.setValid(rs.getInt(3));
                p.setIdProduit(rs.getInt(4));
                p.setIdUser(rs.getInt(5));
                p.setIdLivraison(rs.getInt(6));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        for (Panier x : myList) {

            String requete = "INSERT INTO livraison (DateDepart ,IdPanier,IdProduit,UserId)"
                    + " VALUES ('" + DateConverter.Date_Now_As_String() + "','" + x.getId() + "','" + x.getIdProduit() + "','" + x.getIdUser() + "');";

            try {
                Statement st = MyConnection.getInstance().getCnx()
                        .createStatement();
                st.executeUpdate(requete);
                System.out.println("Livraison Ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            //  alert("Produit detecter","Ce produit deja existe vous pouvez pas l'ajouter");
        }
    }

    private void alert(String un, String deux) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);
        alert.setContentText(deux);
        alert.show();
    }
    
   public static void supprimer(){
    
    String requete="DELETE FROM livraison WHERE (SELECT TIMESTAMPDIFF(DAY,livraison.DateDepart,NOW() )>7 )";
    
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, LoginController.us.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        }  
}

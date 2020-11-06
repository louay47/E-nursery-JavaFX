/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MVente.Panier;

import entities.MProduit.Produit;
import entities.MVente.Panier.Panier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author user
 */
public class CrudPanier {

    public List<Panier> listp() {
        List<Panier> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM Panier WHERE IdUser='" + LoginController.us.getId() + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
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
            System.err.println(ex.getMessage());
        }
        return myList;

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

    public int nbproduit() {
        Produit p = new Produit();
        try {
            String requete1 = "SELECT count(id) AS x FROM panier WHERE panier.IdUser=" + LoginController.us.getId() + "";
            Statement st1 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st1.executeQuery(requete1);
            if (rs.next()) {
                return rs.getInt("x");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    
    public void moin(Panier pa, Produit pr, int xxx) {
        String requete1 = "UPDATE panier SET qtpanier = ? WHERE panier.id = ?";
        String requete2 = "UPDATE produit SET quantite = ? WHERE produit.id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete1);
            PreparedStatement pst1 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);

            pst.setInt(1, pa.getQtpanier() - xxx);
            pst.setInt(2, pa.getId());
            pst.executeUpdate();

            pst1.setInt(1, pr.getQuantite() + xxx);
            pst1.setInt(2, pr.getId_produit());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void plus(Panier pa, Produit pr, int xxx) {
        String requete1 = "UPDATE panier SET qtpanier = ? WHERE panier.id = ?";
        String requete2 = "UPDATE produit SET quantite = ? WHERE produit.id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete1);
            PreparedStatement pst1 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);

            pst.setInt(1, pa.getQtpanier() + xxx);
            pst.setInt(2, pa.getId());
            pst.executeUpdate();

            pst1.setInt(1, pr.getQuantite() - xxx);
            pst1.setInt(2, pr.getId_produit());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimer(int id) {

        String requete = "DELETE FROM panier WHERE panier.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimer() {

        String requete = "DELETE FROM panier WHERE panier.IdUser=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, LoginController.us.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Boolean testProdexist(int idprod)
    {
        try {
        
            String requete4="Select * from panier  WHERE panier.IdProduit="+idprod+" and panier.IdUser ="+LoginController.us.getId()+"";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            if(rs.next())
            {
               return true;
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public void ajouter(int id) {

        if(!testProdexist(id))
        {
            String requete = "INSERT INTO panier (valid,IdProduit,IdUser,qtpanier)"
                + " VALUES ('" + 1 + "','" + id + "','" + LoginController.us.getId() + "','" + 1 + "');";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        alert("Panier","produit ajouté au panier avec succes");
        }
        
         else
         {  String req="UPDATE `panier` SET `qtpanier` = `qtpanier`+'1' WHERE `panier`.`id` = 1";
             String requete1 = "UPDATE `panier` SET `qtpanier` = `qtpanier`+'1'  WHERE panier.IdUser = ? and panier.IdProduit=?";
            String requete2 = "UPDATE `produitv SET `quantite` = `quantite` -'1' WHERE produit.id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete1);
            PreparedStatement pst1 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);

            pst.setInt(1, LoginController.us.getId());
            pst.setInt(2, id);
            pst.executeUpdate();

            pst1.setInt(1, id);
            
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             alert("Redondance Détectée","Ce Produit existe déja dans le panier, incrémentation de la quantité par 1 réussie");
         }
        

    }
private void alert(String un,String deux)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
}

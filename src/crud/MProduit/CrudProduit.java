/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MProduit;

import techniques.MyConnection;
import entities.MProduit.Categorie;
import entities.MProduit.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lazzem
 */
public class CrudProduit {

    public void AjouterProduit(Produit p) {
        try {
            String req = "insert into produit (categorie_id,user_id,nom,description,prix,devis_name1,devis_name2,devis_name3,quantite) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps =  MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            ps.setInt(1, p.getId_categorie());
            ps.setInt(2, p.getId_user());
            ps.setString(3, p.getNom());
            ps.setString(4, p.getDescription());
            ps.setFloat(5, p.getPrix());
            ps.setString(6, p.getDevis_name1());
            ps.setString(7, p.getDevis_name2());
            ps.setString(8, p.getDevis_name3());
            ps.setInt(9, p.getQuantite());
            
            System.out.println("Produit ajouté");
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ModifierProduit(Produit p, int id) {
        String requete2 = "UPDATE produit SET categorie_id=? , user_id=?, nom=? , description=?, prix=?, devis_name1=?, devis_name2=?, devis_name3=?,  quantite=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setInt(1, p.getId_categorie());
            pst.setInt(2, p.getId_user());
            pst.setString(3, p.getNom());
            pst.setString(4, p.getDescription());
            pst.setFloat(5, p.getPrix());
            pst.setString(6, p.getDevis_name1());
            pst.setString(7, p.getDevis_name2());
            pst.setString(8, p.getDevis_name3());
            pst.setInt(9, p.getQuantite());
            pst.setInt(10, id);
            pst.executeUpdate();
            System.out.println("Produit modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerProduit(int id) {
        String requete3 = "DELETE FROM produit WHERE id=?";
        try {
            PreparedStatement pst1 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst1.setInt(1, id);
            pst1.executeUpdate();
            System.out.println("Produit supprimé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Produit> AfficherCategorie() {
        List<Produit> list = new ArrayList();
        String requete4 = "SELECT * FROM produit";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                Produit c = new Produit();
                c.setId_produit(rs.getInt("id"));
                c.setId_categorie(rs.getInt("categorie_id"));
                c.setId_user(rs.getInt("user_id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setPrix(rs.getFloat("prix"));
                c.setDevis_name1(rs.getString("devis_name1"));
                c.setDevis_name2(rs.getString("devis_name2"));
                c.setDevis_name3(rs.getString("devis_name3"));
                c.setQuantite(rs.getInt("quantite"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
        public List<Produit> afficherProduitCategorie(String e) {
        List<Produit> list = new ArrayList();
        String requete5 = "SELECT p.* FROM produit p, categorie c WHERE c.id=p.categorie_id AND c.Type=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete5);
            pst.setString(1, e);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit c = new Produit();
                c.setId_produit(rs.getInt("id"));
                c.setId_categorie(rs.getInt("categorie_id"));
                c.setId_user(rs.getInt("user_id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setPrix(rs.getFloat("prix"));
                c.setDevis_name1(rs.getString("devis_name1"));
                c.setDevis_name2(rs.getString("devis_name2"));
                c.setDevis_name3(rs.getString("devis_name3"));
                c.setQuantite(rs.getInt("quantite"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
        
        public List<Produit> afficherProduitUser(int e) {
                    List<Produit> list = new ArrayList();
        String requete5 = "SELECT p.* FROM produit p, fos_user f WHERE f.id=p.user_id AND f.id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete5);
            pst.setInt(1, e);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit c = new Produit();
                c.setId_produit(rs.getInt("id"));
                c.setId_categorie(rs.getInt("categorie_id"));
                c.setId_user(rs.getInt("user_id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setPrix(rs.getFloat("prix"));
                c.setDevis_name1(rs.getString("devis_name1"));
                c.setDevis_name2(rs.getString("devis_name2"));
                c.setDevis_name3(rs.getString("devis_name3"));
                c.setQuantite(rs.getInt("quantite"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
        
        public  Map<Categorie, Integer> getNbrProduitsParCategorie() {
            CrudCategorie cc = new CrudCategorie();
        List<Categorie> categories = cc.AfficherCategorie();
        Map<Categorie, Integer> result = new HashMap<>();
        for (Categorie c : categories) {
            result.put(c, getNbrProduitsParCat(c));
        }
        return result;
    }
        
        public Integer getNbrProduitsParCat(Categorie c) {
        try {
            String query = "SELECT COUNT(*) AS NBR FROM produit WHERE categorie_id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(query);
            pst.setInt(1, c.getId_categorie());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}

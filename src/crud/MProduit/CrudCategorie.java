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
import java.util.List;

/**
 *
 * @author Lazzem
 */
public class CrudCategorie {

    public void AjouterCategorie(Categorie c) {
        String requete = "INSERT INTO categorie (nom,description,type,devis_name,updated_at)"
                + "VALUES ('" + c.getNom() + "','" + c.getDescription() + "','" + c.getType() + "','" + c.getDevis_name() + "',NOW())";

        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ModifierCategorie(Categorie c, int id) {
        String requete2 = "UPDATE categorie SET nom=? , description=?, type=?, devis_name=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getType());
            pst.setString(4, c.getDevis_name());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Categorie modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerCategorie(int id) {
        String requete3 = "DELETE FROM categorie WHERE id=?";
        try {
            PreparedStatement pst1 = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst1.setInt(1, id);
            pst1.executeUpdate();
            System.out.println("Categorie supprimé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Categorie> AfficherCategorie() {
        List<Categorie> list = new ArrayList();
        String requete4 = "SELECT * FROM categorie";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId_categorie(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setType(rs.getString("type"));
                c.setDevis_name(rs.getString("devis_name"));

                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public List<String> AfficheType(){
        List<String> l = new ArrayList();
        
        l.add("Service");
        l.add("Outils");
        l.add("Arbre");
        return l;
    }

    

}

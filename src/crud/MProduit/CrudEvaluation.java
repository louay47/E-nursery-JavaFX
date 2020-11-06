/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MProduit;

import entities.MProduit.Evaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import techniques.MyConnection;

/**
 *
 * @author Lazzem
 */
public class CrudEvaluation {

    Connection connection;
    private static ResultSet r;
    private PreparedStatement ps;

    public CrudEvaluation() {
        connection = MyConnection.getInstance().getCnx();
    }

    public void ajouterEvaluation(Evaluation v) throws SQLException {
        String req = "insert into evaluation (iduser,idprod,note) values (?,?,?)";
        ps = connection.prepareStatement(req);
        ps.setInt(1, v.getIduser());
        ps.setInt(2, v.getIdprod());
        ps.setDouble(3, v.getNote());
        System.out.println("Evaluation ajouté");
        ps.executeUpdate();
    }

    public String getEvalByIduser(int idprod, int iduser) throws SQLException {
        List<Evaluation> listeEvaluation = new ArrayList<>();
        String requete = "select * from evaluation ";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);
            Evaluation f = new Evaluation();
            while (r.next()) {
                f.setIdevaluation(r.getInt(1));
                f.setIduser(r.getInt(2));
                f.setIdprod(r.getInt(3));
                f.setNote(r.getDouble(4));
            }
            if ((f.getIduser() == iduser) && (f.getIdprod() == idprod)) {
                listeEvaluation.add(f);
            }
            System.out.println("recupération de l'id " + listeEvaluation.toString());
            for (Evaluation v : listeEvaluation) {
                if (v.getIdevaluation() != 0) {
                    return "existe";
                } else {
                    return "non";
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
        }
        return null;
    }

    public double moyByName(int id) {
        double sum = 0;
        double moy = 0;
        CrudEvaluation se = new CrudEvaluation();
        List<Evaluation> eqs = new ArrayList<>();
        eqs = se.findAll().stream().filter(e -> e.getIdprod() == id).collect(Collectors.toList());
        for (Evaluation e : eqs) {
            sum = sum + e.getNote();
        }
        return moy = sum / eqs.size();
    }

    public List<Evaluation> findAll() {
        List<Evaluation> liste = new ArrayList<>();
        String requete = "select * from evaluation ";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);
            while (r.next()) {
                Evaluation f = new Evaluation();
                f.setIdevaluation(r.getInt("idevaluation"));
                f.setIduser(r.getInt("iduser"));
                f.setIdprod(r.getInt("idprod"));
                f.setNote(r.getDouble("note"));
                liste.add(f);
            }
            return liste;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public void modifierEvaluation(Evaluation e, int id) {
        try {
            String req;
            req = "UPDATE evaluation SET iduser=?,idprod=?,note=? WHERE idevaluation=?";
            ps = connection.prepareStatement(req);
            ps.setInt(1, e.getIduser());
            ps.setInt(2, e.getIdprod());
            ps.setDouble(3, e.getNote());
            ps.setInt(4, id);
            System.out.println("Evaluation modifier");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ps);
            System.out.println(ex.getMessage());
        }

    }

    public Evaluation findByIdProd(int iduser, int idprod) {
        String req = "select * from evaluation where iduser = ? and idprod=?";
        Evaluation eva = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, iduser);
            ps.setInt(2, idprod);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                eva = new Evaluation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eva;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MService.Reclamation.BE;

import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import techniques.DateConverter;
import techniques.MyConnection;

/**
 *
 * @author mahjoub
 */
public class CrudReclamation {

    public static void AutoUnbanUser() {

        String requete3 = "UPDATE  fos_user AS f set f.enabled=? WHERE f.id "
                + "IN ("
                + "SELECT DISTINCT  fu.id FROM (select * from fos_user) AS fu WHERE  fu.enabled=0 AND ("
                + "      ( (SELECT TIMESTAMPDIFF(DAY,fu.last_login     ,NOW() ) )    >=7      and fu.nb_ban=1 )"
                + "	OR ( (SELECT TIMESTAMPDIFF(DAY,fu.last_login ,NOW() ) )        >=20     and fu.nb_ban=2 )"
                + "   OR ( (SELECT TIMESTAMPDIFF(DAY,fu.last_login ,NOW() ) )        >=30     and fu.nb_ban=3 )"
                + ") )  ";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, 1);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void AutoViderPoubelle() {
        String requete3 = "DELETE FROM reclamation  WHERE reclamation.id  "
                + "IN ("
                + "SELECT DISTINCT  rr.id FROM (select * from reclamation) AS rr WHERE rr.trash=1 AND (SELECT TIMESTAMPDIFF(DAY,rr.$datetrash,NOW() ) ) >= 10"
                + ")";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private List<Reclamation> ListRsearchMethod(String req) {
        List<Reclamation> myList = new ArrayList();
        try {

            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(req);

            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setSubject(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getDate(5));
                p.setType(rs.getString(6));
                p.setUserToClaim(rs.getString(7));
                p.setState(rs.getInt(8));
                p.setImportant(rs.getInt(9));
                p.setTrash(rs.getInt(10));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Reclamation> listerALLReclamation() {

        String requete4 = "Select * from reclamation where reclamation.important=0 and reclamation.trash=0 ORDER BY reclamation.date DESC ";
        return ListRsearchMethod(requete4);
    }

    public List<Reclamation> ListerNonLu() {
        String requete4 = "Select * from reclamation where reclamation.state=0 and reclamation.important=0 and reclamation.trash=0 ORDER BY reclamation.date DESC";
        return ListRsearchMethod(requete4);
    }

    public List<Reclamation> ListerLu() {
        String requete4 = "Select * from reclamation where reclamation.state=1 and reclamation.important=0 and reclamation.trash=0 ORDER BY reclamation.date DESC";
        return ListRsearchMethod(requete4);
    }

    public List<Reclamation> ListerImportant() {
        String requete4 = "Select * from reclamation where  reclamation.important=1 and reclamation.trash=0 ORDER BY reclamation.date DESC";
        return ListRsearchMethod(requete4);
    }

    public List<Reclamation> ListerTrash() {
        String requete4 = "Select * from reclamation where   reclamation.trash=1 ORDER BY reclamation.date DESC";
        return ListRsearchMethod(requete4);
    }

    public void Supprimer(int idReclamation) {
        String requete3 = "DELETE FROM reclamation WHERE reclamation.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, idReclamation);

            pst.executeUpdate();

            System.out.println("Reclamation Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setAsImportant(int idReclamation) {
        String requete3 = "Update  reclamation set reclamation.important=? WHERE reclamation.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, 1);
            pst.setInt(2, idReclamation);

            pst.executeUpdate();

            System.out.println("Reclamation set as important ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendtopoubelle(int idR) {
        String requete3 = "Update  reclamation set reclamation.trash=?, reclamation.$datetrash = NOW()  WHERE reclamation.id=? ";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, 1);

            pst.setInt(2, idR);

            pst.executeUpdate();

            System.out.println("Reclamation sended to trash ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ban(int idU) {
        String requete3 = "Update  fos_user set fos_user.enabled=? WHERE fos_user.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, 0);
            pst.setInt(2, idU);

            pst.executeUpdate();
            alert("Bannir", "Utilisateur est banne");
            System.out.println("User banned");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void alert(String un, String deux) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);
        alert.setContentText(deux);
        alert.show();
    }

    public int nbToutR() {
        int l = 0;
        try {

            String requete4 = "SELECT COUNT(id) AS x FROM `reclamation`  WHERE  reclamation.important=0 and reclamation.trash=0";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l = rs.getInt("x");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public int nbLuR() {
        int l = 0;
        try {

            String requete4 = "SELECT COUNT(id) AS x FROM `reclamation`  WHERE reclamation.state=1 and reclamation.important=0 and reclamation.trash=0";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l = rs.getInt("x");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public int nbNonluR() {
        int l = 0;
        try {

            String requete4 = "SELECT COUNT(id) AS x FROM `reclamation`  WHERE   reclamation.state=0 and reclamation.important=0 and reclamation.trash=0";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l = rs.getInt("x");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public int nbImportR() {
        int l = 0;
        try {

            String requete4 = "SELECT COUNT(id) AS x FROM `reclamation`  WHERE   reclamation.important=1 and reclamation.trash=0";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l = rs.getInt("x");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public int nbPoubelleR() {
        int l = 0;
        try {

            String requete4 = "SELECT COUNT(id) AS x FROM `reclamation`  WHERE  reclamation.trash=1";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l = rs.getInt("x");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public void setReaded(int idR) {
        String requete3 = "Update  reclamation set reclamation.state=? WHERE reclamation.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, 1);
            pst.setInt(2, idR);

            pst.executeUpdate();

            System.out.println("Reclamation As readed ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reclamation> listerReclamationSpe(String nom) {
        String requete4;

        List<Reclamation> myList = new ArrayList();
        try {

            requete4 = "Select * from reclamation where reclamation.userToClaim='" + nom + "'";

            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                Reclamation p = new Reclamation();
//                p.setId(rs.getInt(1));
//                p.setUser_id(rs.getInt(2));
//                p.setSubject(rs.getString(3));
//                p.setDescription(rs.getString(4));
                p.setDate(rs.getDate(5));
//                p.setType(rs.getString(6));
//                p.setUserToClaim(rs.getString(7));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}

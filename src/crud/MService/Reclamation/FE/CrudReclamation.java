/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MService.Reclamation.FE;

import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author mahjoub
 */
public class CrudReclamation {

    public void ajouterReclamation(Reclamation r) {
        String requete = "INSERT INTO reclamation (user_id, subject, description, date, type, userToClaim, state, important, trash, $datetrash)"
                + " VALUES (" + r.getUser_id() + ",'" + r.getSubject() + "','" + r.getDescription() + "','" + DateConverter.Date_To_String(r.getDate()) + "','" + r.getType() + "','" + r.getUserToClaim() + "', '0', '0', '0', NULL);";

        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierReclamation(Reclamation r, int id) {
        String requete2 = "UPDATE reclamation SET subject =?, description =?, userToClaim =? WHERE reclamation.id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, r.getSubject());
            pst.setString(2, r.getDescription());
            pst.setString(3, r.getUserToClaim());
            pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Reclamtion Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierReclamationA(Reclamation r, int id) {
        String requete2 = "UPDATE reclamation SET subject =?, description =? WHERE reclamation.id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, r.getSubject());
            pst.setString(2, r.getDescription());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("Reclamtion Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerReclamation(int id) {
        String requete3 = "DELETE FROM reclamation WHERE reclamation.id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1, id);

            pst.executeUpdate();

            System.out.println("Reclamation Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Reclamation> listerReclamationSpe(String t) {
        String requete4;

        List<Reclamation> myList = new ArrayList();
        try {

            if (t.equals("Tout")) {
                requete4 = "Select * from reclamation where reclamation.user_id="+LoginController.us.getId()+"";
            } else {
                requete4 = "Select * from reclamation where reclamation.user_id="+LoginController.us.getId()+" and reclamation.type='" + t + "'";
            }

            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setSubject(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getDate(5));
                p.setType(rs.getString(6));
                p.setUserToClaim(rs.getString(7));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    

   

}

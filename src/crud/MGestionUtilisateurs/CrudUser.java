/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MGestionUtilisateurs;

import entities.MGestionUtilisateur.User;
import entities.MProduit.Produit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import techniques.BCryptPasswordEncoder;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author Lazzem
 */
public class CrudUser {

    MyConnection ds;
    PreparedStatement ste;

    public CrudUser() {
        ds = MyConnection.getInstance();
    }

    public void ajouterUser(User a) {

        try {
            String request1 = "insert into fos_user (username,username_canonical,email,email_canonical,enabled,password,roles) values(?,?,?,?,?,?,?)";
            PreparedStatement ste = MyConnection.getInstance().getCnx().prepareStatement(request1);
            ste.setString(1, a.getUsername());
            ste.setString(2, a.getUsername());
            ste.setString(3, a.getEmail());
            ste.setString(4, a.getEmail());
            ste.setInt(5, 1);
            ste.setString(6, a.getPassword());
            ste.setString(7, a.getRoles());
            ste.executeUpdate();
            System.out.println("User ajoutÃ©!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changerMdp(User a, int id) {
        String requete2 = "UPDATE fos_user SET password=?  WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, a.getPassword());
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Mot de passe changer avec succÃ©s !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changerMail(User a, int id) {
        String requete2 = "UPDATE fos_user SET email=? , email_canonical=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, a.getEmail());
            pst.setString(2, a.getEmail());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("E-mail changer avec succÃ©s !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateLastLogin(int id) {
        String requete2 = "UPDATE fos_user SET last_login=?  WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, DateConverter.Date_Now_As_String());            
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("date aujoud'hui updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void changerInfo(User a, int id) {
        String requete2 = "UPDATE fos_user SET about=? , phone_number=? , location=? , address=? , job =? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, a.getAbout());
            pst.setInt(2, a.getPhone_number());
            pst.setString(3, a.getLocation());
            pst.setString(4, a.getAddress());
            pst.setString(5, a.getJob());
            pst.setInt(6, id);
            pst.executeUpdate();
            System.out.println("Information changer avec succÃ©s !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User testauthentification(String e, String m) throws SQLException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String request = "select * from fos_user where username=? ";

        PreparedStatement ste = MyConnection.getInstance().getCnx().prepareStatement(request);
        ste.setString(1, e);

        ResultSet rs = ste.executeQuery();
        List<User> list = new ArrayList<>();
        if (!rs.next()) {
            System.out.println("imposiible de se connecter");//changer par affichage notification
        } else if (passwordEncoder.matches(m, rs.getString(8))) {

            User u = new User();
            u.setId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setUsername_canonical(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setEmail_canonical(rs.getString(5));
            u.setRoles(rs.getString(12));
            u.setLast_login(rs.getDate(9));
            u.setNb_ban(rs.getInt(18));
            u.setEnabled(rs.getInt(6));
            u.setPassword(rs.getString(8));
            u.setAbout(rs.getNString(13));
            u.setPhone_number(rs.getInt(14));
            u.setLocation(rs.getString(15));
            u.setAddress(rs.getString(16));
            u.setJob(rs.getString(17));

            System.out.println("ok");

            return u;
        }

        return null;
    }

    public boolean testUsername(String username) {
        List<String> list = new ArrayList();
        String requete4 = "SELECT username FROM fos_user";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (list.contains(username)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean testMail(String mail) {
        List<String> list = new ArrayList();
        String requete4 = "SELECT email FROM fos_user";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (list.contains(mail)) {
            return true;
        } else {
            return false;
        }

    }

    public String testpasswordauthentification(String e) throws SQLException {
        String request = "select password from fos_user where email=? ";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
        pst.setString(1, e);
        ResultSet result = pst.executeQuery();
        if (result.next()) {
            return result.getString("password");
        }
        return "not ok";

    }

    public ArrayList<String> getUsersNameByType(String type) {
        ArrayList<String> l = new ArrayList<>();
        String ty = "";
        if (type.equals("jardinier")) {
            ty = "a:1:{i:0;s:14:\"ROLE_JARDINIER\";}";
        }
        if (type.equals("entreprise")) {
            ty = "a:1:{i:0;s:15:\"ROLE_ENTREPRISE\";}";
        }
        if (type.equals("agriculteur")) {
            ty = "a:1:{i:0;s:16:\"ROLE_AGRICULTEUR\";}";
        }
        if (type.equals("utilisateur")) {
            ty = "a:0:{}";
        }
        try {

            String requete4 = "Select * from fos_user  WHERE fos_user.id !=" + LoginController.us.getId() + " and roles='" + ty + "' ";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                l.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    public User getUserByName(String nom) {
        User u = new User();
        try {

            String requete4 = "Select * from fos_user  WHERE fos_user.username= '" + nom + "' ";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                String role = rs.getString(12);
                u.setLast_login(rs.getDate(9));
                u.setNb_ban(rs.getInt(18));
                u.setEnabled(rs.getInt(6));
                u.setAbout(rs.getNString(13));
                u.setPhone_number(rs.getInt(14));
                u.setLocation(rs.getString(15));
                u.setAddress(rs.getString(16));
                u.setJob(rs.getString(17));

                if (role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    role = "admin";
                }
                if (role.equals("a:1:{i:0;s:14:\"ROLE_JARDINIER\";}")) {
                    role = "jardinier";
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_ENTREPRISE\";}")) {
                    role = "entreprise";
                }
                if (role.equals("a:1:{i:0;s:16:\"ROLE_AGRICULTEUR\";}")) {
                    role = "agriculteur";
                }
                if (role.equals("a:0:{}")) {
                    role = "utilisateur";
                }

                u.setRoles(role);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public User getUserById(int id) {
        User u = new User();
        try {

            String requete4 = "Select * from fos_user  WHERE fos_user.id=" + id + "";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                String role = rs.getString(12);
                u.setLast_login(rs.getDate(9));
                u.setNb_ban(rs.getInt(18));
                u.setEnabled(rs.getInt(6));
                u.setAbout(rs.getNString(13));
                u.setPhone_number(rs.getInt(14));
                u.setLocation(rs.getString(15));
                u.setAddress(rs.getString(16));
                u.setJob(rs.getString(17));

                if (role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    role = "admin";
                }
                if (role.equals("a:1:{i:0;s:14:\"ROLE_JARDINIER\";}")) {
                    role = "jardinier";
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_ENTREPRISE\";}")) {
                    role = "entreprise";
                }
                if (role.equals("a:1:{i:0;s:16:\"ROLE_AGRICULTEUR\";}")) {
                    role = "agriculteur";
                }
                if (role.equals("a:0:{}")) {
                    role = "utilisateur";
                }

                u.setRoles(role);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public List<User> getAllUserExceptAdmin() {
        List<User> list = new ArrayList();
        try {

            String ss = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
            String requete4 = "Select * from fos_user  WHERE fos_user.roles != '" + ss + "' and fos_user.enabled=1 ";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                String role = rs.getString(12);
                u.setLast_login(rs.getDate(9));
                u.setNb_ban(rs.getInt(18));
                u.setEnabled(rs.getInt(6));
                u.setAbout(rs.getNString(13));
                u.setPhone_number(rs.getInt(14));
                u.setLocation(rs.getString(15));
                u.setAddress(rs.getString(16));
                u.setJob(rs.getString(17));

                if (role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    role = "admin";
                }
                if (role.equals("a:1:{i:0;s:14:\"ROLE_JARDINIER\";}")) {
                    role = "jardinier";
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_ENTREPRISE\";}")) {
                    role = "entreprise";
                }
                if (role.equals("a:1:{i:0;s:16:\"ROLE_AGRICULTEUR\";}")) {
                    role = "agriculteur";
                }
                if (role.equals("a:0:{}")) {
                    role = "utilisateur";
                }

                u.setRoles(role);
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<User> getAllBannedUserExceptAdmin() {
        List<User> list = new ArrayList();
        try {

            String ss = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
            String requete4 = "Select * from fos_user  WHERE fos_user.roles != '" + ss + "' and fos_user.enabled=0 ";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                String role = rs.getString(12);
                u.setLast_login(rs.getDate(9));
                u.setNb_ban(rs.getInt(18));
                u.setEnabled(rs.getInt(6));
                u.setAbout(rs.getNString(13));
                u.setPhone_number(rs.getInt(14));
                u.setLocation(rs.getString(15));
                u.setAddress(rs.getString(16));
                u.setJob(rs.getString(17));

                if (role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    role = "admin";
                }
                if (role.equals("a:1:{i:0;s:14:\"ROLE_JARDINIER\";}")) {
                    role = "jardinier";
                }
                if (role.equals("a:1:{i:0;s:15:\"ROLE_ENTREPRISE\";}")) {
                    role = "entreprise";
                }
                if (role.equals("a:1:{i:0;s:16:\"ROLE_AGRICULTEUR\";}")) {
                    role = "agriculteur";
                }
                if (role.equals("a:0:{}")) {
                    role = "utilisateur";
                }

                u.setRoles(role);
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public User getUserProd(Produit prod) {
        User u = new User();
        try {

            String requete4 = "Select * from fos_user  WHERE fos_user.id=" + prod.getId_user() + "";

            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    //    public User authentification(String login, String password) throws SQLException {
//        User u = null;
//        String mdp = "";
//        String request = "select * from fos_user where username=? and password=? ";
//
//        PreparedStatement statment = MyConnection.getInstance().getCnx().prepareStatement(request);
//        ste.setString(1, login);
//        //ste.setString(2, password);
//        ResultSet rs = ste.executeQuery();
//        //if(rs.getFetchSize()>0){
//        while (rs.next()) {
//            u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("nom"), rs.getString("prenom"), rs.getString("roles"));
//            mdp = rs.getString("password");
//            int index = mdp.indexOf("{");
//            if (!mdp.substring(0, index).equals(password)) {
//                u = null;
//            }
//        }
//
//        return u;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MGestionUtilisateur;

import crud.MGestionUtilisateurs.CrudUser;
import entities.MGestionUtilisateur.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import techniques.AutoJob;
import techniques.DateConverter;
import techniques.Notif;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label outputlogin;
    @FXML
    private Button login1;
    CrudUser sb = new CrudUser();
    public static User us;
    @FXML
    private Label mdpoublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void authentification(ActionEvent event) throws IOException, SQLException {
        String e, m;

        e = username.getText();
        m = password.getText();
        User ad = new User();

        ad = sb.testauthentification(e, m);
        if (ad != null) {
            if (ad.getEnabled() == 1) {
                new Notif("Authentification", "Bienvenue chere "+ad.getUsername(), "windows");
                us = ad;
                sb.updateLastLogin(ad.getId());
                ad.setLast_login(DateConverter.Date_Now_As_Date());
                AutoJob.Start();
                if (ad.getRoles().contains("ROLE_ADMIN")) {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeAcceuil.fxml"));
                    Scene scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene);
                    app_stage.show();
                }
                if (ad.getRoles().equals("a:0:{}")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFrontEnd.fxml"));
                    Scene scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene);
                    app_stage.show();
                }
                if (ad.getRoles().contains("ROLE_ENTREPRISE") || ad.getRoles().contains("ROLE_AGRICULTEUR") || ad.getRoles().contains("ROLE_JARDINIER")) {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeOther.fxml"));
                    Scene scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene);
                    app_stage.show();
                }
            }else
            {
                int nbd=0;
                if(ad.getNb_ban()==1)nbd=7-DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), ad.getLast_login());
                if(ad.getNb_ban()==2)nbd=20-DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), ad.getLast_login());
                if(ad.getNb_ban()==3)nbd=30-DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), ad.getLast_login());
                
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Erreur");
                alert2.setHeaderText("bannir");
                alert2.setContentText("Vous ete banni "+ad.getNb_ban()+" fois !!!! \n"
                    + " attent "+nbd+" jour ");
                Optional<ButtonType> result2 = alert2.showAndWait();
            }
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erreur");
            alert2.setHeaderText("Erreur");
            alert2.setContentText("Username or Password incorrect !");
            Optional<ButtonType> result2 = alert2.showAndWait();
        }
    }

    @FXML
    private void inscription(ActionEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Register.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void reset(MouseEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Reset.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();

    }

}

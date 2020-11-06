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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class BackOfficeInfoController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private Button changer;
    @FXML
    private TextField numtf;
    @FXML
    private TextField adresstf;
    @FXML
    private TextField jobtf;
    @FXML
    private ComboBox<String> locationcb;
    @FXML
    private Button retour;
    @FXML
    private TextArea descta;

    int id = LoginController.us.getId();
    User uss = LoginController.us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locationcb.getItems().addAll("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        adresstf.setText(uss.getAddress());
        descta.setText(uss.getAbout());
        numtf.setText(String.valueOf(uss.getPhone_number()));
        jobtf.setText(uss.getJob());
        locationcb.setValue(uss.getLocation());
    }

    @FXML
    private void deco(MouseEvent event) throws IOException, SQLException {
        LoginController.us = null;
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Login.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();

    }

    @FXML
    private void changer(ActionEvent event) throws IOException, SQLException {
//        descta.setText(uss.getAbout());
//        numtf.setText(String.valueOf(uss.getPhone_number()));
//        locationcb.setValue(uss.getLocation());
//        adresstf.setText(uss.getAddress());
//        jobtf.setText(uss.getJob());

        if ((descta.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre status");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (jobtf.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre occupation");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (adresstf.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre adresse");
            Optional<ButtonType> result = alert.showAndWait();
        } else if ((numtf.getText().length()!=8) )  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre numero");
            Optional<ButtonType> result = alert.showAndWait();
        } 
        else if (!(estUnEntier(numtf.getText())) )  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre numero");
            Optional<ButtonType> result = alert.showAndWait();
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment modifier vos informations ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CrudUser cu = new CrudUser();
                System.out.println("Information modifié");
                User u = new User();
                u.setAbout(descta.getText());
                u.setPhone_number(Integer.valueOf(numtf.getText()));
                u.setLocation(locationcb.getValue());
                u.setAddress(adresstf.getText());
                u.setJob(jobtf.getText());
                uss.setAbout(descta.getText());
                uss.setPhone_number(Integer.valueOf(numtf.getText()));
                uss.setLocation(locationcb.getValue());
                uss.setAddress(adresstf.getText());
                uss.setJob(jobtf.getText());
                cu.changerInfo(u, id);
                Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeProfile.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            }
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeProfile.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
        public boolean estUnEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
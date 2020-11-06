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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import techniques.BCryptPasswordEncoder;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class BackOfficeAdresseMailController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private Button changer;
    @FXML
    private PasswordField passactuel;
    @FXML
    private TextField nouvemail;
    @FXML
    private TextField confemail;
    @FXML
    private Button retour;

    int id = LoginController.us.getId();
    User user = LoginController.us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        CrudUser cu = new CrudUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User u = new User();
        u.setEmail(nouvemail.getText());
        if (!(nouvemail.getText().equals(confemail.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("La nouvelle adresse mail et l'adresse mail de confirmation ne sont pas les memes");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(nouvemail.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une adresse valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (nouvemail.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une adresse mail");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(passwordEncoder.matches(passactuel.getText(), user.getPassword()))) {
            System.out.println("pass :" + user.getPassword());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Mot de passe incorrect");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (passactuel.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir votre mot de passe");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cu.testMail(nouvemail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Adresse mail dejat utilisé");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment modifier votre adresse mail ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                cu.changerMail(u, id);
                user.setEmail(nouvemail.getText());
                System.out.println("Adresse mail modifié");
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
}

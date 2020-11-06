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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import techniques.BCryptPasswordEncoder;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class RegisterController implements Initializable {

    @FXML
    private Label outputregister;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confpassword;
    @FXML
    private Button inscription;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll("Utilisateur", "Entreprise", "Jardinier", "Agriculteur", "Administrateur");

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = new Stage();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void inscription(ActionEvent event) throws IOException, SQLException {
        CrudUser cu = new CrudUser();
        if (username.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir un nom d'utilisateur valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(email.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) || (email.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une adresse mail valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cu.testMail(email.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Adresse mail dejat utilisé");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cu.testUsername(username.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Nom d'utilisateur dejat utilisé");
            Optional<ButtonType> result = alert.showAndWait();
        } else if ((password.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir un mot de passe valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(password.getText().equals(confpassword.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Le mot de passe saisie et le mot de passe de confirmation ne sont pas les memes");
            Optional<ButtonType> result = alert.showAndWait();
        } else if ((role.getValue().equals("Role"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner votre role");
            Optional<ButtonType> result = alert.showAndWait();
        } else {


                User u = new User();
                u.setEmail(email.getText());
                
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password.getText());
                
                 u.setPassword(hashedPassword);
                u.setUsername(username.getText());
                if (role.getValue().equals("Administrateur")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                }
                if (role.getValue().equals("Utilisateur")) {
                    u.setRoles("a:0:{}");
                }
                if (role.getValue().equals("Entreprise")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_ENTREPRISE\";}");
                }
                if (role.getValue().equals("Argiculteur")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_AGRICULTEUR\";}");
                }
                if (role.getValue().equals("Jardinier")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_JARDINIER\";}");
                }
                cu.ajouterUser(u);

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Confirmation");
                alert2.setHeaderText("Creation");
                alert2.setContentText("Bienvenue dans notre communauté cher "+username.getText().toUpperCase()+ ",vous pouvez accéder"
                        + "à votre compte dès maintenant ");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.get() == ButtonType.OK) {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Login.fxml"));
                    Scene scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene);
                    app_stage.show();
                }
            
        }
    }

}
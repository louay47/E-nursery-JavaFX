/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.FE;

import crud.MService.Reclamation.FE.CrudReclamation;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import techniques.BadWords;
import techniques.DateConverter;
import techniques.Notification;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class AjouterReclamationAController implements Initializable {

    @FXML
    private TextField Sujet;
    @FXML
    private TextArea Description;
    @FXML
    private Button SubmitButton;
    private CrudReclamation CR = new CrudReclamation();
    @FXML
    private BorderPane RPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML

    private void AjouterReclamation(ActionEvent event) {
        if (Sujet.getText().equals("") || Description.getText().equals("")) {
            alert("champ vide", "il faut remplire tout les champ vide");
        } else if (BadWords.getInstance().existe(Description.getText()) || BadWords.getInstance().existe(Sujet.getText())) {
            alert("mauvais mots", " il est interdit d'utiliser des mauvais mots");
        } else {
            Reclamation r1 = new Reclamation();
            r1.setUser_id(LoginController.us.getId());
            r1.setType("Autre");
            r1.setDescription(Description.getText());
            r1.setSubject(Sujet.getText());
            r1.setUserToClaim("NONE");
            r1.setDate(DateConverter.Date_Now_As_Date());

            CR.ajouterReclamation(r1);
            LooadUI("Reclamation");
            new Notification("Nouveau Suggestion", Sujet.getText(), "NONE");
        }
    }

    private void LooadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/FE/" + ui + ".fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        RPane.setCenter(root);
    }

    private void alert(String un, String deux) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);
        alert.setContentText(deux);
        alert.show();
    }

    @FXML
    private void retourAction(MouseEvent event) {
        LooadUI("Reclamation");
    }
}

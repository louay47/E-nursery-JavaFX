/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.FE;

import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Reclamation.FE.CrudReclamation;
import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import techniques.BadWords;
import techniques.DateConverter;
import techniques.Mail;
import techniques.Notification;
import techniques.SMS;
import techniques.VOICE_RSS;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class AjouterReclamationAJEUController implements Initializable {

    @FXML
    private Button Ajout;
    @FXML
    private TextField Subject;

    @FXML
    private TextField UserToClaim;
    private CrudReclamation CR = new CrudReclamation();
    @FXML
    private TextArea Description;
    @FXML
    private Label Rtype;

    private static String t;
    @FXML
    private BorderPane RPane;
    private CrudUser cu = new CrudUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserToClaim.setEditable(false);
        Rtype.setText(t);
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {

        if (Subject.getText().equals("") || Description.getText().equals("")) {
            alert("champ vide", "il faut remplire tout les champ vide");
        } else if (BadWords.getInstance().existe(Description.getText()) || BadWords.getInstance().existe(Subject.getText())) {
            alert("mauvais mots", " il est interdit d'utiliser des mauvais mots");
        } else {
            Reclamation r1 = new Reclamation();
            r1.setUser_id(LoginController.us.getId());
            r1.setType(Rtype.getText());
            r1.setDescription(Description.getText());
            r1.setSubject(Subject.getText());
            r1.setUserToClaim(UserToClaim.getText());
            r1.setDate(DateConverter.Date_Now_As_Date());

            CR.ajouterReclamation(r1);
            LooadUI("Reclamation");
            new Notification("Nouveau Reclamation", Subject.getText(), UserToClaim.getText());
            String mail = cu.getUserByName(UserToClaim.getText()).getEmail();
            new Mail(mail, "E-nersery", "vous ete reclamer \n\nSujet est : " + Subject.getText());
            SMS.SMS(cu.getUserByName(UserToClaim.getText()).getPhone_number(), "vous ete reclamer \n\n Sujet est : " + Subject.getText());
            new VOICE_RSS("reclamation envoyer", "fr");

        }
    }

    public static void settype(String Rtype) {
        t = Rtype;
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

    @FXML
    private void usertoclaimsearch(MouseEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("List " + t);

        Pane pane = new Pane();
        VBox vbox = new VBox();
        ListView<String> Listviewu = new ListView();
        Listviewu.setPrefSize(300, 200);
        ArrayList<String> alu = cu.getUsersNameByType(t);

        for (int i = 0; i < alu.size(); i++) {
            Listviewu.getItems().add(alu.get(i));

        }

        Button btn = new Button("OK");
        btn.setPrefSize(300, 50);
        btn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int i = 0;
                for (String o : Listviewu.getSelectionModel().getSelectedItems()) {
                    i++;
                }
                if (i == 0) {
                    alert("Aucune Sélection", "Il faut selectionné  un " + t);
                } else {
                    UserToClaim.setText(Listviewu.getSelectionModel().getSelectedItem());
                    primaryStage.close();
                }
            }
        });

        vbox.getChildren().add(Listviewu);
        vbox.getChildren().add(btn);
        pane.getChildren().add(vbox);
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void alert(String un, String deux) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);
        alert.setContentText(deux);
        alert.show();
    }

    @FXML
    private void retouraction(MouseEvent event) {
        LooadUI("Reclamation");
    }
}

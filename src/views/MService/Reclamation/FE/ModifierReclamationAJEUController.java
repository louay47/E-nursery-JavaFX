/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.FE;

import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Reclamation.FE.CrudReclamation;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import techniques.BadWords;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ModifierReclamationAJEUController implements Initializable {

    @FXML
    private Label Rtype;
    @FXML
    private TextField Sujet;
    @FXML
    private TextField NUAreclamer;
    @FXML
    private TextArea Description;
    @FXML
    private Button SubmitButton;
    private static Reclamation reclamation;
    private CrudUser cu = new CrudUser();
    private CrudReclamation CR = new CrudReclamation();
    @FXML
    private BorderPane RPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NUAreclamer.setEditable(false);
        Rtype.setText(reclamation.getType());
        Sujet.setText(reclamation.getSubject());
        NUAreclamer.setText(reclamation.getUserToClaim());
        Description.setText(reclamation.getDescription());
    }

    @FXML
    private void ModifierR(ActionEvent event) {
        if (Sujet.getText().equals("") || Description.getText().equals("")) {
            alert("champ vide", "il faut remplire tout les champ vide");
        } else if (BadWords.getInstance().existe(Description.getText()) || BadWords.getInstance().existe(Sujet.getText())) {
            alert("mauvais mots", " il est interdit d'utiliser des mauvais mots");
        } else {
            reclamation.setDescription(Description.getText());
            reclamation.setSubject(Sujet.getText());
            reclamation.setUserToClaim(NUAreclamer.getText());

            CR.modifierReclamation(reclamation, reclamation.getId());
            LooadUI("AFFANDSUPPReclamation");

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

    public static void setReclamation(Reclamation reclamat) {
        reclamation = reclamat;
    }

    @FXML
    private void usertoclaimsearch(MouseEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("List " + reclamation.getType());

        Pane pane = new Pane();
        VBox vbox = new VBox();
        ListView<String> Listviewu = new ListView();
        Listviewu.setPrefSize(300, 200);
        ArrayList<String> alu = cu.getUsersNameByType(reclamation.getType());

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
                    alert("Aucune Sélection", "Il faut selectionné  un " + reclamation.getType());
                } else {
                    NUAreclamer.setText(Listviewu.getSelectionModel().getSelectedItem());
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
    private void retourimg(MouseEvent event) {
        LooadUI("AFFANDSUPPReclamation");
    }
}

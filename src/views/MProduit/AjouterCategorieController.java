/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MProduit;

import crud.MProduit.CrudCategorie;
import crud.MProduit.CrudProduit;
import entities.MGestionUtilisateur.User;
import entities.MProduit.Categorie;
import entities.MProduit.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private Button img1;
    @FXML
    private Button ajouter;
    @FXML
    private Label cheminimage;

    FileChooser fc1 = new FileChooser();
    File selectedFile1 = new File("");
    @FXML
    private ImageView imageview;
    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private Button ret;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("Bienvenue " + LoginController.us.getUsername().toUpperCase());
        Type.getItems().addAll("Service", "Outils", "Arbre");
        Type.setValue("Service");

    }

    @FXML
    private void image(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc1.setInitialDirectory(new File("C:\\"));
        selectedFile1 = fc1.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile1, dest);

        File newFile1 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile1.getName());

        FileInputStream input1 = new FileInputStream(newFile1);
        Image image1 = new Image(input1);
        cheminimage.setText(newFile1.getName());
        imageview.setImage(image1);
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException, SQLException {

        if ((nom.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir un nom valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (description.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une description valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cheminimage.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner une image");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (Type.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner le type du categorie");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment ajouter cet categorie ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                String nomCategorie = nom.getText();
                String descriptionCategorie = description.getText();
                String typeCategorie = Type.getValue();
                String image1 = cheminimage.getText();

                CrudCategorie cc = new CrudCategorie();
                Categorie c = new Categorie();
                c.setNom(nomCategorie);
                c.setDescription(descriptionCategorie);
                c.setType(typeCategorie);
                c.setDevis_name(image1);
                cc.AjouterCategorie(c);
                Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AfficherCategorie.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            }
        }
    }

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void monprofile(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeProfile.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
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
    private void retour(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AfficherCategorie.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

}

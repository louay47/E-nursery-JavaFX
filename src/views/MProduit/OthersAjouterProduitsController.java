/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MProduit;

import crud.MProduit.CrudCategorie;
import crud.MProduit.CrudProduit;
import entities.MProduit.Categorie;
import entities.MProduit.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
public class OthersAjouterProduitsController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
    @FXML
    private TextField quantite;
    @FXML
    private Button img1;
    @FXML
    private Button img2;
    @FXML
    private Button img3;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField prix;
    @FXML
    private Button ajouter;
    @FXML
    private Label cheminimage1;
    @FXML
    private Label cheminimage2;
    @FXML
    private Label cheminimage3;
    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView imageview2;
    @FXML
    private ImageView imageview3;
    @FXML
    private Label UserName;
    @FXML
    private Text username;
    @FXML
    private Text deco;

    FileChooser fc1 = new FileChooser();
    File selectedFile1 = new File("");
    FileChooser fc2 = new FileChooser();
    File selectedFile2 = new File("");
    FileChooser fc3 = new FileChooser();
    File selectedFile3 = new File("");
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("Bienvenue " + LoginController.us.getUsername().toUpperCase());
        CrudCategorie categorieType = new CrudCategorie();
        List<Categorie> list = categorieType.AfficherCategorie();
        for (Categorie c : list) {
            categorie.getItems().add(c.getNom());
        }
        categorie.setValue("");
    }

    @FXML
    private void ajoutImage1(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc1.setInitialDirectory(new File("C:\\"));
        selectedFile1 = fc1.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile1, dest);

        File newFile1 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile1.getName());

        FileInputStream input1 = new FileInputStream(newFile1);
        Image image1 = new Image(input1);
        cheminimage1.setText(newFile1.getName());
        imageview1.setImage(image1);
    }

    @FXML
    private void ajoutImage2(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc2.setInitialDirectory(new File("C:\\"));
        selectedFile2 = fc2.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile2, dest);

        File newFile2 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile2.getName());

        FileInputStream input2 = new FileInputStream(newFile2);
        Image image2 = new Image(input2);
        cheminimage2.setText(newFile2.getName());
        imageview2.setImage(image2);
    }

    @FXML
    private void ajoutImage3(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc3.setInitialDirectory(new File("C:\\"));
        selectedFile3 = fc3.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile3, dest);

        File newFile3 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile3.getName());

        FileInputStream input3 = new FileInputStream(newFile3);
        Image image3 = new Image(input3);
        cheminimage3.setText(newFile3.getName());
        imageview3.setImage(image3);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException, SQLException {

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
        } else if (cheminimage1.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner une image");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cheminimage2.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner une image");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (cheminimage3.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner une image");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (categorie.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selectionner le type du categorie");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(estUnFloat(prix.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir un prix valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(estUnEntier(quantite.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une quantité valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment ajouter ce produit ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                String nomProduit = nom.getText();
                int idCategorie = 0;
                CrudCategorie categorieType = new CrudCategorie();
                List<Categorie> list = categorieType.AfficherCategorie();
                for (Categorie c : list) {
                    if (c.getNom().equals(categorie.getValue())) {
                        idCategorie = c.getId_categorie();
                    }
                }

                int idUser = LoginController.us.getId();
                String descriptionProduit = description.getText();
                String image1 = cheminimage1.getText();
                String image2 = cheminimage2.getText();
                String image3 = cheminimage3.getText();
                float pri = Float.parseFloat(prix.getText());
                int quant = Integer.parseInt(quantite.getText());

                CrudProduit cp = new CrudProduit();
                Produit p = new Produit();

                p.setId_categorie(idCategorie);
                p.setId_user(idUser);
                p.setNom(nomProduit);
                p.setDescription(descriptionProduit);
                p.setPrix(pri);
                p.setDevis_name1(image1);
                p.setDevis_name2(image2);
                p.setDevis_name3(image3);
                p.setQuantite(quant);
                cp.AjouterProduit(p);

                Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/OthersAfficherProduit.fxml"));
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

    public boolean estUnEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean estUnFloat(String x) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/OthersAfficherProduit.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}

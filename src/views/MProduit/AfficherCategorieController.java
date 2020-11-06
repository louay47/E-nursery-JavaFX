/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MProduit;


import crud.MProduit.CrudCategorie;
import entities.MProduit.Categorie;
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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.TextFields;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField rech;
    @FXML
    private TextArea description;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button image;
    @FXML
    private ImageView imageview;
    @FXML
    private Button retour;
    @FXML
    private TableView<Categorie> tableview;
    @FXML
    private TableColumn<?, ?> id_cat;
    @FXML
    private TableColumn<?, ?> nom_cat;
    @FXML
    private TableColumn<?, ?> type_cat;
    @FXML
    private TableColumn<?, ?> desc_cat;
    private ObservableList<Categorie> data;
    FileChooser fc1 = new FileChooser();
    File selectedFile1 = new File("");
    @FXML
    private Label idE;
    @FXML
    private Label cheminimage1;
    @FXML
    private Text username;
    @FXML
    private Text deco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().addAll("Service", "Outils", "Arbre");

        data = FXCollections.observableArrayList();

        loadDataFromDatabase();
        setsCllTable();
        setcellValue();
        CrudCategorie reclam = new CrudCategorie();
        List<Categorie> cat = reclam.AfficherCategorie();
        List<String> listS = new ArrayList<>();
        listS.add("Service");
        listS.add("Produit");
        TextFields.bindAutoCompletion(rech, listS);
        search();
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AjouterCategorie.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void modifier(ActionEvent event) {

        String id = idE.getText();
        if (id.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionnée");
            alert.setContentText("S'il vous plait selectionner un produit a modifier.");
            alert.showAndWait();
        } else if (description.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir votre description");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Modification");
                alert.setContentText("Vouler vous vraiment modifier cette categorie ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    int idr = Integer.valueOf(idE.getText());
                    String type_cate = type.getValue();
                    String nom_cate = nom.getText();
                    String desc_cate = description.getText();
                    String image1 = cheminimage1.getText();

                    CrudCategorie services = new CrudCategorie();
                    System.out.println("Categorie modifié");
                    Categorie r = new Categorie();

                    System.out.println("errur");
                    r.setId_categorie(Integer.valueOf(idE.getText()));
                    r.setType(type_cate);
                    r.setNom(nom_cate);
                    r.setDescription(desc_cate);
                    r.setDevis_name(image1);

                    services.ModifierCategorie(r,idr);
                                    imageview.setImage(null);
                nom.setText("");
                description.setText("");
                type.setValue("");
                idE.setText("");
                    data.clear();
//                    setsCllTable();
//                data.clear();
                loadDataFromDatabase();
                    System.out.println("ff");
                }
            }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String id = idE.getText();
        if (id.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionnée");
            alert.setContentText("S'il vous plait selectionner une categorie à supprimer.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression");
            alert.setContentText("Vouler vous vraiment supprimer cette categorie ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CrudCategorie cc = new CrudCategorie();

                cc.SupprimerCategorie(Integer.valueOf(id));
                nom.setText("");
                description.setText("");
                imageview.setVisible(false);
                type.setValue("");
                idE.setText("");
                data.clear();
                loadDataFromDatabase();
            }
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeAcceuil.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setsCllTable() {
        id_cat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nom_cat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type_cat.setCellValueFactory(new PropertyValueFactory<>("type"));
        desc_cat.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void loadDataFromDatabase() {
        try {
            CrudCategorie service = new CrudCategorie();
            List<Categorie> rs = service.AfficherCategorie();
            for (Categorie c : rs) {
                Categorie cr = new Categorie();
                cr.setId_categorie(c.getId_categorie());
                cr.setNom(c.getNom());
                cr.setType(c.getType());
                cr.setDescription(c.getDescription());
                cr.setDevis_name(c.getDevis_name());
                data.add(cr);
                System.out.println("Table vien a etais recuperé avec succes !");

            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableview.setItems(data);

    }

    private void setcellValue() {
        tableview.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Categorie p = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                idE.setText(String.valueOf(p.getId_categorie()));
                nom.setText(String.valueOf(p.getNom()));
                type.setValue(p.getType());
                description.setText(p.getDescription());
                cheminimage1.setText(p.getDevis_name());
                    String image1 = cheminimage1.getText();            }
        });

    }

    public void search() {
        FilteredList<Categorie> filterdata = new FilteredList<>(data, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Categorie>) cats -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((cats.getNom().contains(newValue))
                            || (cats.getNom().toLowerCase().contains(newValue))
                            || (cats.getType().contains(newValue))
                            || (cats.getType().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });

            });
            SortedList<Categorie> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sorteddata);
        });
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
        //cheminimage1.setText(newFile1.getName());
        imageview.setImage(image1);
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
    private void Image1() {

        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", cheminimage1.getText());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);

    }
}

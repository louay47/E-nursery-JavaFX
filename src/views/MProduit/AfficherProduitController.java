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
public class AfficherProduitController implements Initializable {

    @FXML
    private Button ajoutproduit;
    @FXML
    private Button retour;
    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button image1;
    @FXML
    private Button image2;
    @FXML
    private Button image3;
    @FXML
    private TextField prix;
    @FXML
    private TextField quant;
    @FXML
    private ImageView imageviewer1;
    @FXML
    private ImageView imageviewer2;
    @FXML
    private ImageView imageviewer3;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField rech;
    private ObservableList<Produit> data;
    FileChooser fc1 = new FileChooser();
    File selectedFile1 = new File("");
    FileChooser fc2 = new FileChooser();
    File selectedFile2 = new File("");
    FileChooser fc3 = new FileChooser();
    File selectedFile3 = new File("");
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<?, ?> id_prod;
    @FXML
    private TableColumn<?, ?> nom_prod;
    @FXML
    private TableColumn<?, ?> prix_prod;
    @FXML
    private TableColumn<?, ?> quant_prod;
    @FXML
    private Label idE;
    @FXML
    private TextArea description;
    int idCategorie;
    String val;
    private TableColumn<?, ?> desc_prod;
    @FXML
    private TableColumn<?, ?> type_prod;
    @FXML
    private ComboBox<String> affcat;
    @FXML
    private TableColumn<Produit, String> desc_prods;
    @FXML
    private Label cheminimage1;
    @FXML
    private Label cheminimage2;
    @FXML
    private Label cheminimage3;
    User us = LoginController.us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("Bienvenue " + LoginController.us.getUsername().toUpperCase());
        CrudCategorie categorieType = new CrudCategorie();
        List<Categorie> list = categorieType.AfficherCategorie();
        for (Categorie c : list) {
            type.getItems().add(c.getNom());
        }
        affcat.getItems().addAll("Service", "Outils", "Arbre");
        data = FXCollections.observableArrayList();

        loadDataFromDatabase("Arbre");
        setsCllTable();
//        setcellValue();
        CrudProduit cp = new CrudProduit();
        List<Produit> cat = cp.AfficherCategorie();
        List<String> listS = new ArrayList<>();
        listS.add("Service");
        listS.add("Arbre");
        listS.add("Outils");
        TextFields.bindAutoCompletion(rech, listS);
        search();
    }

    @FXML
    private void ajoutproduit(ActionEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AjouterProduit.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
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
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void image1(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc1.setInitialDirectory(new File("C:\\"));
        selectedFile1 = fc1.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile1, dest);

        File newFile1 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile1.getName());

        FileInputStream input1 = new FileInputStream(newFile1);
        Image image1 = new Image(input1);
        cheminimage1.setText(newFile1.getName());
        imageviewer1.setImage(image1);
    }

    @FXML
    private void image2(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc2.setInitialDirectory(new File("C:\\"));
        selectedFile2 = fc2.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile2, dest);

        File newFile2 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile2.getName());

        FileInputStream input2 = new FileInputStream(newFile2);
        Image image2 = new Image(input2);
        cheminimage2.setText(newFile2.getName());
        imageviewer2.setImage(image2);
    }

    @FXML
    private void image3(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\");

        fc3.setInitialDirectory(new File("C:\\"));
        selectedFile3 = fc3.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile3, dest);

        File newFile3 = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\" + selectedFile3.getName());

        FileInputStream input3 = new FileInputStream(newFile3);
        Image image3 = new Image(input3);
        cheminimage3.setText(newFile3.getName());
        imageviewer3.setImage(image3);
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
        } //        else if (type.getValue().equals("")) {
        //            Alert alert = new Alert(Alert.AlertType.ERROR);
        //            alert.setTitle("Erreur de Saisie");
        //            alert.setHeaderText("Erreur");
        //            alert.setContentText("Veuillez selectionner le type du categorie");
        //            Optional<ButtonType> result = alert.showAndWait();
        //        } 
        else if (!(estUnFloat(prix.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir un prix valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!(estUnEntier(quant.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez saisir une quantité valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment modifier ce produit ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                int idr = Integer.valueOf(idE.getText());
                String type_cate = type.getValue();
                String nom_prod = nom.getText();
                String desc_prod = description.getText();
                String image1 = cheminimage1.getText();
                String image2 = cheminimage2.getText();
                String image3 = cheminimage3.getText();
                Float pri = Float.parseFloat(prix.getText());
                int quan = Integer.valueOf(quant.getText());

                CrudProduit services = new CrudProduit();
                System.out.println("Produit modifié");
                Produit r = new Produit();

                System.out.println("" + retId(type.getValue()));
                r.setId_produit(Integer.valueOf(idE.getText()));
                //r.setId_categorie(retId(type.getValue()));

                int idCategorie = 0;
                CrudCategorie categorieType = new CrudCategorie();
                List<Categorie> list = categorieType.AfficherCategorie();
                for (Categorie c : list) {
                    if (c.getNom().equals(type.getValue())) {
                        idCategorie = c.getId_categorie();
                    }
                }
                r.setId_categorie(idCategorie);

                r.setId_user(us.getId());
                r.setPrix(pri);
                r.setQuantite(quan);
                r.setNom(nom_prod);
                r.setDescription(desc_prod);
                r.setDevis_name1(image1);
                r.setDevis_name2(image2);
                r.setDevis_name3(image3);

                services.ModifierProduit(r, idr);
                nom.setText("");
                description.setText("");
                type.setValue("");
                prix.setText("");
                quant.setText("");
                idE.setText("");
                data.clear();
                imageviewer1.setImage(null);
                imageviewer2.setImage(null);
                imageviewer3.setImage(null);
//                    setcellValue();
//                data.clear();
                loadDataFromDatabase("Arbre");
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
            alert.setContentText("S'il vous plait selectionner un produit à supprimer.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression");
            alert.setContentText("Vouler vous vraiment supprimer cette categorie ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CrudProduit cc = new CrudProduit();

                cc.SupprimerProduit(Integer.valueOf(id));
                nom.setText("");
                description.setText("");
                imageviewer1.setImage(null);
                imageviewer2.setImage(null);
                imageviewer3.setImage(null);
                type.setValue("");
                prix.setText("");
                quant.setText("");
                idE.setText("");
                data.clear();
                loadDataFromDatabase("Arbre");
            }
        }
    }

    public void search() {
        FilteredList<Produit> filterdata = new FilteredList<>(data, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Produit>) prods -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((prods.getNom().contains(newValue))
                            || (prods.getNom().toLowerCase().contains(newValue))
                            || (prods.getDescription().toLowerCase().contains(newValue))
                            || (prods.getDescription().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sorteddata);
        });
    }

  /*
      private void setcellValue() {
        tableview.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Produit p = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                idE.setText(String.valueOf(p.getId_produit()));
                nom.setText(p.getNom());
                CrudCategorie categorieType = new CrudCategorie();
                List<Categorie> list = categorieType.AfficherCategorie();
                for (Categorie c : list) {
                    if (c.getId_categorie() == p.getId_categorie()) {
                        type.setValue(c.getNom());
                    }
                }
                description.setText(p.getDescription());
                prix.setText(String.valueOf(p.getPrix()));
                quant.setText(String.valueOf(p.getQuantite()));
                cheminimage1.setText(p.getDevis_name1());
                cheminimage2.setText(p.getDevis_name2());
                cheminimage3.setText(p.getDevis_name3());
                
                Image1();
                Image2();
                Image3();
            }
        });

    }
    
    */

    @FXML
    void getvalcombobox() {
        tableview.getItems().clear();
        loadDataFromDatabase(affcat.getValue());
    }

    private void loadDataFromDatabase(String a) {
        try {
            List<Produit> rs = null;
            CrudProduit service = new CrudProduit();
            //System.out.println("aaaaaaa"+affcat.getValue());
            rs = service.afficherProduitCategorie(affcat.getValue());
            for (Produit c : rs) {
                Produit cr = new Produit();
                cr.setId_produit(c.getId_produit());
                cr.setNom(c.getNom());
                cr.setPrix(c.getPrix());
                cr.setQuantite(c.getQuantite());
                cr.setDescription(c.getDescription());
                cr.setId_categorie(c.getId_categorie());
                cr.setDevis_name1(c.getDevis_name1());
                cr.setDevis_name2(c.getDevis_name2());
                cr.setDevis_name3(c.getDevis_name3());
                data.add(cr);
                System.out.println("Table vien a etais recuperé avec succes !");
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableview.setItems(data);
    }

    public void setsCllTable() {
        id_prod.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix_prod.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quant_prod.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        desc_prods.setCellValueFactory(new PropertyValueFactory<>("description"));
        type_prod.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));

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

    public int retId(String a) {
        int n = 0;
        CrudCategorie categorieType = new CrudCategorie();
        List<Categorie> list = categorieType.AfficherCategorie();
        for (Categorie c : list) {
            a.equals(c.getNom());
            n = c.getId_categorie();
        }
        return n;
    }

    public String retNom(int a) {
        String n = null;
        CrudCategorie categorieType = new CrudCategorie();
        List<Categorie> list = categorieType.AfficherCategorie();
        for (Categorie c : list) {
            a = c.getId_categorie();
            n = c.getNom();
        }
        return n;
    }

    private void Image1() {

        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", cheminimage1.getText());
        Image image = new Image(file.toURI().toString());
        imageviewer1.setImage(image);

    }
    private void Image2() {

        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", cheminimage2.getText());
        Image image = new Image(file.toURI().toString());
        imageviewer2.setImage(image);

    }
    private void Image3() {

        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", cheminimage3.getText());
        Image image = new Image(file.toURI().toString());
        imageviewer3.setImage(image);

    }   

    @FXML
    private void clikedtableview(MouseEvent event) {
        Produit p = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                idE.setText(String.valueOf(p.getId_produit()));
                nom.setText(p.getNom());
                CrudCategorie categorieType = new CrudCategorie();
                List<Categorie> list = categorieType.AfficherCategorie();
                for (Categorie c : list) {
                    if (c.getId_categorie() == p.getId_categorie()) {
                        type.setValue(c.getNom());
                    }
                }
                description.setText(p.getDescription());
                prix.setText(String.valueOf(p.getPrix()));
                quant.setText(String.valueOf(p.getQuantite()));
                cheminimage1.setText(p.getDevis_name1());
                cheminimage2.setText(p.getDevis_name2());
                cheminimage3.setText(p.getDevis_name3());
                
                Image1();
                Image2();
                Image3();
    }
}

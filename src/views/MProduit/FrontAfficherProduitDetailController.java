/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MProduit;

import com.jfoenix.controls.JFXButton;
import crud.MProduit.CrudCategorie;
import crud.MProduit.CrudEvaluation;
import crud.MProduit.CrudProduit;
import crud.MService.Wishlist.CrudWishlist;
import crud.MVente.Panier.CrudPanier;
import entities.MGestionUtilisateur.User;
import entities.MProduit.Categorie;
import entities.MProduit.Evaluation;
import entities.MProduit.Produit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class FrontAfficherProduitDetailController implements Initializable {

    @FXML
    private ListView<Produit> listview;
    @FXML
    private Label lbltitre;
    @FXML
    private ImageView lbl_img;
    @FXML
    private TextArea lbldescription;
    @FXML
    private Button evaluer;
    @FXML
    private Label cheminimage;
    @FXML
    private Rating rating;
    @FXML
    private Label id_produit;

    ObservableList<Produit> data;
    @FXML
    private Label lblprix;
    @FXML
    private Label lblquan;
    User us = LoginController.us;
    //User uss = LoginController.us;
    private Rating rating1;
    @FXML
    private JFXButton ajoutaupanierbtn;
    @FXML
    private JFXButton ajoutauwishbtn;
private CrudWishlist CW=new CrudWishlist();
private CrudPanier cp=new CrudPanier();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setcellValue();
        loadDataFromDatabase();
        listview.setCellFactory(lv -> new Poules());
    }

    private void loadDataFromDatabase() {
        try {
            CrudProduit service = new CrudProduit();
            List<Produit> rs = service.AfficherCategorie();
            for (Produit p : rs) {
                Produit r = new Produit();
                r.setId_produit(p.getId_produit());
                r.setNom(p.getNom());
                r.setDescription(p.getDescription());
                r.setDevis_name1(p.getDevis_name1());
                r.setPrix(p.getPrix());
                r.setQuantite(p.getQuantite());
                data.add(r);
                System.out.println("recup table view ok !");
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        listview.setItems(data);
    }

    private void setcellValue() {
        listview.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Produit p = (Produit) listview.getItems().get(listview.getSelectionModel().getSelectedIndex());
            CrudEvaluation crud = new CrudEvaluation();
            lbltitre.setText(p.getNom());
            lbldescription.setText(p.getDescription());
            cheminimage.setText(p.getDevis_name1());
            lblprix.setText(p.getPrix() + " Dinar(s)");
            lblquan.setText(String.valueOf(p.getQuantite()) + " piece(s)");
            id_produit.setText(String.valueOf(p.getId_produit()));
            Evaluation e = crud.findByIdProd(us.getId(),p.getId_produit());
            CrudEvaluation serv = new CrudEvaluation();
            if (e == null) {
                rating.setRating(0);
            } else if (e != null && e.getIduser() == us.getId()) {
                rating.setRating(e.getNote());
            } else {
                rating.setRating(0);
            }
            File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis", cheminimage.getText());
            Image image = new Image(file.toURI().toString());
            lbl_img.setImage(image);
            lbl_img.setFitHeight(250);
            lbl_img.setFitWidth(150);
        });
    }

    @FXML
    private void Evaluer(ActionEvent event) throws SQLException {
        CrudEvaluation serv = new CrudEvaluation();
        String s = "non";
        CrudEvaluation crud = new CrudEvaluation();
        Evaluation e;
        e = crud.findByIdProd(us.getId(),Integer.valueOf(id_produit.getText()));

        System.out.println(e);
        System.out.println(us.getId());
        if (e == null || e.getIduser() != us.getId()) {
            Evaluation v = new Evaluation();
            v.setIdprod(Integer.valueOf(id_produit.getText()));
            v.setIduser(us.getId());
            v.setNote(rating.getRating());
            serv.ajouterEvaluation(v);
            System.out.println(v);
        } else if (e != null && e.getIduser() == us.getId()) {
            e.setNote(rating.getRating());
            serv.modifierEvaluation(e,e.getIdevaluation());
        }

        data.clear();
        loadDataFromDatabase();
    }

    private void Retour(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFrontEnd.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ajoutpanieraction(ActionEvent event) {
        cp.ajouter(Integer.valueOf(id_produit.getText()));
        
    }

    @FXML
    private void ajoutwishaction(ActionEvent event) {
        CW.ajouterWishlist(Integer.valueOf(id_produit.getText()));
        
    }
private void alert(String un,String deux)
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle("ajout");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
    static public class Poules extends ListCell<Produit> {

        public Poules() {

        }

        protected void updateItem(Produit item, boolean bln) {
            super.updateItem(item, bln);

            if (item != null) {
                Text t2 = new Text(item.getNom());
                Text t3 = new Text(item.getPrix() + " dinar(s)");
                Text t4 = new Text(item.getQuantite() + " piece(s)");
                Text t5 = new Text(item.getDescription());
                Rating rat = new Rating();
                CrudEvaluation crud = new CrudEvaluation();
                rat.setRating(crud.moyByName(item.getId_produit()));
                rat.setDisable(true);
                t2.setStyle("-fx-font-size: 35 arial;");
                t3.setStyle("-fx-font-size: 20 arial;");
                VBox vBox = new VBox(t2, t3, t4, t5, rat);
                File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis", item.getDevis_name1());
                Image image = new Image(file.toURI().toString());
                ImageView img = new ImageView(image);
                img.setFitHeight(100);
                img.setFitWidth(80);
                HBox hBox = new HBox(img, vBox);
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
    }

    private void Image() {

        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis", cheminimage.getText());
        Image image = new Image(file.toURI().toString());
        lbl_img.setImage(image);
        lbl_img.setFitHeight(100);
        lbl_img.setFitWidth(80);

    }

}

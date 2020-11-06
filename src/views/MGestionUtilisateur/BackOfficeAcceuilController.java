/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MGestionUtilisateur;

import crud.MProduit.CrudProduit;
import entities.MProduit.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import techniques.Notification;
import views.MService.Reclamation.BE.ReclamationController;


/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class BackOfficeAcceuilController implements Initializable {

    @FXML
    private HBox produit;
    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private HBox users1;
    @FXML
    private HBox categorie;
    @FXML
    private Pane centerpane;
    @FXML
    private HBox reclamationbtn;
    @FXML
    private PieChart statPieChart;
    Map<Categorie, Integer> mapStat = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CrudProduit cp = new CrudProduit();
        mapStat = cp.getNbrProduitsParCategorie();
        int nbrTotal = 0;
        for (Map.Entry<Categorie, Integer> entry : mapStat.entrySet()) {
            Integer value = entry.getValue();
            nbrTotal += value;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Categorie c : mapStat.keySet()) {
            pieChartData.add(new PieChart.Data(c.getNom(), mapStat.get(c)));
        }

        statPieChart.setTitle("Statistiques nombre produit par categorie");
        statPieChart.setClockwise(true);
        statPieChart.setLabelLineLength(50);
        statPieChart.setLabelsVisible(true);
        statPieChart.setStartAngle(180);
        statPieChart.setData(pieChartData);
        pieChartData.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty().intValue(), " Produits"
                        )
                )
        );

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : statPieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    // caption.setText(String.valueOf((data.pieValueProperty().intValue()/nbrTotal)*100));
                }
            });
        }
        username.setText("Bienvenue "+LoginController.us.getUsername().toUpperCase());
    }

    @FXML
    private void categorie(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AfficherCategorie.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void produit(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/AfficherProduit.fxml"));
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
    private void users(MouseEvent event) throws IOException, SQLException {
        ListeUtilisateurController.ctp=centerpane;
        LooadUI("/views/MGestionUtilisateur/ListeUtilisateur.fxml");
       
    }

    @FXML
    private void monprofile(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeProfile.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }


 private  void LooadUI(String ui)
    {
        
        centerpane.getChildren().clear();
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        
        centerpane.getChildren().add(root); 
        
   
       
    }

    @FXML
    private void ReclamationAction(MouseEvent event) {
        
        new Notification(0);
        ReclamationController.HomeBorderrrr=centerpane;
        LooadUI("/views/MService/Reclamation/BE/Reclamation.fxml");
         
        
    }

    @FXML
    private void blogg(MouseEvent event) {
        LooadUI("/views/MCommunication/backOffice/BoAfficheBlog.fxml");
    }

    @FXML
    private void FrontEnd(MouseEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFrontEnd.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackOfficeAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
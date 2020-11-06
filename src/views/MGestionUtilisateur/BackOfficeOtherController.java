/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MGestionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.MMarketing.Evenement.EvenementController;
import views.MMarketing.Promotion.PromotionController;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class BackOfficeOtherController implements Initializable {

    @FXML
    private HBox produit;
    @FXML
    private HBox event;
    @FXML
    private HBox promo;
    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private Pane Panecenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               username.setText("Bienvenue " + LoginController.us.getUsername().toUpperCase());
    }    

    @FXML
    private void home(MouseEvent event) {
                

    }

    @FXML
    private void produit(MouseEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/OthersAfficherProduit.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
private  void LooadUI(String ui)
    {
        
        Panecenter.getChildren().clear();
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        
        Panecenter.getChildren().add(root); 
        
   
       
    }
    @FXML
    private void event(MouseEvent event) {
        EvenementController.HomePane=Panecenter;
         LooadUI("/views/MMarketing/Evenement/Evenement.fxml");
    }


    @FXML
    private void promo(MouseEvent event) {
           PromotionController.HomePane=Panecenter;
         LooadUI("/views/MMarketing/Promotion/Promotion.fxml");
    }



    @FXML
    private void monprofile(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficeProfile.fxml"));
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
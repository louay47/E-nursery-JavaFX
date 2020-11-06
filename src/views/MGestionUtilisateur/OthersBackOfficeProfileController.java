/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MGestionUtilisateur;

import crud.MService.Reclamation.FE.CrudReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.HomeFrontEndController;

/**
 * FXML Controller class
 *
 * @author Lazzem
 */
public class OthersBackOfficeProfileController implements Initializable {

    @FXML
    private Text username;
    @FXML
    private Text deco;
    @FXML
    private Button retour;
    
    public static BorderPane Pane;
    private BorderPane Pane2;
private CrudReclamation CR=new CrudReclamation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               Pane2=Pane;
               username.setText("Bienvenue "+LoginController.us.getUsername().toUpperCase());
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
         if (LoginController.us.getRoles().equals("a:0:{}")) {
        Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFrontEnd.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
         }
         else
         {
             Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeOther.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
         }
    }




   
   
    private void alert(String un,String deux)
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }

    @FXML
    private void mailp(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficeAdresseMail.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            
            
        } catch (IOException ex) {
            Logger.getLogger(OthersBackOfficeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recp(MouseEvent event) {
       
        
        if(CR.listerReclamationSpe("Tout").size()==0) alert("None ", "Il ya aucun Reclamation existe"); 
        else{
            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/FE/AFFANDSUPPReclamation.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeFrontEnd.fxml"));
                Parent root = fxmlLoader.load();
                HomeFrontEndController cc=fxmlLoader.getController();
                
                cc.borderpane.setCenter(root1);
                Scene scene = new Scene(root);
                
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);                
                app_stage.show();
         
            } catch (IOException ex) {
                Logger.getLogger(OthersBackOfficeProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void infop(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficeInfo.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OthersBackOfficeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mdpp(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficePassword.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OthersBackOfficeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

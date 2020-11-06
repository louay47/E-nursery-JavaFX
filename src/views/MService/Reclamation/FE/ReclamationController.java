/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.FE;

import crud.MService.Reclamation.FE.CrudReclamation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.MGestionUtilisateur.OthersBackOfficeProfileController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button RecJardinier;
    @FXML
    private Button RecEntreprise;
    @FXML
    private Button RecAgriculteur;
    @FXML
    private Button RecUtilisateur;
    @FXML
    private Button RecAutre;
    @FXML
    private Button ConsultProfilebtn;
    @FXML
    private  BorderPane RPane;
    public static BorderPane Pane;
    private BorderPane Pane2;
    private CrudReclamation CR=new CrudReclamation();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Pane2=Pane;
    }    

    @FXML
    private void ReclamerJardinier(ActionEvent event) {
        AjouterReclamationAJEUController.settype("jardinier");
        LooadUI("AjouterReclamationAJEU");
    }

    @FXML
    private void ReclamerEntreprise(ActionEvent event) {
        AjouterReclamationAJEUController.settype("entreprise");        
        LooadUI("AjouterReclamationAJEU");
    }

    @FXML
    private void ReclamerAcriculteur(ActionEvent event) {
        AjouterReclamationAJEUController.settype("agriculteur");
        LooadUI("AjouterReclamationAJEU");
    }

    @FXML
    private void ReclamerUtilisateur(ActionEvent event) {
        AjouterReclamationAJEUController.settype("utilisateur");
         LooadUI("AjouterReclamationAJEU");
      
    }

    @FXML
    private void ReclamerAutre(ActionEvent event) {
        LooadUI("AjouterReclamationA");
    }

    @FXML
    private void ConsultProfile(ActionEvent event) {
        
        OthersBackOfficeProfileController.Pane=Pane2;
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficeProfile.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * Parametre ui must be the all  path to this fxml
     * @param ui 
     */
    private  void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/FE/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        RPane.setCenter(root);
    }
}

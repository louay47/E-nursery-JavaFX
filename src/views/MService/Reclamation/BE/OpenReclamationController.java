/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.BE;

import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Reclamation.BE.CrudReclamation;
import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import techniques.VOICE_RSS;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class OpenReclamationController implements Initializable {

    @FXML
    private Label namesenderuser;
    @FXML
    private Label adressmail;
    @FXML
    private Label sujet;
    @FXML
    private TextArea description;
    @FXML
    private Label date;
    @FXML
    private Label typeReclameur;
    @FXML
    private Label nameReclameeduser;
    @FXML
    private Button setAsimportant;
    @FXML
    private Button profileuserreclameur;
    @FXML
    private Button profileuserreclamed;
    
    public static Reclamation R ;
    @FXML
    private BorderPane BPaneHH;
    private CrudReclamation CR = new CrudReclamation();
    private CrudUser cu =new CrudUser();
    @FXML
    private Button readvoicebtn;
    

    /**
     * Initializes the controller class.
     */
    
    private  void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/BE/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        BPaneHH.setCenter(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u=cu.getUserById(R.getUser_id());
        namesenderuser.setText(u.getUsername());
        adressmail.setText(u.getEmail());
        sujet.setText(R.getSubject());
        description.setText(R.getDescription());
        date.setText(R.getDate()+"");
        typeReclameur.setText(u.getRoles());
        nameReclameeduser.setText(R.getUserToClaim());
    }    

    

    @FXML
    private void importantAction(ActionEvent event) {
         CR.setAsImportant(R.getId());
    }

    @FXML
    private void profileReclameurAction(ActionEvent event) {
        ProfileRController.u=cu.getUserById(R.getUser_id());
        LooadUI("profileR");
    }

    @FXML
    private void profileReclamedAction(ActionEvent event) {
        System.out.println(cu.getUserByName(R.getUserToClaim()).getUsername());
        ProfileRController.u=cu.getUserByName(R.getUserToClaim());
        LooadUI("profileR");
    }

    @FXML
    private void readvoiceaction(ActionEvent event) {
        new VOICE_RSS(R.getDescription(), "fr");
    }

    @FXML
    private void backaction(MouseEvent event) {
         LooadUI("Reclamation");
    }
    
}

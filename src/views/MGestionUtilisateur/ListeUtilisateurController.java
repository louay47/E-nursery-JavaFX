/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MGestionUtilisateur;

import crud.MGestionUtilisateurs.CrudUser;
import entities.MGestionUtilisateur.Listuser;
import entities.MGestionUtilisateur.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import views.MService.Reclamation.BE.ProfileRController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ListeUtilisateurController implements Initializable {

    public static Pane ctp;
    public  Pane centerpane;
    @FXML
    private Button userbtn;
    @FXML
    private Button bannebtn;
    @FXML
    private TableView<Listuser> tablevi;
    @FXML
    private Label lbl;
    @FXML
    private TextField search;
    CrudUser cu =new CrudUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       centerpane=ctp;
        alluser();
    
        
    }
    
    private void alluser()
    {
            ObservableList<Listuser> data = FXCollections.observableArrayList();

        lbl.setText("Utilisateurs");
        tablevi.getColumns().clear();
       // tablevi.getItems().clear();
        
         TableColumn id = new TableColumn("id");
        id.setMinWidth(10);
        id.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("id"));
        
        TableColumn nom = new TableColumn("nom");
        nom.setMinWidth(10);
        nom.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("nom"));
        
        TableColumn dernierAcces = new TableColumn("dernierAcces");
        dernierAcces.setMinWidth(10);
        dernierAcces.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("dernierAcces"));
        
        TableColumn status = new TableColumn("status");
        status.setMinWidth(10);
        status.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("status"));
        TableColumn email = new TableColumn("email");
        email.setMinWidth(10);
        email.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("email"));
        
        TableColumn open = new TableColumn();
        open.setMinWidth(10);
        open.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("open"));
        
        
        tablevi.getColumns().addAll(id,nom,dernierAcces,status,email,open);
        
        List<Listuser> RFBO = new ArrayList();
        Label hh;

        for (User x : cu.getAllUserExceptAdmin()) {
            Listuser Rx = new Listuser();

            
            hh = new Label(""+x.getId());
            Rx.setId(hh);
                        
            hh = new Label(""+x.getUsername());
            Rx.setNom(hh);
            
            hh = new Label(""+x.getEmail());
            Rx.setEmail(hh);
            
            hh = new Label(" " +x.getNb_ban()+" ban ");
            if(x.getNb_ban()==0)hh.setStyle("-fx-background-color: #19D22F;");
            if(x.getNb_ban()==1)hh.setStyle("-fx-background-color: #FAA21B;");
            if(x.getNb_ban()==2)hh.setStyle("-fx-background-color: #D10606;");
            hh.setTextFill(Color.web("#ffffff"));
            Rx.setStatus(hh);
            
            hh = new Label(""+x.getLast_login());
            Rx.setDernierAcces(hh);
            
             hh = new Label("Ouvrire");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#322e91"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                        ProfileRController.u=x;
                        LooadUI("profileR");
                }
            });
            Rx.setOpen(hh);
        RFBO.add(Rx);
        }  
        
        for (Listuser x : RFBO) {
           
            data.add(x);
        }
        FilteredList<Listuser> filteredData = new FilteredList<>(data, p -> true);
         search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(User -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (User.getNom().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });
       
        tablevi.setItems(filteredData);
    }
    
    private void allbanned()
    {
            ObservableList<Listuser> data = FXCollections.observableArrayList();

        lbl.setText("Bannée");
        tablevi.getColumns().clear();
       // tablevi.getItems().clear();
        
         TableColumn id = new TableColumn("id");
        id.setMinWidth(10);
        id.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("id"));
        
        TableColumn nom = new TableColumn("nom");
        nom.setMinWidth(10);
        nom.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("nom"));
        
        TableColumn dernierAcces = new TableColumn("dernierAcces");
        dernierAcces.setMinWidth(10);
        dernierAcces.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("dernierAcces"));
        
        TableColumn status = new TableColumn("status");
        status.setMinWidth(10);
        status.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("status"));
        TableColumn email = new TableColumn("email");
        email.setMinWidth(10);
        email.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("email"));
        
        TableColumn open = new TableColumn();
        open.setMinWidth(10);
        open.setCellValueFactory(
                new PropertyValueFactory<Listuser, Label>("open"));
        
        
        tablevi.getColumns().addAll(id,nom,dernierAcces,status,email,open);
        
        List<Listuser> RFBO = new ArrayList();
        Label hh;

        for (User x : cu.getAllBannedUserExceptAdmin()) {
            Listuser Rx = new Listuser();

            
            hh = new Label(""+x.getId());
            Rx.setId(hh);
                        
            hh = new Label(""+x.getUsername());
            Rx.setNom(hh);
            
            hh = new Label(""+x.getEmail());
            Rx.setEmail(hh);
            
            hh = new Label(" bannée ");
            hh.setStyle("-fx-background-color: #38BBF2;");
            Rx.setStatus(hh);
            
            
            hh = new Label(""+x.getLast_login());
            Rx.setDernierAcces(hh);
            
            hh = new Label("Ouvrire");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#322e91"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                        ProfileRController.u=x;
                        LooadUI("profileR");
                }
            });
            
            
            
        RFBO.add(Rx);
        }  
        
        for (Listuser x : RFBO) {
           
            data.add(x);
        }
        FilteredList<Listuser> filteredData = new FilteredList<>(data, p -> true);
         search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(User -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (User.getNom().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });
        
        tablevi.setItems(filteredData);
    }
    
    
    
    @FXML
    private void userAction(ActionEvent event) {
        alluser();
    }

    @FXML
    private void banneaction(ActionEvent event) {
        allbanned();
    }
    private  void LooadUI(String ui)
    {   
        centerpane.getChildren().clear();
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/BE/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        centerpane.getChildren().add(root); 
    }
}

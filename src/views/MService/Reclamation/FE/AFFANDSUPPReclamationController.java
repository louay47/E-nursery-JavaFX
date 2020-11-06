/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.FE;

import crud.MService.Reclamation.FE.CrudReclamation;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class AFFANDSUPPReclamationController implements Initializable {

     private CrudReclamation CR=new CrudReclamation();
    @FXML
    private Button Supprimbtn;
    @FXML
    private TableView<Reclamation> ListReclamation;
    private final ObservableList<Reclamation> data=FXCollections.observableArrayList();
    @FXML
    private Button Modifierbtn;
    @FXML
    private BorderPane RPane;
    @FXML
    private ComboBox<String> TypeRecSearch;
    
    
      
    /**
     * Initializes the controller class.
     */
    @Override
    
    
    
    public void initialize(URL url, ResourceBundle rb) { 
        ObservableList<String> typee=FXCollections.observableArrayList();
        typee.add("Tout");
        typee.add("jardinier");
        typee.add("user");
        typee.add("Autre");
        typee.add("agriculteur");
        typee.add("entreprise");
        TypeRecSearch.setItems(typee);
        TypeRecSearch.setValue("Tout");
        
        
        
         ListReclamation.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        TableColumn Sujet = new TableColumn("SUJET");
        Sujet.setMinWidth(110);
        Sujet.setCellValueFactory(
                new PropertyValueFactory<Reclamation, String>("subject"));
        
        TableColumn RECLAMATIONCONSERNANT = new TableColumn("RECLAMATION CONSERNANT");
        RECLAMATIONCONSERNANT.setMinWidth(225);
        RECLAMATIONCONSERNANT.setCellValueFactory(
                new PropertyValueFactory<Reclamation, String>("type"));
        
        TableColumn UTILISATEURAÉTÉRÉCLAMÉ = new TableColumn("UTILISATEUR RÉCLAMÉ");
        UTILISATEURAÉTÉRÉCLAMÉ.setMinWidth(205);
        UTILISATEURAÉTÉRÉCLAMÉ.setCellValueFactory(
                new PropertyValueFactory<Reclamation, String>("userToClaim"));
        
        TableColumn DATE = new TableColumn("DATE");
        DATE.setMinWidth(100);
        DATE.setCellValueFactory(
                new PropertyValueFactory<Reclamation, Date>("date"));
        
        TableColumn ID = new TableColumn("ID");
        ID.setMinWidth(0);
        ID.setVisible(false);
        ID.setCellValueFactory(
                new PropertyValueFactory<Reclamation, Integer>("id"));               
        ListReclamation.getColumns().addAll(Sujet, RECLAMATIONCONSERNANT,UTILISATEURAÉTÉRÉCLAMÉ,DATE,ID);
         saisietableview("Tout");
         
         
        
        
            
    }    

    private void saisietableview(String t)
    {
        ListReclamation.getItems().clear();
        for ( Reclamation x :CR.listerReclamationSpe(t)) {
                data.add(x);            
        }
         
         ListReclamation.setItems(data);
    }
    
    @FXML
    private void SupprimerR(ActionEvent event) {
        
            if(nbselection() == 0)  alert("Aucune Sélection"  , "Il faut selectionné  au moin une reclamation pour la supprimer      ");
            else
            {
            for(Reclamation o : ListReclamation.getSelectionModel().getSelectedItems()){
                 CR.supprimerReclamation(o.getId());
            }
            ListReclamation.getItems().clear();
            saisietableview(TypeRecSearch.getValue());
            }
    }

    @FXML
    private void ModifierR(ActionEvent event) 
    {
        
        
       if(nbselection() == 0)  alert("Aucune Sélection"  , "Il faut selectionné  une reclamation pour la modifier      ");
       else if(nbselection()>1)alert("Multiple Sélection", "Il faut selectionné une seul  reclamation pour la modifier ");
       else
       {
           Reclamation r=ListReclamation.getSelectionModel().getSelectedItems().get(0);
           if(r.getType().equals("Autre")) 
           {
               ModifierReclamationAController.setReclamation(r);
               LooadUI("ModifierReclamationA");
               
           }
           else
           {
               ModifierReclamationAJEUController.setReclamation(r);
               LooadUI("ModifierReclamationAJEU");
               
           }
           
           
               
           
       
           
       }
       
    }

    private int nbselection()
    {
        int i=0;
       for(Reclamation o : ListReclamation.getSelectionModel().getSelectedItems()){
                 i++;
            }
       return i;
    }
    private void alert(String un,String deux)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
    
    
    private  void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/FE/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        RPane.setCenter(root);
    }

    @FXML
    private void SearchByType(ActionEvent event) {
        
        saisietableview(TypeRecSearch.getValue());
      
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Evenement;

import crud.MMarketing.Evenement.Crud_Evenement;
import entities.MMarketing.Evenement.Evenement;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import techniques.DateConverter;
//import static views.MMarketing.Evenement.ModifierevenementController.EBPane;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class AjoutevenementController implements Initializable {

    static TableView<Evenement> bpanee;

    @FXML
    private TextField nom_evenementt;
    @FXML
    private TextField descriptionn;
    @FXML
    private TextField nbrecado;
    @FXML
    private DatePicker date_debutt;
    @FXML
    private DatePicker date_finn;
    @FXML
    private Button btnajoutEV;
    private Crud_Evenement cpp = new Crud_Evenement();
      public static Pane EBPane ;
       private Pane HomePane ;
      public static Evenement evv ;
    
    @FXML
    private ImageView backkkk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           HomePane=EBPane;
    }    

    @FXML
    private void ajouterEvenement(ActionEvent event) {
        
        
         if(validerChamps()){
         Evenement e = new Evenement();
         
       // now.setValue(DateConverter.String_To_LocaDate(date_debutt.getValue()+""));
         
        e.setDate_debut(DateConverter.String_To_Date(date_debutt.getValue()+""));
        e.setDate_fin(DateConverter.String_To_Date(date_finn.getValue()+""));
     //   p.setDate_fin(DateConverter.String_To_Date(DateConverter.LocalDate_To_String(date_fin.getValue())));
        e.setNom_evenement(nom_evenementt.getText());
        e.setDescription(descriptionn.getText());
        
        e.setNbr_cado(Double.valueOf(nbrecado.getText()));
       
       
        cpp.ajouterEvenement(e);
        LoPage("Evenement");
    }
          
    }
    
        private boolean validerChamps() {
         
  String a=nbrecado.getText();
    String an=nbrecado.getText();
  int sou=Integer.parseInt(an);
   LocalDate mar=date_debutt.getValue();
        LocalDate mar1=date_finn.getValue();
  
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
      
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Erreur de saisie");
        if (nom_evenementt.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le nom du evenement");
            alert.showAndWait();
            return false;
            
        }
        
          else if (sou>50) {
            alert.setContentText(" pourcentage  du promotion est sup 50");
            alert.showAndWait();
            return false;
            
        }
        else if (nbrecado.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le pourcentage  du promotion");
            alert.showAndWait();
            return false;
            
        }
            
        else if (date_debutt.getValue()==null) {
            alert.setContentText("Veuillez Saisir la date de debut du promotion  ");
            alert.showAndWait();
            return false;
            
        }
        else if (date_finn.getValue()==null) {
            alert.setContentText("Veuillez Saisir la date fin du promotion  ");
            alert.showAndWait();
            return false;
            
        }
    
        else if( wa9t(mar1,mar)!=1)
         {
                  alert.setContentText("Veuillez Saisir la date fin du evenement  ");
            alert.showAndWait();
            return false;
            
        }
        
          else if (estUnEntier(a)) {
            alert.setContentText("Veuillez Saisir le un pourcentage !! du promotion");
            alert.showAndWait();
            return false;
            
        }
        
        
        
        return true;
    }
        public boolean estUnEntier(String x) {
		try {

                        Float.parseFloat(x);
                        
		} catch (NumberFormatException e){
			return true;
		}
 
		return false;
	}
        
        private void LoPage(String e)
    {
        HomePane.getChildren().clear();
        Parent root=null;
        try
        {
            root=FXMLLoader.load(getClass().getResource("/views/MMarketing/Evenement/"+e+".fxml"));
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
         HomePane.getChildren().add(root); 
        
    }
     private int wa9t(LocalDate a , LocalDate b)
    {
        int nbre=-1;
       
        
       int year1= a.getYear();
       int month1=a.getMonthValue();
        int jour1=a.getDayOfMonth();
       
            
       int year2= b.getYear();
       int month2=b.getMonthValue();
        int jour2=b.getDayOfMonth();
        
        if(year1>year2)
        {
            return 1;
        }
        else if((year1==year2)&&(month1>month2))
        {
            return 1;
        }
        else if ((year1==year2)&&(month1==month2)&&(jour1>jour2))
        {
            return 1;
        }
        //////////////////////
        
        
      
        else if ((year1==year2)&&(month1==month2)&&(jour1==jour2))
        {
            return 0;
        }
        
        
        
        return nbre;
    }

    @FXML
    private void returnaction(MouseEvent event) {
        
        
        LoPage("Evenement");
        
    }
    
}

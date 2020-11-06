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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import techniques.DateConverter;
import techniques.Mail;
import techniques.Notif;
import views.MGestionUtilisateur.LoginController;
import static views.MMarketing.Promotion.ModifierpromotionController.Promo;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class ModifierevenementController implements Initializable {

    @FXML
    private TextField nom_evene;
    @FXML
    private TextField descriptionnn;
    @FXML
    private TextField bbbb;
    @FXML
    private DatePicker datetoupdate;
    @FXML
    private DatePicker dateffin;
    @FXML
    private Button modifer_Eve;
     private Crud_Evenement cpp = new Crud_Evenement();
    // public static BorderPane EBPane ;
     public static Evenement evv ;
    // private BorderPane EBp ;
    @FXML
    private ImageView backkkk;
     public static Pane EBPane ;
       private Pane HomePane ;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomePane=EBPane;
        nom_evene.setText(evv.getNom_evenement());
        descriptionnn.setText(evv.getDescription());
        bbbb.setText(evv.getNbr_cado()+"");
          datetoupdate.setValue(DateConverter.String_To_LocaDate(DateConverter.Date_To_String(evv.getDate_debut())));
        dateffin.setValue(DateConverter.String_To_LocaDate(DateConverter.Date_To_String(evv.getDate_fin())));
    }    

    @FXML
    private void modifierEvenement(ActionEvent event) {
         Evenement e = new Evenement();
        e.setDescription(String.valueOf(descriptionnn.getText()));
          e.setNbr_cado(Double.valueOf(bbbb.getText()));
         // e.setDate_debut(DateConverter);
         // e.setDate_debut(DateConverter.String_To_Date(""+datetoupdate.getValue()));
           e.setDate_debut(java.sql.Date.valueOf(datetoupdate.getValue()));
           e.setDate_fin(java.sql.Date.valueOf(dateffin.getValue()));
          
//           e.se(dat.valueOf(bbbb.getText()));
          //e.setDate_debut(DatePicker.valueOf(datetoupdate.getValue()));

        String x = nom_evene.getText();
           

       
        cpp.modifierEvenement(e,x);
        new Notif("promotion modifer", "promotion modifier avec succes", "windows");
         new Mail(LoginController.us.getEmail(), "promotions_modfier" , "merci pour votre consultation de nos produits ") ;
        
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
    @FXML
    private void ret(MouseEvent event) {
          LoPage("Evenement");
    }
    
}

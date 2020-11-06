/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Promotion;

import crud.MMarketing.Promotion.Crud_Promotion;
import entities.MMarketing.Promotion.Promotion;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import techniques.DateConverter;
import techniques.Mail;
import techniques.Notif;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class ModifierpromotionController implements Initializable {

    @FXML
    private TextField nom_promotionn;
    @FXML
    private TextField PRIXPROMOO;
    @FXML
    private TextField pourc;
    @FXML
    private DatePicker datedebutttt;
    @FXML
    private DatePicker datefinnpromo;
    @FXML
    private TextField prixdebut;
    @FXML
    private Button modifier_ajout;
    private Crud_Promotion cp = new Crud_Promotion() ;
    
    public static Promotion  Promo;
    @FXML
    private ImageView image;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", cp.getprod(Promo.getProduit_id()).getDevis_name1());
        image.setImage(new Image(file.toURI().toString()));
        
        
        nom_promotionn.setText(Promo.getNom_promotion());
        PRIXPROMOO.setText(Promo.getPrix_promo()+"");
        pourc.setText(Promo.getPourcentage()+"");
        prixdebut.setText(Promo.getPrix_initiale()+"");
        datedebutttt.setValue(DateConverter.String_To_LocaDate(DateConverter.Date_To_String(Promo.getDate_debut())));
        datefinnpromo.setValue(DateConverter.String_To_LocaDate(DateConverter.Date_To_String(Promo.getDate_fin())));
        
    }    

    @FXML
    private void modifier_Promotion(ActionEvent event) {
        
        
        
           Promotion p = new Promotion();
      
        p.setPrix_promo(Double.valueOf(PRIXPROMOO.getText()));
         p.setPourcentage(Double.valueOf(pourc.getText()));
          p.setDate_debut(java.sql.Date.valueOf(datedebutttt.getValue()));
           p.setDate_fin(java.sql.Date.valueOf(datefinnpromo.getValue()));
           // p.setProduit_id(Integer.valueOf(produit_id.getText()));
              p.setPrix_initiale(Double.valueOf(prixdebut.getText()));
            
          
        String x = nom_promotionn.getText();
       
        cp.modifierPromotion(p,x);
         new Notif("promotion modifier", "promotion modifer avec succes", "windows");
         new Mail(LoginController.us.getEmail(), "promotions_modifer" , "merci pour votre consultation de nos produits ") ;
    }
    
}

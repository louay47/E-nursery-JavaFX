/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Promotion;

import crud.MMarketing.Promotion.Crud_Promotion;
import entities.MMarketing.Promotion.Promotion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import techniques.DateConverter;
import static views.MMarketing.Promotion.ModifierpromotionController.Promo;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class Promotion2Controller implements Initializable {

    @FXML
    private Label namePR;
    @FXML
    private Label prix1PR;
    @FXML
    private Label PRIXFIpr;
    @FXML
    private Label DATEFINpr;
    @FXML
    private ImageView IMAGpr;
    public  Promotion p ;
private Crud_Promotion cp = new Crud_Promotion() ;
    @FXML
    private ImageView ggg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
       
         
        
    }    
    
    public void init()
    {
        System.out.println(this.p.getNom_promotion()+"xxxxxxxxx");
         namePR.setText(p.getNom_promotion());
        prix1PR.setText(p.getPrix_initiale()+"");
        PRIXFIpr.setText(p.getPrix_promo()+"");
        DATEFINpr.setText(DateConverter.Date_To_String(p.getDate_fin()));
         IMAGpr.setImage(new Image("/other/img/"+cp.getprod(p.getProduit_id()).getDevis_name1()));
    }

    public void setP(Promotion p) {
        this.p = p;
        
    }
    
}

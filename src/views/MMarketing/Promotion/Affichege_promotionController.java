/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Promotion;

import crud.MMarketing.Promotion.Crud_Promotion;
import entities.MMarketing.Promotion.Promotion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class Affichege_promotionController implements Initializable {

    @FXML
    private VBox vbox_promotions;
    private Crud_Promotion cp = new Crud_Promotion() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for(Promotion p :cp.listerPromotion())
        {
           
                try
                    {
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MMarketing/Promotion/promotion2.fxml"));
                        
                Parent root = loader.load();
                
                Promotion2Controller pc = loader.getController();
                
                pc.setP(p);
                System.out.println(p.getNom_promotion());
                pc.init();
                System.out.println("1");
                vbox_promotions.getChildren().add(root);
                System.out.println("1");
                                                                                                                                       
                    }catch(IOException ex){   System.err.println(ex.getMessage());}
           
        }
        
        
    }    
    
}

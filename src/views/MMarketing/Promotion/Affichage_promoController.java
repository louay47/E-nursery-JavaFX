/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Promotion;

import crud.MMarketing.Promotion.Crud_Promotion;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class Affichage_promoController implements Initializable {

    @FXML
    private VBox produit_promo;
List<String> list= new ArrayList <String>();
 private Crud_Promotion cp = new Crud_Promotion();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         list=cp.esmproduit2();
         int o=list.size();
        int i = 0 ;
       
          HBox item = new HBox();
          
         produit_promo.getChildren().add(item);
         
         for ( i = 0 ; i<o;i++){
             
         int a = cp.esmproduit22(list.get(i));
         String imageee = cp.esmproduit222(a);
         int prixinitilae=cp.esmproduit1(list.get(i));
         
                  int prixfinalee=cp.esmproduit11(list.get(i));
             int Date = cp.datte(list.get(i));
               int pou = cp.pourc(list.get(i));

                 
                 
         
 //list=cp.esmproduit2();


            //item = new HBox();
           // produit_promo.getChildren().add(item);   
              if(i % 3 == 0){
              
               item = new HBox();
          
         produit_promo.getChildren().add(item);
              }
            VBox content = new VBox();
           
            
             
           
         //   a=list.get(i);
                 
           
             System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
           String img="other/img/a2.png";
//             
//         //   float xx=cp.getprix(a);
//          //  float xxx=cp.getprixpro(a);
//            
//          //  float e=((xx-xxx)*100)/xx;
//           // String ee=Float.toString(e);
            //Label promo = new Label("ggg"); 
//             
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", imageee);
        
          ImageView image = new ImageView(new Image(file.toURI().toString()));
//            System.err.println("hamadiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
           Label title = new Label(list.get(i));
    
         
         
            Label prix = new Label(prixinitilae+" TND");  
            //prix.setStyle("-fx-strikethrough: true");
         prix.getStyleClass().add("barre");
         
            Label prixpromo = new Label(prixfinalee+" TND"); 
            Label da = new Label("REDUCTION " +   pou+"%"+ "    jusqua  "+Date);  
           image.setFitHeight(350);
            image.setFitWidth(250);
          
            content.getChildren().addAll(image,title,prix,prixpromo,da);
            Button btn = new Button("",content);
            
            item.getChildren().add(btn);
 
       }
    }    

  
    
}
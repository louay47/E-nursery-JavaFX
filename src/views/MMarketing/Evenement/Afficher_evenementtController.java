/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Evenement;

import crud.MMarketing.Evenement.Crud_Evenement;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class Afficher_evenementtController implements Initializable {

    @FXML
    private VBox evenementtt;
List<String> list= new ArrayList <String>();
private Crud_Evenement cpp = new Crud_Evenement();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       list=cpp.esmproduit2();
         int o=list.size();
        int i = 0 ;
       
          HBox item = new HBox();
          
         evenementtt.getChildren().add(item);
         
         for ( i = 0 ; i<o;i++){
             
//         int a = cp.esmproduit22(list.get(i));
//         String imageee = cp.esmproduit222(a);
//         int prixinitilae=cp.esmproduit1(list.get(i));
//         
                

              String img="other/img/star.png";
            ImageView image = new ImageView(img);
             Label title = new Label(list.get(i));    
                 
           int prixfinaleee=cpp.esmproduit1(list.get(i));
 //list=cp.esmproduit2();
 int Date = cpp.datte(list.get(i));

            //item = new HBox();
           // produit_promo.getChildren().add(item);   
              if(i % 3 == 0){
              
               item = new HBox();
          
         evenementtt.getChildren().add(item);
              }
            VBox content = new VBox();
           
            
             
           
         //   a=list.get(i);
                 
           
//             System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
//           String img="other/img/a2.png";
//             
//         //   float xx=cp.getprix(a);
//          //  float xxx=cp.getprixpro(a);
//            
//          //  float e=((xx-xxx)*100)/xx;
//           // String ee=Float.toString(e);
            //Label promo = new Label("ggg"); 
//             
//          ImageView image = new ImageView("other/img/"+imageee);
//            System.err.println("hamadiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
           Label titlee = new Label(list.get(i));
    
         
         
//            Label prix = new Label(prixinitilae+" TND");  
//            //prix.setStyle("-fx-strikethrough: true");
//         prix.getStyleClass().add("barre");
//         
            Label prixpromo = new Label(prixfinaleee+" Cadeau"); 
             Label da = new Label("    jusqua  "+Date);  
           image.setFitHeight(350);
            image.setFitWidth(250);
          
            content.getChildren().addAll(image,title,prixpromo,da);
            Button btn = new Button("",content);
            
            item.getChildren().add(btn);
 
       }
    }    
}

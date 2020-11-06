/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Wishlist;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Wishlist.CrudWishlist;
import crud.MVente.Panier.CrudPanier;
import entities.MProduit.Produit;
import entities.MService.Wishlist.Wishlist;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import techniques.DateConverter;


/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ListProduitController implements Initializable {

    @FXML
    private ImageView ImageProduit;
    @FXML
    private Label NomProduit;
    @FXML
    private Label dateajoutproduit;
    @FXML
    private Label vendeurproduit;
    
     @FXML
    private JFXButton similarbtn;
   
   
    private CrudWishlist CW=new CrudWishlist();
    private static Wishlist w;
    private static Produit p;
    public static BorderPane HomeBorderrrr;
    private BorderPane HomeBorder;
    private CrudUser cu =new CrudUser();
    @FXML
    private JFXButton supprimebtn;
    @FXML
    private JFXButton ajoutaupanierbtn;
    private CrudPanier cp=new CrudPanier();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomeBorder=HomeBorderrrr;
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", p.getDevis_name1());
        ImageProduit.setImage(new Image(file.toURI().toString()));
        NomProduit.setText(p.getNom());
        dateajoutproduit.setText(DateConverter.Date_To_String(w.getDate()));
        vendeurproduit.setText(cu.getUserProd(p).getUsername());
    }    
    
        

    @FXML
    private void ajoutpanieraction(ActionEvent event) {
         cp.ajouter(Integer.valueOf(p.getId_produit()));
        
    }

    @FXML
    private void similaraction(ActionEvent event) {
           
        if ( CW.listerSimilarWishlist(p).size()== 0) {                        
                           alert("None ", "Il ya aucun produit existe"); 
            }
        else{
            
            SimilarListController.HomeBorderrrr=HomeBorder;
            SimilarListController.setProduitpWishlistw(p);
            LooadUI("SimilarList");
        }
                   
            
        }
    

    @FXML
    private void supprimeraction(ActionEvent event) {
        if(alert("Voulez vous supprimer").get()==ButtonType.OK)
        {
            CW.supprimerWishlist(w.getId());
            LooadUI("Wishlist");
        }
       
    }
    
    
    public static void setProduitpWishlistw(Produit pp,Wishlist ww)
    {
        p=pp;
        w=ww;
    }
   private  void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Wishlist/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        HomeBorder.setCenter(root);
    }
    
   private Optional<ButtonType> alert(String deux)
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Suppression");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }

   private void alert(String un,String deux)
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
   
}
 


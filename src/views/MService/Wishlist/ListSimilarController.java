/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Wishlist;

import com.jfoenix.controls.JFXButton;
import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Wishlist.CrudWishlist;
import entities.MProduit.Produit;
import entities.MService.Wishlist.Wishlist;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ListSimilarController implements Initializable {

    @FXML
    private ImageView ImageProduit;
    @FXML
    private Label NomProduit;
    @FXML
    private Label vendeurproduit;
    
    private CrudWishlist CW=new CrudWishlist();
    private static Wishlist w;
    private static Produit p;
    public static BorderPane HomeBorderrrr;
    private BorderPane HomeBorder;
    private CrudUser cu =new CrudUser();
    @FXML
    private JFXButton ajouterwishlistbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomeBorder=HomeBorderrrr;
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", p.getDevis_name1());
        ImageProduit.setImage(new Image(file.toURI().toString()));
        
        NomProduit.setText(p.getNom());
        vendeurproduit.setText(cu.getUserProd(p).getUsername());
    }    

    @FXML
    private void ajoutbtn(ActionEvent event) {
        CW.ajouterWishlist(p.getId_produit());
        alert("Wishlist","produit ajouter au wishlist avec succes");
        LooadUI("SimilarList",HomeBorder);
        
        
    }
    private void alert(String un,String deux)
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle("ajout");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
    public static void setProduitpSimilarWishlistw(Produit pp,Wishlist ww)
    {
        p=pp;
        w=ww;
    }
    
    private  void LooadUI(String ui,BorderPane x)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Wishlist/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        x.setCenter(root);
    }
    
    
}

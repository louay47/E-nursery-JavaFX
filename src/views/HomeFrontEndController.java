/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import techniques.Notif;
import views.MCommunication.AfficheBlogController;
import views.MGestionUtilisateur.LoginController;
import views.MService.Reclamation.FE.ReclamationController;
import views.MService.Wishlist.WishlistController;
import views.MVente.Livraison.AfficheLivraisonController;
import views.MVente.Panier.AffichePanierController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class HomeFrontEndController implements Initializable {

    @FXML
    private ImageView imgdhash;
    @FXML
    private ImageView imgshop;
    @FXML
    private ImageView imgpromotion;
    @FXML
    private ImageView imgevenement;
    @FXML
    private ImageView imgblog;
    @FXML
    private ImageView imglivraison;
    @FXML
    private ImageView imgavis;
    @FXML
    private ImageView imgmessage;
    @FXML
    private ImageView imgwishlist;
    @FXML
    private ImageView imgpanier;
    @FXML
    private Label dashlbl;
    @FXML
    private Label shoplbl;
    @FXML
    private Label promotionlbl;
    @FXML
    private Label evenementlbl;
    @FXML
    private Label bloglbl;
    @FXML
    private Label livraisonlbl;
    @FXML
    private Label avislbl;
    @FXML
    private Label messagelbl;
    @FXML
    private Label wishlistlbl;
    @FXML
    private Label panierlbl;
    @FXML
    public BorderPane borderpane;
    @FXML
    private Pane panedhash;
    @FXML
    private Pane paneavis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (LoginController.us.getRoles().equals("a:0:{}")) {
            dashlbl.setText("Profile");

        }
        if (LoginController.us.getRoles().contains("ROLE_ADMIN")) {
            paneavis.setVisible(false);

        }
    }

    
    @FXML
    private void dashbordexit(MouseEvent event) {
        imgdhash.setImage(new Image("/other/img/HomeIcon/admin.png"));
    }

    @FXML
    private void dashbordentred(MouseEvent event) {
        imgdhash.setImage(new Image("/other/img/HomeIcon/admin1.png"));

    }

    @FXML
    private void shopexit(MouseEvent event) {
        imgshop.setImage(new Image("/other/img/HomeIcon/shop.png"));
    }

    @FXML
    private void shopentred(MouseEvent event) {
        imgshop.setImage(new Image("/other/img/HomeIcon/shop1.png"));
    }

    @FXML
    private void promotionexit(MouseEvent event) {
        imgpromotion.setImage(new Image("/other/img/HomeIcon/promotion.png"));
    }

    @FXML
    private void promotionentred(MouseEvent event) {
        imgpromotion.setImage(new Image("/other/img/HomeIcon/promotion1.png"));
    }

    @FXML
    private void evenemtionexit(MouseEvent event) {
        imgevenement.setImage(new Image("/other/img/HomeIcon/evenement.png"));
    }

    @FXML
    private void evenemtionentred(MouseEvent event) {
        imgevenement.setImage(new Image("/other/img/HomeIcon/evenement1.png"));
    }

    @FXML
    private void blogexit(MouseEvent event) {
        imgblog.setImage(new Image("/other/img/HomeIcon/blog.png"));
    }

    @FXML
    private void blogentred(MouseEvent event) {
        imgblog.setImage(new Image("/other/img/HomeIcon/blog1.png"));
    }

    @FXML
    private void livraisonexit(MouseEvent event) {
        imglivraison.setImage(new Image("/other/img/HomeIcon/livraison.png"));
    }

    @FXML
    private void livraisonentred(MouseEvent event) {
        imglivraison.setImage(new Image("/other/img/HomeIcon/livraison1.png"));
    }

    @FXML
    private void avisexit(MouseEvent event) {
        imgavis.setImage(new Image("/other/img/HomeIcon/avis.png"));
    }

    @FXML
    private void avisentred(MouseEvent event) {
        imgavis.setImage(new Image("/other/img/HomeIcon/avis1.png"));
    }

    @FXML
    private void messagexit(MouseEvent event) {
        imgmessage.setImage(new Image("/other/img/HomeIcon/message.png"));
    }

    @FXML
    private void messagentred(MouseEvent event) {
        imgmessage.setImage(new Image("/other/img/HomeIcon/message1.png"));
    }

    @FXML
    private void wishlistexit(MouseEvent event) {
        imgwishlist.setImage(new Image("/other/img/HomeIcon/wishlist.png"));
    }

    @FXML
    private void wishlistentred(MouseEvent event) {
        imgwishlist.setImage(new Image("/other/img/HomeIcon/wishlist1.png"));
    }

    @FXML
    private void panierexit(MouseEvent event) {
        imgpanier.setImage(new Image("/other/img/HomeIcon/panier.png"));
    }

    @FXML
    private void panierentred(MouseEvent event) {
        imgpanier.setImage(new Image("/other/img/HomeIcon/panier1.png"));
    }

    @FXML
    private void dashclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#ffffff"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));

        if (LoginController.us.getRoles().contains("ROLE_ADMIN")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeAcceuil.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (LoginController.us.getRoles().equals("a:0:{}")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/OthersBackOfficeProfile.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/BackOfficeOther.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void shopclik(MouseEvent event) {
        try {
            dashlbl.setTextFill(Color.web("#000000"));
            shoplbl.setTextFill(Color.web("#ffffff"));
            promotionlbl.setTextFill(Color.web("#000000"));
            evenementlbl.setTextFill(Color.web("#000000"));
            bloglbl.setTextFill(Color.web("#000000"));
            livraisonlbl.setTextFill(Color.web("#000000"));
            avislbl.setTextFill(Color.web("#000000"));
            messagelbl.setTextFill(Color.web("#000000"));
            wishlistlbl.setTextFill(Color.web("#000000"));
            panierlbl.setTextFill(Color.web("#000000"));
            
            Parent root = FXMLLoader.load(getClass().getResource("/views/MProduit/FrontAfficherProduitDetail.fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void promoclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#ffffff"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));
        LooadUI("/views/MMarketing/Promotion/affichage_promo");
    }

    @FXML
    private void evenementclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#ffffff"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));
        LooadUI("/views/MMarketing/Evenement/afficher_evenementt");
    }

    @FXML
    private void blogclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#ffffff"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));

        AfficheBlogController.homeBorderPane = borderpane;
        System.out.println("here");
        LooadUI("/views/MCommunication/AfficheBlog");

    }

    @FXML
    private void livclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#ffffff"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));
        AfficheLivraisonController.HomeBorderrrr = borderpane;
        LooadUI("/views/MVente/Livraison/AfficheLivraison");
    }

    @FXML
    private void avisclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#ffffff"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));
        ReclamationController.Pane = borderpane;
        LooadUI("/views/MService/Reclamation/FE/Reclamation");
    }

    @FXML
    private void messeclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#ffffff"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#000000"));
        LooadUI("/messenger/resources/views/LoginView");
    }

    @FXML
    private void wishclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#ffffff"));
        panierlbl.setTextFill(Color.web("#000000"));
        WishlistController.HomeBorderrrr = borderpane;
        LooadUI("/views/MService/Wishlist/Wishlist");
    }

    @FXML
    private void panierclik(MouseEvent event) {
        dashlbl.setTextFill(Color.web("#000000"));
        shoplbl.setTextFill(Color.web("#000000"));
        promotionlbl.setTextFill(Color.web("#000000"));
        evenementlbl.setTextFill(Color.web("#000000"));
        bloglbl.setTextFill(Color.web("#000000"));
        livraisonlbl.setTextFill(Color.web("#000000"));
        avislbl.setTextFill(Color.web("#000000"));
        messagelbl.setTextFill(Color.web("#000000"));
        wishlistlbl.setTextFill(Color.web("#000000"));
        panierlbl.setTextFill(Color.web("#ffffff"));

        AffichePanierController.HomeBorderrrr = borderpane;
        LooadUI("/views/MVente/Panier/AffichePanier");
    }

    private void LooadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        borderpane.setCenter(root);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MVente.Livraison;

import crud.MVente.Livraison.CrudLivraison;
import entities.MProduit.Produit;
import entities.MVente.Livraison.Livraison;
import entities.MVente.Livraison.tvlivraison;
import entities.MVente.Panier.Panier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import techniques.DateConverter;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficheLivraisonController implements Initializable {

    private final ObservableList<tvlivraison> data = FXCollections.observableArrayList();
    @FXML
    private TableView<tvlivraison> Tvlivraison;
    private CrudLivraison cl = new CrudLivraison();
    @FXML
    private AnchorPane anchor;
    /**
     * Initializes the controller class.
     */
    public static BorderPane HomeBorderrrr;
    private BorderPane HomeBorder;
     
     
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomeBorder=HomeBorderrrr;
    // ANIMATION HERE
    
    
    TableColumn img = new TableColumn();
    img.setMinWidth(10);
    img.setCellValueFactory(new PropertyValueFactory<tvlivraison,ImageView>("iv"));
        
     TableColumn  nomProduit = new TableColumn("Nom");
     nomProduit.setMinWidth(10);
     nomProduit.setCellValueFactory(new PropertyValueFactory<tvlivraison,Label>("nomprod")); 
     
     TableColumn DateDepart = new TableColumn("Date départ");
     DateDepart.setMinWidth(10);
     DateDepart.setCellValueFactory(new PropertyValueFactory<tvlivraison,Label>("datedepart"));

     TableColumn DateArrive = new TableColumn("Date arrivée");
     DateArrive.setMinWidth(10);
     DateArrive.setCellValueFactory(new PropertyValueFactory<tvlivraison,Label>("datearrive"));

     TableColumn etat = new TableColumn("Etat");
     etat.setMinWidth(10);
     etat.setCellValueFactory(new PropertyValueFactory<tvlivraison,Label>("etat"));

    TableColumn reclame = new TableColumn();
    reclame.setMinWidth(10);
    reclame.setCellValueFactory(new PropertyValueFactory<tvlivraison,Button>("reclamer"));
   
    Tvlivraison.getColumns().addAll(img,nomProduit,DateDepart,DateArrive,etat,reclame);
    
        List<tvlivraison> lp = new ArrayList();
        ImageView i;
        Label lb1;
        Button b; 
  
    for(Livraison x:cl.listp()){
        tvlivraison l = new tvlivraison();
       
        Panier ppp=cl.getpaniertorlist(x.getIdPanier());
    
        Produit pppp=cl.getproduitorlist(x.getIdProduit());    
     
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", pppp.getDevis_name1());
        i = new ImageView(new Image(file.toURI().toString()));
                i.setFitHeight(60);
                i.setFitWidth(60);
        l.setIv(i);
        
        lb1 = new Label(pppp.getNom());
        l.setNomprod(lb1);
        
        lb1 = new Label(ppp.getQtpanier()+"");
        l.setQt(lb1);
        
        lb1 = new Label(DateConverter.Date_To_String(x.getDateDepart())+"");
        l.setDatedepart(lb1);
        
            Date dt = new Date();
            dt.setTime(x.getDateDepart().getTime()+345600000);
        lb1 = new Label(DateConverter.Date_To_String(dt));
        l.setDatearrive(lb1);
        
        if(dt.after(DateConverter.Date_Now_As_Date())){
        lb1 = new Label("Votre Livraison est en chemin");
        l.setEtat(lb1);
        }
        if(dt.before(DateConverter.Date_Now_As_Date())){
        lb1 = new Label("Livraison effectuée");
        l.setEtat(lb1);
        
        b = new Button("Reclamation");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            
                LooadUI("/views/MService/Reclamation/FE/Reclamation");
                cl.reclam();
         }
        });
        l.setReclamer(b);
        }
        lp.add(l);
        }  
    for(tvlivraison x:lp){
        data.add(x);      
        }  
        Tvlivraison.setItems(data);
    }




private void LooadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        HomeBorder.setCenter(root);
    }    
}
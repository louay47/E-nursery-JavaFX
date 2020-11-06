/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Wishlist;


import crud.MService.Wishlist.CrudWishlist;
import entities.MProduit.Produit;
import entities.MService.Wishlist.Wishlist;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class WishlistController implements Initializable {

    @FXML
    private BorderPane WPane;
    @FXML
    private BorderPane insidepane;
    @FXML
    private TableView<Produit> Listview;
    private final ObservableList<Produit> data=FXCollections.observableArrayList();
    private CrudWishlist CR=new CrudWishlist();
    public static BorderPane HomeBorderrrr;
    private BorderPane HomeBorder;
    @FXML
    private TextField SearchFiled;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomeBorder=HomeBorderrrr;
        Listview.setEditable(false);
        
        
        
        TableColumn nom = new TableColumn("Nom Produit");
        nom.setMinWidth(250);
        nom.setCellValueFactory(
                new PropertyValueFactory<Produit, String>("nom"));
        nom.setResizable(false);
        nom.setSortable(false);
        
        Listview.getColumns().addAll( nom);
       
        for ( Wishlist x :CR.listerWishlist()) {                        
                data.add(CR.getproduitforlist(x.getProduit_id()));            
        }
        
        
        //forsearch
         FilteredList<Produit> filteredData = new FilteredList<>(data, p -> true);
         SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });
        
        
         Listview.setItems(filteredData);
         
         
    }    
    
    
    
    @FXML
    private void OpenProduit(MouseEvent event) {
                ListProduitController.HomeBorderrrr=HomeBorder;
                Produit pro=Listview.getSelectionModel().getSelectedItems().get(0);
               ListProduitController.setProduitpWishlistw(pro,CR.getWishProd(pro.getId_produit()));
               LooadUI("ListProduit");
        
    }

    
    
    
    
   
    
    
private  void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Wishlist/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        insidepane.setCenter(root);
    }
    
}

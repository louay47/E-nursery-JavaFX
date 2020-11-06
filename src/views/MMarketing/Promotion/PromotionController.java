/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Promotion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import static com.itextpdf.text.SpecialSymbol.index;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import crud.MMarketing.Promotion.Crud_Promotion;
import entities.MMarketing.Evenement.Evenement;
import entities.MMarketing.Promotion.Promotion;
import entities.MService.Reclamation.Reclamation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import techniques.DateConverter;
import techniques.MyConnection;
import techniques.Notif;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class PromotionController implements Initializable {

    @FXML
    private Button btnajout;
    private Crud_Promotion cp = new Crud_Promotion();

    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<Promotion> zffichePr;
    private final ObservableList<Promotion> data = FXCollections.observableArrayList();
    ObservableList<String> azerty = FXCollections.observableArrayList();
    @FXML
    private BorderPane Bpane;
    public static Pane HomePane;
    @FXML
    private TextField SearchFiled;
    
 private Promotion selectedReservatio;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
init();
    }
        public void init()
        {
            
        zffichePr.getItems().clear();
        

        zffichePr.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn nompromo = new TableColumn("nompromo");
        nompromo.setMinWidth(11);
        nompromo.setCellValueFactory(
                new PropertyValueFactory<Promotion, String>("nom_promotion"));
        
        
        
        
        
        TableColumn idproduit = new TableColumn("ID-Produit");
        idproduit.setMinWidth(10);
        idproduit.setCellValueFactory(
                new PropertyValueFactory<Promotion, Integer>("produit_id"));

        TableColumn datedebut = new TableColumn("DateDebut");
        datedebut.setMinWidth(10);
        datedebut.setCellValueFactory(
                new PropertyValueFactory<Promotion, DateConverter>("date_debut"));

        TableColumn datefin = new TableColumn("DateFin");
        datefin.setMinWidth(10);
        datefin.setCellValueFactory(
                new PropertyValueFactory<Promotion, DateConverter>("date_fin"));

        TableColumn prixinitial = new TableColumn("Prix-Initial");
        prixinitial.setMinWidth(10);
        prixinitial.setCellValueFactory(
                new PropertyValueFactory<Promotion, Double>("prix_initiale"));

        TableColumn prixfinal = new TableColumn("Prix-Finale");
        prixfinal.setMinWidth(10);
        prixfinal.setCellValueFactory(
                new PropertyValueFactory<Promotion, Double>("prix_promo"));

        TableColumn pour = new TableColumn("pourcentage");
        pour.setMinWidth(10);
        pour.setCellValueFactory(
                new PropertyValueFactory<Promotion, Double>("pourcentage"));

        zffichePr.getColumns().addAll(nompromo,datedebut,datefin,prixinitial,prixfinal,pour);
        for (Promotion x : cp.listerPromotion()) {
            data.add(x);
        }
        
         FilteredList<Promotion> filteredData = new FilteredList<>(data, p -> true);
         SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Promotion -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Promotion.getNom_promotion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });    
        
        zffichePr.setItems(filteredData);

        }
    @FXML
    private void supprimer_Promotion(ActionEvent event) {

       
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("supprimer un promotion");
        a1.setContentText("vous voulez vraiment supprimer cet promotion?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            for (Promotion p : zffichePr.getSelectionModel().getSelectedItems()) {
                            System.out.println(p.getNom_promotion());

                cp.supprimerPromotion(p.getNom_promotion());
                new Notif("promotion supprimer", "promotion supprmier avec succes", "windows");
            }

            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("supprimer une Promotion");
            a2.setContentText("Promotion supprimé avec succés");
            a2.show();
        }
        Loh("Promotion");
                    System.out.println("33333333333333333333");

       // Bpane.setCenter(new Pane());
    }

    

    private int nbselection() {
        int i = 0;
        for (Promotion p : zffichePr.getSelectionModel().getSelectedItems()) {
            i++;
        
        }
       
        return i;
    }

    private void afficher_Promotion() {

        zffichePr.getItems().clear();
        for (Promotion x : cp.listerPromotion()) {

            data.add(x);

        }
//forsearch
         FilteredList<Promotion> filteredData = new FilteredList<>(data, p -> true);
         SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Promotion -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Promotion.getNom_promotion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });
        zffichePr.setItems(filteredData);

    }

    @FXML
    private void ajouterPromotion(ActionEvent event) {
        AjoutpromotionController.PBPane=HomePane;
        AjoutpromotionController.Promo=zffichePr.getSelectionModel().getSelectedItem();
        LoPage("Ajoutpromotion");
    }

   

    @FXML
    private void showP(MouseEvent event) {
        ModifierpromotionController.Promo=zffichePr.getSelectionModel().getSelectedItem();
        LoPage("modifierpromotion");
        
    }
    
    private void LoPage(String s)
    {
        Parent root=null;
        try
        {
            root=FXMLLoader.load(getClass().getResource("/views/MMarketing/Promotion/"+s+".fxml"));
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        Bpane.setCenter(root);
    }
    private void Loh(String s)
    {
        HomePane.getChildren().clear();
        Parent root=null;
        try
        {
            root=FXMLLoader.load(getClass().getResource("/views/MMarketing/Promotion/"+s+".fxml"));
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        HomePane.getChildren().add(root); 
    }

    @FXML
    private void selectionner(MouseEvent event) {
        
           Promotion index = zffichePr.getSelectionModel().getSelectedItem();
        selectedReservatio=index; 
    }
    
    @FXML
    private void createPdf(ActionEvent event) throws Exception {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/mahjoub/Desktop/Promotions.pdf"));
        document.open();
        document.add(new Paragraph("LISTE DES PROMOTIONS ", FontFactory.getFont(FontFactory.TIMES)));
      
        /*String date=new Date().toString();*/
        //document.add(new Paragraph(" Date : " + new Date().toString()));
        document.add(new Paragraph("VOICI la liste des promotions selectionner -------------------------------------------------------------------------------"));
       
        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Détails de la promotion"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GREEN);
        //table.addCell(cell);

         
        
        table.addCell("nompromo");
        table.addCell(selectedReservatio.getNom_promotion());
        table.addCell("date_debut");
       table.addCell(selectedReservatio.getDate_debut().toString());
        table.addCell("date_fin");
       table.addCell(selectedReservatio.getDate_fin().toString());
        table.addCell("date_fin");
       
//        table.addCell("pourcentage");
//        table.addCell(selectedReservation.getPourcentage());
        //table.addCell(selectedUser.getNom());
        //System.out.println("*******************"+selectedUser.getNom().toString());
        document.add(table);
        document.close();
        JOptionPane.showMessageDialog(null, " données exportées en pdf evec succés ");
    }

    
    
}

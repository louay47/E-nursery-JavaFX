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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import techniques.DateConverter;
import techniques.Mail;
import techniques.MyConnection;
import techniques.Notif;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class AjoutpromotionController implements Initializable {

    @FXML
    private TextField nomPromotion;
    @FXML
    private TextField pourcentage;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private ComboBox<String> test;
    @FXML
    private Button btnajout;
private Crud_Promotion cp = new Crud_Promotion() ;
 ObservableList<String> azerty =FXCollections.observableArrayList();
 public static Pane PBPane;
    public static Promotion  Promo;
    private Pane HomePane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomePane=PBPane;
        com();
        test.setItems(azerty);
    }    

    @FXML
    private void ajouterPromotion(ActionEvent event) {
           if(validerChamps())
        {
        Promotion p = new Promotion();
        
        int index=test.getSelectionModel().getSelectedIndex()+1;

        p.setDate_debut(DateConverter.String_To_Date(date_debut.getValue()+""));
        p.setDate_fin(DateConverter.String_To_Date(date_fin.getValue()+""));
     //   p.setDate_fin(DateConverter.String_To_Date(DateConverter.LocalDate_To_String(date_fin.getValue())));
        p.setNom_promotion(nomPromotion.getText());
        p.setPourcentage(Double.valueOf(pourcentage.getText()));
      p.setProduit_id(index);
         int k=cp.getprix(index);
        p.setPrix_initiale(k);
        
        double y=(k*p.getPourcentage())/100;
        double finale=k-y;
        
        p.setPrix_promo(finale);
        
        cp.ajouterPromotion(p);
        new Notif("promotion ajouter", "promotion ajouter avec succes", "windows");
         new Mail(LoginController.us.getEmail(), "promotions_ajouter" , "merci pour votre consultation de nos produits ") ;
           LoPage("Promotion");
        }
         
    }
    private void com()
    {
        
        
        
        String requete4="Select nom from produit";    
               
          Statement st2;
        try {
            st2 = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs=st2.executeQuery(requete4);
             
             while(rs.next())
            {
   
                azerty.add(rs.getString(1));
                
            }   
             
             
             
        } catch (SQLException ex) {
            Logger.getLogger(PromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
         private boolean validerChamps() {
            LocalDate mar=date_debut.getValue();
        LocalDate mar1=date_fin.getValue();
  String a=pourcentage.getText();
         String an=pourcentage.getText();
        int sou=Integer.parseInt(an);
        Alert alert = new Alert(Alert.AlertType.WARNING);
      
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Erreur de saisie");
        if (nomPromotion.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le nom du promotion");
            alert.showAndWait();
            return false;
            
        }
        else if (pourcentage.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le pourcentage  du promotion");
            alert.showAndWait();
            return false;
            
        }
       
          else if (sou>100) {
            alert.setContentText(" pourcentage  du promotion est sup 100");
            alert.showAndWait();
            return false;
            
        }
             else if (test.getSelectionModel().getSelectedIndex()+1 <= 0) {
            alert.setContentText("Veuillez Saisir le produit  ");
            alert.showAndWait();
            return false;
            
        }
        else if (date_debut.getValue()==null) {
            alert.setContentText("Veuillez Saisir la date de debut du promotion  ");
            alert.showAndWait();
            return false;
            
        }
        else if (date_fin.getValue()==null) {
            alert.setContentText("Veuillez Saisir la date fin du promotion  ");
            alert.showAndWait();
            return false;
            
        }
        else if( wa9t(mar1,mar)!=1)
         {
                  alert.setContentText("Veuillez Saisir la date fin du promotion  ");
            alert.showAndWait();
            return false;
         }
    
//        else if (date_debut.getValue().compareTo(date_fin.getValue())!= -1) {
//            alert.setContentText("date debut > date fin -> error ");
//            alert.showAndWait();
//            return false;
//            
//        }
        
          else if (estUnEntier(a)) {
            alert.setContentText("Veuillez Saisir le un pourcentage !! du promotion");
            alert.showAndWait();
            return false;
            
        }
        
        
        
        return true;
    }
          public boolean estUnEntier(String x) {
		try {

                        Float.parseFloat(x);
                        
		} catch (NumberFormatException e){
			return true;
		}
 
		return false;
	}
    
    private void LoPage(String s)
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
    
     private int wa9t(LocalDate a , LocalDate b)
    {
        int nbre=-1;
       
        
       int year1= a.getYear();
       int month1=a.getMonthValue();
        int jour1=a.getDayOfMonth();
       
            
       int year2= b.getYear();
       int month2=b.getMonthValue();
        int jour2=b.getDayOfMonth();
        
        if(year1>year2)
        {
            return 1;
        }
        else if((year1==year2)&&(month1>month2))
        {
            return 1;
        }
        else if ((year1==year2)&&(month1==month2)&&(jour1>jour2))
        {
            return 1;
        }
        //////////////////////
        
        
      
        else if ((year1==year2)&&(month1==month2)&&(jour1==jour2))
        {
            return 0;
        }
        
        
        
        return nbre;
    }

    @FXML
    private void retpro(MouseEvent event) {
        LoPage("Promotion");
    }
}

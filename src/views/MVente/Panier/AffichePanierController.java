/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MVente.Panier;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import static com.itextpdf.text.Image.LEFT;
import static com.itextpdf.text.Image.RIGHT;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import crud.MVente.Livraison.CrudLivraison;
import crud.MVente.Panier.CrudPanier;
import entities.MProduit.Produit;
import entities.MVente.Panier.Panier;
import entities.MVente.Panier.listepanier;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.skins.ModernSkin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichePanierController implements Initializable {

    private final ObservableList<listepanier> data = FXCollections.observableArrayList();
    @FXML
    private TableView<listepanier> Listepanier;
    private CrudPanier cp = new CrudPanier();
    private CrudLivraison cl = new CrudLivraison();
    @FXML
    private Button btnvalider;
    public static BorderPane HomeBorderrrr;
    private BorderPane HomeBorder;
    @FXML
    private Pane panenew;
    private Gauge gauge = new Gauge(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gauge.prefHeight(panenew.getHeight());
         gauge.prefWidth(panenew.getWidth());       
         gauge.setSkin(new ModernSkin(gauge));  //ModernSkin : you guys can change the skin
         gauge.setTitle("Du panier est rempli");  //title
         gauge.setUnit("%");  //unit
         gauge.setUnitColor(Color.WHITE);
         gauge.setDecimals(0);  
         
         gauge.setAnimated(true);
         //gauge.setAnimationDuration(500);

         gauge.setValueColor(Color.WHITE);  
         gauge.setTitleColor(Color.WHITE);  
         gauge.setSubTitleColor(Color.WHITE);  
         gauge.setBarColor(Color.rgb(0, 214, 215));  
         gauge.setNeedleColor(Color.RED);  
         gauge.setThresholdColor(Color.RED);  //color will become red if it crosses thereshold value
         gauge.setThreshold(85);
         gauge.setThresholdVisible(true);
         gauge.setTickLabelColor(Color.rgb(151, 151, 151));  
         gauge.setTickMarkColor(Color.WHITE);  
         gauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL);    
         gauge.setValue(cp.nbproduit()*10);
         panenew.getChildren().addAll(gauge);
        
        reload();
    }    
   
    public void reload(){
    Listepanier.getItems().clear();    
    Listepanier.getColumns().clear();
    
    TableColumn img = new TableColumn();
    img.setMinWidth(10);
    img.setCellValueFactory(new PropertyValueFactory<listepanier,ImageView>("iv"));
        
        TableColumn  nomProduit = new TableColumn("Nom");
        nomProduit.setMinWidth(10);
        nomProduit.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("nomprod"));
        
        TableColumn Description = new TableColumn("Description");
        Description.setMinWidth(10);
        Description.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("descriptionprod"));
        
        TableColumn PrixUnité = new TableColumn("PrixUnité");
        PrixUnité.setMinWidth(10);
        PrixUnité.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("prixunité"));
        
        TableColumn Quantité = new TableColumn("Quantité");
        Quantité.setMinWidth(10);
        Quantité.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("quantite"));

        TableColumn plus = new TableColumn();
        plus.setMinWidth(10);
        plus.setCellValueFactory(new PropertyValueFactory<listepanier,Button>("plus"));

        TableColumn moin = new TableColumn();
        moin.setMinWidth(10);
        moin.setCellValueFactory(new PropertyValueFactory<listepanier,Button>("moin"));

        TableColumn supprimer = new TableColumn("supprimer");
        supprimer.setMinWidth(10);
        supprimer.setCellValueFactory(new PropertyValueFactory<listepanier,Button>("supprimer"));

        Listepanier.getColumns().addAll(img,nomProduit,Description,PrixUnité,moin,Quantité,plus,supprimer);
        List<listepanier> lp = new ArrayList();
        ImageView i;
        Label lb1;
        Button b;

        HomeBorder=HomeBorderrrr;
        for(Panier x:cp.listp()){
        listepanier l = new listepanier();
        Produit ppp=cp.getproduitorlist(x.getIdProduit());
               
        
        File file = new File("G:\\xamppp\\htdocs\\PIDEV\\WEB\\PIDEV\\web\\devis\\", ppp.getDevis_name1());
        i = new ImageView(new Image(file.toURI().toString()));
                i.setFitHeight(30);
                i.setFitWidth(30);
        l.setIv(i);
        lb1 = new Label(ppp.getNom());
        l.setNomprod(lb1);
        
        lb1 = new Label(ppp.getDescription());
        l.setDescriptionprod(lb1);
        
        lb1 = new Label(ppp.getPrix()+"");
        l.setPrixunité(lb1);
            
        lb1 = new Label(String.valueOf(x.getQtpanier()));
        l.setQuantite(lb1);
        
        b = new Button("+");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage sta=new Stage();
                Pane p = new Pane();
                VBox vb =new VBox();
                TextField tfnb=new TextField();
                tfnb.setPromptText("ecrirelenombreduproduiticiyamnayek");
                Button btn=new Button("confirmationation");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        cp.plus(x, ppp,Integer.valueOf(tfnb.getText()));
                        sta.close();
                        
                    }
                });
                vb.getChildren().addAll(tfnb,btn);
                p.getChildren().add(vb);
                Scene scs=new Scene(p);
                
                sta.setScene(scs);
                sta.showAndWait();
            
        reload();    }
        });
        l.setPlus(b);
        
        b = new Button("-");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            Stage sta=new Stage();
                Pane p = new Pane();
                VBox vb =new VBox();
                TextField tfnb=new TextField();
                tfnb.setPromptText("Combien ?");
                Button btn=new Button("confirmer");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       
                        cp.moin(x, ppp,Integer.valueOf(tfnb.getText()));
                        
                        sta.close();
                        
                    }
                });
                btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
               
                    btn.setStyle("-fx-background-color : #000000");
                    
                    
                }
            });
                btn.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                                        btn.setStyle("-fx-background-color : #ffffff");
                  

                }
            });
                vb.getChildren().addAll(tfnb,btn);
                p.getChildren().add(vb);
                Scene scs=new Scene(p);
                
                sta.setScene(scs);
                sta.showAndWait();
                
                
        reload();    }
        });
        l.setMoin(b);
        
        b = new Button("Supprimer");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            cp.supprimer(x.getId());
        reload();    }
        });
        l.setSupprimer(b);
        lp.add(l);
        }  
        for(listepanier x:lp){
        data.add(x);     
        }  
        Listepanier.setItems(data);}

     private void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        HomeBorder.setCenter(root);
    }
    
    @FXML
    private void validaction(ActionEvent event) {
    
        boolean xxx=true;
        for(Panier px : cp.listp())
        {  
            if(cp.getproduitorlist(px.getId()).getQuantite()<px.getQtpanier()) 
            {
                xxx=false;
            }
        }
        
        if(xxx)
        {
        cl.ajouterLivraison();
        
     try{
            
            String file_name="C:\\Users\\mahjoub\\Desktop\\generate_pdf\\Facture.pdf";
            Document document=new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
            MyConnection obJDBConnection=MyConnection.getInstance();
            Connection connection= obJDBConnection.getCnx();
            PreparedStatement ps=null;
            ResultSet rs=null;
            PreparedStatement ps1=null;
            ResultSet rs1=null;
            
            com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("G:\\xamppp\\htdocs\\PIDEV\\JAVA\\PIDEV\\src\\other\\img\\logo1.png");
            image1.scaleAbsolute(150f, 150f);

       image1.setAlignment(Element.ALIGN_CENTER);
        document.add(image1);
            
            
            String query="SELECT * FROM panier WHERE IdUser=?";
            ps=connection.prepareStatement(query);
            ps.setInt(1, LoginController.us.getId());
            rs=ps.executeQuery();
            
            String query1="SELECT * FROM produit WHERE produit.id IN (SELECT IdProduit From panier WHERE IdUser=?)";
            ps1=connection.prepareStatement(query1);
            ps1.setInt(1,LoginController.us.getId());
            rs1=ps1.executeQuery();
            List<Produit> lp= new ArrayList();
            
            PdfPTable table = new PdfPTable(3); // 3 columns.
Font font2 = new Font(Font.FontFamily.HELVETICA  , 15, Font.BOLD);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Nom",font2));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Quantité",font2));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Prix",font2));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            document.add(table);
            
            while (rs1.next()){
              Produit p = new Produit();
              p.setId_produit(rs1.getInt(1));
              p.setNom(rs1.getString(3));
              p.setPrix(rs1.getFloat(5));
              lp.add(p);
                /*Paragraph para=new Paragraph(rs1.getString("nom"));
               
                document.add(para);
                document.add(new Paragraph(" "));
            */}
              Double b=0d;
            while (rs.next()){
                Produit w=null;
                for(Produit x:lp){
                    if(x.getId_produit()==rs.getInt(4))
                        w=x;
                }
                     PdfPTable table1 = new PdfPTable(3); 
               PdfPCell cell4 = new PdfPCell(new Paragraph(w.getNom()+" "));
               PdfPCell cell5 = new PdfPCell(new Paragraph(rs.getInt(2)+" "));
               PdfPCell cell6 = new PdfPCell(new Paragraph((rs.getInt(2)*w.getPrix()+" ")));
               b+=rs.getInt(2)*w.getPrix(); 
               table1.addCell(cell4); 
               table1.addCell(cell5); 
               table1.addCell(cell6); 
               document.add(table1);
                //document.add(new Paragraph(" "));
            }
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 20, Font.BOLD);
            
            PdfPTable table2 = new PdfPTable(3); 
            PdfPCell cell7= new PdfPCell(new Paragraph("Total ",font1));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell8= new PdfPCell(new Paragraph(""));
            PdfPCell cell9= new PdfPCell(new Paragraph(b+""));
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2.addCell(cell7);
            //cell7.setRowspan(2);
            
            table2.addCell(cell8);  
            table2.addCell(cell9);  
            float[] columnWidths = {2f, 0f, 1f};

table2.setWidths(columnWidths);
            document.add(table2);
            
            Paragraph pp = new Paragraph("Merci pour votre achat !");
            pp.setAlignment(Element.ALIGN_CENTER);
            document.add(pp);
            
            document.close();
            
            System.out.println("finished");
        }
        catch(Exception e){
            System.err.println(e);
        }
       

     LooadUI("/views/MVente/Facture/AfficheFacture");
     cp.supprimer();
    //  reload();
        }
        else
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
al.setTitle("OUT OF STOCK !");
al.setContentText("Vous avez un produit qui n'est plus en stock, veuillez vérifier votre panier.Merci!");
al.show();
        }
    }
    public void ShowCitron(){
    LooadUI("/views/MVente/Facture/AfficheCitron");}
    
    
    
    
    
    
}

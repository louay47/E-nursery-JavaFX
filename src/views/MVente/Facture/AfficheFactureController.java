/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MVente.Facture;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import crud.MVente.Panier.CrudPanier;
import entities.MGestionUtilisateur.User;
import entities.MProduit.Produit;
import entities.MVente.Panier.Panier;
import entities.MVente.Panier.listepanier;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.html.CSS;
import techniques.MyConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficheFactureController implements Initializable {
     private final ObservableList<listepanier> data = FXCollections.observableArrayList();
    @FXML
    private TableView<listepanier> tvFacture;
    private TableView<listepanier> tvFacture2;
    private CrudPanier cp = new CrudPanier();
    private int qt;
    private double tot=0;
    @FXML
    private Label labtot;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TableColumn  nomProduit = new TableColumn("Nom");
        nomProduit.setMinWidth(10);
        
        nomProduit.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("nomprod"));
        
        TableColumn Quantité = new TableColumn("Quantité");
        Quantité.setMinWidth(10);
        
        Quantité.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("quantite"));
        
        
        TableColumn Prix = new TableColumn("Prix");
        Prix.setMinWidth(10);
        
        Prix.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("prixunité"));
        
        TableColumn total = new TableColumn("total");
        Prix.setMinWidth(10);
        Prix.setCellValueFactory(new PropertyValueFactory<listepanier,Label>("prixunité"));
        
        tvFacture.getColumns().addAll(nomProduit,Quantité,Prix);
        
         List<listepanier> ll = new ArrayList();
        Label hh;
         for (Panier x : cp.listp()) {
            listepanier Rx = new listepanier();

            Produit ppp=cp.getproduitorlist(x.getIdProduit());
            
            hh = new Label(""+ppp.getNom());
            Rx.setNomprod(hh);
                        
            hh = new Label(""+x.getQtpanier());
            Rx.setQuantite(hh);
            
            hh = new Label(""+ppp.getPrix()*x.getQtpanier());
            Rx.setPrixunité(hh);
            tot=tot+(ppp.getPrix()*x.getQtpanier());
           ll.add(Rx);
           };
        
        labtot.setText("Total : "+tot);
        

        for(listepanier x:ll){
        data.add(x);     
        }  
        tvFacture.setItems(data);
    
        

    
        

    }
   /*  @FXML
    public void Generate_PDF_Dynamic() {
    
        try{
            
            String file_name="C:\\Users\\user\\Desktop\\generate_pdf\\Facture.pdf";
            Document document=new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
            MyConnection obJDBConnection=new MyConnection();
            Connection connection= obJDBConnection.getCnx();
            PreparedStatement ps=null;
            ResultSet rs=null;
            PreparedStatement ps1=null;
            ResultSet rs1=null;
            
            Image image1 = Image.getInstance("C:\\wamp64\\www\\JAVA\\PIDEV\\src\\other\\img\\logo1.png");
            image1.scaleAbsolute(150f, 150f);

       image1.setAlignment(Element.ALIGN_CENTER);
        document.add(image1);
            
            
            String query="SELECT * FROM panier WHERE IdUser=?";
            ps=connection.prepareStatement(query);
            ps.setInt(1, 1);
            rs=ps.executeQuery();
            
            String query1="SELECT * FROM produit WHERE produit.id IN (SELECT IdProduit From panier WHERE IdUser=?)";
            ps1=connection.prepareStatement(query1);
            ps1.setInt(1, 1);
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
              p.setId(rs1.getInt(1));
              p.setNom(rs1.getString(3));
              p.setPrix(rs1.getDouble(5));
              lp.add(p);
                /*Paragraph para=new Paragraph(rs1.getString("nom"));
               
                document.add(para);
                document.add(new Paragraph(" "));
           }
              Double b=0d;
            while (rs.next()){
                Produit w=null;
                for(Produit x:lp){
                    if(x.getId()==rs.getInt(3))
                        w=x;
                }
                     PdfPTable table1 = new PdfPTable(3); 
               PdfPCell cell4 = new PdfPCell(new Paragraph(w.getNom()+" "));
               PdfPCell cell5 = new PdfPCell(new Paragraph(rs.getInt(5)+" "));
               PdfPCell cell6 = new PdfPCell(new Paragraph((rs.getInt(5)*w.getPrix()+" ")));
               b+=rs.getInt(5)*w.getPrix(); 
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
       
}*/
}
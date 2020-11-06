/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MVente.Facture;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import techniques.MyConnection;

/**
 *
 * @author user
 */
public class Generate_PDF_Dynamic {
    
    /*    try{
            
            String file_name="C:\\Users\\user\\Desktop\\generate_pdf\\Facture.pdf";
            Document document=new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
            MyConnection obJDBConnection=new MyConnection();
            Connection connection= obJDBConnection.getCnx();
            PreparedStatement ps=null;
            ResultSet rs=null;
            
            String query="SELECT * FROM panier";
            ps=connection.prepareStatement(query);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Paragraph para=new Paragraph(rs.getString("qtpanier"));
               
                document.add(para);
                document.add(new Paragraph(" "));
            }

            //Paragraph para= new Paragraph("koko");
              // document.add(para);
            document.close();
            
            System.out.println("finished");
        }
        catch(Exception e){
            System.err.println(e);
        }
      */ 
}

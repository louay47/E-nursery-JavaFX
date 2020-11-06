/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import entities.MService.Reclamation.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mahjoub
 */
public class Notification {

    public Notification(String sujet,String description,String userAnotifier) {
       
        String requete="INSERT INTO notifjava (datecreation, sujet, description, seen, userAnotifier)"
                + " VALUES ('"+DateConverter.Datetime_Now_As_String()+"','"+sujet+"','"+description+"','0','"+userAnotifier+"');";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("notif Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public Notification(int modif) {
        String requete2="UPDATE notifjava SET seen =? WHERE notifjava.seen =?  ";           
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setInt(1, 1);
            pst.setInt(2, 0);
            
           
            pst.executeUpdate();
            System.out.println("notif Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Notification(char supp) {
        String requete3="DELETE FROM notifjava WHERE notifjava.seen=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1,1);
            
            pst.executeUpdate();
            
            System.out.println("notif Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Notification() {
        String requete4;  
       int i=0;
             
            
             
        try {
        
            requete4="Select * from notifjava where notifjava.seen=0 "; 
           
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
                    
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {   
                
                i++;
                if(rs.getString(6).equals("NONE")){
                    new Notif(rs.getString(3),rs.getString(4), "windows");
                }
                else{
                    System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                    new Notif(rs.getString(3),rs.getString(6)+" A ete Reclame \n"+rs.getString(4), "windows");
                }         
            } 
            if(i!=0)
                if(i==1)new VOICE_RSS("une nouvelle réclamation", "fr");
                else new VOICE_RSS(i+"nouvelle réclamation", "fr");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

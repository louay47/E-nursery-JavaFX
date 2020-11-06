/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MMarketing.Evenement;

import entities.MMarketing.Evenement.Evenement;
import entities.MMarketing.Promotion.Promotion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import techniques.DateConverter;
import techniques.MyConnection;

/**
 *
 * @author S.DHIA
 */
public class Crud_Evenement {
    public void ajouterEvenement(Evenement e){
        String requete="INSERT INTO evenement (nom_evenement, date_debut, date_fin, description, nbr_cado)"
                + " VALUES ('"+e.getNom_evenement()+"','"+DateConverter.Date_To_String(e.getDate_debut())+"','"+DateConverter.Date_To_String(e.getDate_fin())+"','"+e.getDescription()+"','"+e.getNbr_cado()+"');";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Evenement Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }  
    
    
    public void modifierEvenement(Evenement e,String m){
    String requete2="UPDATE evenement SET  description =?,nbr_cado=?,date_debut=?,date_fin=?  WHERE evenement.nom_evenement =?";           
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, e.getDescription());
             pst.setDouble(2, e.getNbr_cado());
             pst.setDate(3, (Date) e.getDate_debut());
             pst.setDate(4, (Date) e.getDate_fin());
              // pst.setDate(3, (Date) e.getDate_debut());
            pst.setString(5, m);
            
            pst.executeUpdate();
            System.out.println("EVENEMENT Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }

    public void supprimerEvenement(String x) {
        
        String requete3="DELETE FROM evenement WHERE evenement.nom_evenement=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setString(1,x);
            
            pst.executeUpdate();
            
            System.out.println("evenement Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void supprimerEvenement2( ) {
        String requete3 = "DELETE FROM evenement WHERE "
                + " (SELECT TIMESTAMPDIFF(DAY,NOW(),evenement.date_fin ) )=0";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
          //  pst.setString(1, x);

            pst.executeUpdate();

            System.out.println("Promotion Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Evenement> listerEvenement() {
          List<Evenement> myList=new ArrayList();
        try {
        
        String requete4="Select * from evenement";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Evenement e=new Evenement();
                e.setNom_evenement(rs.getString(1));
                 e.setDate_debut(rs.getDate(2));
                   e.setDate_fin(rs.getDate(3));
                e.setDescription(rs.getString(4));
                e.setNbr_cado(rs.getDouble(5));

                
               
                myList.add(e);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        
        
    }
     public List<Evenement> getState() {
        String req11 = "Select `nom_evenement`, `nbr_cado` From evenement order by nbr_cado desc";
        List<Evenement> liste = new ArrayList<Evenement>();
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req11);;
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                  Evenement e=new Evenement();
                e.setNom_evenement(rs.getString(1));
                  e.setNbr_cado(rs.getDouble(2));
                
                liste.add(e);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
     
 public List<String> esmproduit2()
    {
     
        
        
      
  List<String> myList=new ArrayList();
        try {
        
        String requete = "SELECT nom_evenement FROM `evenement` ";
                 Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete);
            
            while(rs.next())
            {
               
               
               
                myList.add(rs.getString(1));
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
     
     
}
    
public  int esmproduit1(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT nbr_cado FROM `evenement` WHERE nom_evenement=?; ";
               ps = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setString(1, x);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
               
               
                xx=rs.getInt(1);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return xx;
     
     
} 

    public int datte(String x) {
        
         int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT date_fin FROM `evenement` WHERE nom_evenement=?; ";
               ps = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setString(1, x);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
               
               
                xx=rs.getInt(1);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return xx;
    }
  
}

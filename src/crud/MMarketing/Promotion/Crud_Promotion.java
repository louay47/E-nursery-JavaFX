/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MMarketing.Promotion;

import entities.MMarketing.Promotion.Promotion;
import entities.MProduit.Produit;
import entities.MService.Reclamation.Reclamation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import techniques.DateConverter;
import techniques.MyConnection;

/**
 *
 * @author S.DHIA
 */
public class Crud_Promotion {

    public void ajouterPromotion(Promotion p) {
        String requete = "INSERT INTO promotion (nom_promotion, produit_id, date_debut, date_fin, pourcentage, prix_initiale, prix_promo)"
                + " VALUES ('" + p.getNom_promotion() + "','" + p.getProduit_id() + "','" + DateConverter.Date_To_String(p.getDate_debut()) + "','" + DateConverter.Date_To_String(p.getDate_fin()) + "','" + p.getPourcentage() + "','" + p.getPrix_initiale() + "', '" + p.getPrix_promo() + "');";

        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Promotoin Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int getprix(int x) {
        int idcom = 0;
        String requete = "SELECT prix FROM `produit` WHERE `id` = ?;";

        PreparedStatement ps;
        try {
            ps = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setInt(1, x);
            ResultSet resultat = ps.executeQuery();

            if (resultat.next());
            {

                idcom = resultat.getInt(1);
                System.out.println(+idcom);
                System.out.println("++++++++++++++++++++++");

                return idcom;
            }
        } catch (SQLException ex) {

            System.out.println("nopeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        }

        return idcom;

    }

    public void modifierPromotion(Promotion p, String m) {
        String requete2 = "UPDATE promotion SET  prix_promo =?, pourcentage =?,date_debut=?,date_fin=?,prix_initiale=? WHERE promotion.nom_promotion =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setDouble(1, p.getPrix_promo());
            pst.setDouble(2, p.getPourcentage());
            pst.setDate(3, (Date) p.getDate_debut());
            pst.setDate(4, (Date) p.getDate_fin());
            //  pst.setInt(5, p.getProduit_id());
            pst.setDouble(5, p.getPrix_initiale());

            pst.setString(6, m);

            pst.executeUpdate();
            System.out.println("promotion Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerPromotion(String x) {
        String requete3 = "DELETE FROM promotion WHERE promotion.nom_promotion=? ";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setString(1, x);

            pst.executeUpdate();

            System.out.println("Promotion Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      public static void supprimerPromotion2( ) {
        String requete3 = "DELETE FROM promotion WHERE "
                + " (SELECT TIMESTAMPDIFF(DAY,NOW(),promotion.date_fin ) )=0";

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

    public List<Promotion> listerPromotion() {
        List<Promotion> myList = new ArrayList();
        try {

            String requete4 = "Select * from promotion";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete4);

            while (rs.next()) {
                Promotion p = new Promotion();
                p.setNom_promotion(rs.getString(1));
                p.setProduit_id(rs.getInt(2));
                p.setDate_debut(rs.getDate(3));
                p.setDate_fin(rs.getDate(4));
                p.setPourcentage(rs.getDouble(5));
                p.setPrix_initiale(rs.getDouble(6));
                p.setPrix_promo(rs.getDouble(7));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public Produit getprod(int x) {

        String requete = "SELECT * FROM `produit` WHERE `id` = ?;";
        Produit p= new Produit();
        PreparedStatement ps;
        try {
            ps = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setInt(1, x);
            ResultSet resultat = ps.executeQuery();

            if (resultat.next());
            {
                    p.setDevis_name1(resultat.getString(8));
            }
        }catch (SQLException ex) {

    }
        
            return p;
    }
    
    
     public List<String> esmproduit2()
    {
     
        
        
      
  List<String> myList=new ArrayList();
        try {
        
        String requete = "SELECT nom_promotion FROM `promotion` ";
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
 public  int esmproduit22(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT produit_id FROM `promotion` WHERE nom_promotion=?; ";
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
  public  String esmproduit222(int x)
    {
     
        
        String xx="";
  
        try {
        PreparedStatement ps;
        String requete = "SELECT devis_name1 FROM `produit` WHERE id=?; ";
               ps = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
               
               
                xx=rs.getString(1);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return xx;
     
     
}
  
 public  int esmproduit1(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT prix_initiale FROM `promotion` WHERE nom_promotion=?; ";
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
   public  int esmproduit11(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT prix_promo FROM `promotion` WHERE nom_promotion=?; ";
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
   
   
   public  int datte(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT date_fin FROM `promotion` WHERE nom_promotion=?; ";
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
    public  int pourc(String x)
    {
     
        
        int xx=0;
  
        try {
        PreparedStatement ps;
        String requete = "SELECT pourcentage FROM `promotion` WHERE nom_promotion=?; ";
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
 
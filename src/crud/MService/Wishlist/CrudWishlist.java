/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MService.Wishlist;

import entities.MGestionUtilisateur.User;
import entities.MProduit.Produit;
import entities.MService.Wishlist.Wishlist;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author mahjoub
 */
public class CrudWishlist {
    
    
    
    public Boolean testProdexist(int idprod)
    {
        try {
        
            String requete4="Select * from wishlist  WHERE wishlist.produit_id="+idprod+" and wishlist.user_id ="+LoginController.us.getId()+"";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            if(rs.next())
            {
               return true;
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     public  void ajouterWishlist(int idProduit){
             
         if(!testProdexist(idProduit))
         {
         Wishlist w=new Wishlist();
         w.setDate(DateConverter.Date_Now_As_Date());
         w.setUser_id(2);
         w.setProduit_id(idProduit);
         
        String requete="INSERT INTO wishlist (user_id, produit_id, date)"
                + " VALUES ("+w.getUser_id()+",'"+w.getProduit_id()+"','"+DateConverter.Date_To_String(w.getDate())+"');";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit Ajoutée");
            alert("Wishlist","produit ajouté au wishlist avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }
         else
         {
             alert("Produit detecter","Ce produit deja existe vous pouvez pas l'ajouter");
         }
    
    }
    
    private void alert(String un,String deux)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(un);      
        alert.setContentText(deux);
        alert.show();
    }
    
    public void supprimerWishlist(int id){
    String requete3="DELETE FROM wishlist WHERE id=? and user_id=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1,id);
            pst.setInt(2,LoginController.us.getId());
            
            pst.executeUpdate();
            
            System.out.println("Produit Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public List<Produit> listerSimilarWishlist(Produit ppp)
    {
        List<Produit> myList=new ArrayList();
        try {
        
        String requete4="Select * from produit where  produit.categorie_id="+ppp.getId_categorie()+"  and produit.id NOT IN(select produit_id from wishlist where wishlist.user_id = "+LoginController.us.getId()+")";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Produit p=new Produit();
                p.setId_produit(rs.getInt(1));
                p.setId_user(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getFloat(5));
                p.setQuantite(rs.getInt(6));
                p.setDevis_name1(rs.getString(7));
                p.setId_categorie(rs.getInt(11));
                myList.add(p);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public List<Wishlist> listerWishlist()
    {
        List<Wishlist> myList=new ArrayList();
        try {
        
        String requete4="Select * from wishlist where wishlist.user_id ="+LoginController.us.getId()+"";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Wishlist p=new Wishlist();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setProduit_id(rs.getInt(3));
                p.setDate(rs.getDate(4));
                myList.add(p);
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public Produit   getproduitforlist(int id)
    {
        Produit p=new Produit();
        try {
        
        String requete4="Select * from produit  WHERE produit.id="+id+""; 
           
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {  
                 p.setId_produit(rs.getInt(1));
                p.setId_user(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getFloat(5));
                p.setQuantite(rs.getInt(6));
                p.setDevis_name1(rs.getString(7));
                p.setId_categorie(rs.getInt(11));
                
               
                
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public Wishlist getWishProd(int idProd)
    {
        Wishlist w=new Wishlist();
        try {
        
        String requete4="Select * from wishlist  WHERE wishlist.produit_id="+idProd+" and wishlist.user_id ="+LoginController.us.getId()+""; 
           
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            if(rs.next())
            {                
                w.setId(rs.getInt(1));
                w.setUser_id(rs.getInt(2));
                w.setProduit_id(rs.getInt(3));
                w.setDate(rs.getDate(4));               
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return w;
    }
    
    
}

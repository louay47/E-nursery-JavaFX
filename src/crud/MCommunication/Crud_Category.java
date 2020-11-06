/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MCommunication;

import entities.MCommunication.Blog_Category;
import entities.MCommunication.Blog_Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import techniques.DateConverter;
import techniques.MyConnection;

/**
 *
 * @author kratos
 */
public class Crud_Category {
    
    public List<Blog_Category> afficheCategoies() {
      
        List<Blog_Category> listeCategories = new ArrayList<>();
        
        try {
        
        String requete4="Select * from category";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Blog_Category p=new Blog_Category();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSlug(rs.getString(3));
               
                p.setPost_count(rs.getInt(4));
               

                listeCategories.add(p);
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeCategories;
        
    }
    
    public void deleteBlogCategory(String name){
    String requete3="DELETE FROM category WHERE category.name=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setString(1,name);
            System.out.println(name);
            pst.executeUpdate();
            
            System.out.println("Categoie Supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public void updateBlogCategory(Blog_Category b,int id){
    String requete2="UPDATE category SET  name =?, slug =?  WHERE category.id =? ";           
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
          pst.setString(1, b.getName());
          pst.setString(2, b.getSlug());
          pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("categorie Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }
    
    public int addBlogCtegory(Blog_Category b){
            
            System.out.println(b);
        String requete="INSERT INTO category (name, slug, post_count)"
                + " VALUES ('"+b.getName()+"','"+b.getSlug()+"',0);";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Ctegorie Ajoutée");
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Slug already exists");
            a.setContentText("can't add category with the same SLUG");
            a.showAndWait();
              return 1;      
        }
    
    }
    
  public List<String> searchCat(String s){
        List<String> res = new ArrayList<>();
     String requete="SELECT name FROM category WHERE category.name LIKE '"+s+"%'";
    
      try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               res.add(rs.getString(1));
           }
            System.out.println("Categorie TROUVEE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return res ;
    } 
    
  public int fetchCatId (String c){
  
      int res = 0;
      String requete="SELECT id FROM category WHERE category.name LIKE '"+c+"'";
  
      try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               res = rs.getInt(1);
           }
            System.out.println("Categorie id TROUVEE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return res ;
    } 
  
   public Blog_Category getCatByName(String s){
        Blog_Category p=new Blog_Category();
     String requete="SELECT * FROM category WHERE category.name LIKE '"+s+"'";
    
      try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSlug(rs.getString(3));
               
                p.setPost_count(rs.getInt(4));
               
           }
            System.out.println("Categorie TROUVEE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return p ;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MCommunication;

import entities.MCommunication.Blog_Post;
import entities.MService.Reclamation.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import techniques.DateConverter;
import techniques.MyConnection;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author kratos
 */
public class Crud_BlogPost {
    
     private Crud_Category CAT = new Crud_Category();

    public List<Blog_Post> afficheBlogs() {
      
        List<Blog_Post> listeBlogs = new ArrayList<>();
        
        try {
        
        String requete4="Select * from blog_post ORDER BY created_at DESC";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Blog_Post p=new Blog_Post();
                p.setUser_id(rs.getInt(3));
                p.setTitle(rs.getString(4));
                p.setId(rs.getInt(1));
                p.setBody(rs.getString(5));
                p.setCategoryName(rs.getString(9));
                p.setImg(rs.getString(10));
                p.setSlug(rs.getString(8));
                p.setCategory_id(rs.getInt(2));
                p.setCreated_at(rs.getDate(6));
                p.setUpdated_at(rs.getDate(7));

                listeBlogs.add(p);
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeBlogs;
        
    }
    
    
     public void deleteBlog(int id){
    String requete3="DELETE FROM blog_post WHERE blog_post.id=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1,id);
            System.out.println(id);
            pst.executeUpdate();
            
            System.out.println("Article Supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
       public void updateBlog(Blog_Post b,int id){
    String requete2="UPDATE blog_post SET  title =?, body =? , updated_at =? , slug =? WHERE blog_post.id =? ";           
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
          pst.setString(1, b.getTitle());
          pst.setString(2, b.getBody());
          pst.setString(3, DateConverter.Datetime_Now_As_String());
          pst.setString(4, b.getSlug());
          pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Article Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }
        public void addBlog(Blog_Post b){
            
           int cat_id = CAT.fetchCatId(b.getCategoryName());
           
           int userId=LoginController.us.getId();
            
            System.out.println("Category id is :"+cat_id);
            
        String requete="INSERT INTO blog_post (user_id, category_id, title, body, created_at,updated_at, slug, categoryName, img)"
                + " VALUES ("+userId+",'"+cat_id+"','"+b.getTitle()+"','"+b.getBody()+"','"+DateConverter.Datetime_Now_As_String()+"','"+DateConverter.Datetime_Now_As_String()+"','"+b.getSlug()+"','"+b.getCategoryName()+"','"+b.getImg()+"');";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Article Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
        
        public List<Blog_Post> fetchBlogsPerCategory(int id) {
      
        List<Blog_Post> listeBlogs = new ArrayList<>();
        
        try {
        
        String requete4="Select * from blog_post where blog_post.category_id = "+id;      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Blog_Post p=new Blog_Post();
                p.setUser_id(rs.getInt(3));
                p.setTitle(rs.getString(4));
                p.setId(rs.getInt(1));
                p.setBody(rs.getString(5));
                p.setCategoryName(rs.getString(9));
                p.setImg(rs.getString(10));
                p.setSlug(rs.getString(8));
                p.setCategory_id(rs.getInt(2));
                p.setCreated_at(rs.getDate(6));
                p.setUpdated_at(rs.getDate(7));

                listeBlogs.add(p);
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeBlogs;
        
    }
        
       public List<String> searchBlog(String s){
        List<String> res = new ArrayList<>();
     String requete="SELECT title FROM blog_post WHERE blog_post.title LIKE '"+s+"%'";
    
      try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               res.add(rs.getString(1));
           }
            System.out.println("BLOG TROUVEE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return res ;
    } 
       
        public List<Blog_Post> getBlogPerTitle(String title) {
      
            List<Blog_Post> listeBlogs = new ArrayList<>();
        
        try {
        
        String requete4="Select * from blog_post where blog_post.title like '"+title+"'";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Blog_Post p=new Blog_Post();
                p.setUser_id(rs.getInt(3));
                p.setTitle(rs.getString(4));
                p.setId(rs.getInt(1));
                p.setBody(rs.getString(5));
                p.setCategoryName(rs.getString(9));
                p.setImg(rs.getString(10));
                p.setSlug(rs.getString(8));
                p.setCategory_id(rs.getInt(2));
                p.setCreated_at(rs.getDate(6));
                p.setUpdated_at(rs.getDate(7));

                listeBlogs.add(p);
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeBlogs;
        
    }
        
           public int postsCount (int id ){
       int noposts =0;
       String requete = "SELECT COUNT(blog_post.id) FROM blog_post WHERE blog_post.user_id = "+id;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               
               noposts = rs.getInt(1);
           }
            System.out.println("Commentaire Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return noposts;
       }
        
}

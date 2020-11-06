/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.MCommunication;

import entities.MCommunication.Blog_Post;
import entities.MCommunication.Comment;
import entities.MService.Reclamation.Reclamation;
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
 * @author kratos
 */
public class  Crud_Comment {
    
    public List<Comment> afficheComments(int id) {
    
      List<Comment> listeComments = new ArrayList<>();
        
         
        try {
        
        String requete4="Select * from comment where post_id ="+id;      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Comment p=new Comment();
               
                p.setUsrname(rs.getString(3));
                p.setId(rs.getInt(1));
                p.setContent(rs.getString(4));
                
                p.setCreatedAt(rs.getDate(5));
               

                listeComments.add(p);
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeComments;
    
}
    
     public void addComment(Comment c){
        String requete="INSERT INTO comment (post_id, username, content, createdAt , modified)"
                + " VALUES ("+c.getPost_id()+",'"+c.getUsrname()+"','"+c.getContent()+"','"+DateConverter.Datetime_Now_As_String()+"','0');";                              
        
        try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Commentaire Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
     
      public void deleteComment(int id){
    String requete3="DELETE FROM comment WHERE comment.id=?";
    
     try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);
            pst.setInt(1,id);
            
            pst.executeUpdate();
            
            System.out.println("Commentaire Supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
      
       public void updateComment(Comment c,int id){
    String requete2="UPDATE comment SET username =?, content =?, createdAt =?, modified =? WHERE comment.id =?";           
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete2);
            pst.setString(1, c.getUsrname());
            pst.setString(2, c.getContent());
            pst.setString(3, DateConverter.Datetime_Now_As_String());
             pst.setInt(4, c.getModified());
            pst.setInt(5, id);
            
           
            pst.executeUpdate();
            System.out.println("Commentaire Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }
       
       public int commentsCount (int id ){
       int noComments =0;
       String requete = "SELECT COUNT(comment.id) FROM comment WHERE comment.post_id = "+id;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               
               noComments = rs.getInt(1);
           }
            System.out.println("Commentaire Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return noComments;
       }
       
       public List<Blog_Post> getBlogPerNbcOMMENT() {
      
            List<Blog_Post> listeBlogs = new ArrayList<>();
        
        try {
        
        String requete4="select * from blog_post order by (select COUNT(comment.id) from comment where comment.post_id=blog_post.id) DESC";      
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
      
      
}

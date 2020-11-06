/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MCommunication;

import java.util.Date;

/**
 *
 * @author kratos
 */
public class Comment {
    
    
    private int id ;
    private String usrname ;
    private String content ;
    private Date createdAt;
    private int post_id;
    private int modified;

    public Comment() {
    }

    public Comment(int id, String usrname, String content, Date createdAt, int post_id, int modified) {
        this.id = id;
        this.usrname = usrname;
        this.content = content;
        this.createdAt = createdAt;
        this.post_id = post_id;
        this.modified = modified;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        this.modified = modified;
    }

   

    
  

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", usrname=" + usrname + ", content=" + content + ", createdAt=" + createdAt + '}';
    }
    
    
    
}

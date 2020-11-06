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
public class Blog_Post {
    
    private int id;
    private String title ;
    private String body ;
    private Date created_at ;
    private Date updated_at ;
    private String slug;
    private String CategoryName;
    private int user_id ;
    private int category_id;
    private String img;

    public Blog_Post() {
    }

    public Blog_Post(int id, String title, String body, Date created_at, Date updated_at, String slug, String CategoryName, int user_id, int category_id, String img) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.slug = slug;
        this.CategoryName = CategoryName;
        this.user_id = user_id;
        this.category_id = category_id;
        this.img = img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

   

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public String toString() {
        return "Blog_Post{" + "id=" + id + ", title=" + title + ", body=" + body + ", created_at=" + created_at + ", updated_at=" + updated_at + ", slug=" + slug + ", CategoryName=" + CategoryName + '}';
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MCommunication;

/**
 *
 * @author kratos
 */
public class Blog_Category {
    
    private int id;
    private String name ;
    private String slug ;
    private int post_count ;

    public Blog_Category() {
    }

    public Blog_Category(int id, String name, String slug, int post_count) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.post_count = post_count;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }
    
    
    
    
}

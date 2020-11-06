/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MService.Reclamation;


import java.util.Date;

/**
 *
 * @author mahjoub
 */
public class Reclamation {
    private int id;
    private int user_id;
    private String subject;
    private String  description;
    private Date    date;
    private String type;
    private String  userToClaim;
    private int     state;
    private int     important;
    private int     trash;
    private Date    $datetrash;

    public Reclamation() {
        
        
    }

    public Reclamation( int user_id, String subject, String description, Date date, String type, String userToClaim) {       
        this.user_id = user_id;
        this.subject = subject;
        this.description = description;
        this.date = date;
        this.type = type;
        this.userToClaim = userToClaim;      
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserToClaim() {
        return userToClaim;
    }

    public void setUserToClaim(String userToClaim) {
        this.userToClaim = userToClaim;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getTrash() {
        return trash;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public Date get$datetrash() {
        return $datetrash;
    }

    public void set$datetrash(Date $datetrash) {
        this.$datetrash = $datetrash;
    }
            
    
}
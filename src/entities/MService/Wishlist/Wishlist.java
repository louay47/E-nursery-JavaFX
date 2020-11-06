/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MService.Wishlist;

import java.util.Date;

/**
 *
 * @author mahjoub
 */
public class Wishlist {
    private int id;
    private int user_id;
    private int produit_id;
    private Date date;

    public Wishlist(int id, int user_id, int produit_id,Date date) {
        this.id = id;
        this.user_id = user_id;
        this.produit_id = produit_id;
        this.date = date;
    }

    public Wishlist() {
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

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}

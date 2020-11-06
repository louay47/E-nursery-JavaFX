/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MVente.Livraison;

import java.util.Date;

/**
 *
 * @author user
 */
public class Livraison {
    private int id;
    private Date DateDepart;
    private int IdPanier;
    private int IdProduit;
    private int UserId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livraison() {
    }

    public Livraison(int id, Date DateDepart, int IdPanier, int IdProduit) {
        this.id = id;
        this.DateDepart = DateDepart;
        this.IdPanier = IdPanier;
        this.IdProduit = IdProduit;
    }
    

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(Date DateDepart) {
        this.DateDepart = DateDepart;
    }

    public int getIdPanier() {
        return IdPanier;
    }

    public void setIdPanier(int IdPanier) {
        this.IdPanier = IdPanier;
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }
    
}

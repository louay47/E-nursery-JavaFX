/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MVente.Panier;

/**
 *
 * @author user
 */
public class Panier {
    private int id;
    private int qtpanier;
    private int valid;
    private int IdProduit;
    private int IdUser;
    private int IdLivraison;

    public Panier() {
    }

    public Panier(int id, int qtpanier, int valid, int IdProduit, int IdUser, int IdLivraison) {
        this.id = id;
        this.qtpanier = qtpanier;
        this.valid = valid;
        this.IdProduit = IdProduit;
        this.IdUser = IdUser;
        this.IdLivraison = IdLivraison;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtpanier() {
        return qtpanier;
    }

    public void setQtpanier(int qtpanier) {
        this.qtpanier = qtpanier;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdLivraison() {
        return IdLivraison;
    }

    public void setIdLivraison(int IdLivraison) {
        this.IdLivraison = IdLivraison;
    }
}

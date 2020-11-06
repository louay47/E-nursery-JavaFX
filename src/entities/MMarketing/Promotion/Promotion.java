/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MMarketing.Promotion;

import java.util.Date;

/**
 *
 * @author S.DHIA
 */
public class Promotion {
     private String nom_promotion ;
   private int produit_id ;
   private Date date_debut ;
   private Date date_fin ;
   private double pourcentage ;
   private double prix_initiale ;
   private double prix_promo ;

    public Promotion() {
    }

    public Promotion(String nom_promotion, int produit_id, Date date_debut, Date date_fin, double pourcentage, double prix_initiale, double prix_promo) {
        this.nom_promotion = nom_promotion;
        this.produit_id = produit_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.pourcentage = pourcentage;
        this.prix_initiale = prix_initiale;
        this.prix_promo = prix_promo;
    }

    public String getNom_promotion() {
        return nom_promotion;
    }

    public void setNom_promotion(String nom_promotion) {
        this.nom_promotion = nom_promotion;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getPrix_initiale() {
        return prix_initiale;
    }

    public void setPrix_initiale(double prix_initiale) {
        this.prix_initiale = prix_initiale;
    }

    public double getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(double prix_promo) {
        this.prix_promo = prix_promo;
    }
   
   
}

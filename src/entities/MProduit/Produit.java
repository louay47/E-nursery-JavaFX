/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MProduit;

/**
 *
 * @author Lazzem
 */
public class Produit {

    private int id_produit;
    private int id_categorie;
    private int id_user;
    private String nom;
    private String description;
    private float prix;
    private String devis_name1;
    private String devis_name2;
    private String devis_name3;
    private int quantite;

    public Produit(int id_produit, int id_categorie, int id_user, String nom, String description, float prix, String devis_name1, String devis_name2, String devis_name3, int quantite) {
        this.id_produit = id_produit;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.devis_name1 = devis_name1;
        this.devis_name2 = devis_name2;
        this.devis_name3 = devis_name3;
        this.quantite = quantite;
    }

    public Produit() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDevis_name1() {
        return devis_name1;
    }

    public String getDevis_name2() {
        return devis_name2;
    }

    public String getDevis_name3() {
        return devis_name3;
    }

    public void setDevis_name1(String devis_name1) {
        this.devis_name1 = devis_name1;
    }

    public void setDevis_name2(String devis_name2) {
        this.devis_name2 = devis_name2;
    }

    public void setDevis_name3(String devis_name3) {
        this.devis_name3 = devis_name3;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
   
}

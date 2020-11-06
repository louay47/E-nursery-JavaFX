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
public class Categorie {
    private int id_categorie;
    private String nom;
    private String description;
    private String type;
    private String devis_name;

    public Categorie() {
    }

    public Categorie(int id_categorie, String nom, String description, String type, String devis_name) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.devis_name = devis_name;
    }


    public String getDescription() {
        return description;
    }

    public String getDevis_name() {
        return devis_name;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDevis_name(String devis_name) {
        this.devis_name = devis_name;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom=" + nom + ", description=" + description + ", type=" + type + ", devis_name=" + devis_name + '}';
    }
    
    
    
    
}

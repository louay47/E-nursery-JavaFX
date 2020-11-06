/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MMarketing.Evenement;

import java.util.Date;

/**
 *
 * @author S.DHIA
 */
public class Evenement {
    private String nom_evenement ;

  private Date date_debut ;
   private Date date_fin ;
    private String description ;
     private double nbr_cado ;
    private Double stat;
     public Evenement( String nom_evenement ,Double stat) {
       
        this.nom_evenement = nom_evenement;
         this.stat = stat;
    }
  
      public Evenement() {
    }
        public Evenement(Double stat) {
        this.stat = stat;
    }

    public Evenement(String nom_evenement, Date date_debut, Date date_fin, String description, double nbr_cado) {
        this.nom_evenement = nom_evenement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.nbr_cado = nbr_cado;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getNbr_cado() {
        return nbr_cado;
    }

    public void setNbr_cado(double nbr_cado) {
        this.nbr_cado = nbr_cado;
    }
      
      
   public Double getStat() {
        return stat;
    }

    public void setStat(Double stat) {
        this.stat = stat;
    }
     

    
}

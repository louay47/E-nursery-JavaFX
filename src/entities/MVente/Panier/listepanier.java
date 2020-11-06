/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MVente.Panier;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class listepanier {
    
    private ImageView iv;
    private Label nomprod;
    private Label descriptionprod;
    private Label prixunité;
    private Label quantite;
    private Button plus; 
     private Button moin; 
    private Button supprimer; 
    public listepanier() {
    }

    public listepanier(ImageView iv, Label nomprod, Label descriptionprod, Label prixunité, Label quantite, Button plus, Button moin, Button supprimer) {
        this.iv = iv;
        this.nomprod = nomprod;
        this.descriptionprod = descriptionprod;
        this.prixunité = prixunité;
        this.quantite = quantite;
        this.plus = plus;
        this.moin = moin;
        this.supprimer = supprimer;
    }
    

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public Label getNomprod() {
        return nomprod;
    }

    public void setNomprod(Label nomprod) {
        this.nomprod = nomprod;
    }

    public Label getDescriptionprod() {
        return descriptionprod;
    }

    public void setDescriptionprod(Label descriptionprod) {
        this.descriptionprod = descriptionprod;
    }

    public Label getPrixunité() {
        return prixunité;
    }

    public void setPrixunité(Label prixunité) {
        this.prixunité = prixunité;
    }

    public Label getQuantite() {
        return quantite;
    }

    public void setQuantite(Label quantite) {
        this.quantite = quantite;
    }

    public Button getPlus() {
        return plus;
    }

    public void setPlus(Button plus) {
        this.plus = plus;
    }

    public Button getMoin() {
        return moin;
    }

    public void setMoin(Button moin) {
        this.moin = moin;
    }

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }
   
}

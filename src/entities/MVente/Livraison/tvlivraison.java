/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MVente.Livraison;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class tvlivraison {
    private ImageView iv;
    private Label nomprod;
    private Label qt;
    private Label datedepart;
    private Label datearrive;
    private Label etat;
    private Button reclamer;

    public Button getReclamer() {
        return reclamer;
    }

    public void setReclamer(Button reclamer) {
        this.reclamer = reclamer;
    }

    public tvlivraison() {
    }

    public tvlivraison(ImageView iv, Label nomprod, Label qt, Label datedepart, Label datearrive, Label etat, Button reclamer) {
        this.iv = iv;
        this.nomprod = nomprod;
        this.qt = qt;
        this.datedepart = datedepart;
        this.datearrive = datearrive;
        this.etat = etat;
        this.reclamer = reclamer;
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

    public Label getQt() {
        return qt;
    }

    public void setQt(Label qt) {
        this.qt = qt;
    }

    public Label getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Label datedepart) {
        this.datedepart = datedepart;
    }

    public Label getDatearrive() {
        return datearrive;
    }

    public void setDatearrive(Label datearrive) {
        this.datearrive = datearrive;
    }

    public Label getEtat() {
        return etat;
    }

    public void setEtat(Label etat) {
        this.etat = etat;
    }

    public Button getReclame() {
        return reclame;
    }

    public void setReclame(Button reclame) {
        this.reclame = reclame;
    }
    private Button reclame; 
}

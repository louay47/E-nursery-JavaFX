/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MGestionUtilisateur;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import techniques.DateConverter;

/**
 *
 * @author mahjoub
 */
public class Listuser {

   
    private Label id;
    private Label nom;
    private Label dernierAcces;
    private Label status;
    private Label email;        
    private Label open;

    public Label getId() {
        return id;
    }

    public void setId(Label id) {
        this.id = id;
    }

    public Label getNom() {
        return nom;
    }

    public void setNom(Label nom) {
        this.nom = nom;
    }

    public Label getDernierAcces() {
        return dernierAcces;
    }

    public void setDernierAcces(Label dernierAcces) {
        this.dernierAcces = dernierAcces;
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(Label status) {
        this.status = status;
    }

    public Label getEmail() {
        return email;
    }

    public void setEmail(Label email) {
        this.email = email;
    }

    public Label getOpen() {
        return open;
    }

    public void setOpen(Label open) {
        this.open = open;
    }

    public Listuser(Label id, Label nom, Label dernierAcces, Label status, Label email, Label open) {
        this.id = id;
        this.nom = nom;
        this.dernierAcces = dernierAcces;
        this.status = status;
        this.email = email;
        this.open = open;
    }

    public Listuser() {
    }

   
}

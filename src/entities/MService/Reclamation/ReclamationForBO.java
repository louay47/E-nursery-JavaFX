/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.MService.Reclamation;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import techniques.DateConverter;

/**
 *
 * @author mahjoub
 */
public class ReclamationForBO {

    private int id;
    private ImageView etoile;
    private Label SenderUser;
    private Label Type;
    private Label ReclamedUser;
    private Label Sub;
    private Label subject;
    private Label setAs;
    private Label sendto;
    private Label daysleft;
    private Label open;

    public ReclamationForBO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageView getEtoile() {
        return etoile;
    }

    public void setEtoile(ImageView etoile) {
        this.etoile = etoile;
    }

    public Label getSenderUser() {
        return SenderUser;
    }

    public void setSenderUser(Label SenderUser) {
        this.SenderUser = SenderUser;
    }

    public Label getType() {
        return Type;
    }

    public void setType(Label Type) {
        this.Type = Type;
    }

    public Label getReclamedUser() {
        return ReclamedUser;
    }

    public void setReclamedUser(Label ReclamedUser) {
        this.ReclamedUser = ReclamedUser;
    }

    public Label getSub() {
        return Sub;
    }

    public void setSub(Label Sub) {
        this.Sub = Sub;
    }

    public Label getSubject() {
        return subject;
    }

    public void setSubject(Label subject) {
        this.subject = subject;
    }

    public Label getSetAs() {
        return setAs;
    }

    public void setSetAs(Label setAs) {
        this.setAs = setAs;
    }

    public Label getSendto() {
        return sendto;
    }

    public void setSendto(Label sendto) {
        this.sendto = sendto;
    }

    public Label getDaysleft() {
        return daysleft;
    }

    public void setDaysleft(Label daysleft) {
        this.daysleft = daysleft;
    }

    public Label getOpen() {
        return open;
    }

    public void setOpen(Label open) {
        this.open = open;
    }

}

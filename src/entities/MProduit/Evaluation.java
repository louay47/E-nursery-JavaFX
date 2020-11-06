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
public class Evaluation {
    private int idevaluation;
    
    private int iduser;
    private int idprod;
    private double note;

    public Evaluation() {
    }

    public Evaluation(int idevaluation, int iduser, int idprod, double note) {
        this.idevaluation = idevaluation;
        this.idprod = idprod;
        this.iduser = iduser;
        this.note = note;
    }
    public Evaluation( int iduser, int idprod, double note) {
        this.idprod = idprod;
        this.iduser = iduser;
        this.note = note;
    }
    public int getIdevaluation() {
        return idevaluation;
    }

    public void setIdevaluation(int idevaluation) {
        this.idevaluation = idevaluation;
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }



    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "idevaluation=" + idevaluation + ", idprod=" + idprod + ", iduser=" + iduser + ", note=" + note + '}';
    }


    
    
    
    
    
}

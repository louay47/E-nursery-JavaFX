/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.BE;

import crud.MService.Reclamation.BE.CrudReclamation;
import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import techniques.Mail;
import views.MVente.Livraison.AfficheLivraisonBackController;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ProfileRController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private Label Email;
    @FXML
    private Label About;
    @FXML
    private Label PhoneNumber;
    @FXML
    private Label Location;
    @FXML
    private Label Address;
    @FXML
    private Label Job;
    @FXML
    private Button banUser;
    private CrudReclamation CR = new CrudReclamation();
    public static User u;
    @FXML
    private BorderPane BPane;

    @FXML
    private Pane panelchart;
    CrudReclamation cr = new CrudReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chatSetting();
        UserName.setText(u.getUsername());
        Email.setText(u.getEmail());
        About.setText(u.getAbout());
        PhoneNumber.setText(u.getPhone_number() + "");
        Location.setText(u.getLocation());
        Address.setText(u.getAddress());
        Job.setText(u.getJob());

    }

    @FXML
    private void banuserAction(ActionEvent event) {
        CR.ban(u.getId());
        new Mail(u.getEmail(), "E-nersery", "Vous ete banné");
    }

   

    private void LooadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/BE/" + ui + ".fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        BPane.setCenter(root);
    }

    @FXML
    private void returnaction(MouseEvent event) {
         LooadUI("Reclamation");
    }

    private void chatSetting() {
        ObservableList<String> cate = FXCollections.observableArrayList();
        cate.addAll("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        final CategoryAxis xAxis = new CategoryAxis(cate);
        xAxis.setLabel("Mois");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre Reclamation");
        // ObservableList<Series<String,Number>> LineChartData = FXCollections.observableArrayList();

        LineChart lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Reclamation Par mois");
        XYChart.Series series = new XYChart.Series();
        
        
        Calendar cal = Calendar.getInstance();
        int [] mois = new int[12];
        float moy=0;
        for (Reclamation r : cr.listerReclamationSpe(u.getUsername())) {
            cal.setTime(r.getDate());
            int month = cal.get(Calendar.MONTH);
            mois[month]+=1;
            moy++;
            System.out.println(month+"   "+mois[month]);
        }
        
        series.setName("moyenne de reclamation par mois :  "+new DecimalFormat("0.00").format(moy/12));
        series.getData().add(new XYChart.Data("Jan", mois[0]));
        series.getData().add(new XYChart.Data("Feb", mois[1]));
        series.getData().add(new XYChart.Data("Mar", mois[2]));
        series.getData().add(new XYChart.Data("Apr", mois[3]));
        series.getData().add(new XYChart.Data("May", mois[4]));
        series.getData().add(new XYChart.Data("Jun", mois[5]));
        series.getData().add(new XYChart.Data("Jul", mois[6]));
        series.getData().add(new XYChart.Data("Aug", mois[7]));
        series.getData().add(new XYChart.Data("Sep", mois[8]));
        series.getData().add(new XYChart.Data("Oct", mois[9]));
        series.getData().add(new XYChart.Data("Nov", mois[10]));
        series.getData().add(new XYChart.Data("Dec", mois[11]));

        //LineChartData.add(series);
        //lineChart.setData(LineChartData);
        lineChart.getData().add(series);
        Group root = new Group(lineChart);
        panelchart.getChildren().add(root);
    }

    @FXML
    private void livb(MouseEvent event) {
        
        
        AfficheLivraisonBackController.user=u;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/MVente/Livraison/AfficheLivraisonBack.fxml"));

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        BPane.setCenter(root);
       
        
    }
}
/*
FXMLLoader loader =new FXMLLoader(getClass().getResource("/views/MService/Reclamation/BE/"+ui+".fxml"));
Parent root = loader.load();
xxxControler pc=loader.getControler();
pc.setNom();
pc.setPrenom();
tfNom.getScean().setRoot(root);
*/

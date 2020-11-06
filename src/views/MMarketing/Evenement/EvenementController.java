/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MMarketing.Evenement;

import crud.MMarketing.Evenement.Crud_Evenement;
import crud.MMarketing.Promotion.Crud_Promotion;
import entities.MMarketing.Evenement.Evenement;
import entities.MMarketing.Promotion.Promotion;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import techniques.DateConverter;
import static views.MMarketing.Evenement.AjoutevenementController.EBPane;
import views.MMarketing.Promotion.AjoutpromotionController;
import views.MMarketing.Promotion.ModifierpromotionController;

/**
 * FXML Controller class
 *
 * @author S.DHIA
 */
public class EvenementController implements Initializable {

   
    @FXML
    private Button btnajoutEV;
     private Crud_Evenement cpp = new Crud_Evenement();
    @FXML
    private Button modifer_Eve;
    
    @FXML
    private Button supprimerevenementt;
    @FXML
    private TableView<Evenement> afficherEVE;
   private final ObservableList<Evenement> data = FXCollections.observableArrayList();
    private Pane Bpane;
    @FXML
    private BorderPane bpanee;
    public  static Pane HomePane;
    @FXML
    private Button statistique;
    @FXML
    private TextField SearchFiled;
   
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bpane=HomePane;
        init();
        
    }    

public void init(){
      afficherEVE.getItems().clear();
            for ( Evenement x :cpp.listerEvenement()) {   
               
                
                data.add(x);            
                
        }
            
            afficherEVE.setItems(data);
        afficherEVE.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       
        TableColumn nomeve = new TableColumn("nomeve");
        nomeve.setMinWidth(10);
        nomeve.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("nom_evenement"));
        
        
        TableColumn Des = new TableColumn("description");
        Des.setMinWidth(10);
        Des.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("description"));
        TableColumn date = new TableColumn("date debut ");
        date.setMinWidth(10);
        date.setCellValueFactory(
                new PropertyValueFactory<Evenement, DateConverter>("date_debut"));
        
        
         TableColumn datefin = new TableColumn("date fin");
        datefin.setMinWidth(10);
        datefin.setCellValueFactory(
                new PropertyValueFactory<Evenement, DateConverter>("date_fin"));
        
        TableColumn nbrecado = new TableColumn("nbre cado");
        nbrecado.setMinWidth(10);
        nbrecado.setCellValueFactory(
                new PropertyValueFactory<Evenement, Double>("nbr_cado"));
        
        
        afficherEVE.getColumns().addAll(nomeve,Des,date,datefin,nbrecado);
       
        FilteredList<Evenement> filteredData = new FilteredList<>(data, p -> true);
         SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Promotion -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Promotion.getNom_evenement().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });    
        
        afficherEVE.setItems(filteredData);
}
   

   

    @FXML
    private void supprimerEvenement(ActionEvent event) {
       //System.out.println(item.getValue().getId());
      // String x=index();
                  Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("supprimer un evenement");
        a1.setContentText("vous voulez vraiment supprimer cet evenement?");
               Optional<ButtonType> result = a1.showAndWait();
  if (result.get() == ButtonType.OK) {
 for (Evenement e : afficherEVE.getSelectionModel().getSelectedItems()) {
              cpp.supprimerEvenement(e.getNom_evenement());
            


        }
           
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("supprimer une evenement");
            a2.setContentText("evenement supprimé avec succés");
            a2.show();
            
            
            
            
  }
      Loh("Evenement");
        //cpp.supprimerEvenement(x);
        
    }
    
     private int nbselection() {
        int i = 0;
        for (Evenement e : afficherEVE.getSelectionModel().getSelectedItems()) {
            i++;
        }
        return i;
    }
     private String index()
    {
        
        
            String selectedItem = afficherEVE.getSelectionModel().getSelectedItem().getNom_evenement();
            
           
            
            System.out.println(selectedItem);
            return selectedItem;
            
        
    }
    

    private void afficherEve(ActionEvent event) {
          
            afficherEVE.getItems().clear();
            for ( Evenement x :cpp.listerEvenement()) {   
               
                
                data.add(x);            
                
        }
            
            afficherEVE.setItems(data);
        
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
        AjoutevenementController.EBPane=Bpane ;
        AjoutevenementController.evv=afficherEVE.getSelectionModel().getSelectedItem();
        LoPage("Ajoutevenement");
    }

    @FXML
    private void showE(MouseEvent event) {
        ModifierevenementController.EBPane=Bpane;
         ModifierevenementController.evv=afficherEVE.getSelectionModel().getSelectedItem();
        LoPage("modifierevenement");
    }
    
      

    @FXML
    private void stat(ActionEvent event) {
        Crud_Evenement a = new Crud_Evenement();
        Scene scene = new Scene(new Group());
        Stage mainStage = new Stage();
          mainStage.setTitle("Line Chart Sample");
        mainStage.setWidth(500);
        mainStage.setHeight(500);
       

        List<Evenement> liste = new ArrayList<Evenement>();
        //a.getStat() hedhi fergha tekhou feha wini requete eli tjib menha..?
        liste = a.getState();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        for (int i = 0; i < liste.size(); i++) {
            pieChartData.add(new PieChart.Data(liste.get(i).getNom_evenement(), liste.get(i).getNbr_cado()));

        }
        
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(" Nombre de cado par evenement");
       
       final Label caption = new Label("");
       
        
        ((Group) scene.getRoot()).getChildren().add(chart);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void statline(ActionEvent event) {
          //Stage stage = null;
        Crud_Evenement a = new Crud_Evenement();
      Scene scene = new Scene(new Group());
   Stage mainStage = new Stage();
       mainStage.setWidth(500);
       mainStage.setHeight(450);
      mainStage.setTitle("Line Chart Sample");
      
        //Scene scene  = new Scene(lineChart,800,600);     

        List<Evenement> liste = new ArrayList<Evenement>();
        //a.getStat() hedhi fergha tekhou feha wini requete eli tjib menha..?
        liste = a.getState();
//        stage.setTitle("Line Chart Sample");
       final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
     
        xAxis.setLabel("cado");  
      final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
         lineChart.setTitle("Stock Monitoring, 2010");                        
        XYChart.Series series = new XYChart.Series();
       
        series.setName("NOM EVENEMENT");
         //series.setTitle(" Nombre de cado par evenement");
           for (int i = 0; i < liste.size(); i++) {
            series.getData().add(new XYChart.Data(liste.get(i).getNom_evenement(), liste.get(i).getNbr_cado()));;
 
        }
           lineChart.getData().add(series);
        //series.getData().add(new XYChart.Data("", 23));
       //PieChart.Data slice1 = new PieChart.Data("l'age des users inferieur à 20 ",String.parseInt(a.getState()));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle(" Nombre de cado par evenement");
//       chart.setLegendSide(Side.LEFT);
//        chart.setStartAngle(30);
      //  chart.setText("le nombre des users est "+String.valueOf(data.getClass()));
     //  final Label caption = new Label("");
        ((Group) scene.getRoot()).getChildren().add(lineChart);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void Loh(String s) {
        Bpane.getChildren().clear();
         Parent root=null;
        try
        {
            root=FXMLLoader.load(getClass().getResource("/views/MMarketing/Evenement/"+s+".fxml"));
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        Bpane.getChildren().add(root); 
    }
     
    
    private void LoPage(String e)
    {
        Parent root=null;
        try
        {
            root=FXMLLoader.load(getClass().getResource("/views/MMarketing/Evenement/"+e+".fxml"));
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        bpanee.setCenter(root);
    }
    
    
    
    
}

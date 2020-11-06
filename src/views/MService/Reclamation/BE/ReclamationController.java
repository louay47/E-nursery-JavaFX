
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MService.Reclamation.BE;

import crud.MGestionUtilisateurs.CrudUser;
import crud.MService.Reclamation.BE.CrudReclamation;
import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import entities.MService.Reclamation.ReclamationForBO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import techniques.DateConverter;
import techniques.Notif;

/**
 * FXML Controller class
 *
 * @author mahjoub
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button toutbtn;
    @FXML
    private Button nonlubtn;
    @FXML
    private Button lubtn;
    @FXML
    private Button importantbtn;
    @FXML
    private Button poubellebtn;
    @FXML
    private Label TypeList;
    @FXML
    private TableView<ReclamationForBO> tableview;
    private final ObservableList<ReclamationForBO> data = FXCollections.observableArrayList();
    private CrudReclamation CR = new CrudReclamation();
    
    @FXML
    private BorderPane BPaneHH;
    @FXML
    private Label newlbl;
    @FXML
    private Label toutlbl;
    @FXML
    private Label nonlulbl;
    @FXML
    private Label lulbl;
    @FXML
    private Label importlbl;
    @FXML
    private Label poubellbl;
    public static Pane HomeBorderrrr;
    private Pane HomeBorder;
private CrudUser cu =new CrudUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setnumbers();
        HomeBorder=HomeBorderrrr;
        showall();
        new Notif("INFO D'utilisation", "Cliquer sur le nom d'utilisateur pour ouvrire son profile", "fx");
        
    }
    
    private void  setnumbers()
    {
        newlbl.setText(CR.nbNonluR()+"New");
        toutlbl.setText(CR.nbToutR()+""); 
        nonlulbl.setText(CR.nbNonluR()+""); 
        lulbl.setText(CR.nbLuR()+""); 
        importlbl.setText(CR.nbImportR()+""); 
        poubellbl.setText(CR.nbPoubelleR()+"");
    }
    
    private void showall()
    {
        
        TypeList.setText("Tout - List");    
        tableview.getColumns().clear();
        tableview.getItems().clear();
        
        TableColumn etoile = new TableColumn();
        etoile.setMinWidth(10);
        etoile.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, ImageView>("etoile"));

        TableColumn SenderUser = new TableColumn();
        SenderUser.setMinWidth(10);
        SenderUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("SenderUser"));

        TableColumn Type = new TableColumn();
        Type.setMinWidth(10);
        Type.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Type"));

        TableColumn ReclamedUser = new TableColumn();
        ReclamedUser.setMinWidth(10);
        ReclamedUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("ReclamedUser"));

        TableColumn Sub = new TableColumn();
        Sub.setMinWidth(10);
        Sub.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Sub"));

        TableColumn subject = new TableColumn();
        subject.setMinWidth(10);
        subject.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("subject"));

        TableColumn setAs = new TableColumn();
        setAs.setMinWidth(10);
        setAs.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("setAs"));

        TableColumn sendto = new TableColumn();
        sendto.setMinWidth(10);
        sendto.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("sendto"));

        TableColumn daysleft = new TableColumn();
        daysleft.setMinWidth(10);
        daysleft.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("daysleft"));

        TableColumn open = new TableColumn();
        open.setMinWidth(20);
        open.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("open"));

        tableview.getColumns().addAll(etoile, SenderUser, Type, ReclamedUser, Sub, subject, setAs, sendto, daysleft, open);

        List<ReclamationForBO> RFBO = new ArrayList();
        Label hh;

        ImageView etoilex;


        for (Reclamation x : CR.listerALLReclamation()) {
            ReclamationForBO Rx = new ReclamationForBO(x.getId());

            if(x.getState()==0)     etoilex = new ImageView(new Image("/other/img/star.png"));
            else                    etoilex = new ImageView(new Image("/other/img/star(1).png"));
            etoilex.setFitHeight(15);
            etoilex.setFitWidth(15);
            Rx.setEtoile(etoilex);

            hh = new Label("" +cu.getUserById(x.getUser_id()).getUsername() );
            Rx.setSenderUser(hh);
            Rx.getSenderUser().setTextFill(Color.web("#322e91"));
            Rx.getSenderUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserById(x.getUser_id()));
                }
            });
            
            
            if(x.getType().equals("Autre")) hh = new Label("Suggestion");
            else hh = new Label("Reclame");
            Rx.setType(hh);

            
            if(x.getUserToClaim().equals("NONE")) hh = new Label("");
            else    hh = new Label("" + x.getUserToClaim());
            Rx.setReclamedUser(hh);
            Rx.getReclamedUser().setTextFill(Color.web("#322e91"));
            Rx.getReclamedUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserByName(x.getUserToClaim()));
                }
            });

            hh = new Label("Subject :");
            Rx.setSub(hh);

            hh = new Label("" + x.getSubject());
            Rx.setSubject(hh);

            hh = new Label("Important");
            Rx.setSetAs(hh);
            Rx.getSetAs().setTextFill(Color.web("#591e1e"));
            Rx.getSetAs().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setAsImportant(x.getId());
                    setnumbers();
                    showall();
                }
            });
            
            hh = new Label("Poubelle");
            Rx.setSendto(hh);
            Rx.getSendto().setTextFill(Color.web("#707027"));
            Rx.getSendto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    SendToPoubelle(x.getId());
                    setnumbers();
                    showall();
                }
            });
            
            
            hh = new Label("" + DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), x.getDate()) +" days ago");            
            Rx.setDaysleft(hh);

            hh = new Label("Open");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#0076a3"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CR.setReaded(x.getId());
                    OpenReclamation(x);
                    setnumbers();
                }
            });
            
            
            
            RFBO.add(Rx);

        }

        
        for (ReclamationForBO x : RFBO) {
           
            data.add(x);
        }
        tableview.setItems(data);
    }
    
    @FXML
    private void toutaction(ActionEvent event) {

        showall();

    }

    private void shownonlu()
    {
         TypeList.setText("Tout - Non Lu");    
        tableview.getColumns().clear();
        tableview.getItems().clear();
        TableColumn etoile = new TableColumn();
        etoile.setMinWidth(10);
        etoile.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, ImageView>("etoile"));

        TableColumn SenderUser = new TableColumn();
        SenderUser.setMinWidth(10);
        SenderUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("SenderUser"));

        TableColumn Type = new TableColumn();
        Type.setMinWidth(10);
        Type.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Type"));

        TableColumn ReclamedUser = new TableColumn();
        ReclamedUser.setMinWidth(10);
        ReclamedUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("ReclamedUser"));

        TableColumn Sub = new TableColumn();
        Sub.setMinWidth(10);
        Sub.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Sub"));

        TableColumn subject = new TableColumn();
        subject.setMinWidth(10);
        subject.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("subject"));

        TableColumn setAs = new TableColumn();
        setAs.setMinWidth(10);
        setAs.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("setAs"));

        TableColumn sendto = new TableColumn();
        sendto.setMinWidth(10);
        sendto.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("sendto"));

        TableColumn daysleft = new TableColumn();
        daysleft.setMinWidth(10);
        daysleft.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("daysleft"));

        TableColumn open = new TableColumn();
        open.setMinWidth(20);
        open.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("open"));

        tableview.getColumns().addAll(etoile, SenderUser, Type, ReclamedUser, Sub, subject, setAs, sendto, daysleft, open);

        List<ReclamationForBO> RFBO = new ArrayList();
        Label hh;

        ImageView etoilex;


        for (Reclamation x : CR.ListerNonLu()) {
            ReclamationForBO Rx = new ReclamationForBO(x.getId());

            
            if(x.getState()==0)     etoilex = new ImageView(new Image("/other/img/star.png"));
            else                    etoilex = new ImageView(new Image("/other/img/star(1).png"));
            etoilex.setFitHeight(15);
            etoilex.setFitWidth(15);
            Rx.setEtoile(etoilex);

            hh = new Label("" +cu.getUserById(x.getUser_id()).getUsername() );
            Rx.setSenderUser(hh);
            Rx.getSenderUser().setTextFill(Color.web("#322e91"));
            Rx.getSenderUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserById(x.getUser_id()));
                }
            });
            
            
            if(x.getType().equals("Autre")) hh = new Label("Suggestion");
            else hh = new Label("Reclame");
            Rx.setType(hh);

            
            if(x.getUserToClaim().equals("NONE")) hh = new Label("");
            else    hh = new Label("" + x.getUserToClaim());
            Rx.setReclamedUser(hh);
            Rx.getReclamedUser().setTextFill(Color.web("#322e91"));
            Rx.getReclamedUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserByName(x.getUserToClaim()));
                }
            });

            hh = new Label("Subject :");
            Rx.setSub(hh);

            hh = new Label("" + x.getSubject());
            Rx.setSubject(hh);

            hh = new Label("Important");
            Rx.setSetAs(hh);
            Rx.getSetAs().setTextFill(Color.web("#591e1e"));
            Rx.getSetAs().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setAsImportant(x.getId());
                    setnumbers();
                    shownonlu();
                }
            });
            
            hh = new Label("Poubelle");
            Rx.setSendto(hh);
            Rx.getSendto().setTextFill(Color.web("#707027"));
            Rx.getSendto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    SendToPoubelle(x.getId());
                    setnumbers();
                    shownonlu();
                }
            });
            
            
            hh = new Label("" + DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), x.getDate()) +" days ago");
            Rx.setDaysleft(hh);

            hh = new Label("Open");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#0076a3"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CR.setReaded(x.getId());
                    OpenReclamation(x);
                    setnumbers();
                }
            });
            
            
            
            RFBO.add(Rx);

        }

        
        for (ReclamationForBO x : RFBO) {
           
            data.add(x);
        }
        tableview.setItems(data);
    }
    private void showlu()
    {
        TypeList.setText("Tout - Lu");    
        tableview.getColumns().clear();
        tableview.getItems().clear();
        
        
        TableColumn etoile = new TableColumn();
        etoile.setMinWidth(10);
        etoile.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, ImageView>("etoile"));

        TableColumn SenderUser = new TableColumn();
        SenderUser.setMinWidth(10);
        SenderUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("SenderUser"));

        TableColumn Type = new TableColumn();
        Type.setMinWidth(10);
        Type.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Type"));

        TableColumn ReclamedUser = new TableColumn();
        ReclamedUser.setMinWidth(10);
        ReclamedUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("ReclamedUser"));

        TableColumn Sub = new TableColumn();
        Sub.setMinWidth(10);
        Sub.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Sub"));

        TableColumn subject = new TableColumn();
        subject.setMinWidth(10);
        subject.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("subject"));

        TableColumn setAs = new TableColumn();
        setAs.setMinWidth(10);
        setAs.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("setAs"));

        TableColumn sendto = new TableColumn();
        sendto.setMinWidth(10);
        sendto.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("sendto"));

        TableColumn daysleft = new TableColumn();
        daysleft.setMinWidth(10);
        daysleft.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("daysleft"));

        TableColumn open = new TableColumn();
        open.setMinWidth(20);
        open.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("open"));

        tableview.getColumns().addAll(etoile, SenderUser, Type, ReclamedUser, Sub, subject, setAs, sendto, daysleft, open);

        List<ReclamationForBO> RFBO = new ArrayList();
        Label hh;

        ImageView etoilex;


        for (Reclamation x : CR.ListerLu()) {
            ReclamationForBO Rx = new ReclamationForBO(x.getId());

            
            if(x.getState()==0)     etoilex = new ImageView(new Image("/other/img/star.png"));
            else                    etoilex = new ImageView(new Image("/other/img/star(1).png"));
            etoilex.setFitHeight(15);
            etoilex.setFitWidth(15);
            Rx.setEtoile(etoilex);

            hh = new Label("" +cu.getUserById(x.getUser_id()).getUsername() );
            Rx.setSenderUser(hh);
            Rx.getSenderUser().setTextFill(Color.web("#322e91"));
            Rx.getSenderUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserById(x.getUser_id()));
                }
            });
            
            
            if(x.getType().equals("Autre")) hh = new Label("Suggestion");
            else hh = new Label("Reclame");
            Rx.setType(hh);

            
            if(x.getUserToClaim().equals("NONE")) hh = new Label("");
            else    hh = new Label("" + x.getUserToClaim());
            Rx.setReclamedUser(hh);
            Rx.getReclamedUser().setTextFill(Color.web("#322e91"));
            Rx.getReclamedUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserByName(x.getUserToClaim()));
                }
            });

            hh = new Label("Subject :");
            Rx.setSub(hh);

            hh = new Label("" + x.getSubject());
            Rx.setSubject(hh);

            hh = new Label("Important");
            Rx.setSetAs(hh);
            Rx.getSetAs().setTextFill(Color.web("#591e1e"));
            Rx.getSetAs().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setAsImportant(x.getId());
                    setnumbers();
                    showlu();
                }
            });
            
            hh = new Label("Poubelle");
            Rx.setSendto(hh);
            Rx.getSendto().setTextFill(Color.web("#707027"));
            Rx.getSendto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    SendToPoubelle(x.getId());
                    setnumbers();
                    showlu();
                }
            });
            
            
            hh = new Label("" + DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), x.getDate()) +" days ago");
            Rx.setDaysleft(hh);

            hh = new Label("Open");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#0076a3"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CR.setReaded(x.getId());
                    OpenReclamation(x);
                    setnumbers();
                }
            });
            
            
            
            RFBO.add(Rx);

        }

        
        for (ReclamationForBO x : RFBO) {
           
            data.add(x);
        }
        tableview.setItems(data);        
    }
    @FXML
    private void luaction(ActionEvent event) {
        showlu();
    }

    
    private void showimportant()
    {
         TypeList.setText("Tout - Important");    
        tableview.getColumns().clear();
        tableview.getItems().clear();
        TableColumn etoile = new TableColumn();
        etoile.setMinWidth(10);
        etoile.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, ImageView>("etoile"));

        TableColumn SenderUser = new TableColumn();
        SenderUser.setMinWidth(10);
        SenderUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("SenderUser"));

        TableColumn Type = new TableColumn();
        Type.setMinWidth(10);
        Type.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Type"));

        TableColumn ReclamedUser = new TableColumn();
        ReclamedUser.setMinWidth(10);
        ReclamedUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("ReclamedUser"));

        TableColumn Sub = new TableColumn();
        Sub.setMinWidth(10);
        Sub.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Sub"));

        TableColumn subject = new TableColumn();
        subject.setMinWidth(10);
        subject.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("subject"));

       

        TableColumn sendto = new TableColumn();
        sendto.setMinWidth(10);
        sendto.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("sendto"));

        TableColumn daysleft = new TableColumn();
        daysleft.setMinWidth(10);
        daysleft.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("daysleft"));

        TableColumn open = new TableColumn();
        open.setMinWidth(20);
        open.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("open"));

        tableview.getColumns().addAll(etoile, SenderUser, Type, ReclamedUser, Sub, subject, sendto, daysleft, open);

        List<ReclamationForBO> RFBO = new ArrayList();
        Label hh;

        ImageView etoilex;


        for (Reclamation x : CR.ListerImportant()) {
            ReclamationForBO Rx = new ReclamationForBO(x.getId());

            
            if(x.getState()==0)     etoilex = new ImageView(new Image("/other/img/star.png"));
            else                    etoilex = new ImageView(new Image("/other/img/star(1).png"));
            etoilex.setFitHeight(15);
            etoilex.setFitWidth(15);
            Rx.setEtoile(etoilex);

            hh = new Label("" +cu.getUserById(x.getUser_id()).getUsername() );
            Rx.setSenderUser(hh);
            Rx.getSenderUser().setTextFill(Color.web("#322e91"));
            Rx.getSenderUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                   OpenProfile(cu.getUserById(x.getUser_id()));
                }
            });
            
            
            if(x.getType().equals("Autre")) hh = new Label("Suggestion");
            else hh = new Label("Reclame");
            Rx.setType(hh);

            
            if(x.getUserToClaim().equals("NONE")) hh = new Label("");
            else    hh = new Label("" + x.getUserToClaim());
            Rx.setReclamedUser(hh);
            Rx.getReclamedUser().setTextFill(Color.web("#322e91"));
            Rx.getReclamedUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserByName(x.getUserToClaim()));
                }
            });

            hh = new Label("Subject :");
            Rx.setSub(hh);

            hh = new Label("" + x.getSubject());
            Rx.setSubject(hh);

            
            
            hh = new Label("Poubelle");
            Rx.setSendto(hh);
            Rx.getSendto().setTextFill(Color.web("#707027"));
            Rx.getSendto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    SendToPoubelle(x.getId());
                    setnumbers();
                    showimportant();
                }
            });
            
            
            hh = new Label("" + DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), x.getDate()) +" days ago");
            Rx.setDaysleft(hh);

            hh = new Label("Open");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#0076a3"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CR.setReaded(x.getId());
                    OpenReclamation(x);
                    setnumbers();
                }
            });
            
            
            
            RFBO.add(Rx);

        }

        
        for (ReclamationForBO x : RFBO) {
           
            data.add(x);
        }
        tableview.setItems(data);
    }
    @FXML
    private void importantaction(ActionEvent event) {
        showimportant();
    }

    private void showpoubelle()
    {     

          TypeList.setText("Tout - Poubelle");    
        tableview.getColumns().clear();
        tableview.getItems().clear();
        TableColumn etoile = new TableColumn();
        etoile.setMinWidth(10);
        etoile.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, ImageView>("etoile"));

        TableColumn SenderUser = new TableColumn();
        SenderUser.setMinWidth(10);
        SenderUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("SenderUser"));

        TableColumn Type = new TableColumn();
        Type.setMinWidth(10);
        Type.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Type"));

        TableColumn ReclamedUser = new TableColumn();
        ReclamedUser.setMinWidth(10);
        ReclamedUser.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("ReclamedUser"));

        TableColumn Sub = new TableColumn();
        Sub.setMinWidth(10);
        Sub.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("Sub"));

        TableColumn subject = new TableColumn();
        subject.setMinWidth(10);
        subject.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("subject"));

        

        TableColumn sendto = new TableColumn();
        sendto.setMinWidth(10);
        sendto.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("sendto"));

        TableColumn daysleft = new TableColumn();
        daysleft.setMinWidth(10);
        daysleft.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("daysleft"));

        TableColumn open = new TableColumn();
        open.setMinWidth(20);
        open.setCellValueFactory(
                new PropertyValueFactory<ReclamationForBO, Label>("open"));

        tableview.getColumns().addAll(etoile, SenderUser, Type, ReclamedUser, Sub, subject, sendto, daysleft, open);

        List<ReclamationForBO> RFBO = new ArrayList();
        Label hh;

        ImageView etoilex;


        for (Reclamation x : CR.ListerTrash()) {
            ReclamationForBO Rx = new ReclamationForBO(x.getId());

            
            if(x.getState()==0)     etoilex = new ImageView(new Image("/other/img/star.png"));
            else                    etoilex = new ImageView(new Image("/other/img/star(1).png"));
            etoilex.setFitHeight(15);
            etoilex.setFitWidth(15);
            Rx.setEtoile(etoilex);

            hh = new Label("" +cu.getUserById(x.getUser_id()).getUsername() );
            Rx.setSenderUser(hh);
            Rx.getSenderUser().setTextFill(Color.web("#322e91"));
            Rx.getSenderUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserById(x.getUser_id()));
                }
            });
            
            
            if(x.getType().equals("Autre")) hh = new Label("Suggestion");
            else hh = new Label("Reclame");
            Rx.setType(hh);

            
            if(x.getUserToClaim().equals("NONE")) hh = new Label("");
            else    hh = new Label("" + x.getUserToClaim());
            Rx.setReclamedUser(hh);
            Rx.getReclamedUser().setTextFill(Color.web("#322e91"));
            Rx.getReclamedUser().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    OpenProfile(cu.getUserByName(x.getUserToClaim()));
                }
            });

            hh = new Label("Subject :");
            Rx.setSub(hh);

            hh = new Label("" + x.getSubject());
            Rx.setSubject(hh);

            
            
            hh = new Label("Supprimer");
            Rx.setSendto(hh);
            Rx.getSendto().setTextFill(Color.web("#707027"));
            Rx.getSendto().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Supprimer(x.getId());
                    setnumbers();
                    showpoubelle();
                }
            });
            
             
            hh = new Label("" + DateConverter.DefferenceBetweenTowDatePerDays(DateConverter.Date_Now_As_Date(), x.getDate()) +" days ago");
            Rx.setDaysleft(hh);

            hh = new Label("Open");
            Rx.setOpen(hh);
            Rx.getOpen().setTextFill(Color.web("#0076a3"));
            Rx.getOpen().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CR.setReaded(x.getId());
                    OpenReclamation(x);
                    setnumbers();
                }
            });
            
            
            
            RFBO.add(Rx);

        }

        
        for (ReclamationForBO x : RFBO) {
           
            data.add(x);
        }
        tableview.setItems(data);
    }
    @FXML
    private void oubelleaction(ActionEvent event) {
        showpoubelle();
    }

    
    
    
    private void OpenProfile(User uu)
    {
        ProfileRController.u=uu;
        LooadUI("profileR");
    }
    
    private void SendToPoubelle(int idReclamation)
    {
        CR.sendtopoubelle(idReclamation);
    }
    private void Supprimer(int idReclamation)
    {
        CR.Supprimer(idReclamation);
    }
    private void setAsImportant(int idReclamation)
    {
       CR.setAsImportant(idReclamation);
    }
    private void OpenReclamation(Reclamation idReclamation)
    {
        OpenReclamationController.R=idReclamation;
        LooadUI("OpenReclamation");
        
    }
private  void LooadUI(String ui)
    {   
        HomeBorder.getChildren().clear();
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("/views/MService/Reclamation/BE/"+ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        HomeBorder.getChildren().add(root); 
    }
    
    
    
    @FXML
    private void nonluaction(ActionEvent event) {
        shownonlu();
    }
}

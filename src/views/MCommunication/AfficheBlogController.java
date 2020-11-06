/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MCommunication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import crud.MCommunication.Crud_BlogPost;
import crud.MCommunication.Crud_Category;
import crud.MCommunication.Crud_Comment;
import entities.MCommunication.Blog_Category;
import entities.MCommunication.Blog_Post;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import techniques.MyConnection;

/**
 * FXML Controller class
 *
 * @author kratos
 */
public class AfficheBlogController implements Initializable {

    public static BorderPane homeBorderPane;
    private BorderPane hbPane ;

    @FXML
    private VBox vbox;
    private Crud_BlogPost cb =new Crud_BlogPost();
    @FXML
    private ImageView addPost;
   
    
    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";
     private static final String ANIMATED_OPTION_SUB_GREEN = "-fx-background-color:#9add87";
    @FXML
    private JFXNodesList nodesList;
    private JFXNodesList nodesListCat;
    @FXML
    private Pane filter;
    @FXML
    private ImageView filter1;
    private Crud_Category CAT = new Crud_Category();
     private Crud_Comment cc = new Crud_Comment();
    ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    private JFXListView<?> catListView;
    @FXML
    private JFXScrollPane scrollCat;
    @FXML
    private JFXTextField searchpost;
    @FXML
    private JFXScrollPane scrollSearch;
    @FXML
    private JFXListView<?> listSearch;
    @FXML
    private ImageView searchBTN;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        hbPane = homeBorderPane;
        BlogController.homeBorderPane= hbPane ;
        Blog_DetailController.homeBorderPane =hbPane;
        AddPostController.homeBorderPane =hbPane;
        vbox.getChildren().clear();

        JFXButton btnMenu = new JFXButton();
        btnMenu.setGraphic(filter1);
        btnMenu.setButtonType(ButtonType.RAISED);
        btnMenu.getStyleClass().addAll(ANIMATED_OPTION_SUB_GREEN);
        
        btnMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                 scrollCat.setVisible(false);
                 catListView.setVisible(false);
                        
            }
        });
        
        JFXButton btnOption1 = new JFXButton("Categories");
        btnOption1.setTooltip(new Tooltip("sort posts per category"));
        btnOption1.setButtonType(ButtonType.RAISED);
        btnOption1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnOption1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              //  vbox.getChildren().clear();
                /* for (Blog_Post bp : cb.fetchBlogsPerCategory(1)) {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MCommunication/Blog.fxml"));

                Parent root = loader.load();
                
                BlogController pc = loader.getController();
                
                vbox.getChildren().add(root);
                System.out.println(bp);
                pc.setBlog(bp);
                pc.init();
               
                        
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
          
        }*/
                observableList.clear();
                scrollCat.setVisible(true);
                catListView.setVisible(true);
                
                for (Blog_Category c : CAT.afficheCategoies()){
                    String catName = c.getName();
                    observableList.add(catName);
                    System.out.println(observableList);
                }
                catListView.setItems(observableList);
   
            }
        });
        
        JFXButton btnOption2 = new JFXButton("Popular Posts");
        btnOption2.setButtonType(ButtonType.RAISED);
        btnOption2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        
        JFXButton btnCollapse = new JFXButton(">>");
        btnCollapse.setTooltip(new Tooltip("Collapse menu"));
        btnCollapse.setOnAction(e->nodesList.animateList(false));
        
        btnCollapse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scrollCat.setVisible(false);
                 catListView.setVisible(false);
            }
        });
        btnOption2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 vbox.getChildren().clear();
                 for (Blog_Post bp : cc.getBlogPerNbcOMMENT()) {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MCommunication/Blog.fxml"));

                Parent root = loader.load();
                
                BlogController pc = loader.getController();
                
                vbox.getChildren().add(root);
                System.out.println(bp);
                pc.setBlog(bp);
                pc.init();
               
                        
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
          
        }
            }
        });

        nodesList.setSpacing(60);
        //nodesList.setStyle("-fx-");
        nodesList.getStyleClass().addAll(FX_TEXT_FILL_WHITE,ANIMATED_OPTION_SUB_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
       
        nodesList.addAnimatedNode(btnMenu);
        nodesList.addAnimatedNode(btnOption1);
        nodesList.addAnimatedNode(btnOption2);
        nodesList.addAnimatedNode(btnCollapse); 
        nodesList.setRotate(90);
         
      
                    
                   
                
        
        addPost.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               LooadUI("/views/MCommunication/AddPost");
                
               
            }
        });
        
        searchBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 vbox.getChildren().clear();
                 for (Blog_Post bp : cb.getBlogPerTitle(searchpost.getText())) {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MCommunication/Blog.fxml"));

                Parent root = loader.load();
                
                BlogController pc = loader.getController();
                
                vbox.getChildren().add(root);
                System.out.println(bp);
                pc.setBlog(bp);
                pc.init();
               
                        
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
          
        }
                
                
            }
        });

        
        for (Blog_Post bp : cb.afficheBlogs()) {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MCommunication/Blog.fxml"));

                Parent root = loader.load();
                
                BlogController pc = loader.getController();
                
                vbox.getChildren().add(root);
                System.out.println(bp);
                pc.setBlog(bp);
                pc.init();
               
                        
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
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
    }

   


 
    @FXML
    private void entred(MouseEvent event) {
        
    }

    @FXML
    private void exited(MouseEvent event) {
        addPost.setImage(new Image("/other/img/add-circular-button.png"));
    }

    @FXML
    private void mouseEntred(MouseEvent event) {
         addPost.setImage(new Image("/other/img/add.png"));
    }
    
     private void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        hbPane.setCenter(root);
    }

    
    @FXML
    private void disableList(MouseEvent event) {
                     scrollCat.setVisible(false);
                     catListView.setVisible(false);
    }

    @FXML
    private void fetchCat(MouseEvent event) {
        
        vbox.getChildren().clear();
        
        /*/////////////////////////////CRUD////////////////////////////////////*/
        
        String catName = (String) catListView.getSelectionModel().getSelectedItem();
        int id = 0;
         try {
        
        String requete4="Select id from category where category.name like '"+catName+"'";      
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs=st2.executeQuery(requete4);
            
            while(rs.next())
            {
              
                id=rs.getInt(1);
               
            }                                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
         /*/////////////////////////////CRUD////////////////////////////////////*/
        
        
                for (Blog_Post bp : cb.fetchBlogsPerCategory(id)) {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MCommunication/Blog.fxml"));

                Parent root = loader.load();
                
                BlogController pc = loader.getController();
                
                vbox.getChildren().add(root);
                System.out.println(bp);
                pc.setBlog(bp);
                pc.init();
               
                        
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
          
        }
    }

    @FXML
    private void searchP(KeyEvent event) {
        
        scrollSearch.setVisible(true);
        listSearch.setVisible(true);
         
        observableList.setAll(cb.searchBlog(searchpost.getText()));
        listSearch.setItems(observableList);
        
    }

    @FXML
    private void returnPost(MouseEvent event) {
        
         String cat = (String) listSearch.getSelectionModel().getSelectedItem();
        
       searchpost.setText(cat) ;
       
       scrollSearch.setVisible(false);
    }

}

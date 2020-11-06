/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MCommunication.backOffice;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import crud.MCommunication.Crud_BlogPost;
import crud.MCommunication.Crud_Category;
import entities.MCommunication.Blog_Category;
import entities.MCommunication.Blog_Post;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kratos
 */
public class BoAfficheBlogController implements Initializable {
    
    private Crud_Category CAT = new Crud_Category();
    ObservableList observableListcat = FXCollections.observableArrayList();
    ObservableList observableListblog = FXCollections.observableArrayList();
    @FXML
    private JFXListView<?> Catlist;
    @FXML
    private JFXTextField addCattextfield;
    @FXML
    private JFXListView<?> bloglist;
    @FXML
    private ImageView addCatbtn;
    private Crud_BlogPost bb = new Crud_BlogPost();
    @FXML
    private JFXTextField slugtextfield;
    @FXML
    private JFXButton updateCat;
    @FXML
    private JFXButton deleteCat;
    @FXML
    private JFXButton deltePost;
    @FXML
    private JFXDialog dialog;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane ppp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int numberOfSquares = 60;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
            }
       
           
           for (Blog_Category c : CAT.afficheCategoies()){              
             String  catname1 = c.getName();
             observableListcat.add(catname1);
             System.out.println(observableListcat);
           }
            
            Catlist.setItems(observableListcat);
            
            addCatbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Blog_Category b = new Blog_Category();
                   
                     
                   
                    if (CAT.searchCat(addCattextfield.getText()).isEmpty()){
                        b.setName(addCattextfield.getText());
                        b.setSlug(slugtextfield.getText());
                        if(CAT.addBlogCtegory(b)==0)
                    {
                          JFXDialogLayout content = new JFXDialogLayout();
                         content.setHeading(new Text("Category added!"));
                         content.setBody(new Text("well done category has been\n" +
                         "added succesfully\n"));
                         StackPane stackPane = new StackPane();
                            dialog =new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    
                         JFXButton button = new JFXButton("Okay");
                             button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                    
                }
            });
            content.setActions(button);
    
                anchor.getChildren().add(stackPane);
                dialog.show();
                    }
                        
                    }else {
                        
                        JFXDialogLayout content = new JFXDialogLayout();
                         content.setHeading(new Text("Category NAME already exists!"));
                         content.setBody(new Text("can't add categories with the\n" +
                         "same name or slug\n"));
                         StackPane stackPane = new StackPane();
                            dialog =new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    
                         JFXButton button = new JFXButton("Okay");
                             button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                    
                }
            });
            content.setActions(button);
    
                anchor.getChildren().add(stackPane);
                dialog.show();
                        
                    }
                    
                    
                }
            });
            
           for (Blog_Post d :bb.afficheBlogs() ){
               
             String  name1 = d.getTitle();
             observableListblog.add(name1);
           }
            
            bloglist.setItems(observableListblog);
        
        
        // TODO
    }    

    @FXML
    private void updateCatBtn(ActionEvent event) {

       if( Catlist.getSelectionModel().getSelectedItem() == null){
            JFXDialogLayout content = new JFXDialogLayout();
                         content.setHeading(new Text("No Category selected !"));
                         content.setBody(new Text("please select a category \n" +
                         "in order to update it\n"));
                         StackPane stackPane = new StackPane();
                            dialog =new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    
                         JFXButton button = new JFXButton("Okay");
                             button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                    
                }
            });
            content.setActions(button);
    
                anchor.getChildren().add(stackPane);
                dialog.show();
       }else {
            addCattextfield.setStyle("-fx-background-color: #18cc27");
            slugtextfield.setStyle("-fx-background-color: #18cc27");
            
            Blog_Category catObj = CAT.getCatByName((String) Catlist.getSelectionModel().getSelectedItem());
        
            addCattextfield.setText(catObj.getName());
            slugtextfield.setText(catObj.getSlug());
            /* Blog_Category b = new Blog_Category();
                    System.out.println("text "+addCattextfield.getText());
                   b= CAT.getCatByName(addCattextfield.getText()); */
            addCatbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                   
                   
                   catObj.setName(addCattextfield.getText());
                    System.out.println("fffff"+addCattextfield.getText());
                   catObj.setSlug(slugtextfield.getText());
                   
                   
                   
                   CAT.updateBlogCategory(catObj, catObj.getId());
                   
                }
            }
            );
       }
        
    }

    @FXML
    private void deleteCatBtn(ActionEvent event) {
        String name = (String) Catlist.getSelectionModel().getSelectedItem();
        CAT.deleteBlogCategory(name);
     
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Category deleted !"));
                content.setBody(new Text("Make sure to update posts with a\n" +
            "new category \n"));
                 StackPane stackPane = new StackPane();
                dialog =new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    
            JFXButton button = new JFXButton("Okay");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                    
                }
            });
            content.setActions(button);
    
                anchor.getChildren().add(stackPane);
                dialog.show();
    }

    @FXML
    private void deletePostBtn(ActionEvent event) {
        
        List<Blog_Post> blogObj = bb.getBlogPerTitle((String) bloglist.getSelectionModel().getSelectedItem());
       Blog_Post b= blogObj.get(0);
       bb.deleteBlog(b.getId());
        
    }
    
      public void generateAnimation(){
        Random rand = new Random();
        int sizeOfSqaure = rand.nextInt(50) + 1;
        int speedOfSqaure = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(420);
        int startYPoint = rand.nextInt(350);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,420-sizeOfSqaure ,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(420-sizeOfSqaure,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 350 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - sizeOfSqaure);
                break;

            default:
                System.out.println("default");
        }

        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        anchor.getChildren().add(anchor.getChildren().size()-1,r1);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MCommunication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import crud.MCommunication.Crud_BlogPost;
import crud.MCommunication.Crud_Category;
import entities.MCommunication.Blog_Post;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static views.MCommunication.AfficheBlogController.homeBorderPane;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
/**
 * FXML Controller class
 *
 * @author kratos
 */
public class AddPostController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea body;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXTextField slug;

     private Crud_BlogPost bb = new Crud_BlogPost();
     private Crud_Category CAT = new Crud_Category();
    @FXML
    private JFXDialog dialog;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton addBlogPost;
    
     public static BorderPane homeBorderPane;
    private BorderPane hbPane ;
    @FXML
    private Pane violet;
    @FXML
    private JFXScrollPane scrollCategory;
    @FXML
    private JFXListView<?> listViewCat;
    
    private Set<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();
   
    HTMLEditor htmlEditor = new HTMLEditor();
    @FXML
    private Pane contentEditor;
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       hbPane = homeBorderPane;
       
        int numberOfSquares = 30;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
            }
        contentEditor.getChildren().add(htmlEditor);
        htmlEditor.prefWidthProperty().bind(contentEditor.widthProperty());
        htmlEditor.prefHeightProperty().bind(contentEditor.heightProperty());
        
        
        
    }    

    @FXML
    private void addPostBtn(ActionEvent event) {
        
        
        
        Blog_Post b = new Blog_Post();
        b.setTitle(title.getText());
       // b.setBody(body.getText());
      
       
        b.setBody(htmlEditor.getHtmlText());
        System.out.println("BODY "+b.getBody());
        b.setCategoryName(category.getText());
        b.setSlug(slug.getText());
        b.setImg("logo1.png");
        System.out.println(b.toString());
         
        
        if (b.getBody().equals("")||b.getTitle().equals("")||b.getCategoryName().equals("")||htmlEditor.getHtmlText().length()<100){
            
            JFXDialogLayout content = new JFXDialogLayout();
            
            if (htmlEditor.getHtmlText().length()<100){
                content.setHeading(new Text("Post content required !"));
                content.setBody(new Text("You need to enter the content of your post in  \n" +
            "oder to complete the process\n"));
            }
            
             if (b.getTitle().equals("")){
                content.setHeading(new Text("Post title required  !"));
                content.setBody(new Text("You need to enter the title of your post in  \n" +
            "oder to complete the process\n"));
            }
             
              if (b.getCategoryName().equals("")){
                content.setHeading(new Text("Post category required !"));
                content.setBody(new Text("You need to enter the category of your post in  \n" +
            "oder to complete the process\n"));
            }
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
            
        }else if(htmlEditor.getHtmlText().length()>100) {
        
            bb.addBlog(b);
            
             JFXDialogLayout content = new JFXDialogLayout();
            
                content.setHeading(new Text("Well done !"));
                content.setBody(new Text("You post has been added,by pressing redirect you will be redirected to  \n" +
            "the home page\n"));
            
                StackPane stackPane = new StackPane();
                dialog =new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    
            JFXButton button = new JFXButton("Redirect");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    dialog.close();
                     LooadUI("/views/MCommunication/AfficheBlog");
                }
            });
            content.setActions(button);
    
                anchor.getChildren().add(stackPane);
                dialog.show();
        
        }
         
    }
    
      private void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        hbPane.setCenter(root);
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
        violet.getChildren().add(violet.getChildren().size()-1,r1);
    }

    

    @FXML
    private void returnCat(MouseEvent event) {
        String cat = (String) listViewCat.getSelectionModel().getSelectedItem();
        
       category.setText(cat) ;
       
       scrollCategory.setVisible(false);
    }

    @FXML
    private void searchCatEvent(KeyEvent event) {
        scrollCategory.setVisible(true);
        listViewCat.setVisible(true);
         
        observableList.setAll(CAT.searchCat(category.getText()));
        listViewCat.setItems(observableList);
    }

    @FXML
    private void createCon(KeyEvent event) {
        
         contentEditor.setVisible(true);
         body.setText("edit your post with HTML Editor");

        
    }


   
    
       
       
}

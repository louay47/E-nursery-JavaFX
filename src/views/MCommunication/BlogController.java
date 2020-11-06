/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MCommunication;

import com.jfoenix.controls.JFXClippedPane;
import crud.MCommunication.Crud_Comment;
import entities.MCommunication.Blog_Post;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import techniques.DateConverter;

/**
 * FXML Controller class
 *
 * @author kratos
 */
public class BlogController implements Initializable {

    @FXML
    private ImageView blogImg;
    @FXML
    private Label title;
    @FXML
    private Label createdAt;
    @FXML
    private Label category;
    @FXML
    private Button readMore;
    @FXML
    private Label CommentsCount;
    private Crud_Comment cc = new Crud_Comment();
    private Blog_Post blog ;
    public static BorderPane homeBorderPane;
    private BorderPane hbPane ;
    @FXML
    private Pane view;
    
    public void setBlog(Blog_Post blog) {
        this.blog = blog;
    }

    HTMLEditor htmlEditor = new HTMLEditor();
     WebView webView = new WebView();
     WebEngine webEngine = webView.getEngine();
     
     
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hbPane = homeBorderPane;
        
        webView.prefWidthProperty().bind(view.widthProperty());
       webView.prefHeightProperty().bind(view.heightProperty());
       
       webView.setDisable(true);
        
       
    }    
    public void init()
    {
        
        
     
        title.setText(blog.getTitle());
        createdAt.setText(DateConverter.Date_To_String(blog.getCreated_at()));
        category.setText(blog.getCategoryName());
        htmlEditor.setHtmlText(blog.getBody());
        webEngine.loadContent(htmlEditor.getHtmlText(), "text/html");
       // body.setText(htmlEditor.getHtmlText());
       
        view.getChildren().add(webView);
       
        blogImg.setImage(new Image ("/other/img/"+blog.getImg()));
        
        CommentsCount.setText(""+cc.commentsCount(blog.getId()));
    }
    @FXML
    private void readMoreAction(ActionEvent event) {
        Blog_DetailController.blog = blog ;
        LooadUI("/views/MCommunication/Blog_Detail");
    }
    
    
   private void LooadUI(String ui)
    {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex) {System.err.println(ex.getMessage());     }
        hbPane.setCenter(root);
    }
    
}

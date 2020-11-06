/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MCommunication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import crud.MCommunication.Crud_BlogPost;
import crud.MCommunication.Crud_Comment;
import entities.MCommunication.Comment;
import entities.MCommunication.Blog_Post;
import entities.MGestionUtilisateur.User;
import entities.MService.Reclamation.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import techniques.DateConverter;
import techniques.MyConnection;
import techniques.ProfanitiesLouay;
import static views.MCommunication.BlogController.homeBorderPane;
import static views.MCommunication.Blog_DetailController.blog;
import views.MGestionUtilisateur.LoginController;

/**
 * FXML Controller class
 *
 * @author kratos
 */
public class Blog_DetailController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private Label createdAt;
    @FXML
    private Label updatedAt;
    @FXML
    private Button home;
    private TextArea body;
    @FXML
    private TextArea addComment;
    @FXML
    private Button addCommentBtn;
    @FXML
    private ImageView img;
    @FXML
    private TextField category;
    @FXML
    private Label author;
    @FXML
    private Label authorPostCount;
    
    public static Blog_Post blog ;
    @FXML
    private VBox comments;
    
    private Crud_Comment cc = new Crud_Comment();
    private Crud_BlogPost bb = new Crud_BlogPost();
    @FXML
    private Button delete;
    @FXML
    private Button update;
    
    public static BorderPane homeBorderPane;
    private BorderPane hbPane ;
    @FXML
    private Button valider;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDialog dialog;
     HTMLEditor htmlEditor = new HTMLEditor();
     WebView webView = new WebView();
     WebEngine webEngine = webView.getEngine();
    @FXML
    private ScrollPane scroollll;
    @FXML
    private Pane view;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        view.getChildren().add(webView);
        webView.prefWidthProperty().bind(view.widthProperty());
       webView.prefHeightProperty().bind(view.heightProperty());
       webView.setDisable(true);
       hbPane = homeBorderPane;
       if(LoginController.us.getId()!=blog.getUser_id() && !LoginController.us.getRoles().contains("ROLE_ADMIN")){
            update.setVisible(false);
            delete.setVisible(false);
        }
        if(LoginController.us.getRoles().contains("ROLE_ADMIN"))
        {
            update.setVisible(false);
        }
        title.setText(blog.getTitle());
        createdAt.setText(DateConverter.Date_To_String(blog.getCreated_at()));
        updatedAt.setText(DateConverter.Date_To_String(blog.getUpdated_at()));
        category.setText(blog.getCategoryName());
        htmlEditor.setHtmlText(blog.getBody());
        webEngine.loadContent(htmlEditor.getHtmlText(), "text/html");
        //body.setText(blog.getBody());
        
        img.setImage(new Image ("/other/img/"+blog.getImg()));
        int i =  bb.postsCount(blog.getUser_id());
        String postcountString = " "+i;
        authorPostCount.setText(postcountString);
        
         String requete = "SELECT username FROM fos_user WHERE id ="+blog.getUser_id();
         try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet u = st.executeQuery(requete);
            System.out.println("utilisateur trouvé");
          
                if (u.next()){
                    String username = u.getString(1) ;
                 System.out.println(username);
                 
                 author.setText(username);
                    
                }
                 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        
        }
        
        postReplies();
        
        
    }    

    @FXML
    private void homeAction(ActionEvent event) {
    }

    @FXML
    private void addCommentBtnAction(ActionEvent event) {
        Comment c = new Comment();
        ProfanitiesLouay.loadConfigs();
        
        String s=  ProfanitiesLouay.filterText(addComment.getText());
      
        System.out.println(s);
        if(s.contains("This message was blocked because a bad word was found."))
        {
            JFXDialogLayout content = new JFXDialogLayout();
            
                content.setHeading(new Text("Can't add this comment!!!"));
                content.setBody(new Text(s));
            
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
        else
        {
             c.setContent(addComment.getText());
             c.setPost_id(blog.getId());
       
        
        String requete = "SELECT username FROM fos_user WHERE id ="+LoginController.us.getId();
         try {
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet u = st.executeQuery(requete);
            System.out.println("utilisateur trouvé");
          
                if (u.next()){
                    String username = u.getString(1) ;
                 System.out.println(username);
                 
                 c.setUsrname(username);
                    
                }
                 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        
        }
         
         c.setCreatedAt(DateConverter.Date_Now_As_Date());
         
             System.out.println(c.toString());
         cc.addComment(c);
         
        LooadUI("/views/MCommunication/Blog_Detail");

         postReplies();
                 
        }
       

         
    
    }
    
    public void postReplies (){
      for (Comment C : cc.afficheComments(blog.getId())) {
          
      
        
        Pane p1 = new Pane();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        Pane p2 = new Pane();
        Pane p3 = new Pane();
        Pane p4 = new Pane();
        p4.setPrefWidth(100);
        VBox v1 =new VBox();
        Separator s = new Separator();
        ImageView iv = new ImageView();
        Label username = new Label();
        Label date = new Label();
        Label content = new Label();
        Button dComment = new Button("delete");
        Button uComment = new Button("update");
        
        if(!LoginController.us.getUsername().equals(C.getUsrname()) && !LoginController.us.getRoles().contains("ROLE_ADMIN")){
            dComment.setVisible(false);
            uComment.setVisible(false);
        }
        if(LoginController.us.getRoles().contains("ROLE_ADMIN"))
        {
            uComment.setVisible(false);
        }
        
        dComment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cc.deleteComment(C.getId());
                comments.getChildren().clear();
                postReplies();
            }
        });
        
         uComment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               Comment c1 = new Comment();
               TextArea newContent = new TextArea();
               newContent.setPrefHeight(80);
               newContent.setText(C.getContent());
               Button OK = new Button("OK");
               comments.getChildren().clear();
               comments.getChildren().addAll(newContent , OK);
               
               OK.setOnMouseClicked(new EventHandler<MouseEvent>() {
                   @Override
                   public void handle(MouseEvent event) {
                       
                                
                                c1.setPost_id(blog.getId());

                                c1.setUsrname(C.getUsrname());
                                System.out.println(C.getUsrname());
                                c1.setContent(newContent.getText());
                                c1.setModified(1);
                                cc.updateComment(c1, C.getId());
                                
                                comments.getChildren().clear();
                                postReplies();
                   }
               });
                                
        
            }
        });
       
         
        
        v1.getChildren().addAll(username , date , content );
       // p4.getChildren().addAll(dComment , uComment);
       // p3.setPrefWidth(50);
        p3.getChildren().add(v1);
        p2.getChildren().add(iv);
        
        
        hb2.getChildren().addAll(dComment , uComment);
        hb1.getChildren().addAll(p2 , p3 ,p4,hb2);

       // p1.setPrefHeight(500);
        p1.getChildren().add(hb1);
        
        comments.getChildren().add(p1);
        comments.getChildren().add(s);
        comments.setPrefHeight(3000);
        
        
        username.setText(C.getUsrname());
        date.setText(DateConverter.Date_To_String(C.getCreatedAt()));
        content.setText(C.getContent());
        
        iv.setImage(new Image("/other/img/logo1.png"));
        iv.setFitHeight(80);
        iv.setFitWidth(80);
         }
      }

    @FXML
    private void deletePostBtn(ActionEvent event) {
        
        bb.deleteBlog(blog.getId());
       LooadUI("/views/MCommunication/AfficheBlog");
    }

    @FXML
    private void updatePostBtn(ActionEvent event) {
        
        Blog_Post b ;
        b = blog ;
      //  body.setEditable(true);
        webView.setVisible(true);
        title.setEditable(true);
        category.setEditable(true);
        view.getChildren().add(htmlEditor);
        htmlEditor.prefWidthProperty().bind(view.widthProperty());
        htmlEditor.prefHeightProperty().bind(view.heightProperty());
        
        valider.setVisible(true);
        
        valider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                     b.setBody(htmlEditor.getHtmlText());
                     b.setTitle(title.getText());
                     b.setCategoryName(category.getText());
                bb.updateBlog(b, blog.getId());
                
                LooadUI("/views/MCommunication/Blog_Detail");
            }
        });
        
        
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





                              
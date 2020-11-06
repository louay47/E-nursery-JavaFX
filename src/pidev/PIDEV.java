/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import techniques.AutoJob;
import techniques.BCryptPasswordEncoder;
import techniques.BadWords;
import techniques.DateConverter;
import techniques.Notif;
import techniques.Notification;
import techniques.SMS;
import views.MGestionUtilisateur.LoginController;


/**
 *
 * @author mahjoub
 */
public class PIDEV extends Application {
  
    @Override
    public void start(Stage primaryStage) {
        
        
      
      
  
       


        try {
          //  Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFE.fxml"));
           Parent root = FXMLLoader.load(getClass().getResource("/views/MGestionUtilisateur/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("E-NERSERY");
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
            
            
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
      
          
       launch(args);
       
         
        
 }
    
}

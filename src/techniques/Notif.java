/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

/**
 *
 * @author mahjoub
 */
public class Notif {
    
    /**
     * 
     * @param sujet
     * @param description
     * @param s ==> windows  OR  fx
     * 
     * windows for windows notification
     * fx      for JavaFx  notification
     * 
     */
    public  Notif(String sujet,String description,String s)
       {
           if(s.equals("windows")) notifWindows(sujet,description);
           if(s.equals("fx")) notif1(sujet,description);
           
       
               
           
       }

  
    private void notif1(String sujet,String description)
    {
        Notifications notify = Notifications.create().title(sujet)
				.text(description)
				.hideAfter(javafx.util.Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notify.darkStyle();
		notify.show();
         
    }
private void notifWindows(String sujet,String description)
{
    try {
               //Obtain only one instance of the SystemTray object
               SystemTray tray = SystemTray.getSystemTray();
               
               //If the icon is a file
               Image image = Toolkit.getDefaultToolkit().createImage("/other/img/logo1.png");
               //Alternative (if the icon is on the classpath):
               //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
               
               TrayIcon trayIcon = new TrayIcon(image, "VALIDATION_PUBLICATION");
               //Let the system resize the image if needed
               trayIcon.setImageAutoSize(true);
               //Set tooltip text for the tray icon
               trayIcon.setToolTip("VOUS UNE NOUVELLE PUBLICATION ACCEPTER");
               tray.add(trayIcon);
               
               trayIcon.displayMessage(sujet,description , TrayIcon.MessageType.INFO);
           } catch (AWTException ex) {
               System.out.println(ex.getMessage());
           }
      
}
}

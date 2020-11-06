/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import crud.MMarketing.Evenement.Crud_Evenement;
import crud.MMarketing.Promotion.Crud_Promotion;
import crud.MService.Reclamation.BE.CrudReclamation;
import crud.MVente.Livraison.CrudLivraison;
import it.sauronsoftware.cron4j.Scheduler;
import views.MGestionUtilisateur.LoginController;

/**
 *
 * @author mahjoub
 */
public class AutoJob {

    private static void runDeleteMarketing() {

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.

        s.schedule("0 0 */1 * *", new Runnable() {
            int i = 0;

            public void run() {
                Crud_Evenement.supprimerEvenement2();
                        Crud_Promotion.supprimerPromotion2();
            }
        });

        s.start();

    }
private static void runDeleteLivraison() {

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.

        s.schedule("*/1 * * * *", new Runnable() {
            int i = 0;

            public void run() {
                CrudLivraison.supprimer();
            }
        });

        s.start();

    }
    private static void runUnbanUser() {

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.

        s.schedule("0 0 */1 * *", new Runnable() {
            int i = 0;

            public void run() {

                i++;
                System.out.println("j= " + i);
                CrudReclamation.AutoUnbanUser();
            }
        });

        // Starts the scheduler.
        s.start();

        // Will run for ten minutes.
        /*try {
			Thread.sleep(1000L * 60L * 10L);
                        
		} catch (InterruptedException e) {
			;
		}*/
        // Stops the scheduler.
        // s.stop();
    }

    private static void runViderPoubelle() {

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.

        s.schedule("0 0 1 1 *", new Runnable() {
            int i = 0;

            public void run() {

                i++;
                System.out.println("i= " + i);
                CrudReclamation.AutoViderPoubelle();

            }
        });

        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        /*try {
			Thread.sleep(1000L * 60L * 10L);
                        
		} catch (InterruptedException e) {
			;
		}*/

        // Stops the scheduler.
        // s.stop();
    }

    private static void runNotif() {

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.

        s.schedule("0 */1 * * *", new Runnable() {
            int i = 0;

            public void run() {
                new Notification('c');
                new Notification();
            }
        });

        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        /*try {
			Thread.sleep(1000L * 60L * 10L);
                        
		} catch (InterruptedException e) {
			;
		}*/

        // Stops the scheduler.
        // s.stop();
    }

    public static void Start() {
        if (LoginController.us.getRoles().contains("ROLE_ADMIN")) {            
            new Notification();
            AutoJob.runNotif();
        }
        
        AutoJob.runUnbanUser();
        AutoJob.runViderPoubelle();
        AutoJob.runDeleteMarketing();
        AutoJob.runDeleteLivraison();
        
    }

}

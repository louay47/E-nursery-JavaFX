package techniques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
    
        public static MyConnection myCNX;
        String url ="jdbc:mysql://localhost:3306/pidev";
        String login="root";
        String mdp="";
        Connection cnx;
            
    private MyConnection() {
       
        try {
        cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance()
    {
        if(myCNX ==null)
        {
            myCNX=new MyConnection();
        }
        return myCNX;
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    
    
}

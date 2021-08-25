package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author E
 */
public class Conectar {

    public static Connection getConnection() {
        try{
            Class.forName("org.sqlite.JDBC");            
            return DriverManager.getConnection("jdbc:sqlite:banco");
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}

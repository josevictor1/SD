package Connect;

import java.sql.*;
/**
 *
 * @author jose
 */
public class ConnectPostgress {
       
    public static Connection conectabd() throws ClassNotFoundException{
        try{
            //Class.forName("org.postgresql.Driver");
            Connection con;   
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Teste01","postgres","jose146932111");
            return con;
        }
        catch(SQLException error ){
            return null;
        }
    }
     
}
package lk.ijse.moneylanding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ~saku~
 */
public class DBConnection {
    private static DBConnection dBConnection;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        connection =  DriverManager.getConnection("jdbc:mysql://localhost/moneylanding", "root", "sakunikalhari");        
    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException{
        if(dBConnection==null){
            dBConnection=new DBConnection();
        }
        return dBConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
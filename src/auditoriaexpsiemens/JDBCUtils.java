/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

/**
 *
 * @author rene
 */

import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
    
    private  static BasicDataSource ds = null;
    
    static {
        //System.out.println("DataSource Initialization...");
        
        ds = new BasicDataSource();
        //ds.setDefaultAutoCommit(true);
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUsername("scia");
            ds.setPassword("RaPa4#9RmSM");
        ds.setUrl("jdbc:oracle:thin:@192.168.100.9:1522:mexico09");
        //ds.setUrl("jdbc:oracle:thin:@187.210.42.27:1521:mexico09"); // base de respaldo pruebas
        //ds.setUrl("jdbc:oracle:thin:@192.168.100.237:1521:mexico09"); // base en maquina virtual
        ds.setInitialSize(1);
        ds.setMaxActive(10);
        ds.setMaxIdle(-1);
        ds.setMaxWait(-1);
        ds.setRemoveAbandoned(true);
        ds.setRemoveAbandonedTimeout(3);
        ds.setLogAbandoned(true);
        ds.setValidationQuery("SELECT 1 FROM dual ");
        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        //System.out.println("Initialization..........[ok]");
    }
    
    /**
     * private constructor for static class
     */
    private JDBCUtils() { }
    
    public static Connection getConnection()
            throws SQLException
    {
        //System.out.print("Request connection...");
        if (ds == null) {
            throw new SQLException("DataSource is null.");
        }
        //System.out.println("[ok]");
        return ds.getConnection();
    }
    
    public static void closePooledConnections() throws SQLException{
        if (ds != null ) {
            ds.close(); //ods.close();
        }
    }
    
    public static void listCacheInfos() throws SQLException{
        
    }
}

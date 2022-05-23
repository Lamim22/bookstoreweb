
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Gerencia a conexao com o banco de dados.Possui as instruções necessarias para
 * conectar ao banco
 * @author devsys-a
 */
public class MySQLconnection {
    
    //Define strings de conexao com o banco
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30:3306/glr_bookstore";
    
    private static final String USER = "lamim";
    private static final String PASS = "21262801";
    
    /**.
     * Cria conexao com o banco de dados MySQL
     * @return
     */
    public static Connection getConnection() {
     
            try {
                Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro na conexao com o BD.Verifique!", ex);
            
        }catch(ClassNotFoundException ex){
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro na conexao com o BD.Verifique!", ex);
        }
     
    }
    
    /**
     * 
     * @param conn 
     */
    
    public static void closeConnection(Connection conn){
        try{ 
            if (conn != null){
                conn.close();
            }        
        }catch (SQLException ex) {
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Fecha a conexão com o DB
     * @param conn Conexão
     * @param stmt Statement
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        closeConnection(conn);
        
        try {
            if (stmt != null){
                stmt.close();               
            }
        }catch (SQLException ex){
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(conn, stmt);
        
        try{
            if (rs != null) {
                rs.close();       
            }
        }catch (SQLException ex) {
            Logger.getLogger(MySQLconnection.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
}

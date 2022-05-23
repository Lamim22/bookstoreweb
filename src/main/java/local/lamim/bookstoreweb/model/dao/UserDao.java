/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.lamim.bookstoreweb.model.dao;

import connection.MySQLconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.lamim.bookstoreweb.model.bean.PojoUser;

/**
 *
 * @author devsys-a
 */
public class UserDao {
    
    private static final String SQL_INSERT = "INSERT INTO user("
            + "email, password, fullname) "
            + "VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user "
            +"WHERE id = ? ";
            
    private static final String SQL_UPDATE = "UPDATE book SET email = ?,"
            + "password = ?, fullname = ? "
            + "WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ? ";
    
    private static final String SQL_SELECT_AUTHENTIC = "SELECT fullname FROM user WHERE email  = ? AND password = ?";    
    public void create(PojoUser u) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());
          
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDao.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        }catch (SQLException sQLException) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, 
                    sQLException);       
        }finally{
            //Encerra a conexao com o banco e o statement.
            MySQLconnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Atualiza um registro na tabela
     * @param p PojoUser a ser atualizado 
     */
    public List<PojoUser> getResults() {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser u = null;
        List<PojoUser> listaPojoUsers = null;
        
        
        try{
            //Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setFullname(rs.getString("fullname"));
            rs = stmt.executeQuery();
            
            //Carrega os dados do Result rs, converte em PojoUser e add na lista de retorno
            
            listaPojoUsers = new ArrayList<>();
            
            
            while (rs.next( )) {
                u = new PojoUser();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("titulo"));
                u.setPassword(rs.getString("Password"));
                u.setFullname(rs.getString("fullname"));
               
                listaPojoUsers.add(u);
                
            }
            
        }catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPojoUsers;
        
    }
    
    /**
     * Atualiza um registro na tabela book
     * @param p PojoUser a ser atualizado.
     */
    
    public PojoUser getResultById(int id) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser b = null;
        
     
        
        try {
            
        stmt = conn.prepareStatement(SQL_SELECT_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
          
            if (rs.next()) {
                b = new PojoUser();
                b.setId(rs.getInt("id"));
                b.setEmail(rs.getString("email"));
                b.setPassword(rs.getString("Password"));
                b.setFullname(rs.getString("fullname"));
               
            }
   
        }catch (SQLException sQLException) {
              Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally {
            // Encerra a conexao com o banco e o statement.
             MySQLconnection.closeConnection(conn, stmt, rs);
        }
        return b;

    }
    
    public void update(PojoUser b) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, b.getId());
            stmt.setString(2, b.getEmail());
            stmt.setString(3, b.getPassword());
            stmt.setString(4, b.getFullname());
                
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, "Update: " + auxRetorno);
             
        }catch (SQLException sQLException) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, sQLException);
       
        }finally{
            //Encerra a conexao com o banco e o statement
             MySQLconnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Exclui um book com base do ID fornecido.
     * @param id IDentificacao do book.
     */
    
    public void delete(int id) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            //Executa Query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDao.class.getName()).log(Level.INFO, null, "Delete! " + auxRetorno);
        } catch (SQLException err) {
            
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, err);
            
        }finally{
            MySQLconnection.closeConnection(conn, stmt);
        }
        
    }
    
    public PojoUser checkLogin (String email, String password){
        
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PojoUser u = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_AUTHENTIC);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
        
            
            if (rs.next()) {
               u = new PojoUser();
               u.setFullname(rs.getString("fullname"));
               u.setEmail(rs.getString("email"));
               u.setPassword(rs.getString("password"));                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return u;
    } 
        
        
    
 }
 
  
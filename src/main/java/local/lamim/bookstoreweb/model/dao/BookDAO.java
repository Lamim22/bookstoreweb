/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.lamim.bookstoreweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import connection.MySQLconnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.lamim.bookstoreweb.model.bean.Book;

/**
 *
 * @author devsys-a
 */

public class BookDAO {
    private static final String SQL_INSERT = "INSERT INTO book("
            + "titulo, autor, preco) "
            + "VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    private static final String SQL_SELECT_ID = "SELECT * FROM book "
            +"WHERE id = ? ";
            
    private static final String SQL_UPDATE = "UPDATE book SET titulo = ?,"
            + "autor = ?, preco = ? "
            + "WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM book WHERE id = ? ";
    
    
    /**
     * 
     * @param b 
     */
    
    public void create(Book b) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, b.getTitulo());
            stmt.setString(2, b.getAutor());
            stmt.setDouble(3, b.getPreco());
          
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        }catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, 
                    sQLException);       
        }finally{
            //Encerra a conexao com o banco e o statement.
            MySQLconnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Atualiza um registro na tabela
     * @param p Book a ser atualizado 
     */
    public List<Book> getResults() {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        List<Book> listaBooks = null;
        
        
        try{
            //Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dados do Result rs, converte em Book e add na lista de retorno
            
            listaBooks = new ArrayList<>();
            
            
            while (rs.next( )) {
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("Autor"));
                b.setPreco(rs.getDouble("preco"));
               
                listaBooks.add(b);
                
            }
            
        }catch(SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaBooks;
        
    }
    
    /**
     * Atualiza um registro na tabela book
     * @param p Book a ser atualizado.
     */
    
    public Book getResultById(int id) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        
     
        
        try {
            
        stmt = conn.prepareStatement(SQL_SELECT_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
          
            if (rs.next()) {
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("Autor"));
                b.setPreco(rs.getDouble("preco"));
               
            }
   
        }catch (SQLException sQLException) {
              Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally {
            // Encerra a conexao com o banco e o statement.
             MySQLconnection.closeConnection(conn, stmt, rs);
        }
        return b;

    }
    
    public void update(Book b) {
        Connection conn = MySQLconnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, b.getId());
            stmt.setString(2, b.getTitulo());
            stmt.setString(3, b.getAutor());
            stmt.setDouble(4, b.getPreco());
                
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, "Update: " + auxRetorno);
             
        }catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sQLException);
       
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
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null, "Delete! " + auxRetorno);
        } catch (SQLException err) {
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, err);
            
        }finally{
            MySQLconnection.closeConnection(conn, stmt);
        }
        
    }
 }
    
    
    

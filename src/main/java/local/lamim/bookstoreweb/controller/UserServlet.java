/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.lamim.bookstoreweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import local.lamim.bookstoreweb.model.bean.PojoUser;
import local.lamim.bookstoreweb.model.dao.UserDao;

/**
 *
 * @author devsys-a
 */
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getPathInfo();
        Logger.getLogger(UserServlet.class.getName()).log(Level.INFO, "Path solocitado {0}", action);

        if (action == null) {
            action = "/list";
        }
        
        try {
            switch(action){
                case "/new":
                    showNewRegister(request, response);
                case "/register":
                    registerUser(request, response);
                    break;
                case "/list":
                
                default:
                    Logger.getLogger(UserServlet.class.getName()).log(Level.INFO, "Carregando a ShowList...");
                    showList(request, response);
            }
        } catch (Exception e) {
        }
       
    }
     private void showList(HttpServletRequest request, HttpServletResponse response)
         throws SQLException, IOException, ServletException{
         UserDao userDao = new UserDao();
         List<PojoUser> listaUser = userDao.getResults();
         
         Logger.getLogger(UserServlet.class.getName()).log(Level.INFO, "Carregados {0} usuarios.", listaUser.size());
         
         request.setAttribute("listaUser", listaUser);
         RequestDispatcher dispacher = request.getRequestDispatcher("/UserList.jsp");
         dispacher.forward(request, response);
     }
                                                                   
     private void showNewRegister(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
         
         RequestDispatcher dispacher = request.getRequestDispatcher("/UserForm.jsp");
         dispacher.forward(request, response);
     }
     
     private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        
        UserDao userDAO = new UserDao();
        PojoUser novoCadastro = new PojoUser();
        
        novoCadastro.setEmail(request.getParameter("userEmail"));
        novoCadastro.setPassword(request.getParameter("userPassword"));
        novoCadastro.setFullname(request.getParameter("userName"));
        
        userDAO.create(novoCadastro);
        response.sendRedirect("/bookstoreweb/index.html");
//        RequestDispatcher dispatcher = request.getRequestDispatchrer("/BookList.jsp");
//        dispatcher.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

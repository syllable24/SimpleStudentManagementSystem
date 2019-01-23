/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.SessionManager;
import ejb.UserData;
import ejb.entities.Lehrer;
import ejb.entities.LogIns;
import ejb.entities.LogInsFacade;
import ejb.entities.Studenten;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ralph
 */
@WebServlet(name="LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

    @EJB
    private UserData userData;

    @EJB
    private LogInsFacade logInLookup;
    
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
        
        boolean bNoUserFound = false;
        
        HttpSession session = request.getSession(true);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            String userName = request.getParameter("UserName");
            String userPassword = request.getParameter("UserPassword");
            
            if((userName != null) && (userPassword != null)){
               
                LogIns login = new LogIns();
                login.setUsernameMD5(userName);
                login.setPasswortMD5(userPassword);

                Object result = logInLookup.determineUserType(login);
                
                if(result instanceof Studenten){
                    userData.setCurrentStudent(((Studenten) result));
                    session.setAttribute("UserID", userData);
                    response.sendRedirect("StudentServlet");
                }
                else if(result instanceof Lehrer){
                    userData.setCurrentLehrer(((Lehrer) result));
                    session.setAttribute("UserID", userData);
                    response.sendRedirect("LehrerMenu");
                }
                else{
                    bNoUserFound = true;
                }
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogIn</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Student Manager LogIn</h1>");
            
            out.println("<form method='post'>");
            out.println("<b>Username:</b></br>");
            out.println("<input type='text' name='UserName'>");
            out.println("</br>");
            out.println("<b>Passwort:</b></br>");
            out.println("<input type='password' name='UserPassword'>");
            out.println("</br>");
            out.println("<input type='submit' value='Log In'>");
            out.println("</form>");

            if(bNoUserFound){
                out.println("<p style = 'border:3px; border-style:solid; border-color:#FF0000; padding:1em;'> "
                        + "Log In Daten nicht gefunden! </p>");
            }
            
            out.println("</body>");
            out.println("</html>");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("/ErrorServlet");
        }
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

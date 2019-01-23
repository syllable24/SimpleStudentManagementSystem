/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UserData;
import ejb.entities.Lehrer;
import ejb.entities.LehrerFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ralph
 */
@WebServlet(name="LehrerKursnoteEintragenServlet", urlPatterns = {"/LehrerKursnoteEintragenServlet"})
public class LehrerKursnoteEintragenServlet extends HttpServlet {

    @EJB
    private LehrerFacade lehrerF;
    
    
    /**
     * 
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
        try (PrintWriter out = response.getWriter()) {
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LehrerKursnoteEintragenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            UserData userData = (UserData) request.getSession().getAttribute("UserID");  
//            long userID = userData.getCurrentUserID();        
//            Lehrer lehr = lehrerF.find(userID);            
//            
//            String lehrName = lehr.getNachname() + " " + lehr.getVorname();
//            Date lehrBirthdate = lehr.getGeburtsdatum();  
//            
//            out.println("<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
//                        + lehrName
//                        + "<br>"  + lehrBirthdate.toString() + " </p>");    
            
            
            out.println("</body>");
            out.println("</html>");
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

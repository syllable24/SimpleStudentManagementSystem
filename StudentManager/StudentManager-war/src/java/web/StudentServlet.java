/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UserData;
import ejb.entities.Kurse;
import ejb.entities.Kursnoten;
import ejb.entities.StudentenFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Stellt eine Übersicht über alle Kurse mit ihren Lehrern und den erhaltenen 
 * Noten für einen Studenten dar.
 */
@WebServlet(name="StudentServlet", urlPatterns = {"/StudentServlet"}) 
public class StudentServlet extends HttpServlet {
    
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
        try (PrintWriter out = response.getWriter()) {
           
            request.getSession(true);
            UserData userData = (UserData) request.getSession().getAttribute("UserID");                                      
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(request.getParameter("LogOut") != null){                
                response.sendRedirect("LogIn");
            }
            
            out.println(userData.getUserOverview());
            
            displayKursnoten(out, userData);

            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Stellt eine Tabelle bestehend aus Kurs, Lehrer und Note für den aktuell
     * eingeloggten User dar.
     * 
     * @param out PrintWriter auf Output-Ziel.
     * @param userData Aktuell eingeloggter User.
     */
    private void displayKursnoten(final PrintWriter out, UserData userData) {
        out.println("<table border = '1'>");
        out.println("<td>Kurs</td>");
        out.println("<td>Lehrer</td>");
        out.println("<td>Note</td>");
        
        List<Kursnoten> kursNotenListe = userData.getCurrentStudent().getKursnoten();
        for (Kursnoten kursNote : kursNotenListe) {
            Kurse kurs = kursNote.getKurse();
            
            out.println("<tr>");
            out.println("<td>" + kurs.getBezeichnung() + "</td>");
            out.println("<td>" + kurs.getLehrer().getNachname() + " " +kurs.getLehrer().getVorname() + "</td>");
            out.println("<td>" + kursNote.getNote() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
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

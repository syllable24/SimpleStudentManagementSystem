/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.entities.Kurse;
import ejb.entities.Studenten;
import ejb.entities.StudentenFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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
@WebServlet(name="StudentServlet", urlPatterns = {"/StudentServlet"}) 
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentenFacade studentenFacade;
    
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
            long userID = (long) request.getSession().getAttribute("UserID");  
            //List<Kurse> kursListe = studentenFacade.getKurse(userID);     
            
            Studenten stud = studentenFacade.find(userID);
            List<Kurse> kursListe = stud.getKurses();
            String studName = stud.getVorname() + " " + stud.getNachname();
            String stuStudigang = stud.getStudiengaenge().getBezeichnung();
            Date studBirthdate = stud.getGeburtsdatum();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + studName
                        + "<br>"  + studBirthdate.toString() 
                        + "<br>"  + stuStudigang                         
                        + "<br> " + stud.getGruppe().getBezeichnung() + " </p>");
            
            out.println("<table border = '1'>");
            out.println("<td>Kurs</td>");            
            out.println("<td>Lehrer</td>");
            out.println("<td>Note</td>");

            for (Kurse kurs : kursListe) {
                out.println("<tr>");                
                out.println("<td>" + kurs.getBezeichnung() + "</td>");                
                out.println("<td>" + kurs.getLehrer().getNachname() + " " +kurs.getLehrer().getVorname() + "</td>");
                out.println("<td>" + kurs.getNote() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");   

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

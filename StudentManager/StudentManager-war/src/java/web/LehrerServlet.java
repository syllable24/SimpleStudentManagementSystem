/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UserData;
import ejb.entities.Kurse;
import ejb.entities.KurseFacade;
import ejb.entities.Lehrer;
import ejb.entities.LehrerFacade;
import ejb.entities.Studenten;
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
@WebServlet(name="LehrerServlet", urlPatterns = {"/LehrerServlet"})
public class LehrerServlet extends HttpServlet {

    @EJB 
    private LehrerFacade lehrerF;
    
    @EJB
    private KurseFacade kurseFacade;
          
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

        String strPressedButton = null;
        long chosenCourseID = 0;
        
        
        //long userID = (long) request.getSession().getAttribute("UserID");  
        //long userID = sblb.getCurrentUserID();
        UserData userData = (UserData) request.getSession().getAttribute("UserID");  
        long userID = userData.getCurrentUserID();        
        Lehrer lehr = lehrerF.find(userID);        
        
        List<Kurse> kursListe = lehr.getKurs();
        String lehrName = lehr.getNachname() + " " + lehr.getVorname();
        Date lehrBirthdate = lehr.getGeburtsdatum();        
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LehrerServlet</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + lehrName
                        + "<br>"  + lehrBirthdate.toString() + " </p>");
            
            out.println("<form method='post'>");
            out.println("<table border = '1'>");
            out.println("<td>Kurs</td>");
            out.println("<td># Studenten</td>");                        
            
            for (Kurse kurs : kursListe) {
                out.println("<tr>");                
                out.println("<td> <input type='submit' value ='" + kurs.getBezeichnung() + "' name='" + kurs.getBezeichnung() +"'></td>");
                out.println("<td>" + kurs.getStudentens().size() + "</td>");
                out.println("</tr>");                
                
                if (strPressedButton == null){
                    strPressedButton = request.getParameter(kurs.getBezeichnung());
                    chosenCourseID = kurs.getId();                    
                }
            }
            out.println("</form>");                        
            
            out.println("<form method='post'>");
            out.println("<b>Kurs: " + strPressedButton + " </b>");
            out.println("<table border = '1'>");
            out.println("<td>Student</td>");
            out.println("<td>Note</td>"); 
            
            Kurse kurs = kurseFacade.find(chosenCourseID);
            List<Studenten> studList = kurs.getStudentens();            
            
            for(Studenten stud : studList){
                out.println("<tr>");
                out.println("<td> " + stud.getNachname() +  " " + stud.getVorname() + " </td>");
                                
                
                out.println("<td> <input type='text' name='noteStudID" +stud.getId()+ "' value='" +"'></td>");
                out.println("</tr>");
            }
            
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UserData;
import ejb.entities.Kurse;
import ejb.entities.KurseFacade;
import ejb.entities.Kursnoten;
import ejb.entities.Lehrer;
import ejb.entities.LehrerFacade;
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
@WebServlet(name="LehrerMenu", urlPatterns = {"/LehrerMenu"})
public class LehrerMenu extends HttpServlet {

    @EJB 
    private LehrerFacade lehrerF;
    
    @EJB
    private StudentenFacade studetnF;   
    
    @EJB
    private KurseFacade kurseF;
    
    private boolean bStudentSuche;
    private boolean bKursSuche;
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LehrerMenu</title>");            
            out.println("</head>");
            out.println("<body>");
            
            UserData userData = (UserData) request.getSession().getAttribute("UserID");                          
            out.println(userData.getUserOverview());            
            
            // <editor-fold defaultstate="collapsed" desc="Menu Buttons anzeigen und Klicks handeln">
            out.println("<form method = 'post'>");            
            out.println("<input type ='submit' value='Student suchen' name='SearchStudent'>");
            out.println("<input type ='submit' value='Kurs suchen' name='SearchKurs'>");
            out.println("<input type ='submit' value='Kursnote eintragen' name='NoteEintragen'>");
            out.println("<input type ='submit' value='Student aufnehmen' name='StudentAufnehmen'>");            
            out.println("</form>");                        
            
            if(!(bStudentSuche || bKursSuche)){
                if (request.getParameter("SearchStudent") != null){
                    bStudentSuche = true;
                    out.println("<form method = 'post'>");
                    out.println("<b>Student Suchen</b>");
                    out.println("<input type='text' name='StudentSearchString'>");
                    out.println("<input type = 'submit' value='Student Suchen' name='SucheStudent'>");                
                    out.println("</form>");
                }
                if (request.getParameter("SearchKurs") != null){
                    bKursSuche = true;
                    out.println("<form method = 'post'>");
                    out.println("<b>Kurs Suchen</b>");
                    out.println("<input type='text' name='KursSearchString'>");
                    out.println("<input type = 'submit' value='Kurs Suchen' name='SucheKurs'>");                
                    out.println("</form>");
                }
            }
            if(request.getParameter("NoteEintragen") != null){
                response.sendRedirect("LehrerNotenEintragenServlet");
            }
            if(request.getParameter("StudentAufnehmen") != null){
                response.sendRedirect("LehrerStudentEinschreibenServlet");
            }
            
            //</editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="Studenten Suche Resultat in Tabelle schreiben">            
            if(bStudentSuche && request.getParameter("StudentSearchString") != null){
                bStudentSuche = false;
                String searchString = request.getParameter("StudentSearchString");
                List<Studenten> result = studetnF.getStudentByName(searchString);
                
                if(result.size() > 0){                
                    for(Studenten stud : result){
                        out.println("<b> " + stud.getNachname() +  " " + stud.getVorname() + " </b>");

                        out.println("<table border = '1'>");
                        out.println("<td>Kurs</td>");                                    
                        out.println("<td>Note</td>");                     
                        List<Kursnoten> kursnoten = stud.getKursnoten();

                        for(Kursnoten kursnote : kursnoten){
                            out.println("<tr>");                    
                            out.println("<td> " + kursnote.getKurse().getBezeichnung() + "</td>");
                            out.println("<td> " + kursnote.getNote() + "</td>");
                            out.println("</tr>");                                                                
                        }
                    }
                }
                else{
                    out.println("<p style = 'border:3px; border-style:solid; border-color:#FF0000; padding:1em;'> "
                               + "Keine Resultate!</p>");                    
                }                                                                  
            }                      
            // </editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="Kurs Suche Resultat in Tabelle schreiben">            
            if(bKursSuche && request.getParameter("KursSearchString") != null){
                bKursSuche = false;
                String searchString = request.getParameter("KursSearchString");
                List<Kurse> result = kurseF.getKursByName(searchString);
                
                if(result.size() > 0){                                    
                    for(Kurse kur : result){
                        out.println("<b> " + kur.getBezeichnung() + " </b>");

                        out.println("<table border = '1'>");
                        out.println("<td>Student</td>");                                    
                        out.println("<td>Note</td>");                     
                        List<Kursnoten> kursnoten = kur.getKursnoten();
                        
                        for(Kursnoten kursnote : kursnoten){
                            out.println("<tr>");                    
                            out.println("<td> " + kursnote.getStudent().getNachname() + " " + kursnote.getStudent().getVorname() + "</td>");
                            out.println("<td> " + kursnote.getNote() + "</td>");
                            out.println("</tr>");                                                                
                        }                        
                    }
                }    
                else{
                    out.println("<p style = 'border:3px; border-style:solid; border-color:#FF0000; padding:1em;'> "
                                + "Keine Resultate!</p>");                    
                }                
            }                           
            // </editor-fold>
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

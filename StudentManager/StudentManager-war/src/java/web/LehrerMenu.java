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
import ejb.entities.Studenten;
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
 * Dem User ist es möglich, aus 4 Varianten zu wählen.
 * Kurs Suche && Studenten Suche: Erzeugt zusätzlich Textbox und Submit-Button 
 * auf der aktuellen Seite. Nach Eingabe eines Suchtextes werden die Ergebnisse
 * in einer erzeugten Tabelle angezeigt.
 * 
 * Noten eintragen: Öffnet LehrerNotenEintragenServlet.
 * 
 * Studenten in Kurse eintragen: Öffnet LehrerStudentEintragenServlet.
 * 
 * @author Ralph & Bianca
 */
@WebServlet(name="LehrerMenu", urlPatterns = {"/LehrerMenu"})
public class LehrerMenu extends HttpServlet {
    
    @EJB
    private StudentenFacade studetnF;   
    
    @EJB
    private KurseFacade kurseF;
        
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
            
            if(request.getParameter("LogOut") != null){                                
                response.sendRedirect("LogIn");
            }            
            
            displayMenuButtons(out);

            if(request.getParameter("SearchStudent") != null){
                displayStudentSucheForm(out);
            }
            if(request.getParameter("SearchKurs") != null){
                displayKurseSuchenForm(out);
            }
            if(request.getParameter("NoteEintragen") != null){
                response.sendRedirect("LehrerNotenEintragenServlet");
            }
            if(request.getParameter("StudentAufnehmen") != null){
                response.sendRedirect("LehrerStudentEinschreibenServlet");
            }                        
            if(request.getParameter("StudentSearchString") != null){
                displayStudentSearchResults(request, out);                                                  
            }                                               
            if(request.getParameter("KursSearchString") != null){                
                displayKursSearchResults(request, out);                
            }                                       
            out.println("</body>");
            out.println("</html>");
        }
    }

     /**
     * Stellt die Ergebnisse einer Kurs-Suche dar.
     * Die Ergebnisse werden in mehreren Tabellen für je einen Kurs mit den 
     * Spalten Student und Note dargestellt.
     * 
     * @param request HTTP-Request aus dem der Search-String ausgelesen wird.
     * @param out  PrintWriter auf Output-Ziel.
     */
    private void displayKursSearchResults(HttpServletRequest request, final PrintWriter out) {
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

    /**
     * Stellt die Ergebnisse einer Studenten-Suche dar.
     * Die Ergebnisse werden in mehreren Tabellen für je einen Studenten mit den 
     * Spalten Kurs und Note dargestellt.
     * 
     * @param request HTTP-Request aus dem der Search-String ausgelesen wird.
     * @param out  PrintWriter auf Output-Ziel.
     */
    private void displayStudentSearchResults(HttpServletRequest request, final PrintWriter out) {
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

    /**
     * Stellt die Menu-Buttons des Lehrer-Interafes dar.
     * Besteht aus 4 Buttons: Student suchen; Kurs suchen; Noten eintragen;
     * Student aufnehmen.
     * 
     * @param out PrintWriter auf Output-Ziel.
     */
    private void displayMenuButtons(final PrintWriter out) {
        out.println("<form method = 'post'>");
        out.println("<input type ='submit' value='Student suchen' name='SearchStudent'>");
        out.println("<input type ='submit' value='Kurs suchen' name='SearchKurs'>");
        out.println("<input type ='submit' value='Kursnote eintragen' name='NoteEintragen'>");
        out.println("<input type ='submit' value='Student aufnehmen' name='StudentAufnehmen'>");
        out.println("</form>");
    }

    /**
     * Stellt das Kurse-Suchen Form dar.
     * Das Form besteht aus einem Textfeld und einem Submit-Button.
     * 
     * @param out PrintWriter auf Output-Ziel.
     */
    private void displayKurseSuchenForm(final PrintWriter out) {        
        out.println("<form method = 'post'>");
        out.println("<b>Kurs Suchen</b>");
        out.println("<input type='text' name='KursSearchString'>");
        out.println("<input type = 'submit' value='Kurs Suchen' name='SucheKurs'>");
        out.println("</form>");
    }

    /**
     * Stellt das Student-Suchen Form dar.
     * Das Form besteht aus einem Textfeld und einem Submit-Button.
     * 
     * @param out PrintWriter auf Output-Ziel.
     */
    private void displayStudentSucheForm(final PrintWriter out) {        
        out.println("<form method = 'post'>");
        out.println("<b>Student Suchen</b>");
        out.println("<input type='text' name='StudentSearchString'>");
        out.println("<input type = 'submit' value='Student Suchen' name='SucheStudent'>");
        out.println("</form>");
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

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
import ejb.entities.KursnotenFacade;
import ejb.entities.LehrerFacade;
import ejb.entities.Studenten;
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
 *
 * @author ralph
 */
@WebServlet(name="LehrerNotenEintragenServlet", urlPatterns = {"/LehrerNotenEintragenServlet"})
public class LehrerNotenEintragenServlet extends HttpServlet {
    
    @EJB
    private KurseFacade kurseFacade;
          
    @EJB
    private KursnotenFacade kursnotenFacade;
    
    private long chosenCourseID;
    private boolean bUpdateSent;
    
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
                
        UserData userData = (UserData) request.getSession().getAttribute("UserID");                          
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LehrerServlet</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println(userData.getUserOverview());
            
            strPressedButton = displayLehrerKurse(out, userData, strPressedButton, request);                        
            
            if (strPressedButton != null || request.getParameter("Commit") != null){                
                displayNotenEintragenSicht(out, strPressedButton, request, response);
            }            
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Erstellt eine Form zum Eintragen von Noten für Studenten des 
     * ausgewählten Kurses.
     * Das Form besteht aus Tabelle und Submit Button. Die Tabelle besteht 
     * aus Studenten und deren Noten. Die Noten sind Textfelder, die dazu 
     * verwendet werden können, um Noten einzutragen. 
     * 
     * @param out PrintWriter auf Output-Ziel.
     * @param strPressedButton ausgewählter Kurs.
     * @param request Ermittelung, ob der Submit Button gedrückt wurde und 
     * Zuordnung des Eingabefeldes der Note zum Studenten. 
     */
    private void displayNotenEintragenSicht(final PrintWriter out, String strPressedButton, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out.println("<form method='post'>");
        out.println("<b>Kurs: " + strPressedButton + " </b>");
        out.println("<table border = '1'>");
        out.println("<td>Student</td>");
        out.println("<td>Note</td>");
        
        Kurse kurs = kurseFacade.find(chosenCourseID);
        List<Kursnoten> kursNotenList = kurs.getKursnoten();
        
        for(Kursnoten kn : kursNotenList){
            Studenten stud = kn.getStudent();
            out.println("<tr>");
            out.println("<td> " + stud.getNachname() +  " " + stud.getVorname() + " </td>");
            out.println("<td> <input type='text' value='" + kn.getNote() + "' name='Note"+ stud.getId() +"'></td>");
            out.println("</tr>");
            
            if(request.getParameter("Commit") != null){
                String insertedNote = request.getParameter("Note"+stud.getId());                                
                sendUpdatedKursnote(kn, kurs, insertedNote, stud);                
                response.sendRedirect("LehrerNotenEintragenServlet");
            }
        }        
        out.println("</form>");
        out.println("<input type='submit' value='Noten eintragen' name='Commit'>");
    }

    /**
     * Erstellt ein Kursnoten Objekt, welches dazu verwendet werden soll einem 
     * Studenten in einem Kurs eine Note zuordnet.
     * 
     * @param kn Neue Kursnote
     * @param kurs Kurs, der eine neue Note bekommt.
     * @param insertedNote Note, die eingetragen werden soll.
     * @param stud Student, der eine Note bekommt
     */
    private void sendUpdatedKursnote(Kursnoten kn, Kurse kurs, String insertedNote, Studenten stud) {
        Kursnoten update = new Kursnoten();
        update.setId(kn.getId());
        update.setKurse(kurs);
        update.setNote(insertedNote);
        update.setStudent(stud);
        kursnotenFacade.updateKursnote(update);        
    }

    /**
     * Stellt eine Tabelle mit allen Kursen des eingeloggtem Lehrers dar.
     * Die Tabelle besteht aus Kurs und Anzahl der Studenten. Die Kurse sind 
     * Buttons, welche dazu verwendet werden können, um die Noten-Eintragen-Sicht
     * des gewählten Kurses anzuzeigen.
     *     
     * @param out PrintWriter auf Output-Ziel.
     * @param userData Aktuell eingeloggter User.
     * @param strPressedButton Ausgewählter Kurs.
     * @param request Zur Ermittelung des gedrückten Buttons.
     * @return Ausgewählten Button. 
     */
    private String displayLehrerKurse(final PrintWriter out, UserData userData, String strPressedButton, HttpServletRequest request) {
        out.println("<form method='post'>");
        out.println("<table border = '1'>");
        out.println("<td>Kurs</td>");
        out.println("<td># Studenten</td>");
        List<Kurse> kursListe = userData.getCurrentLehrer().getKurs();
        for (Kurse kurs : kursListe) {
            out.println("<tr>");
            out.println("<td> <input type='submit' value ='" + kurs.getBezeichnung() + "' name='" + kurs.getBezeichnung() +"'></td>");
            out.println("<td>" + kurs.getKursnoten().size() + "</td>");
            out.println("</tr>");
            
            if (strPressedButton == null){                
                strPressedButton = request.getParameter(kurs.getBezeichnung());
                chosenCourseID = kurs.getId();
            }
        }
        out.println("</form>");
        return strPressedButton;
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

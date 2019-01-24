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
import ejb.entities.Studenten;
import ejb.entities.StudentenFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name="LehrerStudentEinschreibenServlet", urlPatterns = {"/LehrerStudentEinschreibenServlet"})
public class LehrerStudentEinschreibenServlet extends HttpServlet {

    @EJB
    private StudentenFacade studentenFacade;
    
    @EJB
    private KurseFacade kurseFacade;
    
    @EJB
    private KursnotenFacade kursnotenFacade;
    
    private long selectedStudentID;
    
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
            out.println("<title>Servlet LehrerStudentEinschreibenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            UserData userData = (UserData) request.getSession().getAttribute("UserID");                          
            out.println(userData.getUserOverview());               
            
            // 1 Alle Studenten anzeigen 
            List<Studenten> allStudents = studentenFacade.findAll();
            out.println("<form method='post'>");            
            out.println("<table border = '1'>");
            out.println("<td>Student</td>");            
                        
            for(Studenten stud : allStudents){                
                out.println("<tr>");
                out.println("<td> <input type='submit' "
                            + "value='" + stud.getNachname() + " " + stud.getVorname() + "' "
                            + "name ='StudID" + stud.getId()+ "'></td>");
                out.println("</tr>");
                
                if(request.getParameter("StudID"+stud.getId()) != null){                                        
                    selectedStudentID = stud.getId();                    
                }
            }
            out.println("</table>");
            out.println("</form>");         
            
            if(selectedStudentID != 0){
                Studenten stud = studentenFacade.find(selectedStudentID);
                List<Kursnoten> studentKursnoten = stud.getKursnoten();                
                HashMap<Long, Kurse> studKurseEingetragen = new HashMap<>();
                List<Kurse> studKurseNichtEingetragen = new ArrayList<>();
                List<Kurse> allKurse = kurseFacade.findAll();
                
                if(studentKursnoten != null){
                    for (Kursnoten kursnote : studentKursnoten) {
                        studKurseEingetragen.put(kursnote.getId(), kursnote.getKurse());
                    }
                }                 
                
                out.println("<form method='post'>");            
                out.println("<table border = '1'>");
                out.println("<td>Kurse</td>");
                out.println("<td>Einschreiben</td>");
                
                String strChecked;
                for(Kurse kur : allKurse){                    
                    if(studKurseEingetragen.containsValue(kur)){
                        strChecked="checked";
                    }
                    else{
                        strChecked="";
                        studKurseNichtEingetragen.add(kur);
                    }
                    
                    out.println("<tr>");
                    out.println("<td> " + kur.getBezeichnung() + " </td>");                    
                    out.println("<td> <input type='checkbox' name='KursID"+ kur.getId() +"'" + strChecked + "> </td>");
                    out.println("</tr>");       

                    if(request.getParameter("Commit") != null){
                       //String checkedKurs = request.getParameter("KursID"+kur.getId());                       
                       for(Long key : studKurseEingetragen.keySet()){
                           Kurse k = studKurseEingetragen.get(key);
                           
                           if(request.getParameter("KursID"+k.getId())==null){
                               
                               Kursnoten kn = new Kursnoten();                               
                               kn.setId(key);
                               //delete ausführen!!
                               kursnotenFacade.deletetudentKursLink(kn);                               
                           }                           
                       }                                              
                    }
                }
                out.println("<input type='submit' value='Student bearbeiten' name='Commit'>");   
                out.println("</table>");
                out.println("</form>");                
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

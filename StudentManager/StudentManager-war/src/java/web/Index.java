package web;

import ejb.entities.Studenten;
import ejb.entities.StudentenFacade;
import ejb.entities.Studiengaenge;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ralph
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends HttpServlet {

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
        
        try(PrintWriter out = response.getWriter()) {
            InitialContext ctx = new InitialContext(); 
            request.getSession(true);            
            String studiName  = request.getParameter("StudentVorname");            
            
            StudentenFacade stfa = (StudentenFacade) ctx.lookup("java:global/StudentManager/StudentManager-ejb/StudentenFacade");
            
            if (studiName != null){                                                                   
                QueueConnectionFactory qf = (QueueConnectionFactory) ctx.lookup("TestConnectionFactory");
                QueueConnection qc = qf.createQueueConnection();
                qc.start();

                QueueSession ses = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = (Queue)ctx.lookup("TestDestination");            
                QueueSender queueSender = ses.createSender(queue);
                
                Studiengaenge studi = new Studiengaenge();               
                studi.setBezeichnung(studiName);                
                ObjectMessage bjm = ses.createObjectMessage(studi);
                queueSender.send(bjm);                                                                
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Index</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Index at " + request.getContextPath() + "</h1>");                                                
            
            out.println("<form>");                    
            out.println("<input type='text' name='StudentVorname'>");
            out.println("<input type='text' name='StudentNachname'>");
            out.println("<input type='submit'><br/>");            
            out.println("</form>");            
            
            out.println("<table border = '1'>");
            out.println("<td>Vorname</td>");
            out.println("<td>Nachname</td>");                 

            List list = studentenFacade.findAll();
            for(Iterator it = list.iterator(); it.hasNext();){
                out.println("<tr>");
                Studenten st = (Studenten) it.next();
                out.println("<td>" + st.getVorname() + "</td>");
                out.println("<td>" + st.getNachname() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");            
                        
            out.println("</body>");
            out.println("</html>");
        }
        catch (NamingException | JMSException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Throwable t){
            t.printStackTrace();
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

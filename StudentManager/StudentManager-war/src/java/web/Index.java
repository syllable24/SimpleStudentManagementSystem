package web;

import ejb.entities.Studenten;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
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
            
        try {
            String studentVorname = request.getParameter("StudentVorname");
            String studentNachname = request.getParameter("StudentNachname");
            
            if ((studentVorname != null) && (studentNachname != null)){                                        
                InitialContext ctx = new InitialContext();            
                QueueConnectionFactory qf = (QueueConnectionFactory) ctx.lookup("TestConnectionFactory");
                QueueConnection qc = qf.createQueueConnection();
                qc.start();

                QueueSession ses = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = (Queue)ctx.lookup("TestDestination");            
                QueueSender queueSender = ses.createSender(queue);

                Studenten st = new Studenten();
                st.setNachname(studentNachname);
                st.setVorname(studentVorname);
                ObjectMessage bjm = ses.createObjectMessage(st);

                queueSender.send(bjm);
            }
            
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Index</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Index at " + request.getContextPath() + "</h1>");

            out.println("<form>");
            out.println("Vorname: <input type='text' name='StudentVorname'><br/>");
            out.println("Nachname: <input type='text' name='StudentNachname'><br/>");
            out.println("<input type='submit'><br/>");

            out.println("</form>");
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

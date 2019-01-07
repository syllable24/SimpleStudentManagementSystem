package edu;

import org.eclipse.jetty.security.*;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

public class Login {

    static class HelloWebHandler extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().print("<h2>Hello World1</h2>");
        }
    }

    static class LoginHandler extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("<html><form method='GET' action='/j_security_check'>"
                    + "<input type='text' name='j_username'/>"
                    + "<br><input type='password' name='j_password'/>"
                    + "<br><input type='submit' value='Login'/></form></html>");
        }
    }
    
    static class LoginErrorHandler extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.err.println("test");
            response.getWriter().print("<html><body>Anmeldung nicht erfolgreich<br><a href='/login'>Anmelden</a></body></html>");
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context1 = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);
        ServletContextHandler context2 = new ServletContextHandler(server, "/login", ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);
        ServletContextHandler context3 = new ServletContextHandler(server, "/loginerror", ServletContextHandler.SESSIONS );
        
        
        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(403, "/login/page1.xhtml");

        context1.addServlet(new ServletHolder(new HelloWebHandler()), "/*");
        //context1.setErrorHandler(errorHandler);
        context2.addServlet(new ServletHolder(new LoginHandler()),"/*");
        context3.addServlet(new ServletHolder(new LoginErrorHandler()),"/*");

        // Security handler
        
        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[]{"admin1","admin2"});
        constraint.setAuthenticate(true);

        ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setConstraint(constraint);
        constraintMapping.setPathSpec("/*");

        //HashLoginService loginService = new HashLoginService("login");
        //loginService.setConfig("realm.properties");
        JDBCLoginService loginService = new JDBCLoginService("dblogin");
        loginService.setConfig("dbrealm.properties");
        
        server.addBean(loginService);
        
        FormAuthenticator authenticator = new FormAuthenticator("/login", "/loginerror", false);
        
        server.addBean(new ErrorHandler() {
        @Override
        protected void handleErrorPage(
                HttpServletRequest request, Writer writer, int code, String message) throws IOException {
                    writer.write("<!DOCTYPE<html><head><title>Error</title></head><html><body>Fehler<br>"
                    + code + " - " + HttpStatus.getMessage(code) + "</body></html>");
                }
            }, false);

        
        // Session handler
        
        ConstraintMapping[] mappings = new ConstraintMapping[]{constraintMapping};
        context1.setSessionHandler(new SessionHandler());
        context1.setSecurityHandler(createSecurityHandler(loginService, authenticator, mappings));

        context2.setSessionHandler(new SessionHandler());
        
        ContextHandlerCollection collection = new ContextHandlerCollection();
        collection.setHandlers(new Handler[]{context1,context2,context3});
        
        server.setHandler(collection);

        server.start();
        server.join();
    }

    static SecurityHandler createSecurityHandler(LoginService loginService, Authenticator authenticator, ConstraintMapping[] constraints) {
        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.setLoginService(loginService);
        securityHandler.setAuthenticator(authenticator);
        securityHandler.setConstraintMappings(constraints);
        return securityHandler;
    }
}


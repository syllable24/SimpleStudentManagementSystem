
package edu.demo;

import at.co.newe.DateServlet;
import edu.service.SchoolResourceEJB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.scan.StandardJarScanner;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.security.Authenticator;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.security.Constraint;
import org.glassfish.jersey.servlet.ServletContainer;



/**
 * Example of using JSP's with embedded jetty and using a
 * lighter-weight ServletContextHandler instead of a WebAppContext. 
 * 
 * This example is somewhat odd in that it uses custom tag libs which reside
 * in a WEB-INF directory, even though WEB-INF is not meaningful to
 * a ServletContextHandler. This just shows that once we have
 * properly initialized the jsp engine, you can even use this type of
 * custom taglib, even if you don't have a full-fledged webapp.
 * 
 */
public class Main
{
    // Resource path pointing to where the WEBROOT is
    private static final String WEBROOT_INDEX = "/webroot/";
    
    /**
     * JspStarter for embedded ServletContextHandlers
     * 
     * This is added as a bean that is a jetty LifeCycle on the ServletContextHandler.
     * This bean's doStart method will be called as the ServletContextHandler starts,
     * and will call the ServletContainerInitializer for the jsp engine.
     *
     */
    public static class JspStarter extends AbstractLifeCycle implements ServletContextHandler.ServletContainerInitializerCaller
    {
        JettyJasperInitializer sci;
        ServletContextHandler context;
        
        public JspStarter (ServletContextHandler context)
        {
            this.sci = new JettyJasperInitializer();
            this.context = context;
            this.context.setAttribute("org.apache.tomcat.JarScanner", new StandardJarScanner());
        }

        @Override
        protected void doStart() throws Exception
        {
            ClassLoader old = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(context.getClassLoader());
            try
            {
                sci.onStartup(null, context.getServletContext());   
                super.doStart();
            }
            finally
            {
                Thread.currentThread().setContextClassLoader(old);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        LoggingUtil.config();

        Main main = new Main(port);
        main.start();
        main.waitForInterrupt();
    }

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private int port;
    private Server server;

    public Main(int port)
    {
        this.port = port;
    }

    public void start() throws Exception
    {
        server = new Server();
        
        // Define ServerConnector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
    
        // Base URI for servlet context
        URI baseUri = getWebRootResourceUri();
        LOG.info("Base URI: " + baseUri);
        
        // ErrorPageHandler
        
        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(403, "/login/page1.xhtml");
        
        server.addBean(new ErrorHandler() {
        @Override
        protected void handleErrorPage(
                HttpServletRequest request, Writer writer, int code, String message) throws IOException {
                    writer.write("<!DOCTYPE<html><head><title>Error</title></head><html><body>Fehler<br>"
                    + code + " - " + HttpStatus.getMessage(code) + "</body></html>");
                }
            }, false);

        // Create Servlet context
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.SECURITY);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setResourceBase(baseUri.toASCIIString());
        
        // Since this is a ServletContextHandler we must manually configure JSP support.
        enableEmbeddedJspSupport(servletContextHandler);
    
        // Add Application Servlets
        servletContextHandler.addServlet(DateServlet.class, "/date/");
        // Create Example of mapping jsp to path spec
        ServletHolder holderAltMapping = new ServletHolder();
        holderAltMapping.setName("subject.jsp");
        holderAltMapping.setForcedPath("/edu/parameter/subject.jsp");
        servletContextHandler.addServlet(holderAltMapping, "/edu/parameter/");
        
        // Restservice Mapper
        /*
        ServletHolder holderRestMapping = new ServletHolder();
        holderRestMapping.setName("RestService.jsp");
        holderRestMapping.setForcedPath("/edu/rest/service.jsp");
        servletContextHandler.addServlet(holderRestMapping, "/edu/rest/");
        */
        // Configure restContextHandler
	ServletContextHandler restContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
	restContextHandler.setContextPath("/edu/rest");
        servletContextHandler.setResourceBase(baseUri.toASCIIString());
 	// Create Servlet Container
        ServletHolder restHolder = restContextHandler.addServlet(ServletContainer.class, "/*");
        restHolder.setInitOrder(1);
        restHolder.setInitParameter("jersey.config.server.provider.packages","edu.rest");
	restHolder.setInitOrder(0);
	// Tells the Jersey Servlet which REST service/class to load.
	//**restHolder.setInitParameter("jersey.config.server.provider.classnames", RestMessage.class.getCanonicalName());
        restHolder.setInitParameter("jersey.config.server.provider.classnames", SchoolResourceEJB.class.getCanonicalName());
    
        // Default Servlet (always last, always named "default")
        ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
        holderDefault.setInitParameter("resourceBase", baseUri.toASCIIString());
        holderDefault.setInitParameter("dirAllowed", "true");
        servletContextHandler.addServlet(holderDefault, "/");
        
        // ConstrainMapping
        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[]{"admin1","admin2"});
        constraint.setAuthenticate(true);

        ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setConstraint(constraint);
        constraintMapping.setPathSpec("/*");
        
        ConstraintMapping[] mappings = new ConstraintMapping[]{constraintMapping};
        
        // Security handler
        HashLoginService loginService = new HashLoginService("login");
        loginService.setConfig("realm.properties");
 
        //JDBCLoginService loginService = new JDBCLoginService("dblogin");
        //loginService.setConfig("dbrealm.properties");
        
        server.addBean(loginService);
        
        FormAuthenticator authenticator = new FormAuthenticator("/login/login.jsp", "/login/loginerror.jsp", false);
        
        servletContextHandler.setSessionHandler(new SessionHandler());
        servletContextHandler.setSecurityHandler(createSecurityHandler(loginService, authenticator, mappings));

        ContextHandlerCollection collection = new ContextHandlerCollection();
        collection.setHandlers(new Handler[]{servletContextHandler,restContextHandler});
        
        server.setHandler(collection);
        
        // Start Server
        server.start();

        // Show server state
        if (LOG.isLoggable(Level.FINE))
        {
            LOG.fine(server.dump());
        }
    }
    
    static SecurityHandler createSecurityHandler(LoginService loginService, Authenticator authenticator, ConstraintMapping[] constraints) {
        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.setLoginService(loginService);
        securityHandler.setAuthenticator(authenticator);
        securityHandler.setConstraintMappings(constraints);
        return securityHandler;
    }
    
    /**
     * Setup JSP Support for ServletContextHandlers.
     * <p>
     *   NOTE: This is not required or appropriate if using a WebAppContext.
     * </p>
     *
     * @param servletContextHandler the ServletContextHandler to configure
     * @throws IOException if unable to configure
     */
    private void enableEmbeddedJspSupport(ServletContextHandler servletContextHandler) throws IOException
    {
        // Establish Scratch directory for the servlet context (used by JSP compilation)
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");
    
        if (!scratchDir.exists())
        {
            if (!scratchDir.mkdirs())
            {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        servletContextHandler.setAttribute("javax.servlet.context.tempdir", scratchDir);
    
        // Set Classloader of Context to be sane (needed for JSTL)
        // JSP requires a non-System classloader, this simply wraps the
        // embedded System classloader in a way that makes it suitable
        // for JSP to use
        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
        servletContextHandler.setClassLoader(jspClassLoader);
        
        // Manually call JettyJasperInitializer on context startup
        servletContextHandler.addBean(new JspStarter(servletContextHandler));
        
        // Create / Register JSP Servlet (must be named "jsp" per spec)
        ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
        holderJsp.setInitOrder(0);
        holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
        holderJsp.setInitParameter("fork", "false");
        holderJsp.setInitParameter("xpoweredBy", "false");
        holderJsp.setInitParameter("compilerTargetVM", "1.8");
        holderJsp.setInitParameter("compilerSourceVM", "1.8");
        holderJsp.setInitParameter("keepgenerated", "true");
        servletContextHandler.addServlet(holderJsp, "*.jsp");
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
            response.getWriter().print("<html><body>Anmeldung nicht erfolgreich<br><a href='/login'>Anmelden</a></body></html>");
        }
    }
    
    private URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException
    {
        URL indexUri = this.getClass().getResource(WEBROOT_INDEX);
        if (indexUri == null)
        {
            throw new FileNotFoundException("Unable to find resource " + WEBROOT_INDEX);
        }
        // Points to wherever /webroot/ (the resource) is
        return indexUri.toURI();
    }

    public void stop() throws Exception
    {
        server.stop();
    }

    /**
     * Cause server to keep running until it receives a Interrupt.
     * <p>
     * Interrupt Signal, or SIGINT (Unix Signal), is typically seen as a result of a kill -TERM {pid} or Ctrl+C
     * @throws InterruptedException if interrupted
     */
    public void waitForInterrupt() throws InterruptedException
    {
        server.join();
    }
}

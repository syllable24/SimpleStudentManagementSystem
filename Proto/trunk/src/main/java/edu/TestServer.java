package edu;

import java.io.File;
import java.lang.management.ManagementFactory;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author newe
 */
public class TestServer {

    public static void main(String[] args) throws Exception {
    //+++((StdErrLog) Log.getLog()).setSource(false);
    // Setup Threadpool
    QueuedThreadPool threadPool = new QueuedThreadPool();
    threadPool.setMaxThreads(100);
    // Setup server
    Server server = new Server(threadPool);
    server.manage(threadPool);
    // Setup JMX
    MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
    server.addBean(mbContainer);
    server.addBean(Log.getLog());
    // Common HTTP configuration
    HttpConfiguration config = new HttpConfiguration();
    config.setSecurePort(8443);
    config.addCustomizer(new ForwardedRequestCustomizer());
    config.addCustomizer(new SecureRequestCustomizer());
    config.setSendDateHeader(true);
    config.setSendServerVersion(true);
    // Http Connector
    HttpConnectionFactory http = new HttpConnectionFactory(config);
    ServerConnector httpConnector = new ServerConnector(server, http);
    httpConnector.setPort(8080);
    httpConnector.setIdleTimeout(30000);
    server.addConnector(httpConnector);
    // Handlers
    HandlerCollection handlers = new HandlerCollection();
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    RequestLogHandler requestLogHandler = new RequestLogHandler();
    handlers.setHandlers(new Handler[] { contexts, new DefaultHandler(), requestLogHandler });
    // Add restart handler to test the ability to save sessions and restart
    //+++RestartHandler restart = new RestartHandler();
    //+++restart.setHandler(handlers);
    //+++server.setHandler(restart);
    // Setup context
    HashLoginService login = new HashLoginService();
    login.setName("Test Realm");
    login.setConfig("realm.properties");
    server.addBean(login);
    File log = File.createTempFile("jetty-yyyy_mm_dd", "log");
    NCSARequestLog requestLog = new NCSARequestLog(log.toString());
    requestLog.setExtended(false);
    requestLogHandler.setRequestLog(requestLog);
    server.setStopAtShutdown(true);
    WebAppContext webapp = new WebAppContext();
    webapp.setContextPath("/test");
    webapp.setParentLoaderPriority(true);
    webapp.setResourceBase("./src/main/webapp");
    webapp.setAttribute("testAttribute", "testValue");
    File sessiondir = File.createTempFile("sessions", null);
    if (sessiondir.exists())
        sessiondir.delete();
    sessiondir.mkdir();
    sessiondir.deleteOnExit();
    DefaultSessionCache ss = new DefaultSessionCache(webapp.getSessionHandler());
    FileSessionDataStore sds = new FileSessionDataStore();
    ss.setSessionDataStore(sds);
    sds.setStoreDir(sessiondir);
    webapp.getSessionHandler().setSessionCache(ss);
    contexts.addHandler(webapp);
    ContextHandler srcroot = new ContextHandler();
    srcroot.setResourceBase(".");
    srcroot.setHandler(new ResourceHandler());
    srcroot.setContextPath("/src");
    contexts.addHandler(srcroot);
    server.start();
    server.join();    

    }
}
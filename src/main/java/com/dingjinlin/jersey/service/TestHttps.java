package com.dingjinlin.jersey.service;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by DingJinlin on 2016/11/25 22:46.
 * Study
 */
public class TestHttps {
    private static int PORT = 8089;
    private static int IDLE_THREAD_NUM = 30000;

    public static void main(String[] args) {
        startJettyByHttps();
    }

    public static void startJettyByHttps() {
        Server server = new Server();

        HttpConfiguration https_config = new HttpConfiguration();
        https_config.setSecureScheme("https");

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("E:\\Workspace\\Study\\REST\\StudyJersey\\src\\main\\resources\\KeyStore\\jettyServerKS");
        // 私钥
        sslContextFactory.setKeyStorePassword("12345678");
        // 公钥
        sslContextFactory.setKeyManagerPassword("12345678");

        ServerConnector httpsConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https_config));
        // 设置访问端口
        httpsConnector.setPort(PORT);
        httpsConnector.setIdleTimeout(IDLE_THREAD_NUM);
        server.addConnector(httpsConnector);

//        WebAppContext webApp = new WebAppContext();
//        webApp.setContextPath("/");
//        webApp.setResourceBase("./webapp");
//        server.setHandler(webApp);

        ServletHolder jerseyServlet = new ServletHolder(ServletContainer.class);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", "com.dingjinlin.jersey.service.BulkLoadAPI");
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(jerseyServlet, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpsConnector.close();
        }
    }
}
